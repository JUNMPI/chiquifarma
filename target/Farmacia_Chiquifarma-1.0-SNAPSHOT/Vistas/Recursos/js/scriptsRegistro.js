/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function validarFormulario() {
    const correo = document.getElementById("correo").value;
    const telefono = document.getElementById("telefono").value;
    const password = document.getElementById("password").value;
    const numDocumento = document.getElementById("numDocumento").value;
    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const nickname = document.getElementById("nickname").value;

    // Verificación de campos vacíos
    if (!correo || !telefono || !password || !numDocumento || !nombre || !apellido || !nickname) {
        alert("Todos los campos deben estar llenos.");
        return false;
    }

    // Validación de correo electrónico
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(correo)) {
        alert("Por favor, ingrese un correo electrónico válido.");
        return false;
    }

    // Validación de teléfono (debe comenzar con 9 y tener exactamente 9 dígitos)
    const telefonoRegex = /^9\d{8}$/;
    if (!telefonoRegex.test(telefono)) {
        alert("El teléfono debe comenzar con 9 y tener exactamente 9 dígitos.");
        return false;
    }

    // Validación de contraseña (mínimo 6 caracteres, al menos una letra y un número)
   const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*?&]{6,}$/;
    if (!passwordRegex.test(password)) {
        alert("La contraseña debe tener al menos 6 caracteres, incluyendo letras y números.");
        return false;
    }

    // Validación de número de documento (solo números, entre 8 y 11 dígitos)
    const documentoRegex = /^\d{8,11}$/;
    if (!documentoRegex.test(numDocumento)) {
        alert("El número de documento debe contener solo números y tener entre 8 y 11 dígitos.");
        return false;
    }

    // Si todas las validaciones se cumplen, permite el envío del formulario
    return true;
}
