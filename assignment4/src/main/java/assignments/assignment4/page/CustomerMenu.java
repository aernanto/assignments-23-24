package assignments.assignment4.page;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import assignments.assignment3.DepeFood;
import assignments.assignment3.Order;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;
import assignments.assignment4.MainApp;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class CustomerMenu extends MemberMenu {
    private Stage stage;
    private Scene addOrderScene;
    private Scene printBillScene;
    private Scene payBillScene;
    private Scene cekSaldoScene;
    private MainApp mainApp;
    private User user;

    public CustomerMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Nyimpen user
        createBaseMenu();
        this.addOrderScene = createTambahPesananForm();
        this.printBillScene = createBillPrinterForm();
        this.payBillScene = createBayarBillForm();
        this.cekSaldoScene = createCekSaldoScene();
    }

    @Override
    public Scene createBaseMenu() {
        VBox menuLayout = new VBox(10);

        Button addOrderButton = new Button("Buat Pesanan");
        addOrderButton.setOnAction(e -> stage.setScene(addOrderScene));

        Button printBillButton = new Button("Cetak Bill");
        printBillButton.setOnAction(e -> stage.setScene(printBillScene));

        Button payBillButton = new Button("Bayar Bill");
        payBillButton.setOnAction(e -> stage.setScene(payBillScene));

        Button checkBalanceButton = new Button("Cek Saldo");
        checkBalanceButton.setOnAction(e -> stage.setScene(cekSaldoScene));

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> mainApp.logout());

        menuLayout.getChildren().addAll(addOrderButton, printBillButton, payBillButton, checkBalanceButton, logoutButton);
        menuLayout.setAlignment(Pos.CENTER);

        return new Scene(menuLayout, 400, 600);
    }

    private Scene createTambahPesananForm() {
        VBox menuLayout = new VBox(10);

        Label restoLabel = new Label("Nama Restoran:");
        TextField restoInput = new TextField();

        Label dateLabel = new Label("Tanggal Pemesanan (DD/MM/YYYY):");
        TextField dateInput = new TextField();

        Label orderLabel = new Label("Jumlah Pesanan:");
        TextField orderInput = new TextField();

        Label itemsLabel = new Label("Pesanan:");
        TextArea itemsInput = new TextArea();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String restoName = restoInput.getText().trim();
            String date = dateInput.getText().trim();
            String[] items = itemsInput.getText().trim().split("\n");

            handleBuatPesanan(restoName, date, List.of(items));
        });

        menuLayout.getChildren().addAll(restoLabel, restoInput, dateLabel, dateInput, orderLabel, orderInput, itemsLabel, itemsInput, submitButton);
        menuLayout.setAlignment(Pos.CENTER);

        return new Scene(menuLayout, 400, 600);
    }

    private Scene createBillPrinterForm() {
        VBox menuLayout = new VBox(10);

        Label orderIdLabel = new Label("Order ID:");
        TextField orderIdInput = new TextField();

        Button printButton = new Button("Print Bill");
        printButton.setOnAction(e -> {
            String orderId = orderIdInput.getText().trim();
            handleCetakBill(orderId);
        });

        menuLayout.getChildren().addAll(orderIdLabel, orderIdInput, printButton);
        menuLayout.setAlignment(Pos.CENTER);

        return new Scene(menuLayout, 400, 600);
    }

    private Scene createBayarBillForm() {
        VBox menuLayout = new VBox(10);

        Label orderIdLabel = new Label("Order ID:");
        TextField orderIdInput = new TextField();

        Label paymentMethodLabel = new Label("Metode Pembayaran:");
        ComboBox<String> paymentMethodInput = new ComboBox<>();
        paymentMethodInput.setItems(FXCollections.observableArrayList("Credit Card", "Debit"));

        Button payButton = new Button("Bayar");
        payButton.setOnAction(e -> {
            String orderId = orderIdInput.getText().trim();
            int paymentMethod = paymentMethodInput.getSelectionModel().getSelectedIndex() + 1;
            handleBayarBill(orderId, paymentMethod);
        });

        menuLayout.getChildren().addAll(orderIdLabel, orderIdInput, paymentMethodLabel, paymentMethodInput, payButton);
        menuLayout.setAlignment(Pos.CENTER);

        return new Scene(menuLayout, 400, 600);
    }

    private Scene createCekSaldoScene() {
        VBox menuLayout = new VBox(10);

        Button checkBalanceButton = new Button("Cek Saldo");
        checkBalanceButton.setOnAction(e -> handleCekSaldo());

        menuLayout.getChildren().addAll(checkBalanceButton);
        menuLayout.setAlignment(Pos.CENTER);

        return new Scene(menuLayout, 400, 600);
    }

    private void handleBuatPesanan(String namaRestoran, String tanggalPemesanan, List<String> menuItems) {
        Restaurant restaurant = DepeFood.getRestaurantByName(namaRestoran);
        if (restaurant == null) {
            showAlert("Error", "Restoran tidak terdaftar pada sistem.", Alert.AlertType.ERROR);
            return;
        }

        if (!OrderGenerator.validateDate(tanggalPemesanan)) {
            showAlert("Error", "Masukkan tanggal sesuai format (DD/MM/YYYY)", Alert.AlertType.ERROR);
            return;
        }

        if (!DepeFood.validateRequestPesanan(restaurant, menuItems)) {
            showAlert("Error", "Mohon memesan menu yang tersedia di Restoran!", Alert.AlertType.ERROR);
            return;
        }

        Order order = new Order(
                OrderGenerator.generateOrderID(namaRestoran, tanggalPemesanan, user.getNomorTelepon()),
                tanggalPemesanan,
                OrderGenerator.calculateDeliveryCost(user.getLokasi()),
                restaurant,
                DepeFood.getMenuRequest(restaurant, menuItems));

        user.addOrderHistory(order);
        showAlert("Success", String.format("Pesanan dengan ID %s diterima!", order.getOrderId()), Alert.AlertType.INFORMATION);
    }

    private void handleCetakBill(String orderId) {
        Order order = DepeFood.getOrderOrNull(orderId);
        if (order == null) {
            showAlert("Error", "Order ID tidak dapat ditemukan.", Alert.AlertType.ERROR);
            return;
        }
        showAlert("Bill", outputBillPesanan(order), Alert.AlertType.INFORMATION);
    }

    private void handleBayarBill(String orderId, int paymentOption) {
        Order order = DepeFood.getOrderOrNull(orderId);

        if (order == null) {
            showAlert("Error", "Order ID tidak dapat ditemukan.", Alert.AlertType.ERROR);
            return;
        }

        if (order.getOrderFinished()) {
            showAlert("Error", "Pesanan dengan ID ini sudah lunas!", Alert.AlertType.ERROR);
            return;
        }

        if (paymentOption != 1 && paymentOption != 2) {
            showAlert("Error", "Pilihan tidak valid, silakan coba kembali", Alert.AlertType.ERROR);
            return;
        }

        DepeFoodPaymentSystem paymentSystem = user.getPaymentSystem();

        boolean isCreditCard = paymentSystem instanceof CreditCardPayment;

        if ((isCreditCard && paymentOption == 2) || (!isCreditCard && paymentOption == 1)) {
            showAlert("Error", "User belum memiliki metode pembayaran ini!", Alert.AlertType.ERROR);
            return;
        }

        long amountToPay;
        try {
            amountToPay = paymentSystem.processPayment(user.getSaldo(), (long) order.getTotalHarga());
        } catch (Exception e) {
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
            return;
        }

        long saldoLeft = user.getSaldo() - amountToPay;
        user.setSaldo(saldoLeft);
        DepeFood.handleUpdateStatusPesanan(order);

        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);

        showAlert("Success", String.format("Berhasil Membayar Bill sebesar Rp %s", decimalFormat.format(amountToPay)), Alert.AlertType.INFORMATION);
    }

    private void handleCekSaldo() {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);

        showAlert("Saldo", String.format("Sisa saldo sebesar Rp %s", decimalFormat.format(user.getSaldo())), Alert.AlertType.INFORMATION);
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private String outputBillPesanan(Order order) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');

        decimalFormat.setDecimalFormatSymbols(symbols);

        StringBuilder bill = new StringBuilder();
        bill.append("Bill:\n")
            .append("Order ID: ").append(order.getOrderId()).append("\n")
            .append("Tanggal Pemesanan: ").append(order.getTanggal()).append("\n")
            .append("Lokasi Pengiriman: ").append(user.getLokasi()).append("\n")
            .append("Pesanan:\n");

        for (assignments.assignment3.Menu menu : order.getSortedMenu()) {
            bill.append("- ").append(menu.getNamaMakanan()).append(" ")
                .append(decimalFormat.format(menu.getHarga())).append("\n");
        }

        bill.append("Biaya Ongkos Kirim: Rp ").append(decimalFormat.format(order.getOngkir())).append("\n")
            .append("Total Biaya: Rp ").append(decimalFormat.format(order.getTotalHarga()));

        return bill.toString();
    }
}