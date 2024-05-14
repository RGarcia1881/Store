package com.store;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Establecer conexión
            connection = ConnectionDB.getConnection();
            System.out.println("Conexion exitosa a la base de datos");

            // Agregar un nuevo producto
            ApiCart.agregarProducto(connection, "Producto 1", 10.50, 100);
            System.out.println("Producto agregado con éxito");
            ApiCart.agregarProducto(connection, "Producto 2", 13.50, 50);
            System.out.println("Producto agregado con éxito");

            /*
             * Editar un producto existente
             * ApiCart.editarProducto(connection, 3, "Producto 1 Editado", 15.75, 150);
             * System.out.println("Producto editado con éxito");
             */

            /*
             * Eliminar un producto
             * ApiCart.eliminarProducto(connection, 3);
             * System.out.println("Producto eliminado con éxito");
             */
            /*
             * Ver todos los productos
             * List<Product> productos = ApiCart.obtenerProductos(connection);
             * 
             * System.out.println("Lista de productos:");
             * 
             * for (Product producto : productos) {
             * System.out.println(producto);
             * }
             */

            // Obtener un producto por ID
            System.out.println("Producto " + 1);
            int id = 2;
            Product producto = ApiCart.obtenerProducto(connection, id);
            System.out.println(producto);

        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        } finally {
            // Cerrar la conexión
            ConnectionDB.closeConnection(connection);
            System.out.println("Conexion cerrada");
        }
    }
}