package modelos;

/**
 * Interfaz Nivel: cada nivel provee su propio mapa, preguntas y respuestas esperadas.
 * Implementaciones: Basico, Intermedio, Avanzado
 */
public interface Nivel {
    /** Nombre del nivel (ej: "Basico", "Intermedio", "Avanzado") */
    String nombreNivel();

    /** Devuelve el mapa (Celda[][]) para el nivel */
    Celda[][] crearMapa();

    /** Devuelve el arreglo de preguntas para el nivel */
    Pregunta[] obtenerPreguntas();

    /** Devuelve las respuestas esperadas (texto) para las preguntas en el mismo orden */
    String[] obtenerRespuestasEsperadas();
}