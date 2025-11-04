/*
 * Script para menú desplegable
 * Apunta solo al menú de Facultades.
 */

document.addEventListener('DOMContentLoaded', function() {
    
    // 1. Apuntamos directamente al ID del li de Facultades
    const facultadesMenuItem = document.querySelector('#menu-facultades');
    
    // 2. Verificamos que exista
    if (facultadesMenuItem) {
        
        // 3. Verificamos si tiene un submenú
        const submenu = facultadesMenuItem.querySelector('ul');
        
        if (submenu) {
            // Agregamos la clase para el estilo (ej. la flecha)
            facultadesMenuItem.classList.add('has-submenu');
            
            // Obtenemos solo el enlace principal
            const mainLink = facultadesMenuItem.querySelector('a');
            
            // Agregamos el evento de clic
            mainLink.addEventListener('click', function(e) {
                e.preventDefault(); // Evitar que el enlace navegue
                
                // Muestra u oculta solo este menú
                facultadesMenuItem.classList.toggle('active');
            });
        }
    }
});