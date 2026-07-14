package com.example.pixxa.model;

public class Order {

    int id;
    String pizzaName;
    int price;
    int quantity;
    String address;
    String status;
    String time;

    public Order(int id, String pizzaName, int price, int quantity, String address, String status, String time) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.status = status;
        this.time = time;
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

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }
}