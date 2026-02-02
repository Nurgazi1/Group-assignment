package assignment3;

import assignment3.Entities.Customers;
import assignment3.Entities.MenuItem;
import assignment3.Entities.Orders;
import assignment3.database.db.IDatabase;
import assignment3.database.db.PostgresDatabase;
import assignment3.repositories.CustomerRepository;
import assignment3.repositories.OrderRepository;
import assignment3.repositories.MenuItemRepository;
import assignment3.repositoryImpl.CustomerRepositoryImpl;
import assignment3.repositoryImpl.MenuItemRepositoryImpl;
import assignment3.repositoryImpl.OrderRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class FoodSystem {
    Scanner scan =new Scanner(System.in);

    private final MenuItemRepository menuRepo =
            new MenuItemRepositoryImpl();

    OrderRepository orderRepo =
            new OrderRepositoryImpl(new PostgresDatabase());

    CustomerRepository customerRepo =
            new CustomerRepositoryImpl(new PostgresDatabase());



    public void run() {
        while (true) {
            System.out.println("1. Print all Customers");
            System.out.println("2. Print all menu items");
            System.out.println("3. Print all order items");
            System.out.println("4. Print all orders");
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
                case 0:
                    Exit();
                    break;
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
            return;
        }

        for (Orders o : orders) {
            System.out.println(
                    o.getId() + " | customer=" +
                            o.getCustomerId() + " | status=" +
                            o.getStatus()
            );
        }
    }

    private void PrintAllOrderItems() {
    }

    private void PrintAllMenuItems() {
        List<MenuItem> menu = menuRepo.findAll();

        if (menu.isEmpty()) {
            System.out.println("No Orders.");
        }
        else {
            System.out.println("\n=== MENU FROM DATABASE ===");
            for (MenuItem item : menu) {
                System.out.println(
                        item.getId() + " | " +
                                item.getName() + " | " +
                                item.getPrice() + " | available: " +
                                item.isAvailable()
                );
            }
        }
    }

    private void PrintAllCustomers() {
        List<Customers> customers = customerRepo.findAll();

        if (customers.isEmpty()) {
            System.out.println("No customers.");
        } else {
            System.out.println("\n=== CUSTOMERS ===");
            for (Customers c : customers) {
                System.out.println(
                        c.getId() + " | " + c.getName()
                );
            }
        }
        System.out.println();
    }




    public static void main(String[] args) {
        new FoodSystem().run();
    }
}
