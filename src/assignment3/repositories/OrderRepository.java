package assignment3.repositories;

import assignment3.Entities.Orders;
import assignment3.core.Repository;

public interface OrderRepository extends Repository<Orders> {
    void complete(int orderId);
}
