package assignment3.Entities;

public class Orders {
    private int id;
    private int customerId;
    private String status;

    public Orders(int id, int customerId, String status) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getCustomerId() {
        return customerId;
    }
}
