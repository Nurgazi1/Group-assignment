package assignment3.repositories;

import assignment3.Entities.Customers;
import java.util.List;

public interface CustomerRepository {
    List<Customers> findAll();
    Customers findById(int id);

}

