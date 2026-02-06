package assignment3.repositories;

import assignment3.Entities.Orders;
import assignment3.core.Repository;

import java.util.List;

public interface OrderRepository extends Repository<Orders> {
    void complete(int orderId);
    int saveAndReturnId(Orders order);
    Orders findById(int id);
    List<Orders> findAll();
}
