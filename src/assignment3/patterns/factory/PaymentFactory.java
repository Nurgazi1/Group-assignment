package assignment3.patterns.factory;

public class PaymentFactory {
    public static Payment create(String type) {
        return switch (type.toUpperCase()) {
            case "CASH" -> new CashPayment();
            case "CARD" -> new CardPayment();
            default -> throw new IllegalArgumentException("Unknown payment type: " + type);
        };
    }
}
//e