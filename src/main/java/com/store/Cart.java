package com.store;

import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Cart {

    // Atributo que almacena los ítems en el carrito
    private ArrayList<Item> items = new ArrayList<>();

    // Atributo que indica si se ha generado el ticket de compra
    private boolean ticketGenerated = false;

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
            total += item.getPrice() * item.getStock();
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

    // Método para generar un archivo PDF que represente un ticket de compra
    public void generateInvoice(String filePath) {
        if (isEmpty()) {
            System.out.println("No hay elementos en el carrito para generar un ticket de compra.");
            return;
        }
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            // Agregar contenido al PDF
            document.add(new Paragraph("Ticket de Compra"));
            document.add(new Paragraph("------------------------------"));
            for (Item item : items) {
                document.add(new Paragraph(item.toString()));
            }
            document.add(new Paragraph("------------------------------"));
            document.add(new Paragraph("Total: $" + getTotal()));
            document.close();
            System.out.println("Ticket de compra generado correctamente en: " + filePath);
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("Error al generar el ticket de compra: " + e.getMessage());
        }
    }

    // Metodo para ver si el carrito esta vacio
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Método que indica si se ha generado el ticket de compra
    public boolean isTicketGenerated() {
        return ticketGenerated;
    }

}