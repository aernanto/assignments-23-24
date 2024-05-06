/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment2;

<<<<<<< HEAD
import java.util.ArrayList;

// Class Order, memuat orderID, tanggalPemesanan, restaurant, items dalam ArrayList, orderFinished, dan biayaOngkosKirim
public class Order {  
    private String orderID;
    private String tanggalPemesanan;
    private Restaurant restaurant;
    private ArrayList<Menu> items;
    private boolean orderFinished;
    private Integer biayaOngkosKirim;

    public Order(String orderID, String tanggalPemesanan, Restaurant restaurant, ArrayList<Menu> items, boolean orderFinished, int biayaOngkosKirim) {
        this.orderID = orderID;
        this.tanggalPemesanan = tanggalPemesanan;
        this.restaurant = restaurant;
        this.items = items;
        this.orderFinished = orderFinished;
        this.biayaOngkosKirim = biayaOngkosKirim;
    }

    public Integer getBiayaOngkosKirim() {
        return biayaOngkosKirim;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public ArrayList<Menu> getItems() {
        return items;
    }

    public boolean isOrderFinished() {
        return orderFinished;
    }

    public void setOrderFinished(boolean orderFinished) {
        this.orderFinished = orderFinished;
    }
}
=======
public class Order {

    private String orderId;
    private String tanggal;
    private int ongkir;
    private Restaurant restaurant;
    private boolean orderFinished;
    private Menu[] items;

    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, Menu[] items) {
        this.orderId = orderId;
        this.tanggal = tanggal;
        this.ongkir = ongkir;
        this.restaurant = resto;
        this.orderFinished = false;
        this.items = items;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public boolean getOrderFinished() {
        return this.orderFinished;
    }

    public void setOrderFinished(boolean orderFinished) {
        this.orderFinished = orderFinished;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTanggal() {
        return tanggal;
    }

    public int getOngkir() {
        return ongkir;
    }

    public Menu[] getItems() {
        return items;
    }

    public Menu[] getSortedMenu() {
        Menu[] menuArr = new Menu[getItems().length];
        for (int i = 0; i < getItems().length; i++) {
            menuArr[i] = getItems()[i];
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
        return menuArr;
    }

    public double getTotalHarga() {
        double sum = 0;
        for (Menu menu : getItems()) {
            sum += menu.getHarga();
        }
        return sum += getOngkir();
    }
}
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
