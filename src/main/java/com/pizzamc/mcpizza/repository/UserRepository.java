package com.pizzamc.mcpizza.repository;

import com.pizzamc.mcpizza.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
