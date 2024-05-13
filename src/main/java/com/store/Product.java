package com.store;

public class Product {
    private int id;
    private String nombre;
    private double precio;
    private int existencias;

    public Product(int id, String nombre, double precio, int existencias) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.existencias = existencias;
    }

    // Getters y setters
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Precio: " + precio + ", Existencias: " + existencias;
    }
}

