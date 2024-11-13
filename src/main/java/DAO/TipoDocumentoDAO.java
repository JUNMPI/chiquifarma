/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package DAO;

import Modelo.TipoDocumento;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoDocumentoDAO {
    private Conexion con = new Conexion();

    // listo los tipos de documento menos el RUC porque esto es para el login
    public List<TipoDocumento> listarTiposDocumento() throws SQLException {
        List<TipoDocumento> tipos = new ArrayList<>();
        String sql = "{CALL listarTiposDocumento()}"; // Llamada al procedimiento almacenado

        try (Connection connection = con.getConnection();
             CallableStatement cs = connection.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                TipoDocumento tipo = new TipoDocumento();
                tipo.setIdTipoDocumento(rs.getInt("id_TipoDocumento"));
                tipo.setNombre(rs.getString("TipDocNombre"));
                tipos.add(tipo);
            }
        }
        return tipos;
    }
}

