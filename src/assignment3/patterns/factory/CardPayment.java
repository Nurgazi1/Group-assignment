package assignment3.patterns.factory;

public class CardPayment implements Payment {
    @Override
    public void pay(int orderId, double amount) {
        System.out.println("CARD payment for Order#" + orderId + ", amount=" + amount);
    }
}
//e