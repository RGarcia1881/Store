package com.store;

import java.io.*;
import java.net.*;
import java.util.List;

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
            } catch (IOException | ClassNotFoundException e) {
                // Manejar cualquier excepción que pueda ocurrir al recibir o leer la lista
                e.printStackTrace();
            }

            /*
             * try (Socket cl = new Socket(host, pto);
             * BufferedReader br2 = new BufferedReader(new
             * InputStreamReader(cl.getInputStream()))) {
             * 
             * // Creamos variable para leer el mensaje del servidor
             * String mensaje;
             * 
             * // Ciclo para leer los mensajes del servidor
             * while ((mensaje = br2.readLine()) != null) {
             * System.out.println(mensaje);
             * }
             * 
             * } catch (IOException e) {
             * e.printStackTrace();
             * }
             */

            // Hacemos el carrito de compra

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
