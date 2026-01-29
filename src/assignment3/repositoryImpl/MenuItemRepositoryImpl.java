package assignment3.repositoryImpl;

import assignment3.DatabaseConnection;
import assignment3.Entities.MenuItem;
import assignment3.repositories.MenuItemRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepositoryImpl implements MenuItemRepository {

    @Override
    public List<MenuItem> findAll() {
        List<MenuItem> menu = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM menu_items");

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
}