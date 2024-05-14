package com.store;

import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) {

        // Generamos el try cathc en caso de error.
        try {

            // Generamos la variable de flujo de lectura de la entrada estandar.
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

            // Recibimos los datos del servidor y puerto.
            System.out.println("Escriba la dirección del servidor: ");
            String host = br1.readLine();
            System.err.println("\n\nEscriba el puerto:");
            int pto = Integer.parseInt(br1.readLine());

            // Creamos el socket del cliente
            Socket cl = new Socket(host, pto);

            // Generamos otra variable de flujo ligado al socket para recibir el mensaje.
            BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getInputStream()));

            // Almacenamos el mensaje y lo mostramos.
            String mensaje = br2.readLine();
            System.out.println("Recibimos un mensaje desde el servidor");
            System.out.println("Mensaje: " + mensaje);

            /*
             * // Generamos la variable donde el cliente escribira su mensaje.
             * PrintWriter pw = new PrintWriter(new
             * OutputStreamWriter(cl.getOutputStream()));
             * 
             * // Aquí el cliente envía un mensaje al servidor (echo)
             * pw.println(mensaje);
             * pw.flush();
             * 
             * // Cerramos los flujos, el socket y terminamos el programa.
             * br1.close();
             * br2.close();
             * cl.close();
             */

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
