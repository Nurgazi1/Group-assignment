package assignment3.service;

import assignment3.Entities.Orders;
import assignment3.repositories.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void placeOrder(Orders order) {
        orderRepository.save(order);
    }

    public void completeOrder(int id) {
        orderRepository.complete(id);
    }
}
