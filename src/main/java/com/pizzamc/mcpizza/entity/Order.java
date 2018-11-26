package com.pizzamc.mcpizza.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private User user;
    @OneToOne
    private Pizza pizza;
    private int creditCardNumber;
    private String phoneNumber;
    private String creditCardName;

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", pizza=" + pizza +
                ", creditCardNumber=" + creditCardNumber +
                ", phoneNumber=" + phoneNumber +
                ", creditCardName='" + creditCardName + '\'' +
                '}';
    }
}

