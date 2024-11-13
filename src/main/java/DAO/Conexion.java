/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
   private String ruta;
   private Connection con;
   private String user;
   private String password;

   public Conexion() {
       this.con = null;
       this.ruta = "jdbc:mysql://junction.proxy.rlwy.net:47515/railway"; // URL de Railway
       this.user = "root"; // Usuario de Railway
       this.password = "DXRdDJsduVBJuofZIqcVXADxqizKFNUL"; // Contraseña de Railway
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
