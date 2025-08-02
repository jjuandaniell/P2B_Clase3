package umg.edu.gt;

import com.negocio.models.Cliente;
import com.negocio.models.Pedido;
import com.negocio.models.Producto;
import com.negocio.services.InventarioService;
import com.negocio.services.PedidoService;
import com.negocio.db.DatabaseManager;

import java.util.Scanner;

public class Main {
    private static InventarioService inventarioService;
    private static PedidoService pedidoService;
    private static Scanner scanner;

    public static void main(String[] args) {
        System.out.println("=== FOODNET - Simulador de Negocio de Comida Rápida ===");

        // Inicializar servicios
        inventarioService = new InventarioService();
        pedidoService = new PedidoService(inventarioService);
        scanner = new Scanner(System.in);

        // Menú principal
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    mostrarInventario();
                    break;
                case 2:
                    crearNuevoPedido();
                    break;
                case 3:
                    agregarProductoAPedido();
                    break;
                case 4:
                    mostrarPedidos();
                    break;
                case 5:
                    mostrarIngresos();
                    break;
                case 6:
                    aplicarDescuentoAPedido();
                    break;
                // ===== INICIO MEJORA #7: Manejar opción 8 en el menú =====
                case 7:
                    mostrarResumenInventario();
                    break;
                // ===== FIN MEJORA #7 =====
                // ===== INICIO MEJORA #8 =====
                case 8:
                    eliminarProducto();
                    break;
                // ===== FIN MEJORA #8 =====

                case 9:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }

        DatabaseManager.cerrarConexion();
        scanner.close();
        System.out.println("¡Gracias por usar FoodNet!");
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Ver inventario");
        System.out.println("2. Crear nuevo pedido");
        System.out.println("3. Agregar producto a pedido");
        System.out.println("4. Ver pedidos");
        System.out.println("5. Ver ingresos totales");
        System.out.println("6. Aplicar descuento a pedido");
        // ===== INICIO MEJORA #7:
        System.out.println("7. Ver resumen de inventario"); // <- agregada
        // ===== FIN MEJORA #7 =====
        // ===== INICIO MEJORA #8: Agregar opción de eliminar producto =====
        System.out.println("8. Eliminar producto del inventario");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void mostrarInventario() {
        System.out.println("\n--- INVENTARIO ---");
        for (Producto producto : inventarioService.obtenerProductosDisponibles()) {
            System.out.println(producto);
        }
    }
    // ===== INICIO MEJORA #7: Mostrar resumen de inventario =====
    private static void mostrarResumenInventario() {
        System.out.println("\n--- RESUMEN DE INVENTARIO ---");
        for (Producto producto : inventarioService.obtenerTodosLosProductos()) {
            System.out.println("Nombre: " + producto.getNombre() +
                    " | Precio: Q" + producto.getPrecio() +
                    " | Stock: " + producto.getStock());
        }
    }
    // ===== FIN MEJORA #7 =====

    private static void crearNuevoPedido() {
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono del cliente: ");
        String telefono = scanner.nextLine();

        // Aquí puedes implementar un mecanismo para asignar ID de cliente si quieres.
        Cliente cliente = new Cliente(1, nombre, telefono);
        Pedido pedido = pedidoService.crearPedido(cliente);

        System.out.println("Pedido creado con ID: " + pedido.getId());
    }

    private static void agregarProductoAPedido() {
        System.out.print("ID del pedido: ");
        int pedidoId = scanner.nextInt();
        System.out.print("ID del producto: ");
        int productoId = scanner.nextInt();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (pedidoService.agregarProductoAPedido(pedidoId, productoId, cantidad)) {
            System.out.println("Producto agregado exitosamente");
        } else {
            System.out.println("Error al agregar producto");
        }
    }

    private static void mostrarPedidos() {
        System.out.println("\n--- PEDIDOS ---");
        pedidoService.mostrarPedidos();
    }

    private static void mostrarIngresos() {
        double ingresos = pedidoService.calcularIngresosTotales();
        System.out.println("Ingresos totales: Q" + ingresos);
    }
    // ===== INICIO MEJORA #8: Método para eliminar producto con confirmación =====
    private static void eliminarProducto() {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Producto producto = inventarioService.buscarProductoPorId(id);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("¿Está seguro que desea eliminar '" + producto.getNombre() + "'? (s/n): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            boolean eliminado = inventarioService.eliminarProductoPorId(id);
            if (eliminado) {
                System.out.println("✅ Producto eliminado correctamente.");
            } else {
                System.out.println("❌ No se pudo eliminar el producto.");
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    // ===== FIN MEJORA #8 =====

    private static void aplicarDescuentoAPedido() {
        System.out.print("ID del pedido: ");
        int pedidoId = scanner.nextInt();
        System.out.print("Porcentaje de descuento: ");
        double descuento = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        // Buscar pedido y aplicar descuento (implementación simplificada)
        Pedido pedido = pedidoService.obtenerTodosLosPedidos()
                .stream()
                .filter(p -> p.getId() == pedidoId)
                .findFirst()
                .orElse(null);
        if (pedido != null) {
            double totalConDescuento = pedido.aplicarDescuento(descuento);
            System.out.println("Total con descuento: Q" + totalConDescuento);
        } else {
            System.out.println("Pedido no encontrado.");
        }


    }
}
