package com.example.foodd.network;

public class FoodAPIEntity {
    private int id;
    private String name;
    private double price;

    public FoodAPIEntity(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public FoodAPIEntity() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}