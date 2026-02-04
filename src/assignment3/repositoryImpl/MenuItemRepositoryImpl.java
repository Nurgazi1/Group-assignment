package assignment3.repositoryImpl;

import assignment3.database.db.IDatabase;
import assignment3.Entities.MenuItem;
import assignment3.repositories.MenuItemRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepositoryImpl implements MenuItemRepository {

    private final IDatabase db;

    public MenuItemRepositoryImpl(IDatabase db) {
        this.db = db;
    }

    @Override
    public void save(MenuItem entity) {
        throw new UnsupportedOperationException("Not needed now");
    }

    @Override
    public List<MenuItem> findAll() {
        List<MenuItem> menu = new ArrayList<>();

        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM menu_items";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                menu.add(new MenuItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getBoolean("available")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return menu;
    }

    @Override
    public MenuItem findById(int id) {
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM menu_items WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new MenuItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getBoolean("available")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
