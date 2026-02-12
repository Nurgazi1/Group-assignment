package assignment3.Data.repositories;

import assignment3.Domain.Entities.Orders;
import assignment3.Data.repositories.core.Repository;

import java.util.List;

public interface OrderRepository extends Repository<Orders> {
    void complete(int orderId);
    int saveAndReturnId(Orders order);
    Orders findById(int id);
    List<Orders> findAll();
}
