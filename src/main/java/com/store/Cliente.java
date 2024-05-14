package com.store;

import java.io.*;
import java.net.*;

//172.100.64.82

public class Cliente {

    public static void main(String[] args) {

        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in))) {

            // Solicitar dirección del servidor
            System.out.println("Escriba la dirección del servidor: ");
            String host = br1.readLine();
            System.err.println("\n\nEscriba el puerto:");
            int pto = Integer.parseInt(br1.readLine());

            // Establecer conexión con el servidor y recibimos mensajes
            try (Socket cl = new Socket(host, pto);
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getInputStream()))) {

                // Creamos variable para leer el mensaje del servidor
                String mensaje;

                // Ciclo para leer los mensajes del servidor
                while ((mensaje = br2.readLine()) != null) {
                    System.out.println(mensaje);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
