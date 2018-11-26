package com.pizzamc.mcpizza.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dough {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String size;
    private String crust;
    private String bake;
    private String seasoning;
    private String cut;
    private String cheese;
    private boolean sauce;
    private String sauceType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getBake() {
        return bake;
    }

    public void setBake(String bake) {
        this.bake = bake;
    }

    public String getSeasoning() {
        return seasoning;
    }

    public void setSeasoning(String seasoning) {
        this.seasoning = seasoning;
    }

    public String getCut() {
        return cut;
    }

    public void setCut(String cut) {
        this.cut = cut;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public boolean isSauce() {
        return sauce;
    }

    public void setSauce(boolean sauce) {
        this.sauce = sauce;
    }

    public String getSauceType() {
        return sauceType;
    }

    public void setSauceType(String sauceType) {
        this.sauceType = sauceType;
    }

    @Override
    public String toString() {
        return "Dough{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", crust='" + crust + '\'' +
                ", bake='" + bake + '\'' +
                ", seasoning='" + seasoning + '\'' +
                ", cut='" + cut + '\'' +
                ", cheese='" + cheese + '\'' +
                ", sauce=" + sauce +
                ", sauceType='" + sauceType + '\'' +
                '}';
    }
}

