package niveles;

import modelos.*;

/**
 * Implementación del nivel Avanzado.
 *
 * Proporciona el mapa, preguntas y respuestas esperadas para el nivel avanzado.
 */
public class Avanzado implements Nivel {

    @Override
    public String nombreNivel() {
        return "Avanzado";
    }

    /**
     * Crea el mapa del nivel Avanzado.
     *
     * @return matriz de {@link modelos.Celda} representando el mapa del nivel
     */
    @Override
    public Celda[][] crearMapa() {
        Celda[][] mapa = new Celda[7][7];
        for (int r = 0; r < 7; r++) for (int c = 0; c < 7; c++) mapa[r][c] = Celda.LIBRE;
        mapa[0][0] = Celda.INICIO;
        mapa[6][6] = Celda.META;
        mapa[1][4] = Celda.PREGUNTA;
        mapa[3][2] = Celda.PREGUNTA;
        mapa[4][5] = Celda.PREGUNTA;
        mapa[1][1] = Celda.PARED;
        mapa[2][2] = Celda.PARED;
        mapa[3][3] = Celda.PARED;
        return mapa;
    }

    /**
     * Provee las preguntas del nivel Avanzado.
     *
     * @return arreglo de {@link modelos.Pregunta}
     */
    @Override
    public Pregunta[] obtenerPreguntas() {
        return new Pregunta[] {
            new Pregunta("Sinónimo de 'afable' en inglés (texto):", null, 0, 15),
            new Pregunta("Traduce 'desafío' al inglés (texto):", null, 0, 15),
            new Pregunta("Forma pasada de 'write' (texto):", null, 0, 15)
        };
    }

    /**
     * Respuestas esperadas para las preguntas del nivel Avanzado (en el mismo orden).
     *
     * @return arreglo de respuestas esperadas en minúsculas
     */
    @Override
    public String[] obtenerRespuestasEsperadas() {
        return new String[] { "amiable", "challenge", "wrote" };
    }
}