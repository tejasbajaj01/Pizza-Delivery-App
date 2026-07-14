package com.example.pixxa.model;

public class Pizza {

    int image;
    String name;
    int price;

    public Pizza(int image, String name, int price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}