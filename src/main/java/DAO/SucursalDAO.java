/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package DAO;

import Modelo.Sucursal;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SucursalDAO {
    private Conexion con = new Conexion();

    public List<Sucursal> listarSucursales() throws SQLException {
        List<Sucursal> sucursales = new ArrayList<>();
        String sql = "{CALL listarSucursales()}"; // Llamada al procedimiento almacenado

        try (Connection connection = con.getConnection();
             CallableStatement cs = connection.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Sucursal sucursal = new Sucursal();
                sucursal.setIdSucursal(rs.getInt("id_Sucursal"));
                sucursal.setNombre(rs.getString("sucNombre"));
                sucursales.add(sucursal);
            }
        }
        return sucursales;
    }
}

