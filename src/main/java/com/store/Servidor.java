package com.store;

import java.net.*;
import java.io.*;

public class Servidor {

    public static void main(String[] args) {

        // Generamos el try cathc en caso de error.
        try {

            // Creamos el socket y lo ligamos al puerto 6030.
            ServerSocket s = new ServerSocket(6030);
            System.out.println("Esperando cliente ...");

            // Hacemos un ciclo infinito dentro del cual el servidor va a esperar la
            // solicitud de conexion de un cliente.
            for (;;) {
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde " + cl.getInetAddress() + ":" + cl.getPort());

                // Enviamos catalogo de productos al cliente
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));

                // pw.println("Catalogo de productos:");
                for (Product producto : ApiCart.obtenerProductos(ConnectionDB.getConnection())) {
                    pw.println(producto);
                    pw.flush();
                }

                /*
                 * // Definimos el mensaje a enviar y ligamos un Print Writer a un flujo de
                 * salida
                 * // de caracter.
                 * String mensaje =
                 * "García Bautista Luis Raúl - 6CV3 - Aplicaciones para Comunicaciones en Red";
                 * PrintWriter pw = new PrintWriter(new
                 * OutputStreamWriter(cl.getOutputStream()));
                 * pw.println(mensaje);
                 * pw.flush();
                 * 
                 * // Aquí agregamos la lectura de un mensaje del cliente
                 * BufferedReader br = new BufferedReader(new
                 * InputStreamReader(cl.getInputStream()));
                 * String mensajeDelCliente = br.readLine(); // Lee el mensaje del cliente
                 * 
                 * // Imprime el echo del mensaje del cliente
                 * System.out.println("Mensaje del cliente: " + mensajeDelCliente);
                 * pw.println(mensajeDelCliente); // Hace echo del mensaje
                 * pw.flush();
                 * 
                 * // Cerramos el flujo.
                 * pw.close();
                 * cl.close();
                 */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
