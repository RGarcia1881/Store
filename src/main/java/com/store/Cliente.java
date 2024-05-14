package com.store;

import java.io.*;
import java.net.*;
import java.nio.file.Paths;
import java.util.*;

//172.100.64.82

public class Cliente {

    public static void main(String[] args) {

        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in))) {

            // Solicitar dirección del servidor asdasdasd
            System.out.println("Escriba la dirección del servidor: ");
            String host = br1.readLine();
            System.err.println("\nEscriba el puerto:");
            int pto = Integer.parseInt(br1.readLine());
            System.err.println("\n");

            // Establecer conexión con el servidor y recibimos mensajes
            try (Socket cl = new Socket(host, pto);
                    ObjectInputStream ois = new ObjectInputStream(cl.getInputStream())) {

                // Recibir la lista serializada del servidor
                Object obj = ois.readObject();

                // Convertir el objeto recibido a una lista de productos
                List<Product> productos = (List<Product>) obj;

                // Guardar una copia del arreglo de existencias original
                int[] existenciasOriginales = new int[productos.size()];
                for (int i = 0; i < productos.size(); i++) {
                    existenciasOriginales[i] = productos.get(i).getExistencias();
                }

                // Imprimir el contenido de la lista de productos
                /*
                 * System.out.println("Lista de productos recibida del servidor:");
                 * for (Product producto : productos) {
                 * System.out.println(producto);
                 * }
                 */

                // Iniciamos el carrito de compra
                Cart carrito = new Cart();

                // Declaramos una variable para almacenar el índice del producto seleccionado
                int opcion;

                do {

                    System.out.println("#####################    Lista de productos recibida del servidor    #####################\n");
                    for (Product producto : productos) {
                        System.out.println(producto);
                    }

                    // Mostrar menú
                    System.out.println("\n--- Menú ---");
                    System.out.println("1. Agregar producto al carrito");
                    System.out.println("2. Ver contenido del carrito");
                    System.out.println("3. Eliminar producto del carrito");
                    System.out.println("4. Vaciar carrito");
                    System.out.println("5. Pagar y generar ticket de compra");
                    System.out.println("6. Salir\n");

                    // Leer la opción seleccionada
                    System.out.print("Seleccione una opción: ");
                    opcion = Integer.parseInt(br1.readLine());

                    // Realizar la acción correspondiente a la opción seleccionada

                    switch (opcion) {
                        case 1:
                            // Agregar producto al carrito
                            System.out.print("Seleccione el índice del producto a agregar: ");
                            int index = Integer.parseInt(br1.readLine()) - 1;

                            System.out.print("Indique la cantidad a agregar: ");
                            int s = Integer.parseInt(br1.readLine());

                            // Verificamos que el producto tenga existencias
                            if (productos.get(index).getExistencias() < s) {
                                System.out.println("No hay suficientes existencias de este producto");
                                break;
                            } else {
                                Item item = new Item(productos.get(index).getNombre(), productos.get(index).getPrecio(),
                                        s, productos.get(index).getDescripcion());
                                System.out.println("\n" + item + "\n");
                                carrito.addItem(item);
                                productos.get(index).setExistencias(productos.get(index).getExistencias() - s);
                                System.out.println("Existencias restantes: " + productos.get(index).getExistencias());
                                System.out.println("Producto agregado al carrito\n");
                            }

                            break;
                        case 2:
                            // Ver contenido del carrito
                            System.out.println("\nContenido del carrito:\n");
                            System.out.println(carrito);
                            System.out.println("\n");
                            break;
                        case 3:
                            // Eliminar producto del carrito
                            System.out.println(carrito.getItems().size());
                            System.out.print("Seleccione el índice del producto a eliminar: ");
                            int index2 = Integer.parseInt(br1.readLine());
                            s = carrito.getItems().get(index2 - 1).getStock();
                            carrito.removeItem(index2 - 1);
                            productos.get(index2 - 1).setExistencias(productos.get(index2 - 1).getExistencias() + s);
                            System.out.println("Producto eliminado del carrito\n");
                            break;
                        case 4:
                            // Vaciar carrito
                            for (Item item : carrito.getItems()) {
                                for (Product product : productos) {
                                    if (item.getName().equals(product.getNombre())) {
                                        product.setExistencias(product.getExistencias() + item.getStock());
                                    }
                                }
                            }
                            carrito.clear();
                            System.out.println("Carrito vaciado\n");
                            break;
                        case 5:
                            // Generar ticket de compra
                            String currentDir = System.getProperty("user.dir");

                            System.out.println(currentDir);

                            // Combinar el directorio actual con el nombre del archivo PDF del ticket de
                            // compra
                            String fileName = "ticket_de_compra.pdf";
                            String filePath = Paths.get(currentDir, fileName).toString();

                            // Generar el PDF del ticket de compra en el directorio actual
                            carrito.generateInvoice(filePath);

                            // Crear un arreglo de existencias a partir de la lista de productos
                            int[] existenciasArray = new int[productos.size()];
                            for (int i = 0; i < productos.size(); i++) {
                                existenciasArray[i] = productos.get(i).getExistencias();
                            }

                            // enviar el arreglo de existencias al servidor
                            try (ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream())) {
                                // Serializar el arreglo de existencias y enviarlo al servidor
                                oos.writeObject(existenciasArray);
                                System.out.println("Existencias enviadas al servidor correctamente.");
                            } catch (IOException e) {
                                // Manejar cualquier excepción que pueda ocurrir al enviar el arreglo al servidor
                                e.printStackTrace();
                            }
                            break;
                        case 6:
                            // Salir
                            System.out.println("Saliendo...\n");
                            if (!carrito.isTicketGenerated()) {
                                try (ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream())) {
                                    // Serializar el arreglo de existencias originales y enviarlo al servidor
                                    oos.writeObject(existenciasOriginales);
                                    System.out.println("Existencias originales enviadas al servidor correctamente.");
                                } catch (IOException e) {
                                    // Manejar cualquier excepción que pueda ocurrir al enviar el arreglo al servidor
                                    e.printStackTrace();
                                }
                            }
                            break;
                        default:
                            // Opción inválida
                            System.out.println("Opción inválida");
                            break;
                    }
                } while (opcion != 6);

            } catch (IOException | ClassNotFoundException e) {
                // Manejar cualquier excepción que pueda ocurrir al recibir o leer la lista
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
