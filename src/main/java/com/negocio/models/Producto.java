
package com.negocio.models;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    // ===== INICIO MEJORA #6: Validar precio positivo en constructor =====
    public Producto(int id, String nombre, double precio, int stock) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
// ===== FIN MEJORA #6 =====




    // Getters y setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }
    // ===== INICIO MEJORA #6: Validar precio positivo en setter =====
    public void setPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero.");
        }
        this.precio = precio;
    }
    // ===== FIN MEJORA #6 =====

    public int getStock() {
        return stock;
    }

    // ===== INICIO MEJORA #5: Validar que el stock nunca sea negativo =====
    public void reducirStock(int cantidad) {
        if (cantidad > stock) {
            throw new IllegalArgumentException("No hay suficiente stock disponible.");
        }
        this.stock -= cantidad;
    }
    // ===== FIN MEJORA #5 =====
    // ===== INICIO MEJORA #5 (setter) =====
    public void setStock(int stock) {
    if (stock < 0) {
        throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
    this.stock = stock;
    }
    // ===== FIN MEJORA #5 =====


    // Método que verifica si hay stock suficiente (cantidad o más)
    public boolean hayStock(int cantidad) {
        return stock >= cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
