package assignment3.UI;

import assignment3.Domain.Entities.*;
import assignment3.Data.repositories.db.PostgresDatabase;
import assignment3.exception.InvalidQuantityException;
import assignment3.exception.MenuItemNotAvailableException;
import assignment3.exception.NotFoundException;
import assignment3.exception.OrderNotFoundException;
import assignment3.patterns.factory.Payment;
import assignment3.patterns.factory.PaymentFactory;
import assignment3.patterns.singleton.DatabaseSingleton;
import assignment3.Data.repositories.*;
import assignment3.Data.repositories.repositoryImpl.*;

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
            System.out.println("""
                1. Print customers
                2. Print menu
                3. Print order items
                4. Print orders
                5. Create customer
                6. Create order
                7. Pay order
                0. Exit
            """);

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    printAllCustomers();
                    break;
                case 2:
                    printAllMenuItems();
                    break;
                case 3:
                    printAllOrderItems();
                    break;
                case 4:
                    printAllOrders();
                    break;
                case 5:
                    createCustomer();
                    break;
                case 6:
                    createOrder();
                    break;
                case 7:
                    payOrder();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private void payOrder() {
        try {
            System.out.print("Enter order id: ");
            int orderId = scan.nextInt();

            Orders order = orderRepo.findById(orderId);
            if (order == null) {
                throw new OrderNotFoundException("Order not found");
            }

            double total = orderItemRepo.calculateTotalByOrderId(orderId);
            if (total <= 0) {
                throw new RuntimeException("Order has no items");
            }

            System.out.print("Payment type (CARD / CASH): ");
            scan.nextLine();
            String type = scan.nextLine();

            Payment payment = PaymentFactory.create(type);
            payment.pay(orderId, total);

            orderRepo.complete(orderId);
            System.out.println("Order paid successfully!");

        } catch (Exception e) {
            System.out.println("Payment error: " + e.getMessage());
        }
    }


    private void createCustomer() {
        scan.nextLine();
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        customerRepo.save(new Customers(name));
        System.out.println("Customer created");
    }

    private void createOrder() {
        try {
            System.out.print("Customer id: ");
            int customerId = scan.nextInt();
            if (customerRepo.findById(customerId) == null)
                throw new NotFoundException("Customer not found");

            Orders order = new Orders(customerId, "NEW");
            int orderId = orderRepo.saveAndReturnId(order);

            System.out.print("Menu item id: ");
            int menuItemId = scan.nextInt();
            MenuItem item = menuRepo.findById(menuItemId);

            if (item == null)
                throw new NotFoundException("Menu item not found");
            if (!item.isAvailable())
                throw new MenuItemNotAvailableException("Item not available");

            System.out.print("Quantity: ");
            int qty = scan.nextInt();
            if (qty <= 0)
                throw new InvalidQuantityException("Invalid quantity");

            orderItemRepo.save(new OrderItem(orderId, menuItemId, qty));
            System.out.println("Order created");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printAllCustomers() {
        customerRepo.findAll()
                .forEach(c -> System.out.println(c.getId() + " | " + c.getName()));
    }

    private void printAllMenuItems() {
        menuRepo.findAll().stream()
                .filter(MenuItem::isAvailable)
                .forEach(m -> System.out.println(m.getId() + " | " + m.getName()));
    }

    private void printAllOrders() {
        orderRepo.findAll()
                .forEach(o -> System.out.println(o.getId() + " | customer=" + o.getCustomerId()));
    }

    private void printAllOrderItems() {
        orderItemRepo.findAll()
                .forEach(i -> System.out.println(
                        "Order " + i.getOrderId() +
                                " | Item " + i.getMenuItemId() +
                                " | Qty " + i.getQuantity()));
    }

    public static void main(String[] args) {
        new FoodSystem().run();
    }
}
