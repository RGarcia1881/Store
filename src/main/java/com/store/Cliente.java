package com.store;

import java.io.*;
import java.net.*;

//172.100.64.82

public class Cliente {

    public static void main(String[] args) {

        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Escriba la dirección del servidor: ");
            String host = br1.readLine();
            System.err.println("\n\nEscriba el puerto:");
            int pto = Integer.parseInt(br1.readLine());

            try (
                Socket cl = new Socket(host, pto);
                BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getInputStream()))
            ) {
                String mensaje;
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
