/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment2;

import java.util.ArrayList;

// Class User, memuat nama, nomorTelepon, email, role, ordeerHistory dalam ArrayList, dan lokasi
public class User {
    private String nama;
    private String nomorTelepon;
    private String email;
    private String role;
    private ArrayList<Order> orderHistory;
    private String lokasi;

    public User(String nama, String nomorTelepon, String email, String lokasi, String role) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
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

    public String getEmail() {
        return email;
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