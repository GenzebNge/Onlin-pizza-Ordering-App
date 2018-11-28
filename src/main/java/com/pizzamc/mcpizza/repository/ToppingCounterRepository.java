package com.pizzamc.mcpizza.repository;

import com.pizzamc.mcpizza.entity.ToppingCounter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ToppingCounterRepository extends CrudRepository<ToppingCounter, Long> {
    ToppingCounter findByName(String string);
    List<ToppingCounter> findTop3ByOrderByCountDesc();



}
