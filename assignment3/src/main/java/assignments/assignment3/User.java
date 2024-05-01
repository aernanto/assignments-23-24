/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3;

import java.util.ArrayList;
import assignments.assignment3.payment.DepeFoodPaymentSystem;

// Definisi kelas User
public class User {
    private String nama;
    private String nomorTelepon;
    private String email;
    private String lokasi;
    public String role;
    private ArrayList<Order> orderHistory;
    private DepeFoodPaymentSystem payment;
    private long saldo;

    // Constructor
    public User(String nama, String nomorTelepon, String email, String lokasi, String role, DepeFoodPaymentSystem payment, long saldo) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
        this.orderHistory = new ArrayList<>();
        this.payment = payment;
        this.saldo = saldo;
    }

    // Getter nama
    public String getNama() {
        return nama;
    }

    // Getter nomor telepon
    public String getNomorTelepon() {
        return nomorTelepon;
    }

    // Getter email
    public String getEmail() {
        return email;
    }

    // Getter lokasi
    public String getLokasi() {
        return lokasi;
    }

    // Getter payment
    public DepeFoodPaymentSystem getPayment() {
        return payment;
    }

    // Getter saldo
    public long getSaldo() {
        return saldo;
    }

    // Add order history
    public void addOrderHistory(Order order) {
        orderHistory.add(order);
    }

    // Getter order history
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
}