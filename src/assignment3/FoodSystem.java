package assignment3;

import assignment3.Entities.Customers;
import assignment3.Entities.MenuItem;
import assignment3.Entities.Orders;
import assignment3.database.db.PostgresDatabase;
import assignment3.patterns.factory.Payment;
import assignment3.patterns.factory.PaymentFactory;
import assignment3.repositories.CustomerRepository;
import assignment3.repositories.MenuItemRepository;
import assignment3.repositories.OrderItemRepository;
import assignment3.repositories.OrderRepository;
import assignment3.repositoryImpl.CustomerRepositoryImpl;
import assignment3.repositoryImpl.MenuItemRepositoryImpl;
import assignment3.repositoryImpl.OrderRepositoryImpl;

import assignment3.repositoryImpl.OrderItemRepositoryimpl;

import java.util.List;
import java.util.Scanner;

public class FoodSystem {

    private final Scanner scan = new Scanner(System.in);

    private final PostgresDatabase db = new PostgresDatabase();

    private final MenuItemRepository menuRepo = new MenuItemRepositoryImpl(db);
    private final OrderRepository orderRepo = new OrderRepositoryImpl(db);
    private final CustomerRepository customerRepo = new CustomerRepositoryImpl(db);
    private final OrderItemRepository orderItemRepo = new OrderItemRepositoryimpl(db);

    public void run() {
        while (true) {
            System.out.println("1. Print all Customers");
            System.out.println("2. Print all menu items (AVAILABLE ONLY) [LAMBDA]");
            System.out.println("3. Print all order items");
            System.out.println("4. Print all orders");
            System.out.println("5. Pay order (Factory)");
            System.out.println("0. Exit");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    PrintAllCustomers();
                    break;
                case 2:
                    PrintAllMenuItems();
                    break;
                case 3:
                    PrintAllOrderItems();
                    break;
                case 4:
                    PrintAllOrders();
                    break;
                case 5:
                    PayOrder();
                    break;
                case 0:
                    Exit();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void Exit() {
        System.exit(0);
    }

    private void PrintAllOrders() {
        List<Orders> orders = orderRepo.findAll();
        if (orders.isEmpty()) {
            System.out.println("No Orders.");
            System.out.println();
            return;
        }

        System.out.println("\n=== ORDERS ===");
        for (Orders o : orders) {
            System.out.println(o.getId() + " | customer=" + o.getCustomerId() + " | status=" + o.getStatus());
        }
        System.out.println();
    }

    private void PrintAllOrderItems() {
        var items = orderItemRepo.findAll();
        if (items.isEmpty()) {
            System.out.println("No order items.");
            System.out.println();
            return;
        }

        System.out.println("\n=== ORDER ITEMS ===");
        items.forEach(item -> System.out.println(
                "Order #" + item.getOrderId() +
                        " | MenuItem #" + item.getMenuItemId() +
                        " | Qty: " + item.getQuantity()
        ));
        System.out.println();
    }

    private void PrintAllMenuItems() {
        List<MenuItem> menu = menuRepo.findAll();
        if (menu.isEmpty()) {
            System.out.println("No menu items.");
            System.out.println();
            return;
        }

        System.out.println("\n=== AVAILABLE MENU ITEMS ===");

        menu.stream()
                .filter(MenuItem::isAvailable)
                .forEach(item -> System.out.println(
                        item.getId() + " | " + item.getName() + " | " + item.getPrice()
                ));

        System.out.println();
    }

    private void PrintAllCustomers() {
        List<Customers> customers = customerRepo.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers.");
            System.out.println();
            return;
        }

        System.out.println("\n=== CUSTOMERS ===");
        for (Customers c : customers) {
            System.out.println(c.getId() + " | " + c.getName());
        }
        System.out.println();
    }

    private void PayOrder() {
        Payment payment = PaymentFactory.create("CARD");
        payment.pay(1, 2500);
        System.out.println();
    }

    public static void main(String[] args) {
        new FoodSystem().run();
    }
}
