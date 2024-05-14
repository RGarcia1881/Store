package com.store;

public class Item {

    private String name;
    private double price;
    private int stock;
    private String description;

    public Item(String name, double price, int stock, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return stock;
    }

    public void setCount(int count) {
        this.stock = count;
    }

    public String getDesciption() {
        return description;
    }

    @Override
    public String toString() {
        return name + " - $" + price + " x " + stock + description;
    }

}
