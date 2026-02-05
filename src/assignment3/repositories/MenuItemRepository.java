package assignment3.repositories;

import assignment3.Entities.MenuItem;
import assignment3.core.Repository;

public interface MenuItemRepository extends Repository<MenuItem> {
    MenuItem findById(int id);
}
