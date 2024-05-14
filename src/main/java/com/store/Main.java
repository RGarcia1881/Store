package com.store;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Establecer conexión
            connection = ConnectionDB.getConnection();
            System.out.println("Conexion exitosa a la base de datos");

            /* 
             * #############################################################################
             * ###########
             * Puebas CRUD a la Bases de Datos *
             * #############################################################################
             * ###########
             

            // Agregar un nuevo producto
            ApiCart.agregarProducto(connection, "Mango", 15.75, 150, "Mango manila fresco");
            System.out.println("Producto agregado con éxito");
            ApiCart.agregarProducto(connection, "Platano", 13.50, 100, "Amarillo amarillo platano");
            System.out.println("Producto agregado con éxito");
            ApiCart.agregarProducto(connection, "Aguacate", 10.00, 120, "Aguacate hass fresco");
            System.out.println("Producto agregado con éxito");
            ApiCart.agregarProducto(connection, "Doritos", 18.00, 200, "Doritos nacho sin abrir");
            System.out.println("Producto agregado con éxito");

        
             // Editar un producto existente
              ApiCart.editarProducto(connection, 1, "Mango", 15.75, 150,
              "Mango manila fresco");
              System.out.println("Producto editado con éxito");
              ApiCart.editarProducto(connection, 2, "Platano", 13.50, 100,
              "Amarillo amarillo platano");
              System.out.println("Producto editado con éxito");
              ApiCart.editarProducto(connection, 3, "Aguacate", 10.00, 120,
              "Aguacate hass fresco");
              System.out.println("Producto editado con éxito");
              ApiCart.editarProducto(connection, 4, "Doritos", 18.00, 200,
              "Doritos nacho sin abrir");
             System.out.println("Producto editado con éxito");

        
        //Eliminar un producto
          ApiCart.eliminarProducto(connection, 3);
          System.out.println("Producto eliminado con éxito");
         

        
         //Ver todos los productos
         List<Product> productos = ApiCart.obtenerProductos(connection);
         System.out.println("Lista de productos:");
         for (Product producto : productos) {
         System.out.println(producto);
         }
        

        
         //Obtener un producto por ID
         System.out.println("Producto " + 1);
         int id = 2;
         Product producto = ApiCart.obtenerProducto(connection, id);
         System.out.println(producto);
         
         
*/


        /*
         * #############################################################################
         * ###########
         * Puebas CRUD al carrito de compras *
         * #############################################################################
         * ###########
         */

        
         * Cart cart = new Cart();
         * // Menú de opciones
         * int opcion;
         * do {
         * System.out.println("\n--- Menú ---");
         * System.out.println("1. Agregar producto al carrito");
         * System.out.println("2. Ver contenido del carrito");
         * System.out.println("3. Eliminar producto del carrito");
         * System.out.println("4. Vaciar carrito");
         * System.out.println("5. Salir");
         * System.out.print("Seleccione una opción: ");
         * opcion = scanner.nextInt();
         * 
         * switch (opcion) {
         * case 1:
         * agregarProductoAlCarrito(connection, scanner, cart);
         * break;
         * case 2:
         * verContenidoDelCarrito(cart);
         * break;
         * case 3:
         * eliminarProductoDelCarrito(scanner, cart);
         * break;
         * case 4:
         * vaciarCarrito(cart);
         * break;
         * case 5:
         * System.out.println("Saliendo del programa...");
         * break;
         * default:
         * System.out.println("Opción inválida. Intente de nuevo.");
         * }
         * } while (opcion != 5);
         * 
         * 
         * } catch (SQLException e) {
         * System.out.println("Error al conectar a la base de datos: " +
         * e.getMessage());
         * } finally {
         * // Cerrar la conexión y el scanner
         * ConnectionDB.closeConnection(connection);
         * System.out.println("Conexion cerrada");
         * scanner.close();
         * }
         */ }
    /*
     * #############################################################################
     * ###########
     * Funciones para las Puebas CRUD al carrito de compras *
     * #############################################################################
     * ###########
     */

    /*
     * // Método para agregar un producto al carrito
     * private static void agregarProductoAlCarrito(Connection connection, Scanner
     * scanner, Cart cart)
     * throws SQLException {
     * // Solicitar al usuario que ingrese el ID del producto que desea agregar al
     * // carrito
     * System.out.print("Ingrese el ID del producto que desea agregar al carrito: "
     * );
     * int productId = scanner.nextInt();
     * 
     * // Obtener el producto de la base de datos utilizando el ID ingresado por el
     * // usuario
     * Product product = ApiCart.obtenerProducto(connection, productId);
     * 
     * // Verificar si se encontró el producto en la base de datos
     * if (product != null) {
     * // Solicitar al usuario que ingrese la cantidad de existencias que desea
     * agregar
     * // al carrito
     * System.out.
     * print("Ingrese la cantidad de existencias que desea agregar al carrito para el producto '"
     * + product.getNombre() + "': ");
     * int cantidad = scanner.nextInt();
     * 
     * // Crear un nuevo ítem con la información del producto y la cantidad
     * ingresada
     * // por el usuario, y agregarlo al carrito
     * cart.addItem(new Item(product.getNombre(), product.getPrecio(), cantidad,
     * product.getDescripcion()));
     * System.out.println("Producto agregado al carrito con éxito.");
     * } else {
     * // Informar al usuario si el producto no fue encontrado en la base de datos
     * System.out.println("El producto con ID " + productId +
     * " no fue encontrado en la base de datos.");
     * }
     * }
     * 
     * // Método para ver el contenido del carrito
     * private static void verContenidoDelCarrito(Cart cart) {
     * // Verificar si el carrito está vacío
     * if (cart.getItems().isEmpty()) {
     * // Informar al usuario si el carrito está vacío
     * System.out.println("El carrito está vacío.");
     * } else {
     * // Mostrar el contenido del carrito
     * System.out.println("Contenido del carrito:");
     * System.out.println(cart);
     * }
     * }
     * 
     * // Método para eliminar un producto del carrito
     * private static void eliminarProductoDelCarrito(Scanner scanner, Cart cart) {
     * // Verificar si el carrito está vacío
     * if (cart.getItems().isEmpty()) {
     * // Informar al usuario si el carrito está vacío y no hay productos para
     * eliminar
     * System.out.println("El carrito está vacío. No hay productos para eliminar.");
     * } else {
     * // Solicitar al usuario que ingrese el índice del producto que desea eliminar
     * // del carrito
     * System.out.
     * print("Ingrese el índice del producto que desea eliminar del carrito: ");
     * int index = scanner.nextInt();
     * 
     * // Verificar si el índice ingresado por el usuario es válido
     * if (index >= 0 && index < cart.getItems().size()) {
     * // Eliminar el producto del carrito utilizando el índice proporcionado por el
     * // usuario
     * cart.removeItem(index);
     * System.out.println("Producto eliminado del carrito con éxito.");
     * } else {
     * // Informar al usuario si el índice ingresado es inválido
     * System.out.println("Índice inválido. Intente de nuevo.");
     * }
     * }
     * }
     * 
     * // Método para vaciar el carrito
     * private static void vaciarCarrito(Cart cart) {
     * // Vaciar el carrito eliminando todos los productos
     * cart.clear();
     * System.out.println("El carrito ha sido vaciado.");
     * }
     * 
     */

     // Método para generar el PDF del ticket de compra
private static void generarPDFTicketCompra(Scanner scanner, Cart cart) {
    // Obtener el directorio actual donde se encuentra el archivo que contiene el método main()
    String currentDir = System.getProperty("user.dir");
    
    // Combinar el directorio actual con el nombre del archivo PDF del ticket de compra
    String fileName = "ticket_de_compra.pdf";
    String filePath = Paths.get(currentDir, fileName).toString();
    
    // Generar el PDF del ticket de compra en el directorio actual
    cart.generateInvoice(filePath);
}
}
