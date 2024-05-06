/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3;

import java.util.ArrayList;
import java.util.Scanner;

import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;
<<<<<<< HEAD
=======
import assignments.assignment3.systemCLI.AdminSystemCLI;
import assignments.assignment3.systemCLI.CustomerSystemCLI;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
import assignments.assignment3.systemCLI.UserSystemCLI;

public class MainMenu {
    private final Scanner input;
    private final LoginManager loginManager;
    private static ArrayList<User> userList;
    private static ArrayList<Restaurant> restoList;

    // Constructor MainMenu
    public MainMenu(Scanner input, LoginManager loginManager) {
        this.input = input;
        this.loginManager = loginManager;
    }

<<<<<<< HEAD
    // Running
    public void run() {
        boolean running = true;

        while (running) {
            printHeader();
            UserSystemCLI system = login();
            if (system != null) {
                system.run();
            } else {
                System.out.println("Peran tidak dikenal, silakan coba lagi.");
=======
    public static void main(String[] args) {
        initUser();

        restoList = new ArrayList<>();

        MainMenu mainMenu = new MainMenu(new Scanner(System.in),
                new LoginManager(new AdminSystemCLI(), new CustomerSystemCLI()));

        mainMenu.run();
    }

    public void run() {
        printHeader();
        boolean exit = false;
        while (!exit) {
            startMenu();
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> login();
                case 2 -> exit = true;
                default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            }
        }
    }

<<<<<<< HEAD
    // Header
    private void printHeader() {
        System.out.println("\n-----------------------------");
        System.out.println("   SELAMAT DATANG DI DepeFood  ");
        System.out.println("-----------------------------\n");
    }

    // Login
    private UserSystemCLI login() {
        System.out.print("Masukkan peran (Admin / Customer): ");
        String role = input.nextLine();
        return loginManager.getSystem(role);
    }

    // Init User
    public static void initUser() {
        userList = new ArrayList<>();

        userList.add(new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer", new DebitPayment(500000), 500000));
        userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer", new CreditCardPayment(2000000), 2000000));
        userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer", new DebitPayment(750000), 750000));
        userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer", new CreditCardPayment(1800000), 1800000));
        userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer", new DebitPayment(650000), 650000));

        userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin", new CreditCardPayment(0), 0));
        userList.add(new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin", new CreditCardPayment(0), 0));
    }
=======
    private void login() {
        UserSystemCLI system;
        System.out.println("\nSilakan Login:");
        System.out.print("Nama: ");
        String nama = input.nextLine();
        System.out.print("Nomor Telepon: ");
        String noTelp = input.nextLine();

        User userLoggedIn = getUser(nama, noTelp);

        if (userLoggedIn == null) {
            System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
            return;
        }

        System.out.printf("Selamat Datang %s!%n", userLoggedIn.getNama());

        system = loginManager.getSystem(userLoggedIn.role);

        system.setInput(input);
        system.setUserLoggedIn(userLoggedIn);
        system.setRestoList(restoList);
        system.setUserList(userList);

        system.run();
    }

    public static User getUser(String nama, String nomorTelepon) {

        for (User user : userList) {
            if (user.getNama().equals(nama.trim()) && user.getNomorTelepon().equals(nomorTelepon.trim())) {
                return user;
            }
        }
        return null;
    }

    

    private static void printHeader() {
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    private static void startMenu() {
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void initUser() {
        userList = new ArrayList<>();

        userList.add(
                new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer", new DebitPayment(), 500000));
        userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer",
                new CreditCardPayment(), 2000000));
        userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer",
                new DebitPayment(), 750000));
        userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer",
                new CreditCardPayment(), 1800000));
        userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer", new DebitPayment(),
                650000));

        userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin", new CreditCardPayment(), 0));
        userList.add(
                new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin", new CreditCardPayment(), 0));
    }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
}