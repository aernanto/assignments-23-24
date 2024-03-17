package assignments.assignment2;

import java.util.ArrayList;

public class User {
    private String nama;
    private String nomorTelepon;
    private String role;
    private ArrayList<Order> orderHistory;
    private String lokasi;

    public User(String nama, String nomorTelepon, String role, String lokasi) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.role = role;
        this.orderHistory = new ArrayList<>();
        this.lokasi = lokasi;
    }

    public String getNama() {
        return nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public String getRole() {
        return role;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public String getLokasi() {
        return lokasi;
    }
}