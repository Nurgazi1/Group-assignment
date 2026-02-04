package assignment3;

import assignment3.Entities.Customers;
import assignment3.Entities.MenuItem;
import assignment3.Entities.Orders;
import assignment3.patterns.singleton.DatabaseSingleton;
import assignment3.database.db.PostgresDatabase;
import assignment3.patterns.factory.Payment;
import assignment3.patterns.factory.PaymentFactory;
import assignment3.repositories.CustomerRepository;
import assignment3.repositories.MenuItemRepository;
import assignment3.repositories.OrderItemRepository;
import assignment3.repositories.OrderRepository;
import assignment3.repositoryImpl.CustomerRepositoryImpl;
import assignment3.repositoryImpl.MenuItemRepositoryImpl;
import assignment3.repositoryImpl.OrderItemRepositoryimpl;
import assignment3.repositoryImpl.OrderRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class FoodSystem {

    private final Scanner scan = new Scanner(System.in);

    private final PostgresDatabase db = DatabaseSingleton.getInstance();

    private final MenuItemRepository menuRepo = new MenuItemRepositoryImpl(db);
    private final OrderRepository orderRepo = new OrderRepositoryImpl(db);
    private final CustomerRepository customerRepo = new CustomerRepositoryImpl(db);
    private final OrderItemRepository orderItemRepo = new OrderItemRepositoryimpl(db);

    public void run() {
        while (true) {
            System.out.println("\n1. Print all Customers");
            System.out.println("2. Print all menu items (AVAILABLE ONLY) [LAMBDA]");
            System.out.println("3. Print all order items");
            System.out.println("4. Print all orders");
            System.out.println("5. Pay order (Factory)");
            System.out.println("0. Exit");

            int choice = scan.nextInt();

            switch (choice) {
                case 1 -> printAllCustomers();
                case 2 -> printAllMenuItems();
                case 3 -> printAllOrderItems();
                case 4 -> printAllOrders();
                case 5 -> payOrder();
                case 0 -> exit();
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void exit() {
        System.exit(0);
    }

    private void printAllOrders() {
        List<Orders> orders = orderRepo.findAll();

        if (orders.isEmpty()) {
            System.out.println("No orders.");
            return;
        }

        System.out.println("\n=== ORDERS ===");
        for (Orders o : orders) {
            System.out.println(
                    o.getId() + " | customer=" + o.getCustomerId() + " | status=" + o.getStatus()
            );
        }
    }

    private void printAllOrderItems() {
        var items = orderItemRepo.findAll();

        if (items.isEmpty()) {
            System.out.println("No order items.");
            return;
        }

        System.out.println("\n=== ORDER ITEMS ===");
        items.forEach(item ->
                System.out.println(
                        "Order #" + item.getOrderId() +
                                " | MenuItem #" + item.getMenuItemId() +
                                " | Qty: " + item.getQuantity()
                )
        );
    }

    private void printAllMenuItems() {
        List<MenuItem> menu = menuRepo.findAll();

        if (menu.isEmpty()) {
            System.out.println("No menu items.");
            return;
        }

        System.out.println("\n=== AVAILABLE MENU ITEMS ===");

        menu.stream()
                .filter(MenuItem::isAvailable)
                .forEach(item -> // lambdas
                        System.out.println(
                                item.getId() + " | " +
                                        item.getName() + " | " +
                                        item.getPrice()
                        )
                );
    }

    private void printAllCustomers() {
        List<Customers> customers = customerRepo.findAll();

        if (customers.isEmpty()) {
            System.out.println("No customers.");
            return;
        }

        System.out.println("\n=== CUSTOMERS ===");
        for (Customers c : customers) {
            System.out.println(c.getId() + " | " + c.getName());
        }
    }

    private void payOrder() {
        Payment payment = PaymentFactory.create("CARD");
        payment.pay(1, 2500); // factory
    }

    public static void main(String[] args) {
        new FoodSystem().run();
    }
    Orders order = new Orders.Builder()
            .id(1)
            .customerId(1)
            .status("NEW")
            .build();
}
// builder