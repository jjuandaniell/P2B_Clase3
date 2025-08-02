
package com.negocio.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<Producto> productos;

    // ===== INICIO MEJORA #2: Agregar fecha y hora del pedido =====
    private LocalDateTime fecha;
    // ===== FIN MEJORA #2 =====

    private double total;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.productos = new ArrayList<>();

        // ===== INICIO MEJORA #2 =====
        this.fecha = LocalDateTime.now(); // Al momento de crear el pedido
        // ===== FIN MEJORA #2 =====

        this.total = 0.0;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        calcularTotal();
    }

    private void calcularTotal() {
        total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
    }

    public Producto obtenerPrimerProducto() {
        if (!productos.isEmpty()) {
            return productos.get(0);
        } else {
            return null;
        }
    }

    public double aplicarDescuento(double porcentaje) {
        return total - (total * porcentaje / 100);
    }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<Producto> getProductos() { return productos; }

    // ===== INICIO MEJORA #2 =====
    public LocalDateTime getFecha() { return fecha; }
    // ===== FIN MEJORA #2 =====

    public double getTotal() { return total; }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente.getNombre() +
                ", productos=" + productos.size() +
                ", fecha=" + fecha +  // <- Mostrar también la fecha
                ", total=" + total +
                '}';
    }
}
