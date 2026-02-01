package assignment3.repositories;

import assignment3.Entities.Orders;

import java.util.List;

public interface OrderRepository {
    void save(Orders order);
    void complete(int orderId);
    List<Orders> findAll();
}
