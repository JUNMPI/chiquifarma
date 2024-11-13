/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Categoria;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Conexion con = new Conexion();

    // Método para listar todas las categorías usando procedimiento almacenado
    public List<Categoria> listarCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "{CALL ListarCategorias()}";

        try (Connection connection = con.getConnection();
             CallableStatement cs = connection.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_Categoria"));
                categoria.setNombre(rs.getString("CatNombre"));
                categoria.setDescripcion(rs.getString("CatDescripcion"));
                categoria.setEstado(rs.getInt("CatEstado"));
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    // Método para agregar una nueva categoría usando procedimiento almacenado
    public boolean agregarCategoria(Categoria categoria) throws SQLException {
        String sql = "{CALL AgregarCategoria(?, ?, ?)}";

        try (Connection connection = con.getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {

            cs.setString(1, categoria.getNombre());
            cs.setString(2, categoria.getDescripcion());
            cs.setInt(3, categoria.getEstado());
            cs.executeUpdate();
            return true;
        }
    }

    // Método para obtener una categoría por ID usando procedimiento almacenado
    public Categoria obtenerCategoria(int idCategoria) throws SQLException {
        Categoria categoria = null;
        String sql = "{CALL ObtenerCategoria(?)}";

        try (Connection connection = con.getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {

            cs.setInt(1, idCategoria);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_Categoria"));
                categoria.setNombre(rs.getString("CatNombre"));
                categoria.setDescripcion(rs.getString("CatDescripcion"));
                categoria.setEstado(rs.getInt("CatEstado"));
            }
        }
        return categoria;
    }

    // Método para actualizar una categoría usando procedimiento almacenado
    public boolean actualizarCategoria(Categoria categoria) throws SQLException {
        String sql = "{CALL ActualizarCategoria(?, ?, ?, ?)}";

        try (Connection connection = con.getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {

            cs.setInt(1, categoria.getIdCategoria());
            cs.setString(2, categoria.getNombre());
            cs.setString(3, categoria.getDescripcion());
            cs.setInt(4, categoria.getEstado());
            cs.executeUpdate();
            return true;
        }
    }

    // Método para eliminar una categoría por ID usando procedimiento almacenado
    public boolean eliminarCategoria(int idCategoria) throws SQLException {
        String sql = "{CALL EliminarCategoria(?)}";

        try (Connection connection = con.getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {

            cs.setInt(1, idCategoria);
            cs.executeUpdate();
            return true;
        }
    }
}
