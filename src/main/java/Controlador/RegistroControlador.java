/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

// Controlador actualizado: RegistroControlador.java
package Controlador;

import DAO.Conexion;
import DAO.PersonaDAO;
import DAO.UsuarioDAO;
import DAO.SucursalDAO;
import DAO.TipoDocumentoDAO;
import Modelo.Persona;
import Modelo.Usuario;
import Modelo.Sucursal;
import Modelo.TipoDocumento;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

@WebServlet(name = "RegistroControlador", urlPatterns = {"/RegistroControlador"})
public class RegistroControlador extends HttpServlet {

    private PersonaDAO personaDAO = new PersonaDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private SucursalDAO sucursalDAO = new SucursalDAO();
    private TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
    private Conexion conexion = new Conexion();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null || action.equals("nuevo")) {
                mostrarFormularioNuevoUsuario(request, response);
            } else if (action.equals("guardar")) {
                guardarUsuario(request, response);
            } else {
                mostrarFormularioNuevoUsuario(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Vistas/Login/Login.jsp?error=true"); // En caso de excepción general
        }
    }

    private void mostrarFormularioNuevoUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Cargar datos de sucursales y tipos de documento
            List<Sucursal> sucursales = sucursalDAO.listarSucursales();
            List<TipoDocumento> tiposDocumento = tipoDocumentoDAO.listarTiposDocumento();

            // Enviar los datos al JSP
            request.setAttribute("sucursales", sucursales);
            request.setAttribute("tiposDocumento", tiposDocumento);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Forward a la página JSP
        request.getRequestDispatcher("/Vistas/Login/Registro.jsp").forward(request, response);
    }

        protected void guardarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = conexion.getConnection()) {
            connection.setAutoCommit(false); // Iniciar la transacción

            String correo = request.getParameter("correo");
            String numDocumento = request.getParameter("numDocumento");
            String nickname = request.getParameter("nickname");
            String telefono = request.getParameter("telefono");

            if (personaDAO.existeCorreo(correo, connection) ||
                personaDAO.existeNumDocumento(numDocumento, connection) ||
                usuarioDAO.existeNickname(nickname, connection) ||
                personaDAO.existeTelefono(telefono, connection)) {

                request.setAttribute("registroFallido", true);
                mostrarFormularioNuevoUsuario(request, response);
                return;
            }

            Persona persona = new Persona(
                correo,
                request.getParameter("nombre"),
                request.getParameter("apellido"),
                telefono,
                Integer.parseInt(request.getParameter("tipoDocumento")),
                numDocumento
            );

            int idPersona = personaDAO.agregarPersona(persona, connection);
            if (idPersona == -1) {
                throw new SQLException("Error al obtener el ID de Persona.");
            }

            Usuario usuario = new Usuario();
            usuario.setIdPersona(idPersona);
            usuario.setNickname(nickname);
            usuario.setPassword(request.getParameter("password")); // Encriptada ya en UsuarioDAO
            usuario.setIdRol(Integer.parseInt(request.getParameter("idRol")));
            usuario.setIdSucursal(Integer.parseInt(request.getParameter("sucursal")));
            usuario.setEstado(true);

            usuarioDAO.agregarUsuario(usuario, connection);

            connection.commit(); // Confirmar la transacción

            // Enviar correo de bienvenida después de un registro exitoso
            String contextPath = request.getContextPath();
            String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
            String homeLink = baseUrl + "/Vistas/Home/Home.jsp";

            // Llamada al método que envía el correo, pasando el link
            enviarCorreoBienvenida(correo, nickname, homeLink);


            request.setAttribute("registroExitoso", true);
            mostrarFormularioNuevoUsuario(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al registrar el usuario. Inténtelo de nuevo.");
            mostrarFormularioNuevoUsuario(request, response);
        }
    }

        private void enviarCorreoBienvenida(String email, String nickname, String homeLink) {
        String remitente = "junioralvines1005@gmail.com";
        String clave = "pytu ugil vnal rsau"; // Usa una contraseña segura o aplicación de autenticación

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
        mensaje.setFrom(new InternetAddress(remitente, "Farmacia Chiquifarma"));
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        mensaje.setSubject("Bienvenido a Farmacia Chiquifarma");

        // Cargar la plantilla HTML y reemplazar los placeholders
        String contenido = cargarPlantillaHTML("/Vistas/Template/Email/EmailBienvenida.jsp", nickname, homeLink);

        mensaje.setContent(contenido, "text/html; charset=utf-8");
        Transport.send(mensaje);

        System.out.println("Correo de bienvenida enviado a " + email);

    } catch (MessagingException | IOException e) {
        e.printStackTrace();
    }
    }

      
   private String cargarPlantillaHTML(String ruta, String nickname, String homeLink) throws IOException {
    StringBuilder contenido = new StringBuilder();
    try (InputStream is = getServletContext().getResourceAsStream(ruta);
         BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            contenido.append(linea);
        }
    }

    return contenido.toString()
            .replace("${nickname}", nickname)
            .replace("${homeLink}", homeLink);
}





    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "RegistroControlador";
    }
}

