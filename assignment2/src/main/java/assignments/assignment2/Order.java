package assignments.assignment2;

import java.util.ArrayList;

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

    // Getter method for biayaOngkosKirim
    public Integer getBiayaOngkosKirim() {
        return biayaOngkosKirim;
    }

    // Other getter and setter methods
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