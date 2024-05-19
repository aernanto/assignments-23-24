package assignments.assignment4.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import assignments.assignment3.DepeFood;
import assignments.assignment3.Menu;
import assignments.assignment3.Order;

public class BillPrinter {
    public BillPrinter(Stage stage) {
    }

    private Scene createBillPrinterForm() {
        VBox layout = new VBox(10);

        Label orderIdLabel = new Label("Order ID:");
        TextField orderIdInput = new TextField();

        TextArea billOutput = new TextArea();
        billOutput.setEditable(false);

        Button printButton = new Button("Print Bill");
        printButton.setOnAction(e -> {
            String orderId = orderIdInput.getText().trim();
            printBill(orderId, billOutput);
        });

        layout.getChildren().addAll(orderIdLabel, orderIdInput, printButton, billOutput);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        return new Scene(layout, 400, 600);
    }

    private void printBill(String orderId, TextArea billOutput) {
        Order order = DepeFood.getOrderOrNull(orderId);
        if (order == null) {
            billOutput.setText("Order ID tidak dapat ditemukan.");
            return;
        }
        billOutput.setText(outputBillPesanan(order));
    }

    private String outputBillPesanan(Order order) {
        return String.format(
                "Bill:%nOrder ID: %s%nTanggal Pemesanan: %s%nLokasi Pengiriman: %s%nStatus Pengiriman: %s%nPesanan:%n%s%nBiaya Ongkos Kirim: Rp %s%nTotal Biaya: Rp %s%n",
                order.getOrderId(), order.getTanggal(), order.getRestaurant().getNama(), 
                order.getOrderFinished() ? "Finished" : "Not Finished", 
                getMenuPesananOutput(order), 
                formatCurrency(order.getOngkir()), 
                formatCurrency(order.getTotalHarga()));
    }

    private String getMenuPesananOutput(Order order) {
        StringBuilder pesananBuilder = new StringBuilder();
        for (Menu menu : order.getSortedMenu()) {
            pesananBuilder.append("- ").append(menu.getNamaMakanan()).append(" ").append(formatCurrency(menu.getHarga())).append("\n");
        }
        if (pesananBuilder.length() > 0) {
            pesananBuilder.deleteCharAt(pesananBuilder.length() - 1);
        }
        return pesananBuilder.toString();
    }

    private String formatCurrency(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);
        return decimalFormat.format(amount);
    }

    public Scene getScene() {
        return this.createBillPrinterForm();
    }

    public class MenuItem {
        private final StringProperty itemName;
        private final StringProperty price;

        public MenuItem(String itemName, String price) {
            this.itemName = new SimpleStringProperty(itemName);
            this.price = new SimpleStringProperty(price);
        }

        public StringProperty itemNameProperty() {
            return itemName;
        }

        public StringProperty priceProperty() {
            return price;
        }

        public String getItemName() {
            return itemName.get();
        }

        public String getPrice() {
            return price.get();
        }
    }
}