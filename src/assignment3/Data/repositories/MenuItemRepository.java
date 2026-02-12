package assignment3.Data.repositories;

import assignment3.Domain.Entities.MenuItem;
import assignment3.Data.repositories.core.Repository;

public interface MenuItemRepository extends Repository<MenuItem> {
    MenuItem findById(int id);
}
