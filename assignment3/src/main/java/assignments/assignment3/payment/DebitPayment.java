package assignments.assignment3.payment;

// Implement DepeFoodPaymentSystem
public class DebitPayment implements DepeFoodPaymentSystem {
    private static final double MINIMUM_TOTAL_PRICE = 50000;
    private long saldo;

    // Constructor debit payment
    public DebitPayment(long saldo) {
        this.saldo = saldo;
    }

    public DebitPayment() {
        this.saldo = 0;
    }

    @Override
    // Process Payment
    public long processPayment(long amount) {
        if (amount < MINIMUM_TOTAL_PRICE) {
            return -1; // jika kurang dari harga minimum
        }
        if (amount > saldo) {
            return -2; // jika melebihi saldo
        }
        this.saldo -= amount;
        return 0;
    }

    public void addFunds(long amount) {
        if (amount > 0) {
            this.saldo += amount;
        }
    }

    // Getter saldo
    public long getSaldo() {
        return saldo;
    }

    // Setter saldo
    public void setSaldo(long saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        }
    }
}