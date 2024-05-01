/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3;

import java.util.ArrayList;

// Definisi kelas Restaurant
public class Restaurant {
    private String nama;
    private String lokasi;
    private ArrayList<Menu> menu;

    // Constructor Restaurant
    public Restaurant(String nama, String lokasi) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.menu = new ArrayList<>();
    }

    // Getter nama
    public String getNama() {
        return nama;
    }

    // Getter lokasi
    public String getLokasi() {
        return lokasi;
    }

    // Getter menu
    public ArrayList<Menu> getMenu() {
        return menu;
    }

    // new menu
    public void addMenu(Menu newMenu) {
        menu.add(newMenu);
    }
}