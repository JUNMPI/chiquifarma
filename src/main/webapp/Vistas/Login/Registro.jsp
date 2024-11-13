<%-- 
    Document   : Registro
    Created on : 9 nov. 2024, 16:23:01
    Author     : JUNIOR ALVINES
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario - Chiquifarma</title>
     <link rel="icon" href="${pageContext.request.contextPath}/Vistas/Recursos/images/logo2.png" type="image/png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Vistas/Recursos/css/stylesRegistro.css">
    <script src="${pageContext.request.contextPath}/Vistas/Recursos/js/scriptsRegistro.js"></script>
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="form-box p-5">
            <!-- Contenedor para el mensaje de éxito -->
            <c:if test="${registroExitoso == true}">
                <div id="mensajeExito" class="alert alert-success">
                    Usuario creado correctamente. Por favor, inicia sesión.
                </div>
                <script>
                    // Redirigir al login después de 5 segundos
                    setTimeout(function() {
                        window.location.href = "${pageContext.request.contextPath}/Vistas/Login/Login.jsp";
                    }, 5000); // 5000 ms = 5 segundos
                </script>
            </c:if>

            <!-- Mensaje de error en caso de fallo en el registro -->
            <c:if test="${registroFallido == true}">
                <div class="alert alert-danger">
                    Ya existe un usuario con el correo, número de documento, nickname o teléfono proporcionado. Inténtalo de nuevo.
                </div>
            </c:if>

            <c:if test="${param.error == 'true'}">
                <div class="alert alert-danger">
                    Ha ocurrido un error en el registro. Inténtalo de nuevo.
                </div>
            </c:if>
            
            <div class="title-container d-flex align-items-center justify-content-center mb-4">
                <h1 class="text-center mr-2">Crear Cuenta</h1>
                <img src="${pageContext.request.contextPath}/Vistas/Recursos/images/logo1.png" alt="Logo de la Farmacia" class="logo">
            </div>
            
            <form action="RegistroControlador?action=guardar" method="post" onsubmit="return validarFormulario()">
                <!-- Combo Box de Tipo de Documento -->
                <div class="form-group">
                    <label for="tipoDocumento">Tipo de Documento:</label>
                    <select class="form-control" id="tipoDocumento" name="tipoDocumento" required>
                        <c:forEach var="tipoDocumento" items="${tiposDocumento}">
                            <option value="${tipoDocumento.idTipoDocumento}">${tipoDocumento.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="numDocumento">Número de Documento:</label>
                    <input type="text" class="form-control" id="numDocumento" name="numDocumento" required>
                </div>

                <!-- Combo Box de Sucursal -->
                <div class="form-group">
                    <label for="sucursal">Sucursal:</label>
                    <select class="form-control" id="sucursal" name="sucursal" required>
                        <c:forEach var="sucursal" items="${sucursales}">
                            <option value="${sucursal.idSucursal}">${sucursal.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Otros Campos de Persona -->
                <div class="form-group">
                    <label for="correo">Correo Electrónico:</label>
                    <input type="email" class="form-control" id="correo" name="correo" required>
                </div>

                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                </div>

                <div class="form-group">
                    <label for="apellido">Apellido:</label>
                    <input type="text" class="form-control" id="apellido" name="apellido" required>
                </div>

                <div class="form-group">
                    <label for="telefono">Teléfono:</label>
                    <input type="text" class="form-control" id="telefono" name="telefono" required>
                </div>

                <!-- Datos de Usuario -->
                <div class="form-group">
                    <label for="nickname">Nombre de Usuario:</label>
                    <input type="text" class="form-control" id="nickname" name="nickname" required>
                </div>

                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>

                <!-- Rol por defecto -->
                <input type="hidden" name="idRol" value="4"><!-- Ejemplo: rol de cliente -->

                <button type="submit" class="btn btn-primary btn-block">Registrar</button>
            </form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
