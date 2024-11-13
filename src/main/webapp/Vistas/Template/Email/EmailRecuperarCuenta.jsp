<%-- 
    Document   : EmailRecuperarCuenta
    Created on : 11 nov. 2024, 01:57:57
    Author     : JUNIOR ALVINES
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Recuperar Cuenta</title>
    <style>
        /* Estilos generales */
        body {
            font-family: Arial, sans-serif;
            background-color: #f3f4f6;
            margin: 0;
            padding: 0;
        }
        .email-container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2); /* Sombra para efecto 3D */
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .email-container:hover {
            transform: translateY(-10px); /* Efecto de elevación */
            box-shadow: 0px 12px 24px rgba(0, 0, 0, 0.3); /* Sombra más intensa al pasar el ratón */
        }
        .title-container {
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #367f2b; /* Color verde oscuro para el encabezado */
            padding: 10px;
            border-radius: 8px 8px 0 0;
        }
        .title-container h1 {
            color: #ffffff;
            font-size: 1.5rem;
            margin-left: 10px;
        }
        .logo {
            width: 80px;
            height: auto;
        }
        .email-container h2 {
            color: #367f2b; /* Color verde oscuro */
            font-size: 1.8rem;
            margin-top: 15px;
        }
        .email-container p {
            color: #5a5a5a;
            font-size: 1rem;
            margin-top: 10px;
        }
        .button-link {
            display: inline-block;
            padding: 12px 25px;
            margin: 20px 0;
            background-color: #a4d518; /* Color verde para el botón */
            color: #ffffff;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }
        .button-link:hover {
            background-color: #367f2b; /* Color verde más oscuro al pasar el ratón */
        }
        .email-container .link-text {
            color: #244180;
            background-color: #e0f7d1;
            padding: 10px;
            border-radius: 5px;
            display: inline-block;
            margin-top: 10px;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
    <div class="email-container">
        <!-- Encabezado con logo y título -->
        <div class="title-container">
            <img src="https://i.ibb.co/PGtSJmD/Green-Minimalist-Pharmacy-Medical-Logo-1.png" alt="Logo de Farmacia Chiquifarma" class="logo">
            <h1>Farmacia Chiquifarma</h1>
        </div>

        <!-- Contenido del correo -->
        <h2>Recuperar Contraseña</h2>
        <p>Hola, <strong>${nickname}</strong></p>
        <p>Has solicitado recuperar tu contraseña. Haz clic en el enlace de abajo para restablecer tu contraseña:</p>
        <p>
            <a href="${recoveryLink}" target="_blank" class="button-link">Recuperar Contraseña</a>
        </p>
        <p>Si no te funciona el botón, copia y pega la siguiente dirección en tu navegador:</p>
        <p class="link-text">${recoveryLink}</p>
        <br>
        <p>Si no solicitaste este cambio, ignora este mensaje.</p>
    </div>
</body>
</html>

