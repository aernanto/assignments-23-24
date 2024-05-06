<<<<<<< HEAD
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
=======
package assignments.assignment3;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;

public class Restaurant {
    private String nama;
    private ArrayList<Menu> menu;

    public Restaurant(String nama) {
        this.nama = nama;
        this.menu = new ArrayList<>();
    }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public String getNama() {
        return nama;
    }

<<<<<<< HEAD
    // Getter lokasi
    public String getLokasi() {
        return lokasi;
    }

    // Getter menu
=======
    public void addMenu(Menu newMenu) {
        menu.add(newMenu);
    }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public ArrayList<Menu> getMenu() {
        return menu;
    }

<<<<<<< HEAD
    // new menu
    public void addMenu(Menu newMenu) {
        menu.add(newMenu);
    }
}
=======
    private ArrayList<Menu> sortMenu() {
        Menu[] menuArr = new Menu[menu.size()];
        for (int i = 0; i < menu.size(); i++) {
            menuArr[i] = menu.get(i);
        }
        int n = menuArr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (menuArr[j].getHarga() > menuArr[j + 1].getHarga()) {

                    Menu temp = menuArr[j];
                    menuArr[j] = menuArr[j + 1];
                    menuArr[j + 1] = temp;
                }
            }
        }
        return new ArrayList<>(Arrays.asList(menuArr));
    }

    public String printMenu() {
        StringBuilder menuString = new StringBuilder("Menu:\n");
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('\u0000');
        decimalFormat.setDecimalFormatSymbols(symbols);
        int menuNumber = 1;
        for (Menu menuItem : sortMenu()) {
            menuString.append(menuNumber).append(". ").append(menuItem.getNamaMakanan()).append(" ")
                    .append(decimalFormat.format(menuItem.getHarga())).append("\n");
            menuNumber++;
        }
        if (menuString.length() > 0) {
            menuString.deleteCharAt(menuString.length() - 1);
        }
        return menuString.toString();
    }
}
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
