package com.store;

import java.util.ArrayList;

public class Cart {

    // Atributo que almacena los ítems en el carrito
    private ArrayList<Item> items = new ArrayList<>();

    // Constructor de la clase Cart
    public Cart() {
        // Inicializa la lista de ítems
        this.items = new ArrayList<>();
    }

    // Método para agregar un ítem al carrito
    public void addItem(Item item) {
        items.add(item);
    }

    // Método para eliminar un ítem del carrito por índice
    public void removeItem(int index) {
        items.remove(index);
    }

    // Método para obtener todos los ítems en el carrito
    public ArrayList<Item> getItems() {
        return items;
    }

    // Método para calcular el total del carrito
    public double getTotal() {
        double total = 0;
        // Itera sobre todos los ítems en el carrito y calcula el total
        for (Item item : items) {
            total += item.getPrice() * item.getCount();
        }
        return total;
    }

    // Método para limpiar el carrito, eliminando todos los ítems
    public void clear() {
        items.clear();
    }

    // Método toString para devolver una representación de cadena del carrito
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Itera sobre todos los ítems en el carrito y los agrega a la cadena
        for (Item item : items) {
            sb.append(item.toString()).append("\n");
        }
        // Agrega el total al final de la cadena
        sb.append("Total: $").append(getTotal());
        return sb.toString();
    }

}
