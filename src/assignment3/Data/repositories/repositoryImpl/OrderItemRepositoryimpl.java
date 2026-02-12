package assignment3.Data.repositories.repositoryImpl;

import assignment3.Domain.Entities.OrderItem;
import assignment3.Data.repositories.db.IDatabase;
import assignment3.Data.repositories.OrderItemRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepositoryimpl implements OrderItemRepository {
    private final IDatabase db;

    public OrderItemRepositoryimpl(IDatabase db) {
        this.db = db;
    }

    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> items = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM order_items";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("menu_item_id"),
                        rs.getInt("quantity")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void save(OrderItem item) {
        try (Connection con = db.getConnection()) {
            String sql = """
                INSERT INTO order_items(order_id, menu_item_id, quantity)
                VALUES (?, ?, ?)
            """;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getMenuItemId());
            ps.setInt(3, item.getQuantity());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calculateTotalByOrderId(int orderId) {
        double total = 0;

        try (Connection con = db.getConnection()) {
            String sql = """
            SELECT oi.quantity, m.price
            FROM order_items oi
            JOIN menu_items m ON oi.menu_item_id = m.id
            WHERE oi.order_id = ?
        """;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total += rs.getInt("quantity") * rs.getDouble("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

}
