/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", function() {
    const recoverForm = document.getElementById("recoverForm");

    recoverForm.addEventListener("submit", function(event) {
        const emailInput = document.getElementById("email").value.trim();

        // Expresión regular para verificar el formato del correo electrónico
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

        if (!emailInput) {
            alert("Por favor, ingresa tu correo electrónico.");
            event.preventDefault();
        } else if (!emailRegex.test(emailInput)) {
            alert("Por favor, ingresa un correo electrónico válido.");
            event.preventDefault();
        }
    });
});
