package modelos;

/**
 * Tipos de celdas que componen el mapa del juego.
 *
 * <ul>
 *   <li><b>PARED</b>: celda inaccesible</li>
 *   <li><b>LIBRE</b>: celda transitable</li>
 *   <li><b>INICIO</b>: punto de inicio del jugador</li>
 *   <li><b>META</b>: objetivo final del nivel</li>
 *   <li><b>PREGUNTA</b>: celda que contiene una pregunta</li>
 * </ul>
 */
public enum Celda {
    /**
     * Celda que representa una pared u obstáculo.
     */
    PARED,
    /**
     * Celda libre/recorrible.
     */
    LIBRE,
    /**
     * Celda de inicio del jugador.
     */
    INICIO,
    /**
     * Celda meta/objetivo del nivel.
     */
    META,
    /**
     * Celda que contiene una pregunta.
     */
    PREGUNTA;
}