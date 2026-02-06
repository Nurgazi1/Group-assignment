package assignment3.repositoryImpl;

import assignment3.database.db.IDatabase;
import assignment3.Entities.Orders;
import assignment3.repositories.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private final IDatabase db;

    public OrderRepositoryImpl(IDatabase db) {
        this.db = db;
    }

    @Override
    public void save(Orders order) {
        try (Connection con = db.getConnection()) {

            String sql = "INSERT INTO orders(customer_id, status) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, order.getCustomerId());
            ps.setString(2, order.getStatus());

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void complete(int orderId) {
        try (Connection con = db.getConnection()) {

            String sql = "UPDATE orders SET status = 'COMPLETED' WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int saveAndReturnId(Orders order) {
        try (Connection con = db.getConnection()) {
            String sql = """
            INSERT INTO orders(customer_id, status)
            VALUES (?, ?)
            RETURNING id
        """;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order.getCustomerId());
            ps.setString(2, order.getStatus());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Failed to create order");
    }

    @Override
    public Orders findById(int id) {
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM orders WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Orders(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Orders> findAll() {
        List<Orders> orders = new ArrayList<>();

        try (Connection con = db.getConnection()) {

            String sql = "SELECT * FROM orders";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(
                        new Orders(
                                rs.getInt("id"),
                                rs.getInt("customer_id"),
                                rs.getString("status")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }
}
