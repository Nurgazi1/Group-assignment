package assignment3.patterns.factory;

public class CashPayment implements Payment {
    @Override
    public void pay(int orderId, double amount) {
        System.out.println("CASH payment for Order#" + orderId + ", amount=" + amount);
    }
}

