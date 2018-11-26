package com.pizzamc.mcpizza.repository;

import com.pizzamc.mcpizza.entity.MenuItem;
import org.springframework.data.repository.CrudRepository;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
}
