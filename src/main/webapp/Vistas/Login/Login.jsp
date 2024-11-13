<%-- 
    Document   : Login
    Created on : 9 nov. 2024, 16:22:48
    Author     : JUNIOR ALVINES
--%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Chiquifarma</title>
    <link rel="icon" href="${pageContext.request.contextPath}/Vistas/Recursos/images/logo2.png" type="image/png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Vistas/Recursos/css/stylesLogin.css">
    <script src="${pageContext.request.contextPath}/Vistas/Recursos/js/scriptsLogin.js"></script>

</head>
<body>

<div class="login-container">
    <div class="login-box">
        <!-- Título del formulario -->
        <h2>Iniciar Sesión</h2>

        <!-- Formulario de login -->
        <form id="loginForm" action="${pageContext.request.contextPath}/LoginControlador?action=login" method="post">
            <!-- Campo Nombre de Usuario -->
            <div class="form-group">
                <label for="nickname">Nombre de Usuario</label>
                <input type="text" class="form-control" id="nickname" name="nickname" required>
            </div>

            <!-- Campo Contraseña -->
            <div class="form-group">
                <label for="password">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <!-- Botón de Iniciar Sesión -->
            <button type="submit" class="btn btn-primary mb-3">Iniciar Sesión</button>

            <!-- Enlace de ¿Olvidaste tu contraseña? -->
            <div class="text-small">
                    <a href="${pageContext.request.contextPath}/LoginControlador?action=recoverPassword">¿Olvidaste tu contraseña?</a>

            </div>

            <!-- Enlace para crear cuenta -->
            <div class="text-small mt-3">
                ¿Aún no tienes una cuenta? <a href="${pageContext.request.contextPath}/LoginControlador?action=register">Crear cuenta</a>

            </div>
        </form>

        <%-- Mostrar mensaje de error si existe --%>
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger mt-3">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/Vistas/Recursos/js/scriptsLogin.js"></script>
</body>
</html>
