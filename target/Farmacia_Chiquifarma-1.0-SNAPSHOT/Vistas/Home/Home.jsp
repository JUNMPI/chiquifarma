<%-- 
    Document   : Home
    Created on : 9 nov. 2024, 16:19:38
    Author     : JUNIOR ALVINES
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Home - Chiquifarma</title>
    <link rel="icon" href="${pageContext.request.contextPath}/Vistas/Recursos/images/logo2.png" type="image/png">

</head>
<body>

<jsp:include page="/Vistas/Template/header_tienda.jsp" />

<!-- Contenido principal de la página de inicio -->
<main class="container mt-5">
    <h2>Bienvenido a Chiquifarma</h2>
    <p>Descubre nuestros productos y encuentra lo que necesitas con la mejor calidad y precios accesibles.</p>
    <!-- Aquí puedes agregar más contenido o secciones de productos -->
</main>

<jsp:include page="/Vistas/Template/footer_tienda.jsp" />

</body>
</html>
