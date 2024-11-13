/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// Activa el fondo oscuro cuando el campo de búsqueda está en foco
document.addEventListener("DOMContentLoaded", function() {
    const searchInput = document.querySelector(".input-group .form-control");
    
    searchInput.addEventListener("focus", function() {
        document.body.classList.add("search-active"); // Activa el fondo oscuro
    });
    
    searchInput.addEventListener("blur", function() {
        document.body.classList.remove("search-active"); // Quita el fondo oscuro
    });
});
