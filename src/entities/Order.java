package entities;

public class Order {
    private int id;
    private int customerId;
    private String status;

    public Order(int id, int customerId, String status) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
    }

    public int getId() { return id; }
}
