package modelos;

/**
 * Representa una pregunta del juego.
 *
 * Puede ser de opción múltiple (cuando {@code opciones} contiene elementos) o
 * de texto libre (cuando {@code opciones} está vacío y se valida por coincidencia
 * de texto en otro lugar).
 */
public class Pregunta {
    private String enunciado;
    private String[] opciones;
    private int indiceCorrecto;
    private int puntos;

    /**
     * Crea una nueva pregunta.
     *
     * @param enunciado     Texto de la pregunta o enunciado.
     * @param opciones      Arreglo de opciones (puede ser {@code null} para indicar ausencia de opciones).
     * @param indiceCorrecto Índice de la opción correcta dentro de {@code opciones} (si aplica).
     * @param puntos        Puntos otorgados por responder correctamente (se normaliza a >= 0).
     */
    public Pregunta(String enunciado, String[] opciones, int indiceCorrecto, int puntos) {
        this.enunciado = enunciado;
        this.opciones = opciones != null ? opciones : new String[0];
        this.indiceCorrecto = indiceCorrecto;
        this.puntos = Math.max(0, puntos);
    }

    /**
     * Verifica si el índice proporcionado corresponde a la opción correcta.
     *
     * @param indice Índice propuesto por el jugador.
     * @return {@code true} si el índice coincide con la opción correcta; {@code false} en caso contrario.
     */
    public boolean verificarRespuesta(int indice) {
        return indice == indiceCorrecto;
    }

    /**
     * Obtiene el enunciado de la pregunta.
     *
     * @return texto del enunciado.
     */
    public String getEnunciado() { return enunciado; }

    /**
     * Obtiene las opciones disponibles.
     *
     * @return arreglo de opciones (puede estar vacío).
     */
    public String[] getOpciones() { return opciones; }

    /**
     * Obtiene el índice de la opción correcta.
     *
     * @return índice correcto dentro de {@link #getOpciones()}.
     */
    public int getIndiceCorrecto() { return indiceCorrecto; }

    /**
     * Obtiene los puntos otorgados por la pregunta.
     *
     * @return puntos (valor no negativo).
     */
    public int getPuntos() { return puntos; }

    /**
     * Representación en cadena de la pregunta (enunciado, opciones, índice correcto y puntos).
     *
     * @return cadena descriptiva de la pregunta.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pregunta{").append(enunciado).append(", opciones=[");
        for (int i = 0; i < opciones.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(opciones[i]);
        }
        sb.append("], indiceCorrecto=").append(indiceCorrecto).append(", puntos=").append(puntos).append("}");
        return sb.toString();
    }
}