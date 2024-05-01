/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3;

import java.util.ArrayList;

import assignments.assignment3.payment.DepeFoodPaymentSystem;
// Kelas User
public class User {
    // Instansi kelas User
    private String nama;
    private String nomorTelepon;
    private String email;
    private String lokasi;
    public String role;
    private ArrayList<Order> orderHistory;
    private DepeFoodPaymentSystem payment;
    private long saldo;

    // Konstruktor User
    public User(String nama, String nomorTelepon, String email, String lokasi, String role, DepeFoodPaymentSystem payment, long saldo) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
        this.orderHistory = new ArrayList<>();
        this.saldo = 0;
    }

    // Getter untuk nama
    public String getNama() {
        return nama;
    }

    // Getter untuk nomorTelepon
    public String getNomorTelepon() {
        return nomorTelepon;
    }

    // Getter untuk email
    public String getEmail() {
        return email;
    }

    // Getter untuk lokasi
    public String getLokasi() {
        return lokasi;
    }

    public String getRole() {
        return role;
    }

    // Method untuk menambah order ke orderHistory
    public void addOrderHistory(Order order) {
        orderHistory.add(order);
    }

    // Getter untuk orderHistory
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public boolean isOrderBelongsToUser(String orderId) {
        for (Order order : orderHistory) {
            if (order.getOrderID().equals(orderId)) {
                return true;
            }
        }
        return false;
    }

    public DepeFoodPaymentSystem getPayment() {
        return payment;
    }

    public void setPayment(DepeFoodPaymentSystem payment) {
        this.payment = payment;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return String.format("User dengan nama %s dan nomor telepon %s", nama, nomorTelepon);
    }
}