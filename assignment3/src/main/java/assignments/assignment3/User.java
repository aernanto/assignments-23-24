<<<<<<< HEAD
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
=======
package assignments.assignment3;

import java.util.ArrayList;
import java.util.List;

import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class User {

    private String nama;
    private String nomorTelepon;
    private String email;
    public final String role;
    private String lokasi;

    private DepeFoodPaymentSystem paymentSystem;
    private long saldo;
    private ArrayList<Order> orderHistory;

    public User(String nama, String nomorTelepon, String email, String lokasi, String role,
            DepeFoodPaymentSystem paymentSystem, long saldo) {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
<<<<<<< HEAD
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
=======
        this.paymentSystem = paymentSystem;
        this.saldo = saldo;
        orderHistory = new ArrayList<>();
    }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public String getEmail() {
        return email;
    }

<<<<<<< HEAD
    // Getter lokasi
=======
    public String getNama() {
        return nama;
    }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public String getLokasi() {
        return lokasi;
    }

<<<<<<< HEAD
    // Getter payment
    public DepeFoodPaymentSystem getPayment() {
        return payment;
    }

    // Getter saldo
=======
    public String getNomorTelepon() {
        return nomorTelepon;
    }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public long getSaldo() {
        return saldo;
    }

<<<<<<< HEAD
    // Add order history
=======
    public String getRole() {
        return role;
    }

    public DepeFoodPaymentSystem getPaymentSystem() {
        return paymentSystem;
    }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public void addOrderHistory(Order order) {
        orderHistory.add(order);
    }

<<<<<<< HEAD
    // Getter order history
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
}
=======
    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public boolean isOrderBelongsToUser(String orderId) {
        for (Order order : orderHistory) {
            if (order.getOrderId().equals(orderId)) {
                return true;
            }
        }
        return false;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return String.format("User dengan nama %s dan nomor telepon %s", nama, nomorTelepon);
    }

}
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
