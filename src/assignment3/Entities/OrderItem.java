package assignment3.Entities;

public class OrderItem {
    private int id;
    private int orderId;
    private int menuItemId;
    private int quantity;

    public OrderItem(int id, int orderId, int menuItemId) {
        this.id = id;
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = this.quantity;
    }

    public int getId() { return id; }
    public int getOrderId() { return orderId; }
    public int getMenuItemId() { return menuItemId; }
    public int getQuantity() { return quantity; }
}
