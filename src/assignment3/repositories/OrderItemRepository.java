package assignment3.repositories;

import assignment3.Entities.OrderItem;
import java.util.List;

public interface OrderItemRepository {
    List<OrderItem> findAll();
    void save(OrderItem orderItem);
    double calculateTotalByOrderId(int orderId);
}
