package com.pizzamc.mcpizza.entity;

import javax.persistence.*;

@Entity
public class Topping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String amount;
    @ManyToOne
    private Pizza pizza;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Topping{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", pizza=" + pizza +
                '}';
    }
}
