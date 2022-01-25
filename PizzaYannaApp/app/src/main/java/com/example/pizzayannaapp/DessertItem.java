package com.example.pizzayannaapp;

import java.io.Serializable;

public class DessertItem implements Serializable {
    private String name;
    private double price;
    private String size;
    private int quantity;

    public DessertItem() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getSize() {
        return this.size;
    }

    public int getQuantity() {
        return this.quantity;
    }
}