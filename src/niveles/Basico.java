package niveles;

import modelos.*;

/**
 * Implementación del nivel Básico.
 *
 * Proporciona el mapa del nivel, las preguntas y las respuestas esperadas para este nivel.
 */
public class Basico implements Nivel {

    @Override
    public String nombreNivel() {
        return "Basico";
    }

    /**
     * Crea el mapa del nivel Básico (5x5).
     *
     * @return matriz de {@link modelos.Celda} representando el mapa
     */
    @Override
    public Celda[][] crearMapa() {
        Celda[][] mapa = new Celda[5][5];
        for (int r = 0; r < 5; r++) for (int c = 0; c < 5; c++) mapa[r][c] = Celda.LIBRE;
        mapa[0][0] = Celda.INICIO;
        mapa[4][4] = Celda.META;
        mapa[1][2] = Celda.PREGUNTA;
        mapa[2][3] = Celda.PREGUNTA;
        mapa[3][1] = Celda.PREGUNTA;
        return mapa;
    }

    /**
     * Provee las preguntas del nivel.
     *
     * @return arreglo de {@link modelos.Pregunta}
     */
    @Override
    public Pregunta[] obtenerPreguntas() {
        return new Pregunta[] {
            new Pregunta("Traduce 'gato' al inglés (texto):", null, 0, 10),
            new Pregunta("Traduce 'perro' al inglés (texto):", null, 0, 10),
            new Pregunta("¿De qué color es el cielo en un día claro? (texto):", null, 0, 10)
        };
    }

    /**
     * Respuestas esperadas para las preguntas del nivel (en el mismo orden).
     *
     * @return arreglo de respuestas esperadas en minúsculas
     */
    @Override
    public String[] obtenerRespuestasEsperadas() {
        return new String[] { "cat", "dog", "blue" };
    }
}