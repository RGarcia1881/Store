package com.store;

public class Item {

    // Atributos de la clase Item
    private String name; // Nombre del artículo
    private double price; // Precio del artículo
    private int stock; // Cantidad en stock del artículo
    private String description; // Descripción del artículo

    // Constructor de la clase Item
    public Item(String name, double price, int stock, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    // Método para obtener el nombre del artículo
    public String getName() {
        return name;
    }

    // Método para obtener el precio del artículo
    public double getPrice() {
        return price;
    }

    // Método para obtener la cantidad en stock del artículo
    public int getCount() {
        return stock;
    }

    // Método para establecer la cantidad en stock del artículo
    public void setCount(int count) {
        this.stock = count;
    }

    // Método para obtener la descripción del artículo
    public String getDescription() {
        return description;
    }

    // Método toString para devolver una representación de cadena del objeto Item
    @Override
    public String toString() {
        // La representación incluye el nombre, precio, cantidad en stock y descripción del artículo
        return name + " - $" + price + " x " + stock + " " + description;
    }

}
