/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment2;

import java.util.ArrayList;

// Class Restaurant, memuat nama dan menu dalam ArrayList
public class Restaurant {
    private String nama;
    private ArrayList<Menu> menu;

    public Restaurant(String nama, ArrayList<Menu> menu) {
        this.nama = nama;
        this.menu = menu;
    }

    public String getNama() {
        return nama;
    }

    public ArrayList<Menu> getMenu() {
        return menu;
    }
}