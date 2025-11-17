package modelos;

/**
 * Direcciones posibles para el movimiento en el mapa.
 *
 * Representa los cuatro vectores cardinales utilizados por {@link Posicion#mover}.
 */
public enum Direccion {
    /**
     * Movimiento hacia la fila anterior (arriba).
     */
    ARRIBA,
    /**
     * Movimiento hacia la fila siguiente (abajo).
     */
    ABAJO,
    /**
     * Movimiento hacia la columna anterior (izquierda).
     */
    IZQUIERDA,
    /**
     * Movimiento hacia la columna siguiente (derecha).
     */
    DERECHA;
}