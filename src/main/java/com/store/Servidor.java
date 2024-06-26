package com.store;

import java.net.*;
import java.util.List;
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
                // PrintWriter pw = new PrintWriter(new
                // OutputStreamWriter(cl.getOutputStream()));

                // Serializar lista de productos
                List<Product> productos = ApiCart.obtenerProductos(ConnectionDB.getConnection());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(productos);
                oos.flush();

                // Enviar lista serializada al cliente
                OutputStream os = cl.getOutputStream();
                os.write(baos.toByteArray());
                os.flush();
                
                // Recibir lista de existencias restantes serializada del cliente
                ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
                int[] existenciasArray = (int[]) ois.readObject();

                // Imprimir la lista recibida
                System.out.println("Existencias recibidas desde el cliente:");
                for (int existencia : existenciasArray) {
                    System.out.println(existencia);
                }

                // Actualizar el stock en la base de datos
                for (int i = 0; i < existenciasArray.length; i++) {
                int existencias = existenciasArray[i];
                // Obtener el producto correspondiente a este índice
                Product producto = productos.get(i);
                // Actualizar el stock del producto en la base de datos
                ApiCart.editarProducto(ConnectionDB.getConnection(), producto.getId(), producto.getNombre(), producto.getPrecio(), existencias, producto.getDescripcion());
                }
                System.out.println("Existencias Actualizadas en la BD");

                // Cerrar conexiones y flujos
                oos.close();
                ois.close();
                baos.close();
                os.close();
                cl.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
