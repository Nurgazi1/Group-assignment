package assignment3.repositoryImpl;

import assignment3.Entities.Customers;
import assignment3.database.db.IDatabase;
import assignment3.repositories.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    private final IDatabase db;

    public CustomerRepositoryImpl(IDatabase db) {
        this.db = db;
    }

    // ✅ ОБЯЗАТЕЛЬНО (используется в FoodSystem)
    @Override
    public List<Customers> findAll() {
        List<Customers> customers = new ArrayList<>();

        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customers.add(
                        new Customers(
                                rs.getInt("id"),
                                rs.getString("name")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public Customers findById(int id) {
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM customers WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Customers(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
