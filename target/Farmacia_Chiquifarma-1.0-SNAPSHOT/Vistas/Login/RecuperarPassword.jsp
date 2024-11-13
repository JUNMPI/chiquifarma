<%-- 
    Document   : RecuperarPassword
    Created on : 11 nov. 2024, 01:17:29
    Author     : JUNIOR ALVINES
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recuperar Contraseña - Chiquifarma</title>
    <link rel="icon" href="${pageContext.request.contextPath}/Vistas/Recursos/images/logo2.png" type="image/png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Vistas/Recursos/css/stylesRecoverPassword.css">
</head>
<body>

<div class="login-container">
    <div class="login-box">
        <!-- Título del formulario -->
        <h2>Recuperar Contraseña</h2>

        <!-- Formulario de recuperación de contraseña -->
        <form id="recoverForm" action="${pageContext.request.contextPath}/RecuperarPasswordControlador?action=verifyEmail" method="post">
            <!-- Campo para ingresar el correo electrónico -->
            <div class="form-group">
                <label for="email">Correo Electrónico</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <!-- Botón de enviar email de recuperación -->
            <button type="submit" class="btn btn-primary mb-3">Enviar Email de Recuperación</button>
        </form>

                <!-- Mostrar mensaje de éxito o error si existe -->
         <!-- Mostrar mensaje de éxito o error si existe -->
        <% if (request.getAttribute("mensaje") != null) { %>
            <div class="<%= request.getAttribute("mensaje").equals("El correo electrónico existe. Se ha enviado un email de recuperación.") ? "alert-success" : "alert-error" %> mt-3">
                <%= request.getAttribute("mensaje") %>
            </div>
        <% } %>



    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/Vistas/Recursos/js/scriptsRecoverPassword.js"></script>
</body>
</html>

