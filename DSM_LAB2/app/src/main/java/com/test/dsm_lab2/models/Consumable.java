package com.test.dsm_lab2.models;

public class Consumable {
    private final String id;
    private final String name;
    private final double price;
    private final String image;

    public Consumable(String id, String name, double price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
