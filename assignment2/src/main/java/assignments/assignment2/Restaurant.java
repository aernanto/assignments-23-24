package assignments.assignment2;

import java.util.ArrayList;

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