package com.store;

public class Product {
    private int id;
    private String nombre;
    private double precio;
    private int existencias;
    private String descripcion;

    public Product(int id, String nombre,String descripcion, double precio, int existencias) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.existencias = existencias;
        this.descripcion = descripcion;
    }

    // Getters y setters
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Descripcion: " + descripcion + ", Precio: " + precio + ", Existencias: " + existencias;
    }
}

