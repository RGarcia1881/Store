package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        // Conexion a la base de datos

        // Definimos la variable de conexión
        Connection connection = null;

        try {

            // Configuramos los parametros de conexión
            String url = "jdbc:postgresql://localhost:5432/store";
            String user = "postgres";
            String password = "N0m3l0";

            connection = DriverManager.getConnection(url, user, password);

            // La conexión se ha establecido correctamente
            System.out.println("\nConexión exitosa a la base de datos PostgreSQL\n");

            // Insertar un producto

            System.out.println("Insertando un producto en la base de datos...\n");

            // ProductoDAO.insertarProducto(connection, "Pan de Muerto", 10.50, 100);

        } catch (SQLException /* | IOException */ e) {
            // Manejar cualquier excepción
            e.printStackTrace();
        }
    }
}