/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;

import Modelo.Persona;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaDAO {

  public int agregarPersona(Persona persona, Connection connection) throws SQLException {
    int idGenerado = -1;
    String sql = "{CALL agregarPersona(?, ?, ?, ?, ?, ?, ?)}"; // Llamada al procedimiento almacenado

    try (CallableStatement cs = connection.prepareCall(sql)) {
        // Configura los parámetros de entrada del procedimiento
        cs.setString(1, persona.getCorreo());
        cs.setString(2, persona.getNombre());
        cs.setString(3, persona.getApellido());
        cs.setString(4, persona.getTelefono());
        cs.setInt(5, persona.getIdTipoDocumento());
        cs.setString(6, persona.getNumDocumento());

        // Configura el parámetro de salida para el ID generado
        cs.registerOutParameter(7, java.sql.Types.INTEGER);

        // Ejecuta el procedimiento almacenado
        cs.executeUpdate();

        // Obtiene el ID generado
        idGenerado = cs.getInt(7); // Valor devuelto por el procedimiento
        if (idGenerado != -1) {
            persona.setIdPersona(idGenerado); // Asigna el ID a la persona
        }
    }

    return idGenerado;
}

  public boolean existeCorreo(String correo, Connection connection) throws SQLException {
    String sql = "{CALL existeCorreo(?)}"; // Llamada al procedimiento almacenado

    try (CallableStatement cs = connection.prepareCall(sql)) {
        // Establece el parámetro de entrada
        cs.setString(1, correo);

        // Ejecuta el procedimiento almacenado
        try (ResultSet rs = cs.executeQuery()) {
            // Verifica si el correo ya existe
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    }
    return false;
}


     public boolean existeNumDocumento(String numDocumento, Connection connection) throws SQLException {
        String sql = "{CALL existeNumDocumento(?)}"; // Llamada al procedimiento almacenado
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, numDocumento);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean existeTelefono(String telefono, Connection connection) throws SQLException {
        String sql = "{CALL existeTelefono(?)}"; // Llamada al procedimiento almacenado
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, telefono);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public String obtenerNicknamePorCorreo(String email, Connection connection) throws SQLException {
    String sql = "{CALL existeNicknamePorCorreo(?, ?)}";
    try (CallableStatement cs = connection.prepareCall(sql)) {
        cs.setString(1, email); // Establece el parámetro de entrada (correo)
        cs.registerOutParameter(2, java.sql.Types.VARCHAR); // Registra el parámetro de salida (nickname)

        cs.execute();

        // Obtiene el nickname desde el parámetro de salida
        return cs.getString(2);
    }
}
    
     // Método para obtener el idUsuario asociado a un correo electrónico
    public int obtenerIdUsuarioPorCorreo(String email, Connection connection) throws SQLException {
        String sql = "SELECT u.id_Usuario FROM Usuario u "
                   + "JOIN Persona p ON u.id_Persona = p.id_Persona "
                   + "WHERE p.PerCorreo = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_Usuario");
                }
            }
        }
        
        // Retorna -1 si no se encuentra un usuario con ese correo electrónico
        return -1;
    }

}


