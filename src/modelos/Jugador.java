package modelos;

/**
 * Modelo que representa al jugador dentro del juego.
 *
 * Mantiene estado básico como nombre, vidas, puntaje, nivel y posición.
 */
public class Jugador {
    private String nombre;
    private int vidas;
    private int puntaje;
    private String nivel;
    private Posicion posicion;

    /**
     * Crea un nuevo jugador con valores iniciales por defecto.
     *
     * @param nombre Nombre del jugador.
     * @param nivel  Nombre del nivel asignado al jugador.
     */
    public Jugador(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.vidas = 3;
        this.puntaje = 0;
        this.posicion = new Posicion(0, 0);
    }

    /**
     * Mueve la posición del jugador en la dirección indicada.
     *
     * @param d Dirección del movimiento; si es {@code null} no se realiza el cambio.
     */
    public void mover(Direccion d) {
        if (this.posicion != null && d != null) {
            this.posicion = this.posicion.mover(d);
        }
    }

    /**
     * Suma puntos al puntaje del jugador. Sólo se suman puntos positivos.
     * Se asegura que el puntaje no sea negativo.
     *
     * @param pts puntos a añadir (positivo).
     */
    public void sumarPuntos(int pts) {
        if (pts > 0) {
            this.puntaje += pts;
        }
        if (this.puntaje < 0) {
            this.puntaje = 0;
        }
    }

    /**
     * Reduce en una unidad las vidas del jugador (no baja de 0).
     */
    public void perderVida() {
        this.vidas--;
        if (this.vidas < 0) this.vidas = 0;
    }

    /**
     * Indica si el jugador aún tiene vidas.
     *
     * @return {@code true} si vidas > 0.
     */
    public boolean estaVivo() {
        return this.vidas > 0;
    }

    /**
     * @return nombre del jugador.
     */
    public String getNombre() { return nombre; }
    /**
     * @return número de vidas restantes.
     */
    public int getVidas() { return vidas; }
    /**
     * @return puntaje acumulado.
     */
    public int getPuntaje() { return puntaje; }
    /**
     * @return nombre del nivel asociado.
     */
    public String getNivel() { return nivel; }
    /**
     * @return posición actual del jugador.
     */
    public Posicion getPosicion() { return posicion; }

    /**
     * Establece la posición actual del jugador.
     *
     * @param p nueva posición.
     */
    public void setPosicion(Posicion p) { this.posicion = p; }

    /**
     * Representación en cadena del jugador con sus atributos principales.
     *
     * @return cadena descriptiva del jugador.
     */
    @Override
    public String toString() {
        return String.format("Jugador{nombre=%s, nivel=%s, vidas=%d, puntaje=%d, posicion=%s}", nombre, nivel, vidas, puntaje, posicion);
    }
}