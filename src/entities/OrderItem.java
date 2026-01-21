package entities;

public class OrderItem {
    private int id;
    private int orderId;
    private int menuItemId;
    private int quantity;

    public OrderItem(int id, int orderId, int menuItemId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }
}
