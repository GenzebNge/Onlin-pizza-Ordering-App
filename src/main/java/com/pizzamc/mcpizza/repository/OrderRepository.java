package com.pizzamc.mcpizza.repository;

import com.pizzamc.mcpizza.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository  extends CrudRepository<Order, Long> {

}
