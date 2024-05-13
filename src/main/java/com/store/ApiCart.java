package com.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApiCart {
    // Método para agregar un nuevo producto al carrito
    public static void agregarProducto(Connection connection, String nombre, double precio, int existencias) throws SQLException {
        String sql = "INSERT INTO producto (nombre, precio, existencias) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setDouble(2, precio);
            statement.setInt(3, existencias);
            statement.executeUpdate();
        }
    }

    // Método para editar un producto existente en el carrito
    public static void editarProducto(Connection connection, int id, String nombre, double precio, int existencias) throws SQLException {
        String sql = "UPDATE producto SET nombre = ?, precio = ?, existencias = ? WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setDouble(2, precio);
            statement.setInt(3, existencias);
            statement.setInt(4, id);
            statement.executeUpdate();
        }
    }

    // Método para eliminar un producto del carrito
    public static void eliminarProducto(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM producto WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // Método para obtener todos los productos del carrito
    public static List<Product> obtenerProductos(Connection connection) throws SQLException {
        List<Product> productos = new ArrayList<>();
        String sql = "SELECT id, nombre, precio, existencias FROM producto";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                int existencias = resultSet.getInt("existencias");
                Product producto = new Product(id, nombre, precio, existencias);
                productos.add(producto);
            }
        }
        return productos;
    }
}