package com.negocio.services;

import com.negocio.models.Producto;
import java.util.ArrayList;
import java.util.List;

public class InventarioService {
    private List<Producto> productos;

    public InventarioService() {
        this.productos = new ArrayList<>();
        inicializarProductos();
    }

    private void inicializarProductos() {
        agregarProductoSiNoExiste(new Producto(1, "Hamburguesa", 15.50, 20));
        agregarProductoSiNoExiste(new Producto(2, "Pizza", 25.00, 15));
        agregarProductoSiNoExiste(new Producto(3, "Tacos", 8.75, 30));
        agregarProductoSiNoExiste(new Producto(4, "Refresco", 3.50, 50));
    }

    // ✅ NUEVO MÉTODO para evitar productos repetidos por nombre
    private boolean existeProductoPorNombre(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    private void agregarProductoSiNoExiste(Producto nuevoProducto) {
        if (!existeProductoPorNombre(nuevoProducto.getNombre())) {
            productos.add(nuevoProducto);
        } else {
            System.out.println("⚠️ El producto '" + nuevoProducto.getNombre() + "' ya existe y no fue agregado.");
        }
    }

    public Producto buscarProductoPorId(int id) {
        int i = 0;
        while (i < productos.size()) {
            if (productos.get(i).getId() == id) {
                return productos.get(i);
            }
            i++;
        }
        return null;
    }

    // ===== INICIO MEJORA #6: Validar que la cantidad a vender sea positiva =====
    public boolean venderProducto(int id, int cantidad) {
        if (cantidad <= 0) {
            System.out.println("⚠️ La cantidad debe ser mayor que cero.");
            return false;
        }

        Producto producto = buscarProductoPorId(id);
        if (producto != null && producto.hayStock(cantidad)) {
            producto.reducirStock(cantidad);
            System.out.println("Venta realizada: " + producto.getNombre());
            return true;
        }
        return false;
    }
    // ===== INICIO MEJORA #8: Método para eliminar producto por ID =====
    public boolean eliminarProductoPorId(int id) {
        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            return productos.remove(producto);
        }
        return false;
    }
    // ===== FIN MEJORA #8 =====

    // ===== FIN MEJORA #6 =====


    public List<Producto> obtenerProductosDisponibles() {
        List<Producto> disponibles = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getStock() > 0) {
                disponibles.add(producto);
            }
        }
        return disponibles;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productos;
    }
}
