package assignments.assignment3.payment;

<<<<<<< HEAD
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
=======
public class DebitPayment implements DepeFoodPaymentSystem {
    private static final double MINIMUM_PAYMENT = 50000;

    @Override
    public long processPayment(long saldo, long amount) throws Exception {
        if (amount < MINIMUM_PAYMENT) {
            throw new Exception("Jumlah pesanan < 50000 mohon menggunakan metode pembayaran yang lain");
        }

        if (saldo < amount) {
            throw new Exception("Saldo tidak mencukupi mohon menggunakan metode pembayaran yang lain");
        }

        return amount;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }
}