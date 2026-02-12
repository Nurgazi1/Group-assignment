package assignment3.Data.repositories.core;

import java.util.List;

public interface Repository<T> {
    void save(T entity);
    List<T> findAll();
}
//e