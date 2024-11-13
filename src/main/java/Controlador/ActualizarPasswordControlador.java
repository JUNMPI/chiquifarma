/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controlador;

import DAO.Conexion;
import DAO.TokenDAO;
import DAO.UsuarioDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ActualizarPasswordControlador", urlPatterns = {"/ActualizarPasswordControlador"})
public class ActualizarPasswordControlador extends HttpServlet {

    private TokenDAO tokenDAO = new TokenDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Conexion conexion = new Conexion();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (token == null || token.isEmpty()) {
            request.setAttribute("mensaje", "El enlace de recuperación es inválido.");
            request.getRequestDispatcher("/Vistas/Login/ErrorToken.jsp").forward(request, response);
            return;
        }

        try (Connection connection = conexion.getConnection()) {
            // Verificar si el token es válido
            Integer idUsuario = tokenDAO.verificarToken(token, connection);
            if (idUsuario == null) {
                request.setAttribute("mensaje", "El enlace ha expirado o es inválido.");
                request.getRequestDispatcher("/Vistas/Login/ErrorToken.jsp").forward(request, response);
                return;
            }

            // Si es una solicitud POST (envío del formulario de restablecimiento)
            if (request.getMethod().equalsIgnoreCase("POST")) {
                if (!newPassword.equals(confirmPassword)) {
                    request.setAttribute("mensaje", "Las contraseñas no coinciden.");
                    request.setAttribute("token", token);
                    request.getRequestDispatcher("/Vistas/Login/RestablecerPassword.jsp").forward(request, response);
                    return;
                }

                // Actualizar la contraseña del usuario
                boolean actualizado = usuarioDAO.actualizarPassword(idUsuario, newPassword, connection);
                if (actualizado) {
                    // Eliminar el token después de actualizar la contraseña
                    boolean tokenEliminado = tokenDAO.eliminarToken(token, connection);
                    if (tokenEliminado) {
                        // Redirigir al usuario al login con un mensaje de éxito
                        response.sendRedirect(request.getContextPath() + "/Vistas/Login/Login.jsp?mensaje=Contraseña actualizada correctamente.");
                    } else {
                        request.setAttribute("mensaje", "No se pudo eliminar el token de recuperación. Contacte al soporte.");
                        request.getRequestDispatcher("/Vistas/Login/ErrorToken.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("mensaje", "No se pudo actualizar la contraseña. Inténtelo nuevamente.");
                    request.setAttribute("token", token);
                    request.getRequestDispatcher("/Vistas/Login/RestablecerPassword.jsp").forward(request, response);
                }
            } else {
                // Si es una solicitud GET, mostrar la página de restablecimiento
                request.setAttribute("token", token);
                request.getRequestDispatcher("/Vistas/Login/RestablecerPassword.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error de base de datos. Intente nuevamente.");
            request.getRequestDispatcher("/Vistas/Login/ErrorToken.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Actualiza la contraseña de un usuario con un token de recuperación válido.";
    }
}
