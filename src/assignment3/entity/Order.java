package assignment3.entity;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int customerId;
    private String status;
    private LocalDateTime createdAt;

    public Order(int id, int customerId, String status) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
    }
    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public String getStatus() { return status; }
}
