package com.pizzamc.mcpizza.repository;

import com.pizzamc.mcpizza.entity.Topping;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ToppingRepository extends CrudRepository<Topping, Long> {


}
