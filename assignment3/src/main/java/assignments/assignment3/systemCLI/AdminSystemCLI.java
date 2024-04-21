package assignments.assignment3.systemCLI;

import java.util.Scanner;
import assignments.assignment3.systemCLI.UserSystemCLI;

public class AdminSystemCLI implements UserSystemCLI {
    private Scanner input;

    public AdminSystemCLI(Scanner input) {
        this.input = input;
    }

    @Override
    public void displayMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    @Override
    public boolean handleMenu(int command) {
        switch (command) {
            case 1:
                handleTambahRestoran();
                return true;
            case 2:
                handleHapusRestoran();
                return true;
            case 3:
                return false;
            default:
                System.out.println("Perintah tidak diketahui, silakan coba kembali");
                return true;
        }
    }

    private void handleTambahRestoran() {
        // Implementasi untuk menambah restoran
        System.out.println("TODO: Implementasi untuk menambah restoran");
    }

    private void handleHapusRestoran() {
        // Implementasi untuk menghapus restoran
        System.out.println("TODO: Implementasi untuk menghapus restoran");
    }
}