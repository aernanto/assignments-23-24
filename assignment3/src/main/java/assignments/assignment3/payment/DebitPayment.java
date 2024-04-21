package assignments.assignment3.payment;

public class DebitPayment implements DepeFoodPaymentSystem {
    private static final double MINIMUM_TOTAL_PRICE = 50000;

    private long saldo;

    public DebitPayment(long saldo) {
        this.saldo = saldo;
    }

    @Override
    public long processPayment(long amount) {
        if (amount < MINIMUM_TOTAL_PRICE) {
            return -1; // Error: Total harga pesanan kurang dari minimum
        }
        if (amount > saldo) {
            return -2; // Error: Saldo tidak mencukupi
        }
        this.saldo -= amount;
        return 0; // Berhasil
    }
}