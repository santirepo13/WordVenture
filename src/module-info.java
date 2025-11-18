/**
 * Módulo principal de la aplicación worldVenture.
 *
 * Este archivo declara el módulo y exporta los paquetes públicos para que
 * herramientas como javadoc y la navegación desde la documentación HTML
 * funcionen correctamente (los enlaces a las clases estarán activos).
 */
module worldVenture {
    requires java.desktop;
	requires junit; // Para Swing/GUI

    exports modelos;
    exports niveles;
    exports app;
    exports validadores;
    exports gui;
}