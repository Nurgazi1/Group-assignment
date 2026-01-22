package assignment3.repositories;

import assignment3.Entities.Orders;

public interface OrderRepository {
    void save(Orders order);
    void complete(int orderId);

}
