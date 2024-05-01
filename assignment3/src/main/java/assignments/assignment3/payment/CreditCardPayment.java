package assignments.assignment3.payment;

public class CreditCardPayment implements DepeFoodPaymentSystem {
    private static final double TRANSACTION_FEE_PERCENTAGE = 0.02;
    private long saldo;

    public CreditCardPayment(long saldo) {
        this.saldo = saldo;
    }

    public CreditCardPayment() {
        this.saldo = 0;
    }

    public long countTransactionFee(long amount) {
        return (long) (amount * TRANSACTION_FEE_PERCENTAGE);
    }

    @Override
    public long processPayment(long amount) {
        long transactionFee = countTransactionFee(amount);
        if (amount + transactionFee > this.saldo) {
            return -1;
        }
        this.saldo -= (amount + transactionFee);
        return transactionFee;
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