/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3.systemCLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import assignments.assignment2.Restaurant;
import assignments.assignment2.Menu;

public class AdminSystemCLI implements UserSystemCLI {
    private Scanner input;
    private ArrayList<Restaurant> restoList;

    public AdminSystemCLI(Scanner input, ArrayList<Restaurant> restoList) {
        this.input = input;
        this.restoList = restoList;
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
        System.out.println("--------------Tambah Restoran----------------");
        String name;
        while (true) {
            System.out.print("Nama: ");
            name = input.nextLine().trim().toLowerCase();
            if (isRestoranExist(name)) {
                System.out.println("Restoran dengan nama " + name + " sudah terdaftar. Mohon masukkan nama yang berbeda!");
                continue;
            }
            if (name.length() < 4) {
                System.out.println("Nama Restoran tidak valid!");
                continue;
            }
            break;
        }

        ArrayList<Menu> menuList = new ArrayList<>();
        System.out.print("Jumlah Makanan: ");
        int count = Integer.parseInt(input.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.print("Masukkan nama dan harga makanan (contoh: Pizza 20000): ");
            String foodInput = input.nextLine();
            String[] parts = foodInput.split("\\s+");
            try {
                double price = Double.parseDouble(parts[parts.length - 1]);
                String foodName = String.join(" ", Arrays.copyOfRange(parts, 0, parts.length - 1));
                menuList.add(new Menu(foodName, price));
            } catch (NumberFormatException e) {
                System.out.println("Harga harus berupa angka. Silakan masukkan kembali.");
                i--; // Decrement to repeat the input for this item
            }
        }

        Restaurant restaurant = new Restaurant(name, menuList);
        restoList.add(restaurant);
        System.out.println("Restaurant " + name + " berhasil terdaftar.");
    }

    private void handleHapusRestoran() {
        System.out.println("\n--------------Hapus Restoran----------------");
        System.out.print("Nama Restoran: ");
        String name = input.nextLine().trim().toLowerCase();
        boolean found = false;
        for (int i = 0; i < restoList.size(); i++) {
            if (restoList.get(i).getNama().toLowerCase().equals(name)) {
                restoList.remove(i);
                System.out.println("Restoran " + name + " berhasil dihapus.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Restoran tidak terdaftar pada sistem.");
        }
    }

    private boolean isRestoranExist(String name) {
        for (Restaurant restaurant : restoList) {
            if (restaurant.getNama().toLowerCase().equals(name)) {
                return true;
            }
        }
        return false;
    }
}