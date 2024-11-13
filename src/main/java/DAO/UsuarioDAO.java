/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package DAO;

import Modelo.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO {

 public boolean agregarUsuario(Usuario usuario, Connection connection) throws SQLException {
    String sql = "{CALL agregarUsuario(?, ?, ?, ?, ?, ?)}"; // Llamada al procedimiento almacenado

    // Encriptar la contraseña antes de guardarla
    String hashedPassword = hashPassword(usuario.getPassword());

    // Imprimir la contraseña en texto plano y la contraseña encriptada en la consola
    System.out.println("Contraseña en texto plano: " + usuario.getPassword());
    System.out.println("Contraseña encriptada (hash): " + hashedPassword);

    try (CallableStatement cs = connection.prepareCall(sql)) {
        cs.setInt(1, usuario.getIdPersona());
        cs.setString(2, usuario.getNickname());
        cs.setString(3, hashedPassword); // Guardar la contraseña encriptada
        cs.setInt(4, usuario.getIdRol());
        cs.setInt(5, usuario.getIdSucursal());
        
        // Convertir el estado booleano a entero (1 para true, 0 para false)
        cs.setInt(6, usuario.isEstado() ? 1 : 0);

        cs.executeUpdate();
        return true;
    }
}



    // Método para encriptar la contraseña
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    // Método para verificar la contraseña
  // Método para verificar la contraseña
    public boolean verificarPassword(String password, String hashedPassword) {
    // Imprime ambas contraseñas para ver si son iguales
    System.out.println("Contraseña ingresada (texto plano): " + password);
    System.out.println("Contraseña almacenada (hash): " + hashedPassword);

    boolean esCorrecta = BCrypt.checkpw(password, hashedPassword);

    // Imprime el resultado de la verificación
    System.out.println("¿La contraseña es correcta? " + esCorrecta);

    return esCorrecta;
}


  public Usuario obtenerUsuarioPorNickname(String nickname, Connection connection) throws SQLException {
    String sql = "SELECT * FROM Usuario WHERE UsuNickname = ?";
    Usuario usuario = null;
    
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, nickname);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_Usuario"));
                usuario.setIdPersona(rs.getInt("id_Persona"));
                usuario.setNickname(rs.getString("UsuNickname"));
                usuario.setPassword(rs.getString("UsuPassword")); // Almacena el hash en el objeto Usuario
                usuario.setIdRol(rs.getInt("id_Rol"));
                usuario.setIdSucursal(rs.getInt("id_Sucursal"));

                // Convert the integer estado (1 or 0) to boolean and set it in the Usuario object
                usuario.setEstado(rs.getInt("UsuEstado") == 1);
            }
        }
    }
    return usuario;
}

    
     public boolean existeNickname(String nickname, Connection connection) throws SQLException {
        String sql = "{CALL existeNickname(?)}"; 
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nickname);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
     
    public boolean actualizarPassword(int idUsuario, String nuevaPassword, Connection connection) throws SQLException {
        String sql = "{CALL actualizarPassword(?, ?)}";

        // Encripta la contraseña
        String hashedPassword = hashPassword(nuevaPassword);

        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, idUsuario);
            cs.setString(2, hashedPassword); // Guarda la contraseña encriptada
            int rowsUpdated = cs.executeUpdate();
            return rowsUpdated > 0;
        }
    }
    

   

    public boolean eliminarUsuarioYPersona(int idUsuario, Connection connection) throws SQLException {
        String sql = "{CALL eliminarUsuarioYPersona(?)}"; 
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, idUsuario); // Set the id_Usuario parameter
            int rowsAffected = cs.executeUpdate();
            return rowsAffected > 0; // Returns true if deletion was successful
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al eliminar el usuario y la persona asociada", e);
        }
    }

}
