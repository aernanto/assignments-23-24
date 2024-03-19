package assignments.assignment2;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<Restaurant> restoList;
    private static ArrayList<User> userList;
    private static final String kode39Characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    // Menu utama
    public static void main(String[] args) {
        restoList = new ArrayList<>();
        userList = new ArrayList<>();
        initUser();
        boolean programRunning = true;
        while (programRunning) {
            printHeader();
            startMenu();
            if (input.hasNextInt()) {
                int command = input.nextInt();
                input.nextLine();
        
                // jika user memilih 1
                if (command == 1) {
                    System.out.println("\nSilahkan Login:");
                    System.out.print("Nama: ");
                    String nama = input.nextLine();
                    System.out.print("Nomor Telepon: ");
                    String noTelp = input.nextLine();
        
                    User userLoggedIn = getUser(nama, noTelp);
                    boolean isLoggedIn = true;
                    if (userLoggedIn != null) {
                        if (userLoggedIn.getNama().equalsIgnoreCase("Admin") || userLoggedIn.getNama().equalsIgnoreCase("Admin Baik")) {
                            while (isLoggedIn) {
                                menuAdmin();
                                if (input.hasNextInt()) {
                                    int commandAdmin = input.nextInt();
                                    input.nextLine();
        
                                    // Command admin
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
                                } else {
                                    System.out.println("Perintah tidak valid. Silakan coba kembali.");
                                    input.nextLine(); // Consume invalid input
                                }
                            }
                        } else {
                            while (isLoggedIn) {
                                menuCustomer();
                                if (input.hasNextInt()) {
                                    int commandCust = input.nextInt();
                                    input.nextLine();
        
                                    // Command customer
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
                                } else {
                                    System.out.println("Perintah tidak valid. Silakan coba kembali.");
                                    input.nextLine(); 
                                }
                            }
                        }
                    } else {
                        System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                    }
                // jika user memilih 2
                } else if (command == 2) {
                    programRunning = false;
                } else {
                    System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                }
            } else {
                System.out.println("Perintah tidak valid. Silakan coba kembali.");
                input.nextLine(); 
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood!");
    }    

    // Untuk mengecek apakah tanggal valid (DD/MM/YYYY)
    // Imported from OrderGenerator.java
    public static boolean isValidTanggal(String tanggal) {
        if (tanggal.length() != 10) {
            return false;
        }

        int hari = 0, bulan = 0, tahun = 0;
        int awal = 0;
        for (int i = 0; i < tanggal.length(); i++) {
            if (tanggal.charAt(i) == '/') {
                if (hari == 0) {
                    hari = toInt(tanggal.substring(awal, i)); // Peng-convert-an ke integer
                    awal = i + 1;
                } else if (bulan == 0) {
                    bulan = toInt(tanggal.substring(awal, i)); // Peng-convert-an ke integer
                    awal = i + 1;
                }
            }
        }
        tahun = toInt(tanggal.substring(awal)); // Peng-convert-an ke integer
        if (hari < 1 || hari > 31 || bulan < 1 || bulan > 12 || tahun < 1000) {
            return false;
        }
        if (!isTanggalValid(hari, bulan, tahun)) {
            return false;
        }
        return true;
    }

    // Untuk cek apakah nomor telepon valid
    // imported from OrderGenerator.java
    public static boolean isValidNoTelp(String noTelp) {
        for (int i = 0; i < noTelp.length(); i++) {
            char digit = noTelp.charAt(i);
            if (digit < '0' || digit > '9') {
                return false; // Jika karakter bukan digit maka akan return false
            }
        }
        return !noTelp.isEmpty(); // Jika no telp kosong
    }  

    // Untuk mengecek apakah hari, bulan, dan tanggal memenuhi kriteria penanggalan
    // imported from OrderGenerator.java
    public static boolean isTanggalValid(int hari, int bulan, int tahun) {
        if (bulan < 1 || bulan > 12) {
            return false;
        }
        int[] hariSetiapBulannya = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (bulan == 2 && isTahunKabisat(tahun)) {
            hariSetiapBulannya[1] = 29;
        }
        return hari >= 1 && hari <= hariSetiapBulannya[bulan - 1];
    }

    // Kondisi tahun kabisat
    // Imported from OrderGenerator.java
    public static boolean isTahunKabisat(int tahun) {
        return (tahun % 400 == 0) || ((tahun % 4 == 0) && (tahun % 100 != 0));
    }

    // Getter user
    public static User getUser(String nama, String nomorTelepon) {
        for (User user : userList) {
            if (user.getNama().equals(nama) && user.getNomorTelepon().equals(nomorTelepon)) {
                return user;
            }
        }
        return null;
    }

    // Untuk convert string menjadi integer
    // Imported from OrderGenerator.java
    public static int toInt(String str) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            char digit = str.charAt(i);
            if (digit < '0' || digit > '9') {
                return -1; // Kalau karakter bukan digit
            }
            result = result * 10 + (digit - '0'); // Peng-convert-an ke integer
        }
        return result;
    }
    
    // Menu untuk buat pesanan
    public static void handleBuatPesanan() {
        System.out.println("\n--------------Buat Pesanan----------------");
        String nomorTeleponPengguna = ""; // Inisialisasi nomorTeleponPengguna di sini
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine();
            Restaurant restaurant = getRestaurant(restaurantName);
            if (restaurant == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.");
                System.out.print("");
                continue;
            }
            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalOrder = input.nextLine();
            if (!isValidTanggal(tanggalOrder)) {
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)!");
                System.out.print("");
                continue;
            }
            System.out.print("Jumlah Pesanan: ");
            int itemCount = input.nextInt();
            input.nextLine();
            System.out.println("Order:");
            
            ArrayList<Menu> orderItems = new ArrayList<>();
            for (int i = 0; i < itemCount; i++) {
                System.out.print("");
                String itemName = input.nextLine();
                Menu menu = getMenuFromRestaurant(restaurant, itemName);
                if (menu == null) {
                    System.out.println("Mohon memesan menu yang tersedia di Restoran!");
                    return;
                }
                orderItems.add(menu);
            }
            
            System.out.print("Nama: ");
            String namaPengguna = input.nextLine();
            System.out.print("Nomor Telepon: ");
            nomorTeleponPengguna = input.nextLine(); 
            
            String orderID = generateOrderID(restaurantName, tanggalOrder, nomorTeleponPengguna);
            if (orderID.isEmpty()) {
                return;
            }
            System.out.println("Pesanan dengan ID " + orderID + " diterima!");
            
            Order order = new Order(orderID, tanggalOrder, restaurant, orderItems, false, 0);
            
            for (User user : userList) {
                if (user.getNama().equals(namaPengguna) && user.getNomorTelepon().equals(nomorTeleponPengguna)) {
                    user.getOrderHistory().add(order);
                    return;
                }
            }
            System.out.println("Pemesan tidak ditemukan.");
        }
    }              
        
    // Menu cetak bill
    public static void handleCetakBill() {
        boolean orderFound = false;
        while (!orderFound) {
            System.out.println("\n--------------Cetak Bill----------------");
            System.out.print("Masukkan Order ID: ");
            String orderID = input.nextLine();
            Order order = getOrder(orderID);
            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan.");
                continue;
            }
            String namaPengguna = null;
            String nomorTeleponPengguna = null;
            String lokasiPengguna = null;
            for (User user : userList) {
                for (Order userOrder : user.getOrderHistory()) {
                    if (userOrder.getOrderID().equals(orderID)) {
                        namaPengguna = user.getNama();
                        nomorTeleponPengguna = user.getNomorTelepon();
                        lokasiPengguna = user.getLokasi();
                        break;
                    }
                }
                if (namaPengguna != null && nomorTeleponPengguna != null) {
                    break;
                }
            }
            if (namaPengguna == null || nomorTeleponPengguna == null) {
                System.out.println("Pengguna tidak ditemukan.");
                continue;
            }
            System.out.println(lokasiPengguna);
            System.out.println(generateBill(orderID, lokasiPengguna));
            orderFound = true;
        }
    }

    // Menu lihat menu
    public static void handleLihatMenu() {
        boolean restaurantFound = false;
        while (!restaurantFound) {
            System.out.println("\n--------------Lihat Menu----------------");
            System.out.print("Nama Restoran: ");
            String name = input.nextLine();
            Restaurant restaurant = getRestaurant(name);
            if (restaurant == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.");
                System.out.print(""); 
                continue; 
            }
            System.out.println("Menu:");
            int menuNumber = 1;
            for (Menu menu : restaurant.getMenu()) {
                String formattedPrice = String.format("%.0f", menu.getHarga());
                System.out.println(menuNumber + ". " + menu.getNamaMakanan() + " " + formattedPrice);
                menuNumber++;
            }
            restaurantFound = true; 
        }
    }

    // Menu update status pesanan
    public static void handleUpdateStatusPesanan() {
        boolean orderFound = false;
        while (!orderFound) {
            System.out.println("\n--------------Update Status Pesanan----------------");
            System.out.print("Order ID: ");
            String orderID = input.nextLine();
            Order order = getOrder(orderID);
            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan.");
                continue; 
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
            orderFound = true; // Set flag to exit the loop
        }
    }

    // Menu tambah restoran
    public static void handleTambahRestoran() {
        System.out.println("--------------Tambah Restoran----------------");
        String name;
        while (true) {
            System.out.print("Nama: ");
            name = input.nextLine().toLowerCase();
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
            System.out.print("");
            String foodInput = input.nextLine();
            String[] foodParts = foodInput.split("\\s+");
            StringBuilder foodNameBuilder = new StringBuilder();
            double price = 0.0;
            boolean priceFound = false;
    
            for (String part : foodParts) {
                if (!priceFound) {
                    try {
                        price = Double.parseDouble(part);
                        priceFound = true;
                    } catch (NumberFormatException ignored) {
                        foodNameBuilder.append(part).append(" ");
                    }
                } else {
                    foodNameBuilder.append(part).append(" ");
                }
            }
    
            String foodName = foodNameBuilder.toString().trim();
            if (foodName.isEmpty() || price == 0.0) {
                System.out.println("Harga menu harus bilangan bulat!");
                i--; 
                continue;
            }
    
            menuList.add(new Menu(foodName, price));
        }
    
        Restaurant restaurant = new Restaurant(name, menuList);
        restoList.add(restaurant);
        System.out.println("Restaurant " + name + " berhasil terdaftar.");
    }

    // Menu hapus restoran
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

    // Untuk membuat order ID
    // Imported from OrderGenerator.java
    public static String generateOrderID(String namaRestoran, String tanggalOrder, String nomorTeleponPengguna) {
        if (namaRestoran.length() < 4) {
            System.out.println("Nama Restoran tidak valid!");
            return "";
        }
    
        String kodeRestoran = namaRestoran.substring(0, 4).toUpperCase();
    
        if (!isValidFormatTanggal(tanggalOrder)) {
            System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!");
            return "";
        }
    
        if (!isValidNoTelp(nomorTeleponPengguna)) {
            System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif.");
            return "";
        }
    
        String tanggalOrderDenganFormat = formatTanggal(tanggalOrder);
        int sumDariNoTelp = getSumDariNoTelp(nomorTeleponPengguna);
        String kodeNoTelp = String.format("%02d", sumDariNoTelp % 100);
    
        int checksum1 = hitungChecksum(kodeRestoran + tanggalOrderDenganFormat + kodeNoTelp, true);
        int checksum2 = hitungChecksum(kodeRestoran + tanggalOrderDenganFormat + kodeNoTelp, false);
        char checksum1Char = kode39Characters.charAt(checksum1);
        char checksum2Char = kode39Characters.charAt(checksum2);
    
        return kodeRestoran + tanggalOrderDenganFormat + kodeNoTelp + checksum1Char + checksum2Char;
    }

    // Untuk mendapatkan sum dari nomor telepon
    // Imported from OrderGenerator.java
    public static int getSumDariNoTelp(String noTelp) {
        int sum = 0;
        for (char c : noTelp.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        return sum;
    }

    // Untuk cek apakah format tanggal valid
    // Imported from OrderGenerator.java
    public static boolean isValidFormatTanggal(String tanggal) {
        if (tanggal.length() != 10) {
            return false;
        }

        int hari = 0, bulan = 0, tahun = 0;
        int awal = 0;
        for (int i = 0; i < tanggal.length(); i++) {
            if (tanggal.charAt(i) == '/') {
                if (hari == 0) {
                    hari = toInt(tanggal.substring(awal, i)); // Peng-convert-an ke integer
                    awal = i + 1;
                } else if (bulan == 0) {
                    bulan = toInt(tanggal.substring(awal, i)); // Peng-convert-an ke integer
                    awal = i + 1;
                }
            }
        }
        tahun = toInt(tanggal.substring(awal)); // Peng-convert-an ke integer
        if (hari < 1 || hari > 31 || bulan < 1 || bulan > 12 || tahun < 1000) {
            return false;
        }
        if (!isTanggalValid(hari, bulan, tahun)) {
            return false;
        }
        return true;
    }

    // Untuk memformat tanggal
    // Imported from OrderGenerator.java
    public static String formatTanggal(String tanggalOrder) {
        int hari = 0, bulan = 0, tahun = 0;
        int awal = 0;
        for (int i = 0; i < tanggalOrder.length(); i++) {
            if (tanggalOrder.charAt(i) == '/') {
                if (hari == 0) {
                    hari = toInt(tanggalOrder.substring(awal, i)); // Peng-convert-an ke integer
                    awal = i + 1;
                } else if (bulan == 0) {
                    bulan = toInt(tanggalOrder.substring(awal, i)); // Peng-convert-an ke integer
                    awal = i + 1;
                }
            }
        }
        tahun = toInt(tanggalOrder.substring(awal)); // Peng-convert-an ke integer
        return String.format("%02d%02d%02d", hari, bulan, tahun);
    }

    // Untuk hitung checksum
    // Imported from OrderGenerator.java
    public static int hitungChecksum(String orderId, boolean isChecksum1) {
        int sum = 0;
        for (int i = 0; i < orderId.length(); i++) {
            int nilaiNumerik = Character.getNumericValue(orderId.charAt(i));
            if ((i % 2 == 0 && isChecksum1) || (i % 2 != 0 && !isChecksum1)) {
                sum += nilaiNumerik;
            }
        }
        sum %= 36;
        return sum;
    }

    // Data-data user yang diberikan
    public static void initUser() {
        userList.add(new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer"));
        userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer"));
        userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer"));
        userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer"));
        userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer"));

        userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin"));
        userList.add(new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin"));
    }

    // Untuk membuat bill
    // Imported from OrderGenerator.java
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
    
        Order order = getOrder(orderID);
        if (order == null) {
            return "Order ID tidak dapat ditemukan.";
        }
    
        double totalBiaya = 0.0;
        StringBuilder pesanan = new StringBuilder();
        for (Menu menu : order.getItems()) {
            pesanan.append("- ").append(menu.getNamaMakanan()).append(" ").append(String.format("Rp %,.0f", menu.getHarga())).append("\n");
            totalBiaya += menu.getHarga();
        }
        totalBiaya += biayaDelivery;
    
        String[] bagianOrderID = splitOrderID(orderID);
        String tanggalOrderDenganFormat = bagianOrderID[0].substring(4, 6) + "/" + bagianOrderID[0].substring(6, 8) + "/" + bagianOrderID[0].substring(8, 12);
        String biayaDeliveryDenganFormat = "Rp " + (int) biayaDelivery;
        String totalBiayaDenganFormat = "Rp " + (int) totalBiaya;
    
        return "Bill:\n" +
                "Order ID: " + orderID + "\n" +
                "Tanggal Pemesanan: " + tanggalOrderDenganFormat + "\n" +
                "Restaurant: " + order.getRestaurant().getNama() + "\n" +
                "Lokasi Pengiriman: " + lokasi.toUpperCase() + "\n" +
                "Status Pengiriman: " + (order.isOrderFinished() ? "Finished" : "Not Finished") + "\n" +
                "Pesanan:\n" + pesanan.toString() +
                "Biaya Ongkos Kirim: " + biayaDeliveryDenganFormat.replace(",", ".") + "\n" +
                "Total Biaya: " + totalBiayaDenganFormat.replace(",", ".") + "\n";
    }    

    // Untuk bagi bagian dari ID Order
    // Imported from OrderGenerator.java
    public static String[] splitOrderID(String orderID) {
        String[] bagian = new String[2];
        bagian[0] = orderID.substring(0, 12);
        bagian[1] = orderID.substring(12);
        return bagian;
    }

    // Getter Restoran
    public static Restaurant getRestaurant(String name) {
        String nameLowerCase = name.toLowerCase(); // Convert input to lowercase
        for (Restaurant restaurant : restoList) {
            if (restaurant.getNama().toLowerCase().equals(nameLowerCase)) { // Convert restaurant name to lowercase for comparison
                return restaurant;
            }
        }
        return null;
    }    

    // Getter Menu dari restoran
    public static Menu getMenuFromRestaurant(Restaurant restaurant, String itemName) {
        for (Menu menu : restaurant.getMenu()) {
            if (menu.getNamaMakanan().equalsIgnoreCase(itemName)) {
                return menu;
            }
        }
        return null;
    }

    // Apabila restoran sudah ada
    public static boolean isRestoranExist(String name) {
        for (Restaurant restaurant : restoList) {
            if (restaurant.getNama().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Getter order
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

    // Header DepeFood
    public static void printHeader() {
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    // Tampilan setelah header
    public static void startMenu() {
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    // Menu admin
    public static void menuAdmin() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("\n--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    // Menu customer
    public static void menuCustomer() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Update Status Pesanan");
        System.out.println("5. Keluar");
        System.out.println("\n--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }
}