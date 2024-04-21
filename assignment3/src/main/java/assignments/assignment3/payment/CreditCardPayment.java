package assignments.assignment3.payment;

public class CreditCardPayment implements DepeFoodPaymentSystem {
    private static final double TRANSACTION_FEE_PERCENTAGE = 0.02;

    private long saldo;

    public CreditCardPayment(long saldo) {
        this.saldo = saldo;
    }

    public long countTransactionFee(long amount) {
        return (long) (amount * TRANSACTION_FEE_PERCENTAGE);
    }

    @Override
    public long processPayment(long amount) {
        long transactionFee = countTransactionFee(amount);
        this.saldo -= (amount + transactionFee);
        return transactionFee;
    }
}