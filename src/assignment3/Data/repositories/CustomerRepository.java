package assignment3.Data.repositories;

import assignment3.Domain.Entities.Customers;
import java.util.List;

public interface CustomerRepository {
    List<Customers> findAll();
    Customers findById(int id);
    void save(Customers customer);
}
