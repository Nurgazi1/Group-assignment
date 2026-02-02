package assignment3.core;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    void save(T entity);
}

//e