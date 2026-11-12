package ec.edu.puce.tienda;

import java.util.Scanner;
import java.util.Locale;

public class Main {

        static Scanner teclado = new Scanner(System.in).useLocale(Locale.US);
        static Producto[] inventario = new Producto[100];
        static int cantidadProductos = 0;

        public static void main(String[] args) {

            int opcion;

            do {
                System.out.println("\n===== CONTROL DE PRODUCTOS DE TIENDA =====");
                System.out.println("1. Registrar producto");
                System.out.println("2. Mostrar inventario");
                System.out.println("3. Salir");

                opcion = leerEntero("Seleccione una opcion: ");

                switch (opcion) {

                    case 1:
                        registrarProducto();
                        break;

                    case 2:
                        mostrarInventario();
                        break;

                    case 3:
                        System.out.println("Gracias por utilizar el sistema.");
                        break;

                    default:
                        System.out.println("Opcion no valida. Intente nuevamente.");
                }

            } while (opcion != 3);
        }

        public static void registrarProducto() {

            if (cantidadProductos >= inventario.length) {
                System.out.println("El inventario esta lleno.");
                return;
            }

            System.out.println("\n===== REGISTRAR PRODUCTO =====");

            String nombre;

            do {
                System.out.print("Ingrese el nombre del producto: ");
                nombre = teclado.nextLine().trim();

                if (nombre.isEmpty()) {
                    System.out.println("Error: el nombre no puede estar vacio.");
                }

            } while (nombre.isEmpty());

            int stock = leerStock();

            double precio = leerPrecio();

            Producto nuevoProducto = new Producto(nombre, stock, precio);

            inventario[cantidadProductos] = nuevoProducto;

            cantidadProductos++;

            System.out.println("Producto registrado correctamente.");
        }


        public static void mostrarInventario() {

            System.out.println("\n===== INVENTARIO DE PRODUCTOS =====");

            if (cantidadProductos == 0) {
                System.out.println("No hay productos registrados.");
                return;
            }

            for (int i = 0; i < cantidadProductos; i++) {

                System.out.println("\nProducto: " + inventario[i].nombre());
                System.out.println("Stock: " + inventario[i].stock() + " unidades");
                System.out.printf(Locale.US, "Precio: $%.2f%n", inventario[i].precio());

            }

            System.out.println("\nTotal de productos: " + cantidadProductos);
        }

        public static int leerEntero(String mensaje) {

            while (true) {

                System.out.print(mensaje);

                if (teclado.hasNextInt()) {

                    int numero = teclado.nextInt();
                    teclado.nextLine();

                    return numero;

                } else {

                    System.out.println("Error: debe ingresar un numero entero.");
                    teclado.nextLine();
                }
            }
        }

        public static int leerStock() {

            while (true) {

                int stock = leerEntero("Ingrese la cantidad disponible: ");

                if (stock >= 0) {
                    return stock;
                }

                System.out.println("Error: el stock no puede ser negativo.");
            }
        }

        public static double leerPrecio() {

            while (true) {

                System.out.print("Ingrese el precio del producto: ");

                if (teclado.hasNextDouble()) {

                    double precio = teclado.nextDouble();
                    teclado.nextLine();

                    if (precio >= 0 && Double.isFinite(precio)) {
                        return precio;
                    }

                    System.out.println("Error: ingrese un precio valido y no negativo.");

                } else {

                    System.out.println("Error: debe ingresar un numero decimal.");
                    teclado.nextLine();
                }
            }
        }
    }
