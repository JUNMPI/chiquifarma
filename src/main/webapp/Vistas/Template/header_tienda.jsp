<%-- 
    Document   : header_Tienda
    Created on : 9 nov. 2024, 16:34:55
    Author     : JUNIOR ALVINES
--%>

<%@ page import="Modelo.Usuario" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Vistas/Recursos/css/stylesHeader.css">
</head>
<body>

<!-- Header -->
<header class="bg-light border-bottom">
    <div class="container py-3">
        <div class="row align-items-center">
            <!-- Logo -->
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/Vistas/Home/Home.jsp">
                    <img src="${pageContext.request.contextPath}/Vistas/Recursos/images/logoLargo.png" alt="Logo" class="img-fluid">
                </a>
            </div>

            <!-- Barra de búsqueda -->
            <div class="col-md-5">
                <form action="${pageContext.request.contextPath}/BusquedaControlador" method="get">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Buscar productos..." name="query">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="submit">Buscar</button>
                        </div>
                    </div>
                </form>
            </div>

            <!-- Usuario y Carrito -->
            <div class="col-md-4 text-right">
                <% if (usuario == null) { %>
                    <a href="${pageContext.request.contextPath}/Vistas/Login/Login.jsp" class="btn btn-link">Iniciar Sesión</a>
                <% } else { %>
                    <span>Hola, <%= usuario.getNickname() %></span>
                    <a href="${pageContext.request.contextPath}/LoginControlador?action=logout" class="btn btn-link">Cerrar Sesión</a>
                <% } %>
                
                <!-- Carrito de compras -->
                <a href="${pageContext.request.contextPath}/Vistas/Carrito/Carrito.jsp" class="btn btn-outline-primary ml-3">
                    <i class="fas fa-shopping-cart"></i> Carrito
                </a>
            </div>
        </div>
    </div>
</header>

<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/Vistas/Recursos/js/scriptsHeader.js"></script>
</body>
</html>
