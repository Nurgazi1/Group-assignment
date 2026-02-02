package assignment3.service;

public class PaymentService {
    public void pay(int orderId, double amount) {
        System.out.println("Payment successful for Order#" + orderId + ", amount=" + amount);
    }
}
//ee