package com.store;

import java.util.ArrayList;

public class Cart {

    private ArrayList<Item> items = new ArrayList<>();

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice() * item.getCount();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Total: $").append(getTotal());
        return sb.toString();
    }

}
