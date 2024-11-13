<%-- 
    Document   : RestablecerPassword
    Created on : 11 nov. 2024, 19:19:31
    Author     : JUNIOR ALVINES
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Restablecer Contraseña</title>
    <link rel="icon" href="${pageContext.request.contextPath}/Vistas/Recursos/images/logo2.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Vistas/Recursos/css/stylesRestablecerPassword.css">
    <script src="${pageContext.request.contextPath}/Vistas/Recursos/js/scriptsRestablecerPassword.js" defer></script>
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <!-- Título del formulario -->
            <h2>Restablecer Contraseña</h2>
            <!-- Formulario de restablecimiento de contraseña -->
            <form id="resetPasswordForm" action="${pageContext.request.contextPath}/ActualizarPasswordControlador" method="post" onsubmit="return validarContrasenas();">
                <!-- Campo para nueva contraseña -->
                <div class="form-group">
                    <label for="newPassword">Nueva Contraseña:</label>
                    <input type="password" id="newPassword" name="newPassword" required>
                </div>
                <!-- Campo para confirmar nueva contraseña -->
                <div class="form-group">
                    <label for="confirmPassword">Confirmar Nueva Contraseña:</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required>
                </div>
                <!-- Enviar el token como un campo oculto -->
                <input type="hidden" name="token" value="${param.token}">
                <!-- Botón para restablecer la contraseña -->
                <button type="submit" class="btn-primary">Restablecer Contraseña</button>
            </form>
            <!-- Mensaje de error -->
            <div id="mensajeError" class="alert-error" style="display: none;">
                Las contraseñas no coinciden.
            </div>
            
        </div>
    </div>
</body>
</html>


