package assignments.assignment2;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<User> userList = new ArrayList<>();
    private static ArrayList<Restaurant> restaurantList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
        int choice = input.nextInt();
        input.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                handleLogin();
                break;
            case 2:
                System.out.println("Terima kasih telah menggunakan DepeFood!");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }

    public static void handleLogin() {
        System.out.println("Silahkan Login:");
        System.out.print("Nama: ");
        String name = input.nextLine();
        System.out.print("Nomor Telepon: ");
        String phoneNumber = input.nextLine();

        System.out.println("Input Name: " + name);
        System.out.println("Input Phone Number: " + phoneNumber);

        User user = getUser(name, phoneNumber);
        if (user != null) {
            System.out.println("Selamat Datang " + user.getNama() + "!");
            if (user.getRole().equals("Admin")) {
                handleAdminMenu();
            } else {
                handleCustomerMenu(user);
            }
        } else {
            System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
        }
    }

    public static User getUser(String name, String phoneNumber) {
        for (User user : userList) {
            if (user.getNama().equals(name) && user.getNomorTelepon().equals(phoneNumber)) {
                System.out.println("Selamat Datang " + user.getNama() + "!");
                if (user.getRole().equals("Admin")) {
                    handleAdminMenu();
                } else {
                    handleCustomerMenu(user);
                }
                return User;
            }
        }

        // If no matching user is found
        System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
    }

    public static void handleAdminMenu() {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("Pilih menu:");
            System.out.println("1. Tambah Restoran");
            System.out.println("2. Hapus Restoran");
            System.out.println("3. Keluar");
            System.out.println("--------------------------------------------");
            System.out.print("Pilihan menu: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    handleTambahRestoran();
                    break;
                case 2:
                    handleHapusRestoran();
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan DepeFood!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    public static void handleTambahRestoran() {
        System.out.println("--------------Tambah Restoran----------------");
        System.out.print("Nama: ");
        String name = input.nextLine();
        if (isRestoranExist(name)) {
            System.out.println("Restoran dengan nama " + name + " sudah pernah terdaftar. Mohon masukkan nama yang berbeda!");
            return;
        }
        if (name.length() < 4) {
            System.out.println("Nama Restoran tidak valid!");
            return;
        }
        ArrayList<Menu> menuList = new ArrayList<>();
        while (true) {
            System.out.print("Jumlah Makanan: ");
            int count = input.nextInt();
            input.nextLine(); // Consume newline
            if (count <= 0) {
                System.out.println("Jumlah makanan harus lebih dari 0!");
                continue;
            }
            for (int i = 0; i < count; i++) {
                System.out.print("Nama Makanan: ");
                String foodName = input.nextLine();
                System.out.print("Harga Makanan: ");
                double price = input.nextDouble();
                input.nextLine(); // Consume newline
                if (price <= 0) {
                    System.out.println("Harga menu harus lebih dari 0!");
                    return;
                }
                menuList.add(new Menu(foodName, price));
            }
            break;
        }
        Restaurant restaurant = new Restaurant(name, menuList);
        restaurantList.add(restaurant);
        System.out.println("Restaurant " + name + " Berhasil terdaftar.");
    }

    public static boolean isRestoranExist(String name) {
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getNama().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void handleHapusRestoran() {
        System.out.println("--------------Hapus Restoran----------------");
        System.out.print("Nama Restoran: ");
        String name = input.nextLine();
        if (!isRestoranExist(name)) {
            System.out.println("Restoran tidak terdaftar pada sistem.");
            return;
        }
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getNama().equals(name)) {
                restaurantList.remove(i);
                System.out.println("Restoran berhasil dihapus.");
                return;
            }
        }
    }

    public static void handleCustomerMenu(User user) {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("Pilih menu:");
            System.out.println("1. Buat Pesanan");
            System.out.println("2. Cetak Bill");
            System.out.println("3. Lihat Menu");
            System.out.println("4. Update Status Pesanan");
            System.out.println("5. Keluar");
            System.out.println("--------------------------------------------");
            System.out.print("Pilihan menu: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    handleBuatPesanan(user);
                    break;
                case 2:
                    handleCetakBill(user);
                    break;
                case 3:
                    handleLihatMenu();
                    break;
                case 4:
                    handleUpdateStatusPesanan();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan DepeFood!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    public static void handleBuatPesanan(User user) {
        System.out.println("--------------Buat Pesanan----------------");
        System.out.print("Nama Restoran: ");
        String restaurantName = input.nextLine();
        Restaurant restaurant = getRestaurant(restaurantName);
        if (restaurant == null) {
            System.out.println("Restoran tidak terdaftar pada sistem.");
            return;
        }
        System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
        String date = input.nextLine();
        if (!isValidDate(date)) {
            System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)!");
            return;
        }
        System.out.print("Jumlah Pesanan: ");
        int itemCount = input.nextInt();
        input.nextLine(); // Consume newline

        ArrayList<Menu> orderItems = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            System.out.print("Order " + (i + 1) + ": ");
            String itemName = input.nextLine();
            Menu menu = getMenuFromRestaurant(restaurant, itemName);
            if (menu == null) {
                System.out.println("Mohon memesan menu yang tersedia di Restoran!");
                return;
            }
            orderItems.add(menu);
        }
        String orderID = generateOrderID(restaurantName, date);
        Order order = new Order(orderID, date, restaurant, orderItems, false, 0); // Initialize biayaOngkosKirim as 0
        user.getOrderHistory().add(order);
        System.out.println("Pesanan dengan ID " + orderID + " diterima!");
    }

    public static boolean isValidDate(String date) {
        return date.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    public static String generateOrderID(String restaurantName, String date) {
        int randomNum = (int) (Math.random() * 1000);
        return restaurantName.substring(0, 4).toUpperCase() + date.replaceAll("/", "") + randomNum + "C" + ((int) (Math.random() * 10));
    }

    public static Restaurant getRestaurant(String name) {
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getNama().equalsIgnoreCase(name)) {
                return restaurant;
            }
        }
        return null;
    }

    public static Menu getMenuFromRestaurant(Restaurant restaurant, String itemName) {
        for (Menu menu : restaurant.getMenu()) {
            if (menu.getNamaMakanan().equalsIgnoreCase(itemName)) {
                return menu;
            }
        }
        return null;
    }

    public static void handleCetakBill(User user) {
        System.out.println("--------------Cetak Bill----------------");
        System.out.print("Masukkan Order ID: ");
        String orderID = input.nextLine();
        Order order = getOrder(orderID);
        if (order == null) {
            System.out.println("Order ID tidak dapat ditemukan.");
            return;
        }
        System.out.println("Bill:");
        System.out.println("Order ID: " + order.getOrderID());
        System.out.println("Tanggal Pemesanan: " + order.getTanggalPemesanan());
        System.out.println("Restaurant: " + order.getRestaurant().getNama());
        System.out.println("Lokasi Pengiriman: " + user.getLokasi());
        System.out.println("Status Pengiriman: " + (order.isOrderFinished() ? "Selesai" : "Belum Selesai"));
        System.out.println("Pesanan:");
        double total = 0;
        for (Menu item : order.getItems()) {
            System.out.println("- " + item.getNamaMakanan() + " " + item.getHarga());
            total += item.getHarga();
        }
        System.out.println("Total Biaya: Rp " + (total + order.getBiayaOngkosKirim()));
    }    

    public static void handleLihatMenu() {
        System.out.println("--------------Lihat Menu----------------");
        System.out.print("Nama Restoran: ");
        String name = input.nextLine();
        Restaurant restaurant = getRestaurant(name);
        if (restaurant == null) {
            System.out.println("Restoran tidak terdaftar pada sistem.");
            return;
        }
        System.out.println("Menu:");
        for (Menu menu : restaurant.getMenu()) {
            System.out.println(menu.getNamaMakanan() + " " + menu.getHarga());
        }
    }

    public static void handleUpdateStatusPesanan() {
        System.out.println("--------------Update Status Pesanan----------------");
        System.out.print("Order ID: ");
        String orderID = input.nextLine();
        Order order = getOrder(orderID);
        if (order == null) {
            System.out.println("Order ID tidak dapat ditemukan.");
            return;
        }
        System.out.print("Status: ");
        String status = input.nextLine();
        if (status.equalsIgnoreCase("Selesai")) {
            if (!order.isOrderFinished()) {
                order.setOrderFinished(true);
                System.out.println("Status pesanan dengan ID " + orderID + " berhasil diupdate!");
            } else {
                System.out.println("Status pesanan dengan ID " + orderID + " tidak berhasil diupdate!");
            }
        } else {
            System.out.println("Status pesanan tidak valid!");
        }
    }

    public static Order getOrder(String orderID) {
        for (User user : userList) {
            for (Order order : user.getOrderHistory()) {
                if (order.getOrderID().equals(orderID)) {
                    return order;
                }
            }
        }
        return null;
    }    
}