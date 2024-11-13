/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


package Controlador;

import DAO.Conexion;
import DAO.UsuarioDAO;
import Modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "LoginControlador", urlPatterns = {"/LoginControlador"})
public class LoginControlador extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Conexion conexion = new Conexion();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/Vistas/Login/Login.jsp");
            return;
        }

        switch (action) {
            case "login":
                try {
                    autenticarUsuario(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/Vistas/Login/Login.jsp");
                }
                break;
            case "logout":
                cerrarSesion(request, response);
                break;
            case "register":
                response.sendRedirect(request.getContextPath() + "/RegistroControlador?action=nuevo");
                break;
            case "recoverPassword":
                // Redirigir a la página de recuperación de contraseña
                request.getRequestDispatcher("/Vistas/Login/RecuperarPassword.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/Vistas/Login/Login.jsp");
                break;
        }
    }

    
private void autenticarUsuario(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
    String nickname = request.getParameter("nickname");
    String password = request.getParameter("password");

    try (Connection connection = conexion.getConnection()) {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorNickname(nickname, connection);

        if (usuario != null) {
            // Verificar si el usuario está inactivo
            if (!usuario.isEstado()) { 
                request.setAttribute("error", "Su cuenta está inactiva. Contacte al administrador.");
                request.getRequestDispatcher("/Vistas/Login/Login.jsp").forward(request, response);
                return;
            }

            // Comparar contraseñas en texto plano
            boolean esPasswordCorrecto = usuarioDAO.verificarPassword(password, usuario.getPassword());

            if (esPasswordCorrecto) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                session = request.getSession(true);
                session.setAttribute("usuario", usuario);
                response.sendRedirect(request.getContextPath() + "/Vistas/Home/Home.jsp");
            } else {
                request.setAttribute("error", "Nombre de usuario o contraseña incorrectos.");
                request.getRequestDispatcher("/Vistas/Login/Login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Nombre de usuario o contraseña incorrectos.");
            request.getRequestDispatcher("/Vistas/Login/Login.jsp").forward(request, response);
        }
    }
}



    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/Vistas/Home/Home.jsp");
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
        return "LoginControlador";
    }
}
