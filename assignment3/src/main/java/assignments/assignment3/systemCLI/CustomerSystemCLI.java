/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3.systemCLI;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Scanner;

import assignments.assignment2.MainMenu;
import assignments.assignment2.Menu;
import assignments.assignment2.Order;
import assignments.assignment2.Restaurant;
import assignments.assignment2.User;

// Customer System CLI
public class CustomerSystemCLI implements UserSystemCLI {
    private Scanner input;
    private ArrayList<Restaurant> restoList;
    private ArrayList<User> userList;

    // Constructor Customer System CLI
    public CustomerSystemCLI(Scanner input, ArrayList<Restaurant> restoList, ArrayList<User> userList) {
        this.input = input;
        this.restoList = restoList;
        this.userList = userList;
    }

    // Display menu
    @Override
    public void displayMenu() {
=======
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import assignments.assignment1.OrderGenerator;
import assignments.assignment3.Order;
import assignments.assignment3.Restaurant;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class CustomerSystemCLI extends UserSystemCLI {

    @Override
    boolean handleMenu(int choice) {
        switch (choice) {
            case 1 -> handleBuatPesanan();
            case 2 -> handleCetakBill();
            case 3 -> handleLihatMenu();
            case 4 -> handleBayarBill();
            case 5 -> handleCekSaldo();
            case 6 -> {
                return false;
            }
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    @Override
    void displayMenu() {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Bayar Bill");
        System.out.println("5. Cek Saldo");
        System.out.println("6. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

<<<<<<< HEAD
    // Handle menu
    @Override
    public boolean handleMenu(int command) {
        switch (command) {
            case 1:
                handleBuatPesanan();
                return true;
            case 2:
                handleCetakBill();
                return true;
            case 3:
                handleLihatMenu();
                return true;
            case 4:
                handleBayarBill();
                return true;
            case 5:
                handleCekSaldo();
                return true;
            case 6:
                return false;
            default:
                System.out.println("Perintah tidak diketahui, silakan coba kembali");
                return true;
        }
    }

    // handle Buat Pesanan
    private void handleBuatPesanan() {
        System.out.println("\n--------------Buat Pesanan----------------");
        System.out.print("Nama Restoran: ");
        String restaurantName = input.nextLine();
        Restaurant restaurant = getRestaurant(restaurantName);
        if (restaurant == null) {
            System.out.println("Restoran tidak terdaftar pada sistem.");
            return;
        }

        System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
        String tanggalOrder = input.nextLine();
        if (!isValidTanggal(tanggalOrder)) {
            System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)!");
            return;
        }

        System.out.print("Jumlah Pesanan: ");
        int itemCount = input.nextInt();
        input.nextLine();
        ArrayList<Menu> orderItems = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            System.out.print("Nama Menu: ");
            String itemName = input.nextLine();
            Menu menu = getMenuFromRestaurant(restaurant, itemName);
            if (menu == null) {
                System.out.println("Menu tidak tersedia di restoran.");
                return;
            }
            orderItems.add(menu);
        }

        System.out.print("Nama Pengguna: ");
        String namaPengguna = input.nextLine();
        System.out.print("Nomor Telepon: ");
        String nomorTeleponPengguna = input.nextLine();
        User user = getUser(namaPengguna, nomorTeleponPengguna);
        if (user == null) {
            System.out.println("Pengguna tidak ditemukan.");
            return;
        }

        String orderID = generateOrderID(restaurantName, tanggalOrder, nomorTeleponPengguna);
        if (orderID.isEmpty()) {
            return;
        }
        System.out.println("Pesanan dengan ID " + orderID + " berhasil diterima.");
        Order order = new Order(orderID, tanggalOrder, restaurant, orderItems, false, calculateDeliveryCharge(user.getLokasi()));
        user.getOrderHistory().add(order);
    }

    // Get menu from restaurant
    private Menu getMenuFromRestaurant(Restaurant restaurant, String itemName) {
        for (Menu menu : restaurant.getMenu()) {
            if (menu.getNamaMakanan().equalsIgnoreCase(itemName)) {
                return menu;
            }
        }
        return null; 
    }    

    // Handle cetak bill
    private void handleCetakBill() {
        System.out.println("\n--------------Cetak Bill----------------");
        System.out.print("Masukkan Order ID: ");
        String orderID = input.nextLine();
        Order order = getOrder(orderID);
        if (order == null) {
            System.out.println("Order ID tidak dapat ditemukan.");
            return;
        }
        System.out.println(generateBill(order));
    }

    // Handle lihat menu
    private void handleLihatMenu() {
        System.out.println("\n--------------Lihat Menu----------------");
        System.out.print("Nama Restoran: ");
        String name = input.nextLine();
        Restaurant restaurant = getRestaurant(name);
        if (restaurant == null) {
            System.out.println("Restoran tidak terdaftar pada sistem.");
            return;
        }
        System.out.println("Menu:");
        restaurant.getMenu().forEach(menu -> {
            System.out.println(menu.getNamaMakanan() + " - Rp " + String.format("%.2f", menu.getHarga()));
        });
    }

    // Handle bayar bill
    private void handleBayarBill() {
        System.out.println("\n--------------Bayar Bill----------------");
        System.out.print("Masukkan Order ID: ");
        String orderID = input.nextLine();
        Order order = getOrder(orderID);
        if (order == null) {
            System.out.println("Order ID tidak dapat ditemukan.");
            return;
        }
        User user = getUserByOrderID(orderID);
        if (user == null) {
            System.out.println("Pengguna tidak ditemukan.");
            return;
        }
        long amountToPay = calculateTotalBill(order);
        long result = user.getPayment().processPayment(amountToPay);
        if (result == 0) {
            System.out.println("Pembayaran berhasil.");
            order.setOrderFinished(true);
        } else {
            System.out.println("Pembayaran gagal. Error code: " + result);
        }
    }

    // Handle cek saldo
    private void handleCekSaldo() {
        System.out.println("\n--------------Cek Saldo----------------");
        System.out.print("Nama Pengguna: ");
        String namaPengguna = input.nextLine();
        User user = getUserByName(namaPengguna);
        if (user == null) {
            System.out.println("Pengguna tidak ditemukan.");
            return;
        }
        System.out.println("Saldo saat ini: Rp " + user.getSaldo());
    }

    // Method helper
    private Restaurant getRestaurant(String name) {
        String nameLowerCase = name.toLowerCase(); 
        for (Restaurant restaurant : restoList) {
            if (restaurant.getNama().toLowerCase().equals(nameLowerCase)) { 
                return restaurant;
            }
        }
        return null;
    }    

    // Constructor getUser
    private User getUser(String name, String phone) {
        for (User user : userList) {
            if (user.getNama().equalsIgnoreCase(name) && user.getNomorTelepon().equals(phone)) {
                return user;
            }
        }
        return null;
    }    

    // Kalau tanggal valid
    private boolean isValidTanggal(String tanggal) {
        if (tanggal.length() != 10 || tanggal.charAt(2) != '/' || tanggal.charAt(5) != '/') {
            return false;
        }
        String[] parts = tanggal.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        if (month < 1 || month > 12 || day < 1) return false;
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 2 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {
            daysInMonth[1] = 29;
        }
        return day <= daysInMonth[month - 1];
    }
    
    // Kondisi tahun kabisat
    public static boolean isTahunKabisat(int tahun) {
        return (tahun % 400 == 0) || ((tahun % 4 == 0) && (tahun % 100 != 0));
    }

    private String generateOrderID(String namaRestoran, String tanggalOrder, String nomorTelepon) {
        if (namaRestoran.length() < 4 || !isValidTanggal(tanggalOrder) || nomorTelepon.length() < 4) {
            return "";
        }
        return namaRestoran.substring(0, 4).toUpperCase() +
               tanggalOrder.replaceAll("/", "") +
               nomorTelepon.substring(nomorTelepon.length() - 4);
    }

    // Biaya delivery
    private int calculateDeliveryCharge(String lokasi) {
        switch (lokasi.toUpperCase()) {
            case "P": return 10000;
            case "U": return 20000;
            case "T": return 30000;
            case "S": return 40000;
            case "B": return 50000;
            default: return 15000;
        }
    }    

    // Getter order
    private Order getOrder(String orderID) {
        for (User user : userList) {
            for (Order order : user.getOrderHistory()) {
                if (order.getOrderID().equals(orderID)) {
                    return order;
                }
            }
        }
        return null;
    }

    // generate bill
    private String generateBill(Order order) {
        StringBuilder bill = new StringBuilder("Bill:\nOrder ID: ").append(order.getOrderID()).append("\n");
        bill.append("Items:\n");
        double total = 0.0;
        for (Menu item : order.getItems()) {
            bill.append(item.getNamaMakanan()).append(" - Rp ").append(String.format("%.2f", item.getHarga())).append("\n");
            total += item.getHarga();
        }
        int deliveryCharge = calculateDeliveryCharge(order.getRestaurant().getLokasi());
        total += deliveryCharge;
        bill.append("Delivery Charge: Rp ").append(deliveryCharge).append("\nTotal: Rp ").append(String.format("%.2f", total));
        return bill.toString();
    }

    // Get user by order ID
    private User getUserByOrderID(String orderID) {
        for (User user : userList) {
            for (Order order : user.getOrderHistory()) {
                if (order.getOrderID().equals(orderID)) {
                    return user;
                }
            }
        }
        return null;
    }

    // Kalkulasi total bill
    private long calculateTotalBill(Order order) {
        long total = 0;
        for (Menu item : order.getItems()) {
            total += item.getHarga();
        }
        total += calculateDeliveryCharge(order.getRestaurant().getLokasi());
        return total;
    }

    // Get user by name
    private User getUserByName(String name) {
        for (User user : userList) {
            if (user.getNama().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }
=======
    private void handleBuatPesanan() {
        System.out.println("--------------Buat Pesanan----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if (restaurant == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }

            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalPemesanan = input.nextLine().trim();
            if (!OrderGenerator.validateDate(tanggalPemesanan)) {
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)");
                System.out.println();
                continue;
            }

            System.out.print("Jumlah Pesanan: ");
            int jumlahPesanan = Integer.parseInt(input.nextLine().trim());
            System.out.println("Order: ");

            List<String> listMenuPesananRequest = new ArrayList<>();

            for (int i = 0; i < jumlahPesanan; i++) {
                listMenuPesananRequest.add(input.nextLine().trim());
            }

            if (!validateRequestPesanan(restaurant, listMenuPesananRequest)) {
                System.out.println("Mohon memesan menu yang tersedia di Restoran!");
                continue;
            }

            Order order = new Order(
                    OrderGenerator.generateOrderID(restaurantName, tanggalPemesanan, userLoggedIn.getNomorTelepon()),
                    tanggalPemesanan,
                    OrderGenerator.calculateDeliveryCost(userLoggedIn.getLokasi()),
                    restaurant,
                    getMenuRequest(restaurant, listMenuPesananRequest));

            System.out.printf("Pesanan dengan ID %s diterima!", order.getOrderId());
            userLoggedIn.addOrderHistory(order);
            return;
        }
    }

    private void handleCetakBill() {
        System.out.println("--------------Cetak Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();
            Order order = getOrderOrNull(orderId);
            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            System.out.println("");
            System.out.print(outputBillPesanan(order));
            return;
        }

    }

    void handleLihatMenu() {
        System.out.println("--------------Lihat Menu----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if (restaurant == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print(restaurant.printMenu());
            return;
        }
    }

    void handleUpdateStatusPesanan(Order order) {
        order.setOrderFinished(true);
    }

    void handleBayarBill() {
        System.out.println("--------------Bayar Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();

            Order order = getOrderOrNull(orderId);

            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }

            if (order.getOrderFinished()) {
                System.out.println("Pesanan dengan ID ini sudah lunas!\n");
                return;
            }

            System.out.println(outputBillPesanan(order));

            System.out.println("Opsi Pembayaran:");
            System.out.println("1. Credit Card");
            System.out.println("2. Debit");

            System.out.print("Pilihan Metode Pembayaran: ");
            String paymentOption = input.nextLine().trim();

            if (!paymentOption.equals("1") && !paymentOption.equals("2")) {
                System.out.println("Pilihan tidak valid, silakan coba kembali\n");
                continue;
            }

            DepeFoodPaymentSystem paymentSystem = userLoggedIn.getPaymentSystem();

            boolean isCreditCard = paymentSystem instanceof CreditCardPayment;

            if ((isCreditCard && paymentOption.equals("2")) || (!isCreditCard && paymentOption.equals("1"))) {
                System.out.println("User belum memiliki metode pembayaran ini!\n");
                continue;
            }

            long amountToPay = 0;

            try {
                amountToPay = paymentSystem.processPayment(userLoggedIn.getSaldo(), (long) order.getTotalHarga());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println();
                continue;
            }

            long saldoLeft = userLoggedIn.getSaldo() - amountToPay;

            userLoggedIn.setSaldo(saldoLeft);
            handleUpdateStatusPesanan(order);

            DecimalFormat decimalFormat = new DecimalFormat();
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator('.');
            decimalFormat.setDecimalFormatSymbols(symbols);

            System.out.printf("Berhasil Membayar Bill sebesar Rp %s", decimalFormat.format(amountToPay));

            return;
        }
    }

    void handleCekSaldo() {
        System.out.println("--------------Cek Saldo----------------");

        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);

        System.out.printf("Sisa saldo sebesar Rp %s", decimalFormat.format(userLoggedIn.getSaldo()));
    }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
}