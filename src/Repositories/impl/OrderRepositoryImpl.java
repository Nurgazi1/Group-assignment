package Repositories.impl;

import Repositories.OrderRepository;

public class OrderRepositoryImpl implements OrderRepository {
    private final IDatabase db;

    public OrderRepositoryImpl(IDatabase db) {
        this.db = db;
    }
}
