package com.pizzamc.mcpizza.repository;

import com.pizzamc.mcpizza.entity.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {
}
