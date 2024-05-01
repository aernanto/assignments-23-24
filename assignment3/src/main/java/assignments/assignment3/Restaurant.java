/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3;

import java.util.ArrayList;

// Class Restaurant, memuat nama dan menu dalam ArrayList
public class Restaurant {
    private String nama;
    private String lokasi; 
    private ArrayList<Menu> menu;

    public Restaurant(String nama, String lokasi, ArrayList<Menu> menu) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.menu = menu;
    }

    public String getNama() {
        return nama;
    }

    public String getLokasi() { 
        return lokasi;
    }

    public ArrayList<Menu> getMenu() {
        return menu;
    }
}