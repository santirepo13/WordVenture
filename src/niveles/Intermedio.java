package niveles;

import modelos.*;

/**
 * Implementación del nivel Intermedio.
 *
 * Proporciona el mapa (6x6), las preguntas y las respuestas esperadas para el nivel intermedio.
 */
public class Intermedio implements Nivel {

    @Override
    public String nombreNivel() {
        return "Intermedio";
    }

    /**
     * Crea el mapa del nivel Intermedio.
     *
     * @return matriz de {@link modelos.Celda} representando el mapa del nivel
     */
    @Override
    public Celda[][] crearMapa() {
        Celda[][] mapa = new Celda[6][6];
        for (int r = 0; r < 6; r++) for (int c = 0; c < 6; c++) mapa[r][c] = Celda.LIBRE;
        mapa[0][0] = Celda.INICIO;
        mapa[5][5] = Celda.META;
        mapa[1][3] = Celda.PREGUNTA;
        mapa[2][4] = Celda.PREGUNTA;
        mapa[3][1] = Celda.PREGUNTA;
        mapa[1][1] = Celda.PARED;
        mapa[2][2] = Celda.PARED;
        return mapa;
    }

    /**
     * Provee las preguntas del nivel Intermedio.
     *
     * @return arreglo de {@link modelos.Pregunta}
     */
    @Override
    public Pregunta[] obtenerPreguntas() {
        return new Pregunta[] {
            new Pregunta("Traduce 'casa' al inglés (texto):", null, 0, 12),
            new Pregunta("Escribe el infinitivo de 'corre' en inglés (texto):", null, 0, 12),
            new Pregunta("Traduce 'agua' al inglés (texto):", null, 0, 12)
        };
    }

    /**
     * Respuestas esperadas para las preguntas del nivel Intermedio (en el mismo orden).
     *
     * @return arreglo de respuestas esperadas en minúsculas
     */
    @Override
    public String[] obtenerRespuestasEsperadas() {
        return new String[] { "house", "run", "water" };
    }
}