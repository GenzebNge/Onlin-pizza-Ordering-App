package com.pizzamc.mcpizza.repository;

import com.pizzamc.mcpizza.entity.Order;
import com.pizzamc.mcpizza.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository  extends CrudRepository<Order, Long> {

    List<Order> findByUser(User user);

}
