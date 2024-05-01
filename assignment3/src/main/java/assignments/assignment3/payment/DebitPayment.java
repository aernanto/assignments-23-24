package assignments.assignment3.payment;

public class DebitPayment implements DepeFoodPaymentSystem {
    private static final double MINIMUM_TOTAL_PRICE = 50000;
    private long saldo;

    public DebitPayment(long saldo) {
        this.saldo = saldo;
    }

    public DebitPayment() {
        this.saldo = 0;
    }

    @Override
    public long processPayment(long amount) {
        if (amount < MINIMUM_TOTAL_PRICE) {
            return -1;
        }
        if (amount > saldo) {
            return -2;
        }
        this.saldo -= amount;
        return 0;
    }

    public void addFunds(long amount) {
        if (amount > 0) {
            this.saldo += amount;
        }
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        }
    }
}