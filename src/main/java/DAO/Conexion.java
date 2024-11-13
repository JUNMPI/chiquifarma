/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JUNIOR ALVINES
 */
public class Conexion {
   private String ruta;
    private Connection con;
    private String user;
    private String password;

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Conexion() {
        this.con =null;
        this.ruta="jdbc:mysql://localhost:3306/farmacia";
        this.user = "root";
        this.password="";
        
    }

    public Conexion(String ruta, Connection con, String user, String password) {
        this.ruta = ruta;
        this.con = con;
        this.user = user;
        this.password = password;
    }
    
 public Connection getConnection() {
    Connection cn = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        cn = DriverManager.getConnection(this.ruta, this.user, this.password);
    } catch (ClassNotFoundException e) {
        System.out.println("Error: No se encontró el controlador JDBC.");
        e.printStackTrace();
    } catch (SQLException e) {
        System.out.println("Error: No se pudo establecer la conexión con la base de datos.");
        e.printStackTrace();
    }
    return cn;
}
}
