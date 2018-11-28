package com.pizzamc.mcpizza.entity;

import com.pizzamc.mcpizza.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Dough dough;
    @OneToMany
    private List<Topping> toppings;
    private double price;
    private String name;

    public Pizza(){
        this.dough = new Dough();
        this.toppings = new ArrayList<Topping>();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Dough getDough() {
        return dough;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public void addTopping(Topping topping){
        this.toppings.add(topping);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", dough=" + dough +
                ", toppings=" + toppings +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
