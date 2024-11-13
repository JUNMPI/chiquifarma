<%-- 
    Document   : EmailBienvenida
    Created on : 12 nov. 2024, 14:09:05
    Author     : JUNIOR ALVINES
--%>

 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bienvenido a Chiquifarma</title>
    <style>
        /* Estilos generales */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            color: #333;
        }

        /* Contenedor principal */
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        /* Encabezado con el logo */
        .header {
            background-color: #3a7d44; /* Color característico de la farmacia */
            padding: 30px;
            border-radius: 8px 8px 0 0;
            color: #ffffff;
        }

        .header img {
            max-width: 120px;
            margin-bottom: 10px;
        }

        .header h1 {
            margin: 0;
            font-size: 24px;
            font-weight: bold;
        }

        /* Texto de bienvenida */
        .welcome-text {
            padding: 20px 0;
            font-size: 18px;
        }

        .highlight {
            color: #3a7d44; /* Color para resaltar el nombre de la farmacia */
            font-weight: bold;
        }

        /* Botón */
        .button {
            display: inline-block;
            background-color: #3a7d44;
            color: #ffffff;
            padding: 15px 30px;
            margin-top: 20px;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            text-decoration: none;
        }

        .button:hover {
            background-color: #2e6238;
        }

        /* Pie de página */
        .footer {
            padding: 20px;
            font-size: 12px;
            color: #777;
        }

        .footer a {
            color: #3a7d44;
            text-decoration: none;
        }

        .footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Encabezado con el logo y título -->
        <div class="header">
            <img src="https://i.ibb.co/PGtSJmD/Green-Minimalist-Pharmacy-Medical-Logo-1.png" alt="Logo de Farmacia Chiquifarma" class="logo">
            <h1>Bienvenido a Chiquifarma</h1>
        </div>

        <!-- Mensaje de bienvenida -->
        <div class="welcome-text">
            <p>Gracias por unirte a <span class="highlight">Chiquifarma</span>. A partir de ahora, podrás disfrutar de todos nuestros servicios y beneficios exclusivos.</p>
            <p>Si tienes alguna pregunta, no dudes en visitar nuestra página de ayuda o contactarnos.</p>
        </div>

        <!-- Botón para redirigir a la farmacia -->
        <a href="${homeLink}" class="button">Visítanos</a>

        <!-- Pie de página -->
        <div class="footer">
            <p>Este correo ha sido enviado automáticamente, por favor no respondas a este mensaje.</p>
            <p>Para más información, visita nuestra <a href="[Enlace a Política de privacidad]">Política de privacidad</a>.</p>
            <p>&copy; [2024] [Farmacia Chiquifarma]. Todos los derechos reservados.</p>
        </div>
    </div>
</body>
</html>
