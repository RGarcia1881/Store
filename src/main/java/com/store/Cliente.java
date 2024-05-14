package com.store;

import java.io.*;
import java.net.*;
import java.util.*;

//172.100.64.82

public class Cliente {

    public static void main(String[] args) {

        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in))) {

            // Solicitar dirección del servidor asdasdasd
            System.out.println("Escriba la dirección del servidor: ");
            String host = br1.readLine();
            System.err.println("\n\nEscriba el puerto:");
            int pto = Integer.parseInt(br1.readLine());

            // Establecer conexión con el servidor y recibimos mensajes
            try (Socket cl = new Socket(host, pto);
                    ObjectInputStream ois = new ObjectInputStream(cl.getInputStream())) {

                // Recibir la lista serializada del servidor
                Object obj = ois.readObject();

                // Convertir el objeto recibido a una lista de productos
                List<Product> productos = (List<Product>) obj;

                // Imprimir el contenido de la lista de productos
                System.out.println("Lista de productos recibida del servidor:");
                for (Product producto : productos) {
                    System.out.println(producto);
                }

                // Iniciamos el carrito de compra
                Cart carrito = new Cart();

                // Declaramos una variable para almacenar el índice del producto seleccionado
                int opcion;

                do {

                    // Mostrar menú
                    System.out.println("\n--- Menú ---");
                    System.out.println("1. Agregar producto al carrito");
                    System.out.println("2. Ver contenido del carrito");
                    System.out.println("3. Eliminar producto del carrito");
                    System.out.println("4. Vaciar carrito");
                    System.out.println("5. Salir");

                    // Leer la opción seleccionada
                    System.out.print("Seleccione una opción: ");
                    opcion = Integer.parseInt(br1.readLine());

                    // Realizar la acción correspondiente a la opción seleccionada

                    switch (opcion) {
                        case 1:
                            // Agregar producto al carrito
                            System.out.print("Seleccione el índice del producto a agregar: ");
                            int index = Integer.parseInt(br1.readLine());
                            System.out.print("Indique la cantidad a agregar: ");
                            int s = Integer.parseInt(br1.readLine());
                            Item item = new Item(productos.get(index).getNombre(), productos.get(index).getPrecio(),
                                    s - 1, productos.get(index).getDescripcion());
                            System.out.println("\n" + item + "\n");
                            carrito.addItem(item);
                            System.out.println("Producto agregado al carrito");
                            break;
                        case 2:
                            // Ver contenido del carrito
                            System.out.println("Contenido del carrito:");
                            System.out.println(carrito);
                            break;
                        case 3:
                            // Eliminar producto del carrito
                            System.out.print("Seleccione el índice del producto a eliminar: ");
                            int index2 = Integer.parseInt(br1.readLine());
                            carrito.removeItem(index2 - 1);
                            System.out.println("Producto eliminado del carrito");
                            break;
                        case 4:
                            // Vaciar carrito
                            carrito.clear();
                            System.out.println("Carrito vaciado");
                            break;
                        case 5:
                            // Salir
                            System.out.println("Saliendo...");
                            break;
                        default:
                            // Opción inválida
                            System.out.println("Opción inválida");
                            break;
                    }

                    System.out.println(opcion);
                } while (opcion != 5);

            } catch (IOException | ClassNotFoundException e) {
                // Manejar cualquier excepción que pueda ocurrir al recibir o leer la lista
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
