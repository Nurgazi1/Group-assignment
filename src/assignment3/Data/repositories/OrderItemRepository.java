package assignment3.Data.repositories;

import assignment3.Domain.Entities.OrderItem;
import java.util.List;

public interface OrderItemRepository {
    List<OrderItem> findAll();
    void save(OrderItem orderItem);
    double calculateTotalByOrderId(int orderId);
}
