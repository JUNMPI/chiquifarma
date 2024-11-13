/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;
import java.util.List;

public class Carrito {
    private int idCarrito;
    private int idUsuario;
    private Date fechaCreacion;
    private int estado;
    private List<DetalleCarrito> detalles; // Lista de detalles del carrito

    // Constructor vacío
    public Carrito() {
    }

    // Constructor con parámetros
    public Carrito(int idCarrito, int idUsuario, Date fechaCreacion, int estado, List<DetalleCarrito> detalles) {
        this.idCarrito = idCarrito;
        this.idUsuario = idUsuario;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.detalles = detalles;
    }

    // Getters y Setters
    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<DetalleCarrito> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCarrito> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "idCarrito=" + idCarrito +
                ", idUsuario=" + idUsuario +
                ", fechaCreacion=" + fechaCreacion +
                ", estado=" + estado +
                ", detalles=" + detalles +
                '}';
    }
}
