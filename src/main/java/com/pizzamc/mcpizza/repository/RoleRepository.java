package com.pizzamc.mcpizza.repository;

import com.pizzamc.mcpizza.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}
