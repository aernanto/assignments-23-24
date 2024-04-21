package assignments.assignment3.systemCLI;

import java.util.Scanner;

public class CustomerSystemCLI extends UserSystemCLI {
    private Scanner input;

    public CustomerSystemCLI(Scanner input) {
        this.input = input;
    }

    @Override
    public void displayMenu() {
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

    private void handleBuatPesanan() {
        // Implementasi untuk membuat pesanan
        System.out.println("TODO: Implementasi untuk membuat pesanan");
    }

    private void handleCetakBill() {
        // Implementasi untuk mencetak bill
        System.out.println("TODO: Implementasi untuk mencetak bill");
    }

    private void handleLihatMenu() {
        // Implementasi untuk melihat menu
        System.out.println("TODO: Implementasi untuk melihat menu");
    }

    private void handleBayarBill() {
        // Implementasi untuk membayar bill
        System.out.println("TODO: Implementasi untuk membayar bill");
    }

    private void handleCekSaldo() {
        // Implementasi untuk mengecek saldo
        System.out.println("TODO: Implementasi untuk mengecek saldo");
    }
}