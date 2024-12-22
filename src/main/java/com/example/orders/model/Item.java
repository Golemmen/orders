package com.example.orders.model;

public class Item {

    private String name;
    private double price;

    // Конструкторы
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
