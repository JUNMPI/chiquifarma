/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DAO.Conexion;
import DAO.PersonaDAO;
import DAO.TokenDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RecuperarPasswordControlador", urlPatterns = {"/RecuperarPasswordControlador"})
public class RecuperarPasswordControlador extends HttpServlet {

    private PersonaDAO personaDAO = new PersonaDAO();
    private TokenDAO tokenDAO = new TokenDAO();
    private Conexion conexion = new Conexion();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("verifyEmail".equals(action)) {
            verificarCorreo(request, response);
        } else {
            response.sendRedirect("Vistas/Login/RecuperarPassword.jsp");
        }
    }

  private void verificarCorreo(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String email = request.getParameter("email");

    try (Connection connection = conexion.getConnection()) {
        boolean existeCorreo = personaDAO.existeCorreo(email, connection);

        if (existeCorreo) {
            // Obtener el id_Usuario del usuario por el email
            int idUsuario = personaDAO.obtenerIdUsuarioPorCorreo(email, connection);
            
            // Obtener el nickname del usuario
            String nickname = personaDAO.obtenerNicknamePorCorreo(email, connection);

            // Generar y guardar el enlace de recuperación
            String recoveryLink = generarEnlaceRecuperacion(idUsuario, connection);
            
            // Enviar correo de recuperación
            enviarCorreoRecuperacion(email, recoveryLink, nickname);
            
            request.setAttribute("mensaje", "El correo electrónico existe. Se ha enviado un email de recuperación.");
        } else {
            request.setAttribute("mensaje", "El correo electrónico no está registrado.");
        }
        request.getRequestDispatcher("/Vistas/Login/RecuperarPassword.jsp").forward(request, response);

    } catch (SQLException e) {
        e.printStackTrace();
        request.setAttribute("mensaje", "Error al verificar el correo. Intente nuevamente.");
        request.getRequestDispatcher("/Vistas/Login/RecuperarPassword.jsp").forward(request, response);
    }
}


  private String generarEnlaceRecuperacion(int idUsuario, Connection connection) throws SQLException {
    // Genera y guarda el token en la base de datos usando TokenDAO
    String token = tokenDAO.crearTokenDeRecuperacion(idUsuario, connection);
    
    // Devuelve el enlace de recuperación completo con el token como parámetro
    return "http://localhost:8080/Farmacia_Chiquifarma/ActualizarPasswordControlador?token=" + token;
}




   private void enviarCorreoRecuperacion(String email, String recoveryLink, String nickname) {
    String remitente = "junioralvines1005@gmail.com";
    String clave = "pytu ugil vnal rsau";

    Properties propiedades = new Properties();
    propiedades.put("mail.smtp.host", "smtp.gmail.com");
    propiedades.put("mail.smtp.port", "587");
    propiedades.put("mail.smtp.auth", "true");
    propiedades.put("mail.smtp.starttls.enable", "true");
    propiedades.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    Session session = Session.getInstance(propiedades, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(remitente, clave);
        }
    });

    try {
        Message mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(remitente, "Farmacia Chiquifarma")); // Nombre personalizado
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        mensaje.setSubject("Recuperación de Contraseña");

        // Cargar la plantilla HTML y reemplazar los placeholders de recoveryLink y nickname
        String contenido = cargarPlantillaHTML("/Vistas/Template/Email/EmailRecuperarCuenta.jsp", recoveryLink, nickname);

        mensaje.setContent(contenido, "text/html; charset=utf-8");

        Transport.send(mensaje);

        System.out.println("Correo de recuperación enviado a " + email);

    } catch (MessagingException | IOException e) {
        e.printStackTrace();
    }
}

private String cargarPlantillaHTML(String ruta, String recoveryLink, String nickname) throws IOException {
    // Leer el archivo de plantilla como texto
    StringBuilder contenido = new StringBuilder();

    try (InputStream is = getServletContext().getResourceAsStream(ruta);
         BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            contenido.append(linea);
        }
    } catch (NullPointerException e) {
        throw new IOException("No se pudo cargar la plantilla: " + ruta, e);
    }

    // Reemplazar los placeholders de recoveryLink y nickname
    return contenido.toString()
            .replace("${recoveryLink}", recoveryLink)
            .replace("${nickname}", nickname);
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
        return "RecuperarPasswordControlador";
    }
}
