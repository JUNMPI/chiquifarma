<%-- 
    Document   : footer_tienda
    Created on : 9 nov. 2024, 16:35:20
    Author     : JUNIOR ALVINES
--%>

<!DOCTYPE html>
<html lang="es">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<!-- Footer -->
<footer class="bg-dark text-white mt-5">
    <div class="container py-4">
        <div class="row">
            <!-- Información de la tienda -->
            <div class="col-md-4">
                <h5>Chiquifarma</h5>
                <p>Tienda en línea de productos de farmacia. Encuentra lo que necesitas con la mejor calidad y servicio.</p>
            </div>

            <!-- Navegación rápida -->
            <div class="col-md-4">
                <h5>Navegación</h5>
                <ul class="list-unstyled">
                    <li><a href="${pageContext.request.contextPath}/Vistas/Home/Home.jsp" class="text-white">Inicio</a></li>
                    <li><a href="${pageContext.request.contextPath}/Vistas/Nosotros/Nosotros.jsp" class="text-white">Sobre Nosotros</a></li>
                    <li><a href="${pageContext.request.contextPath}/Vistas/Contacto/Contacto.jsp" class="text-white">Contacto</a></li>
                    <li><a href="${pageContext.request.contextPath}/Vistas/Permisos/Politica.jsp" class="text-white">Política de Privacidad</a></li>
                </ul>
            </div>

            <!-- Redes Sociales -->
            <div class="col-md-4 text-center">
                <h5>Síguenos</h5>
                <a href="https://facebook.com" target="_blank" class="text-white mr-2">
                    <i class="fab fa-facebook fa-lg"></i>
                </a>
                <a href="https://twitter.com" target="_blank" class="text-white mr-2">
                    <i class="fab fa-twitter fa-lg"></i>
                </a>
                <a href="https://instagram.com" target="_blank" class="text-white mr-2">
                    <i class="fab fa-instagram fa-lg"></i>
                </a>
                <a href="https://linkedin.com" target="_blank" class="text-white">
                    <i class="fab fa-linkedin fa-lg"></i>
                </a>
            </div>
        </div>

        <div class="text-center mt-3">
            <p class="mb-0">&copy; 2024 Chiquifarma. Todos los derechos reservados.</p>
        </div>
    </div>
</footer>

<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>
</html>
