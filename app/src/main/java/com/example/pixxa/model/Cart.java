package com.example.pixxa.model;

public class Cart {

    private int id;
    private String pizzaName;
    private int price;
    private int quantity;

    public Cart(int id, String pizzaName, int price, int quantity) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.price = price;
        this.quantity = quantity;
    }

    public Cart(String pizzaName, int price, int quantity) {
        this.pizzaName = pizzaName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}