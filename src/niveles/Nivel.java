package niveles;

import modelos.Celda;
import modelos.Pregunta;

/**
 * Interfaz que define el contrato para implementaciones de niveles.
 *
 * Cada nivel proporciona un mapa, preguntas y respuestas esperadas.
 */
public interface Nivel {

    /**
     * Obtiene el nombre del nivel.
     *
     * @return nombre del nivel (ej: "Basico", "Intermedio", "Avanzado", "Leyenda")
     */
    String nombreNivel();

    /**
     * Crea y retorna el mapa del nivel.
     *
     * @return matriz de {@link modelos.Celda} representando el mapa del nivel
     */
    Celda[][] crearMapa();

    /**
     * Proporciona las preguntas del nivel.
     *
     * @return arreglo de {@link modelos.Pregunta} para este nivel
     */
    Pregunta[] obtenerPreguntas();

    /**
     * Proporciona las respuestas esperadas para las preguntas del nivel.
     *
     * Las respuestas deben estar en el mismo orden que las preguntas retornadas
     * por {@link #obtenerPreguntas()}, en minúsculas.
     *
     * @return arreglo de respuestas esperadas en minúsculas
     */
    String[] obtenerRespuestasEsperadas();
}
