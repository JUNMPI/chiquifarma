/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", function() {
    const loginForm = document.getElementById("loginForm");

    loginForm.addEventListener("submit", function(event) {
        // Obtener los valores de los campos
        const nickname = document.getElementById("nickname").value.trim();
        const password = document.getElementById("password").value.trim();

        // Validación del campo de nombre de usuario
        if (nickname === "") {
            alert("Por favor, ingresa tu nombre de usuario.");
            event.preventDefault(); // Evita que el formulario se envíe
            return;
        }

        // Validación del campo de contraseña
        if (password === "") {
            alert("Por favor, ingresa tu contraseña.");
            event.preventDefault();
            return;
        } else if (password.length < 6) {
            alert("La contraseña debe tener al menos 6 caracteres.");
            event.preventDefault();
            return;
        }
    });
});


