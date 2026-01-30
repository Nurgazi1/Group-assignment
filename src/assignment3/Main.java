package assignment3;

import assignment3.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import assignment3.database.db.PostgresDatabase;
import assignment3.Entities.Orders;
import assignment3.repositories.OrderRepository;
import assignment3.repositoryImpl.OrderRepositoryImpl;
import assignment3.service.OrderService;

public class Main {

    public static void main(String[] args) {

        System.out.println("Connecting to Supabase...");

        try (Connection connection = DatabaseConnection.getConnection()) {

            System.out.println("Connected successfully!");

            String sql = "SELECT CURRENT_TIMESTAMP";

            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    System.out.println("Database time: " + rs.getTimestamp(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while connecting to database:");
            e.printStackTrace();

        }



        OrderRepository repo = new OrderRepositoryImpl(new PostgresDatabase());
        OrderService service = new OrderService(repo);

        Orders order = new Orders(1, 1, "NEW");
        service.placeOrder(order);
        service.completeOrder(1);

        System.out.println("Order created and completed successfully");
    }
}
