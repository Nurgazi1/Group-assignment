package assignment3.repositories;

import assignment3.Entities.MenuItem;
import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findAll();
}
