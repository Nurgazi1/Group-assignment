package assignment3.repositoryImpl;

import assignment3.Entities.OrderItem;
import assignment3.database.db.IDatabase;
import assignment3.repositories.OrderItemRepository;

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
    public void save(int orderId, int menuItemId, int quantity) {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO order_items(order_id, menu_item_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, menuItemId);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//eе