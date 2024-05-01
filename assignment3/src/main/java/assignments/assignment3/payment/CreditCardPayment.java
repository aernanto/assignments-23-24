package assignments.assignment3.payment;

// Definisi credit card payment
public class CreditCardPayment implements DepeFoodPaymentSystem {
    private static final double TRANSACTION_FEE_PERCENTAGE = 0.02;
    private long saldo;

    // Constructor credit card payment
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
    // Process payment
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