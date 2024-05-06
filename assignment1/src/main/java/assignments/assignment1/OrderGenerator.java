package assignments.assignment1;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);
<<<<<<< HEAD
    private static final String kode39Characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /* 
    Anda boleh membuat method baru sesuai kebutuhan Anda
    Namun, Anda tidak boleh menghapus ataupun memodifikasi return type method yang sudah ada.
    */
=======
    private static final int ORDER_ID_LENGTH = 16;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5

    /*
     * Anda boleh membuat method baru sesuai kebutuhan Anda
     * Namun, Anda tidak boleh menghapus ataupun memodifikasi return type method
     * yang sudah ada.
     */

<<<<<<< HEAD
    public static void showMenu() {
=======
    /*
     * Method ini untuk menampilkan DepeFood
     */
    public static void initMenu() {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.err.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
    }

    /*
     * Method ini untuk menampilkan menu
     */
    public static void showMenu() {
        System.out.println("Pilih menu:");
        System.err.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }

    /*
     * Method ini digunakan untuk membuat ID
     * dari nama restoran, tanggal order, dan nomor telepon
     *
     * @return String Order ID dengan format sesuai pada dokumen soal
     */

    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {
<<<<<<< HEAD
        if (namaRestoran.length() < 4) {
            System.out.println("Nama Restoran tidak valid!");
            return "";
        }

        String kodeRestoran = namaRestoran.substring(0, 4).toUpperCase();

        if (!isValidFormatTanggal(tanggalOrder)) {
            System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!");
            return "";
        }

        if (!isValidNoTelp(noTelepon)) {
            System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif.");
            return "";
        }

        String tanggalOrderDenganFormat = formatTanggal(tanggalOrder);
        int sumDariNoTelp = getSumDariNoTelp(noTelepon);
        String kodeNoTelp = String.format("%02d", sumDariNoTelp % 100);

        int checksum1 = hitungChecksum(kodeRestoran + tanggalOrderDenganFormat + kodeNoTelp, true);
        int checksum2 = hitungChecksum(kodeRestoran + tanggalOrderDenganFormat + kodeNoTelp, false);
        char checksum1Char = kode39Characters.charAt(checksum1);
        char checksum2Char = kode39Characters.charAt(checksum2);

        return kodeRestoran + tanggalOrderDenganFormat + kodeNoTelp + checksum1Char + checksum2Char;
    }

    // Untuk mendapatkan sum dari nomor telepon
    public static int getSumDariNoTelp(String noTelp) {
        int sum = 0;
        for (int i = 0; i < noTelp.length(); i++) {
            char digit = noTelp.charAt(i);
            if (Character.isDigit(digit)) {
                sum += digit - '0'; // Peng-convert-an ke integer
            }
        }
        return sum;
    }

    // Untuk memformat tanggal
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
=======

        String restaurantCode = getRestaurantCode(namaRestoran);
        String formattedDate = formatDate(tanggalOrder);
        String phoneNumberChecksum = getPhoneNumberChecksum(noTelepon);

        String id = restaurantCode + formattedDate + phoneNumberChecksum;
        id = id.toUpperCase();
        String checksum = calculateChecksum(id);

        return id + checksum;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

    /*
     * Method ini digunakan untuk membuat bill
     * dari order id dan lokasi
     *
     * @return String Bill dengan format sesuai di bawah:
     * Bill:
     * Order ID: [Order ID]
     * Tanggal Pemesanan: [Tanggal Pemesanan]
     * Lokasi Pengiriman: [Kode Lokasi]
     * Biaya Ongkos Kirim: [Total Ongkos Kirim]
     */
<<<<<<< HEAD

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
=======
    public static String generateBill(String OrderID, String lokasi) {
        String formattedDate = OrderID.substring(4, 12);
        String tanggalPemesanan = formattedDate.substring(0, 2) + "/" + formattedDate.substring(2, 4) + "/"
                + formattedDate.substring(4, 8);

        return outputBill(OrderID, tanggalPemesanan, lokasi, calculateDeliveryCost(lokasi));
    }

    public static boolean validateRestaurantName(String restaurantName) {
        return restaurantName != null && !restaurantName.isEmpty() && getRestaurantCode(restaurantName).length() >= 4;
    }

    public static boolean validateDate(String date) {
        String[] parts = date.split("/");
        if (parts.length != 3) {
            return false;
        }

        for (String part : parts) {
            if (!part.chars().allMatch(Character::isDigit)) {
                return false;
            }
        }

        return parts[0].length() == 2 && parts[1].length() == 2 && parts[2].length() == 4;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.chars().allMatch(Character::isDigit);
    }

    public static boolean validateLocation(String location) {
        char[] locationList = { 'P', 'U', 'T', 'S', 'B' };

        return location.length() == 1 && new String(locationList).contains(location);
    }

    public static boolean validateOrderID(String orderID) {
        if (orderID.length() != ORDER_ID_LENGTH) {
            System.out.println("Order ID minimal 16 karakter");
            return false;
        }

        if (!orderID.chars().allMatch(Character::isLetterOrDigit) || !checkIfChecksumValid(orderID)) {
            System.out.println("Silahkan masukkan Order ID yang valid!");
            return false;
        }

        return true;
    }

    public static boolean checkIfChecksumValid(String id) {
        String idWithoutChecksum = id.substring(0, id.length() - 2);
        String checksum = id.substring(id.length() - 2);

        return calculateChecksum(idWithoutChecksum).equals(checksum);
    }

    public static String getRestaurantCode(String restaurantName) {
        String[] words = restaurantName.split(" ");

        StringBuilder code = new StringBuilder();

        for (String word : words) {
            code.append(word);
        }

        return code.substring(0, Math.min(code.length(), 4));
    }

    public static String formatDate(String date) {
        String[] parts = date.split("/");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        return day + month + year;
    }

    public static String getPhoneNumberChecksum(String phoneNumber) {
        int sum = 0;
        for (char c : phoneNumber.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        int checksum = sum % 100;
        return (checksum < 10) ? "0" + checksum : String.valueOf(checksum);
    }

    public static String calculateChecksum(String id) {
        int sumEven = 0;
        int sumOdd = 0;

        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            int numericValue = getNumericValue(c);
            if (i % 2 == 0) {
                sumEven += numericValue;
            } else {
                sumOdd += numericValue;
            }
        }
        int remainderEven = sumEven % 36;
        int remainderOdd = sumOdd % 36;
        return reverseAssignment(remainderEven) + reverseAssignment(remainderOdd);
    }

    public static int getNumericValue(char c) {
        if (Character.isDigit(c)) {
            return Character.getNumericValue(c);
        } else {
            return c - 'A' + 10;
        }
    }

    public static String reverseAssignment(int remainder) {
        String code39CharacterSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return String.valueOf(code39CharacterSet.charAt(remainder));
    }

    public static int calculateDeliveryCost(String location) {
        switch (location) {
            case "P":
                return 10000;
            case "U":
                return 20000;
            case "T":
                return 35000;
            case "S":
                return 40000;
            case "B":
                return 60000;
            default:
                return 0;
        }
    }

    public static String outputBill(String orderID, String tanggalPemesanan, String lokasiPengiriman,
            int biayaOngkosKirim) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');

        decimalFormat.setDecimalFormatSymbols(symbols);

        return "Bill:\n" + "Order ID: " + orderID + "\n" + "Tanggal Pemesanan: " + tanggalPemesanan + "\n"
                + "Lokasi Pengiriman: " + lokasiPengiriman + "\n" + "Biaya Ongkos Kirim: Rp "
                + decimalFormat.format(biayaOngkosKirim) + "\n";
    }

    /*
     * Method ini untuk memproses ID Order
     */
    public static void processGenerateOrder() {
        boolean isInputValid = false;

        while (!isInputValid) {
            System.out.println();
            System.out.print("Nama Restoran: ");
            String namaRestoran = input.nextLine().toUpperCase();
            if (!validateRestaurantName(namaRestoran)) {
                System.out.println("Nama Restoran tidak valid!");
                continue;
            }

            System.out.print("Tanggal Pemesanan: ");
            String tanggalOrder = input.nextLine();

            if (!validateDate(tanggalOrder)) {
                System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!");
                continue;
            }

            System.out.print("No. Telpon: ");
            String noTelepon = input.nextLine();
            if (!validatePhoneNumber(noTelepon)) {
                System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif.");
                continue;
            }

            System.out.println(
                    "Order ID " + generateOrderID(namaRestoran, tanggalOrder, noTelepon) + " diterima!");

            isInputValid = true;
        }
    }

    /*
     * Method ini untuk memproses generate bill
     */
    public static void processGenerateBill() {
        boolean isInputValid = false;

        while (!isInputValid) {
            System.out.println();
            System.out.print("Order ID: ");
            String orderID = input.nextLine().toUpperCase();
            if (!validateOrderID(orderID)) {
                continue;
            }

            System.out.print("Lokasi Pengiriman: ");
            String lokasi = input.nextLine().toUpperCase();
            if (!validateLocation(lokasi)) {
                System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!");
                continue;
            }

            System.out.println(generateBill(orderID, lokasi));
            isInputValid = true;
        }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

    // Untuk bagi bagian dari ID Order
    public static String[] splitOrderID(String orderID) {
        String[] bagian = new String[2];
        bagian[0] = orderID.substring(0, 12);
        bagian[1] = orderID.substring(12);
        return bagian;
    }

    // Untuk cek apakah format tanggal valid
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

    // Untuk cek apakah nomor telepon valid
    public static boolean isValidNoTelp(String noTelp) {
        for (int i = 0; i < noTelp.length(); i++) {
            char digit = noTelp.charAt(i);
            if (digit < '0' || digit > '9') {
                return false; // Jika karakter bukan digit maka akan return false
            }
        }
        return !noTelp.isEmpty(); // Jika no telp kosong
    }

    // Untuk cek apakah tanggal valid
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
    public static boolean isTahunKabisat(int tahun) {
        return (tahun % 400 == 0) || ((tahun % 4 == 0) && (tahun % 100 != 0));
    }

    // Method utamanya
    public static void main(String[] args) {
<<<<<<< HEAD
        boolean exit = false;

        while (!exit) {
            showMenu();
            System.out.println("--------------------------------------------");
            System.out.print("Pilihan menu: ");
            String pilihanStr = input.nextLine();

            // Cek apakah input merupakan angka
            if (!isNumerik(pilihanStr)) {
                System.out.println("Harap masukkan nomor menu dalam bentuk bilangan bulat positif.");
                continue;
            }

            int pilihanMenu = toInt(pilihanStr); // Peng-convert-an ke integer
            switch (pilihanMenu) {
                case 1:
                    String namaRestoran;
                    boolean namaRestoranValid;
                    do {
                        namaRestoranValid = true; 
                        System.out.print("Nama Restoran: ");
                        namaRestoran = input.nextLine();
                        if (namaRestoran.length() < 4) {
                            System.out.println("Nama Restoran tidak valid!");
                            namaRestoranValid = false;
                            continue; // Agar mengulang ke tahap nama restoran
                        }

                        String tanggalOrder;
                        boolean tanggalValid;
                        do {
                            tanggalValid = true;
                            System.out.print("Tanggal Pemesanan: ");
                            tanggalOrder = input.nextLine();
                            if (!isValidFormatTanggal(tanggalOrder)) {
                                System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!");
                                tanggalValid = false;
                                break; // Agar mengulang ke tahap nama restoran
                            }
                            if (tanggalValid) { 
                                String noTelp;
                                boolean validNoTelp;
                                do {
                                    validNoTelp = true; 
                                    System.out.print("No. Telepon: ");
                                    noTelp = input.nextLine();
                                    if (!isValidNoTelp(noTelp)) {
                                        System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif.");
                                        validNoTelp = false;
                                    }

                                    if (validNoTelp) { 
                                        String orderID = generateOrderID(namaRestoran, tanggalOrder, noTelp);
                                        if (orderID.isEmpty()) {
                                            break; 
                                        }
                                        System.out.println("Order ID " + orderID + " diterima!");
                                        break; 
                                    }
                                } while (true); 
                            }
                        } while (!tanggalValid); 
                    } while (!namaRestoranValid); 

                    if (!namaRestoranValid) {
                        continue; // Agar mengulang ke tahap nama restoran
                    }
                    break;

                case 2:
                    boolean validOrderID;
                    String billOrderID;
                    do {
                        validOrderID = true; 
                        System.out.print("Order ID: ");
                        billOrderID = input.nextLine();

                        if (billOrderID.length() != 16) {
                            System.out.println("Order ID minimal 16 karakter!");
                            validOrderID = false;
                            continue;
                        }

                        int checksum1 = hitungChecksum(billOrderID.substring(0, 14), true);
                        int checksum2 = hitungChecksum(billOrderID.substring(0, 14), false);
                        char checksum1Char = kode39Characters.charAt(checksum1);
                        char checksum2Char = kode39Characters.charAt(checksum2);

                        if (billOrderID.charAt(14) != checksum1Char || billOrderID.charAt(15) != checksum2Char) {
                            System.out.println("Silahkan masukkan Order ID yang valid!");
                            validOrderID = false;
                        }

                    } while (!validOrderID); 

                    String lokasiDelivery;
                    boolean lokasiValid = false;
                    do {
                        System.out.print("Lokasi Pengiriman: ");
                        lokasiDelivery = input.nextLine().toUpperCase();
                        if (lokasiDelivery.equals("P") || lokasiDelivery.equals("U") || lokasiDelivery.equals("T") || lokasiDelivery.equals("S") || lokasiDelivery.equals("B")) {
                            lokasiValid = true;
                        } else {
                            System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!");
                        }
                    } while (!lokasiValid);

                    System.out.println(generateBill(billOrderID, lokasiDelivery));
                    break;

                case 3:
                    exit = true;
                    System.out.println("Terima kasih telah menggunakan DepeFood!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid, silakan pilih nomor menu yang tersedia.");
            }
        }
    }

    // Untuk convert string menjadi integer
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

    public static boolean isNumerik(String str) {
        return toInt(str) != -1;
    }
}
=======
        boolean isRunning = true;

        initMenu();
        while (isRunning) {
            showMenu();
            System.out.println("--------------------------------------------");
            System.out.print("Pilihan Menu: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    processGenerateOrder();
                    break;
                case 2:
                    processGenerateBill();
                    break;
                case 3:
                    isRunning = false;
                    System.out.println("Terima kasih telah menggunakan DepeFood!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
            System.out.println("--------------------------------------------");
        }
    }
}
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
