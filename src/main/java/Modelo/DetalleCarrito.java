/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class DetalleCarrito {
    private int idDetalleCarrito;
    private int idCarrito;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;

    // Constructor vacío
    public DetalleCarrito() {
    }

    // Constructor con parámetros
    public DetalleCarrito(int idDetalleCarrito, int idCarrito, int idProducto, int cantidad, double precioUnitario) {
        this.idDetalleCarrito = idDetalleCarrito;
        this.idCarrito = idCarrito;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Getters y Setters
    public int getIdDetalleCarrito() {
        return idDetalleCarrito;
    }

    public void setIdDetalleCarrito(int idDetalleCarrito) {
        this.idDetalleCarrito = idDetalleCarrito;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    // Método para calcular el subtotal de este detalle
    public double getSubtotal() {
        return this.cantidad * this.precioUnitario;
    }

    @Override
    public String toString() {
        return "DetalleCarrito{" +
                "idDetalleCarrito=" + idDetalleCarrito +
                ", idCarrito=" + idCarrito +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                '}';
    }
}
