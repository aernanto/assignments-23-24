package assignments.assignment2;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<Restaurant> restoList;
    private static ArrayList<User> userList;
    private static final String kode39Characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        restoList = new ArrayList<>();
        userList = new ArrayList<>();
        initUser();
        boolean programRunning = true;
        while (programRunning) {
            printHeader();
            startMenu();
            int command = input.nextInt();
            input.nextLine(); 

            if (command == 1) {
                System.out.println("\nSilahkan Login:");
                System.out.print("Nama: ");
                String nama = input.nextLine();
                System.out.print("Nomor Telepon: ");
                String noTelp = input.nextLine();

                User userLoggedIn = getUser(nama, noTelp);
                if (userLoggedIn != null) {
                    if (userLoggedIn.getRole().equals("Customer")) {
                        boolean isLoggedIn = true;
                        while (isLoggedIn) {
                            menuCustomer();
                            int commandCust = input.nextInt();
                            input.nextLine(); // Consume newline

                            switch (commandCust) {
                                case 1:
                                    handleBuatPesanan();
                                    break;
                                case 2:
                                    handleCetakBill();
                                    break;
                                case 3:
                                    handleLihatMenu();
                                    break;
                                case 4:
                                    handleUpdateStatusPesanan();
                                    break;
                                case 5:
                                    isLoggedIn = false;
                                    break;
                                default:
                                    System.out.println("Perintah tidak diketahui, silakan coba kembali");
                                    break;
                            }
                        }
                    } else {
                        boolean isLoggedIn = true;
                        while (isLoggedIn) {
                            menuAdmin();
                            int commandAdmin = input.nextInt();
                            input.nextLine(); // Consume newline

                            switch (commandAdmin) {
                                case 1:
                                    handleTambahRestoran();
                                    break;
                                case 2:
                                    handleHapusRestoran();
                                    break;
                                case 3:
                                    isLoggedIn = false;
                                    break;
                                default:
                                    System.out.println("Perintah tidak diketahui, silakan coba kembali");
                                    break;
                            }
                        }
                    }
                } else {
                    System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                }
            } else if (command == 2) {
                programRunning = false;
            } else {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood ^___^");
    }

    public static boolean isValidDate(String tanggal) {
        return OrderGenerator.isValidFormatTanggal(tanggal);
    }

    public static User getUser(String nama, String nomorTelepon) {
        for (User user : userList) {
            if (user.getNama().equals(nama) && user.getNomorTelepon().equals(nomorTelepon)) {
                return user;
            }
        }
        return null;
    }

    public static void handleBuatPesanan() {
        System.out.println("\n--------------Buat Pesanan----------------");
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
        String orderID = generateOrderID(restaurantName, date, ""); 
        
        System.out.print("Nama Pengguna: ");
        String namaPengguna = input.nextLine();
        System.out.print("Nomor Telepon Pengguna: ");
        String nomorTeleponPengguna = input.nextLine();
        
        Order order = new Order(orderID, date, restaurant, orderItems, false, 0);        

        for (User user : userList) {
            if (user.getNama().equals(namaPengguna) && user.getNomorTelepon().equals(nomorTeleponPengguna)) {
                user.getOrderHistory().add(order);
                System.out.println("Pesanan dengan ID " + orderID + " diterima!");
                return;
            }
        }
        System.out.println("Pemesan tidak ditemukan.");
    }
    
    public static void handleCetakBill() {
        System.out.println("\n--------------Cetak Bill----------------");
        System.out.print("Masukkan Order ID: ");
        String orderID = input.nextLine();
        Order order = getOrder(orderID);
        if (order == null) {
            System.out.println("Order ID tidak dapat ditemukan.");
            return;
        }
        String namaPengguna = null;
        String nomorTeleponPengguna = null;
        for (User user : userList) {
            for (Order userOrder : user.getOrderHistory()) {
                if (userOrder.getOrderID().equals(orderID)) {
                    namaPengguna = user.getNama();
                    nomorTeleponPengguna = user.getNomorTelepon();
                    break;
                }
            }
            if (namaPengguna != null && nomorTeleponPengguna != null) {
                break;
            }
        }
        if (namaPengguna == null || nomorTeleponPengguna == null) {
            System.out.println("Pengguna tidak ditemukan.");
            return;
        }
        System.out.println(generateBill(orderID, nomorTeleponPengguna));
    }    

    public static void handleLihatMenu() {
        System.out.println("\n--------------Lihat Menu----------------");
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
        System.out.println("\n--------------Update Status Pesanan----------------");
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

    public static void handleTambahRestoran() {
        System.out.println("--------------Tambah Restoran----------------");
        String name;
        while (true) {
            System.out.print("Nama: ");
            name = input.nextLine();
            if (isRestoranExist(name)) {
                System.out.println("Restoran dengan nama " + name + " sudah pernah terdaftar. Mohon masukkan nama yang berbeda!");
                continue;
            }
            if (name.length() < 4) {
                System.out.println("Nama Restoran tidak valid!");
                continue;
            }
            break;
        }

        ArrayList<Menu> menuList = new ArrayList<>();
        int count;
        while (true) {
            try {
                System.out.print("Jumlah Makanan: ");
                count = Integer.parseInt(input.nextLine());
                if (count <= 0) {
                    System.out.println("Jumlah makanan harus lebih dari 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Jumlah makanan harus berupa angka!");
            }
        }

        for (int i = 0; i < count; i++) {
            String foodName;
            double price;

            System.out.println("Nama Makanan " + (i + 1) + ":");
            while (true) {
                System.out.print("Nama Makanan: ");
                foodName = input.nextLine();
                if (foodName.isEmpty()) {
                    System.out.println("Nama makanan tidak boleh kosong!");
                    continue;
                }
                break;
            }

            while (true) {
                try {
                    System.out.print("Harga Makanan: ");
                    price = Double.parseDouble(input.nextLine());
                    if (price <= 0) {
                        System.out.println("Harga menu harus lebih dari 0!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Harga harus berupa angka!");
                }
            }
            menuList.add(new Menu(foodName, price));
        }

        Restaurant restaurant = new Restaurant(name, menuList);
        restoList.add(restaurant);
        System.out.println("Restaurant " + name + " Berhasil terdaftar.");
    }

    public static void handleHapusRestoran() {
        System.out.println("\n--------------Hapus Restoran----------------");

        System.out.print("Nama Restoran: ");
        String name = input.nextLine().toLowerCase(); // Convert input to lowercase
        boolean found = false;
        for (int i = 0; i < restoList.size(); i++) {
            if (restoList.get(i).getNama().toLowerCase().equals(name)) { // Compare in lowercase
                restoList.remove(i);
                System.out.println("Restoran berhasil dihapus.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Restoran tidak terdaftar pada sistem.");
        }
    }

    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {
        int sumNoTelp = getSumDariNoTelp(noTelepon);
        String formattedDate = formatTanggal(tanggalOrder);
        String orderID = "";
        if (isValidFormatTanggal(tanggalOrder)) {
            orderID = namaRestoran.substring(0, 4).toUpperCase() +
                    formattedDate.replace("/", "") +
                    sumNoTelp +
                    "C" +
                    hitungChecksum(namaRestoran + formattedDate + sumNoTelp, true);
        } else {
            System.out.println("Format tanggal tidak valid.");
        }
        return orderID;
    }

    public static int getSumDariNoTelp(String noTelp) {
        int sum = 0;
        for (char c : noTelp.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        return sum;
    }

    public static boolean isValidFormatTanggal(String tanggal) {
        return tanggal.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    public static String formatTanggal(String tanggal) {
        String[] splitTanggal = tanggal.split("/");
        return String.format("%02d/%02d/%04d", Integer.parseInt(splitTanggal[0]), Integer.parseInt(splitTanggal[1]), Integer.parseInt(splitTanggal[2]));
    }

    public static int hitungChecksum(String data, boolean isFirstChecksum) {
        int total = 0;
        for (int i = 0; i < data.length(); i++) {
            int value = kode39Characters.indexOf(data.charAt(i));
            if (isFirstChecksum) {
                total += value * (i + 1);
            } else {
                total += value * (i + 2);
            }
        }
        return total % 43;
    }

    public static void initUser() {
        userList.add(new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer"));
        userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer"));
        userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer"));
        userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer"));
        userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer"));

        userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin"));
        userList.add(new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin"));
    }

    public static String generateBill(String orderID, String lokasi) {
        int biayaDelivery;
        switch (lokasi.toUpperCase()) {
            case "P":
                biayaDelivery = 10000;
                break;
            case "U":
                biayaDelivery = 20000;
                break;
            case "T":
                biayaDelivery = 35000;
                break;
            case "S":
                biayaDelivery = 40000;
                break;
            case "B":
                biayaDelivery = 60000;
                break;
            default:
                return "Harap masukkan lokasi pengiriman yang ada pada jangkauan!";
        }

        String[] bagianOrderID = splitOrderID(orderID);
        String tanggalOrderDenganFormat = bagianOrderID[0].substring(4, 6) + "/" + bagianOrderID[0].substring(6, 8) + "/" + bagianOrderID[0].substring(8, 12);
        String biayaDeliveryDenganFormat = String.format("Rp %,.0f", (double) biayaDelivery);

        return "Bill:\n" +
                "Order ID: " + orderID + "\n" +
                "Tanggal Pemesanan: " + tanggalOrderDenganFormat + "\n" +
                "Lokasi Pengiriman: " + lokasi.toUpperCase() + "\n" +
                "Biaya Ongkos Kirim: " + biayaDeliveryDenganFormat.replace(",", ".") + "\n";
    }

    public static String[] splitOrderID(String orderID) {
        return orderID.split("C");
    }

    public static Restaurant getRestaurant(String name) {
        for (Restaurant restaurant : restoList) {
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

    public static boolean isRestoranExist(String name) {
        for (Restaurant restaurant : restoList) {
            if (restaurant.getNama().equals(name)) {
                return true;
            }
        }
        return false;
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

    public static void printHeader() {
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    public static void startMenu() {
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuAdmin() {
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.print("Pilihan: ");
    }

    public static void menuCustomer() {
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Update Status Pesanan");
        System.out.println("5. Keluar");
        System.out.print("Pilihan: ");
    }
}