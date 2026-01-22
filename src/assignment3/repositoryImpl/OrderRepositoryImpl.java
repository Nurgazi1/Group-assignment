package assignment3.repositoryImpl;

import assignment3.database.db.IDatabase;
import assignment3.Entities.Orders;
import assignment3.repositories.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
            ps.setInt(1, order.getId());
            ps.setString(2, "NEW");
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void complete(int orderId) {
        try (Connection con = db.getConnection()) {
            String sql = "UPDATE orders SET status='COMPLETED' WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
