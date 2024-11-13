/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class TokenDAO {

    public String crearTokenDeRecuperacion(int idUsuario, Connection connection) throws SQLException {
        String token = UUID.randomUUID().toString(); // Genera un token único
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1); // Expira en 1 hora

        String sql = "INSERT INTO PasswordResetToken (id_Usuario, token, expiration) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setString(2, token);
            ps.setTimestamp(3, Timestamp.valueOf(expirationTime));
            ps.executeUpdate();
        }

        // Devuelve el enlace de recuperación
        return token;
    }

    public Integer verificarToken(String token, Connection connection) throws SQLException {
        String sql = "SELECT id_Usuario FROM PasswordResetToken WHERE token = ? AND expiration > NOW()";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_Usuario"); // Devuelve id_Usuario si el token es válido
                }
            }
        }
        return null; // Retorna null si el token es inválido o ha expirado
    }
    
    public boolean eliminarToken(String token, Connection connection) throws SQLException {
    String sql = "{CALL eliminarToken(?)}";
    try (CallableStatement cs = connection.prepareCall(sql)) {
        cs.setString(1, token);
        int rowsDeleted = cs.executeUpdate();
        return rowsDeleted > 0;
    }
}

}
