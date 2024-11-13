/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function () {
    const mensajeError = document.getElementById("mensajeError");
    if (mensajeError) {
        mensajeError.style.display = "none"; // Asegurarse de que el mensaje de error esté oculto desde el inicio
    }
});

// Validación de contraseñas en el formulario de restablecimiento de contraseña
function validarContrasenas() {
    const newPassword = document.getElementById("newPassword").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const mensajeError = document.getElementById("mensajeError");

    if (newPassword !== confirmPassword) {
        // Mostrar el mensaje de error si las contraseñas no coinciden
        if (mensajeError) {
            mensajeError.style.display = "block";
        }
        return false; // Evitar el envío del formulario
    } else {
        // Ocultar el mensaje de error si las contraseñas coinciden
        if (mensajeError) {
            mensajeError.style.display = "none";
        }
        return true; // Permitir el envío del formulario
    }
}
