package assignments.assignment3;

import java.util.Scanner;

import assignments.assignment2.User;
import assignments.assignment3.LoginManager;
import assignments.assignment3.systemCLI.AdminSystemCLI;
import assignments.assignment3.systemCLI.CustomerSystemCLI;

public class MainMenu {
    private Scanner input;
    private LoginManager loginManager;

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
}