package modelos;

/**
 * Representa una posición en una cuadrícula (mapa) definida por fila y columna.
 * Proporciona utilidades para obtener desplazamientos relativos según {@link Direccion}.
 */
public class Posicion {
    private int fila;
    private int columna;

    /**
     * Crea una nueva posición con fila y columna especificadas.
     *
     * @param fila    índice de la fila (base 0)
     * @param columna índice de la columna (base 0)
     */
    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Calcula y devuelve una nueva {@link Posicion} resultante de mover la posición
     * actual en la dirección indicada.
     *
     * @param d dirección del movimiento; si es {@code null} se devuelve esta posición
     * @return nueva posición desplazada según {@code d} o la misma instancia si {@code d} es {@code null}
     */
    public Posicion mover(Direccion d) {
        if (d == null) return this;
        switch (d) {
            case ARRIBA: return new Posicion(fila - 1, columna);
            case ABAJO: return new Posicion(fila + 1, columna);
            case IZQUIERDA: return new Posicion(fila, columna - 1);
            case DERECHA: return new Posicion(fila, columna + 1);
            default: return this;
        }
    }

    /**
     * Obtiene la fila de la posición.
     *
     * @return índice de fila (base 0)
     */
    public int getFila() { return fila; }

    /**
     * Obtiene la columna de la posición.
     *
     * @return índice de columna (base 0)
     */
    public int getColumna() { return columna; }

    /**
     * Compara esta posición con otro objeto por fila y columna.
     *
     * @param o objeto a comparar
     * @return {@code true} si el objeto es una {@link Posicion} con las mismas coordenadas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Posicion)) return false;
        Posicion p = (Posicion) o;
        return fila == p.fila && columna == p.columna;
    }

    /**
     * Representación textual de la posición en formato "(fila,columna)".
     *
     * @return cadena con la fila y columna formateadas
     */
    @Override
    public String toString() {
        return String.format("(%d,%d)", fila, columna);
    }
}