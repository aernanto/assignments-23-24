package assignments.assignment3;

import java.util.ArrayList;
import java.util.Scanner;

import assignments.assignment2.Restaurant;
import assignments.assignment2.User;
<<<<<<< HEAD
import assignments.assignment3.LoginManager;
=======
import assignments.assignment3.LoginManager
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;
>>>>>>> 1285504d2decc6c014c883956975c97d285a0497
import assignments.assignment3.systemCLI.AdminSystemCLI;
import assignments.assignment3.systemCLI.CustomerSystemCLI;

public class MainMenu {
<<<<<<< HEAD
    private Scanner input;
    private LoginManager loginManager;
=======
    private final Scanner input;
    private final LoginManager loginManager;
    private static ArrayList<Restaurant> restoList;
    private static ArrayList<User> userList;
>>>>>>> 1285504d2decc6c014c883956975c97d285a0497

    public MainMenu(Scanner input, LoginManager loginManager) {
        this.input = input;
        this.loginManager = loginManager;
    }

    public void run() {
        boolean running = true;

        while (running) {
            printHeader();
            UserSystemCLI system = login();
            if (system != null) {
                system.run();
            } else {
                System.out.println("Peran tidak dikenal, silakan coba lagi.");
            }
        }
    }

    private void printHeader() {
        System.out.println("\n-----------------------------");
        System.out.println("   SELAMAT DATANG DI DepeFood  ");
        System.out.println("-----------------------------\n");
    }

    private UserSystemCLI login() {
        System.out.print("Masukkan peran (Admin / Customer): ");
        String role = input.nextLine();
        return loginManager.getSystem(role);
    }
<<<<<<< HEAD
}
=======

    private static void startMenu(){
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void initUser(){
        userList = new ArrayList<User>();

        //TODO: Adjust constructor dan atribut pada class User di Assignment 2
        userList.add(new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer", new DebitPayment(), 500000));
        userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer", new CreditCardPayment(), 2000000));
        userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer", new DebitPayment(), 750000));
        userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer", new CreditCardPayment(), 1800000));
        userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer", new DebitPayment(), 650000));

        userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin", new CreditCardPayment(), 0));
        userList.add(new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin", new CreditCardPayment(), 0));
    }
}
>>>>>>> 1285504d2decc6c014c883956975c97d285a0497
