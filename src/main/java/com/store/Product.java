package com.store;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String nombre;
    private double precio;
    private int existencias;
    private String descripcion;

    public Product(int id, String nombre, String descripcion, double precio, int existencias) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.existencias = existencias;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Descripcion: " + descripcion + ", Precio: " + precio
                + ", Existencias: " + existencias;
    }
}
