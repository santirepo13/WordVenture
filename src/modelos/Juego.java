package modelos;

import validadores.Validador;

/**
 * Clase que encapsula la lógica y el estado mínimo de una partida.
 *
 * Mantiene la referencia al {@link Jugador}, al {@link Nivel} seleccionado, el
 * mapa de celdas y las preguntas asociadas al nivel. Proporciona operaciones
 * para iniciar/terminar la partida, mover al jugador, procesar respuestas y
 * consultar el estado actual.
 */
public class Juego {
    private Jugador jugador;
    private Nivel nivel;
    private Celda[][] mapa;
    private Pregunta[] preguntas;
    private boolean enEjecucion;
    private int indicePreguntaActual;

    /**
     * Construye un juego usando el jugador y el nivel provistos.
     *
     * @param jugador jugador que participará en la partida
     * @param nivel   nivel que provee mapa y preguntas (puede ser {@code null})
     */
    public Juego(Jugador jugador, Nivel nivel) {
        this.jugador = jugador;
        this.nivel = nivel;
        this.mapa = nivel != null ? nivel.crearMapa() : new Celda[0][0];
        this.preguntas = nivel != null ? nivel.obtenerPreguntas() : new Pregunta[0];
        this.enEjecucion = false;
        this.indicePreguntaActual = 0;
    }

    /**
     * Verifica si se cumplen las condiciones mínimas para iniciar la partida.
     *
     * Delegado a {@link validadores.Validador#puedeIniciarJuego}.
     *
     * @return {@code true} si el juego puede iniciarse.
     */
    public boolean puedeIniciar() {
        return Validador.puedeIniciarJuego(jugador, preguntaEnunciados());
    }

    private String[] preguntaEnunciados() {
        String[] arr = new String[preguntas.length];
        for (int i = 0; i < preguntas.length; i++) arr[i] = preguntas[i].getEnunciado();
        return arr;
    }

    /**
     * Inicia la partida.
     *
     * Lanza {@link IllegalStateException} si no se cumplen las condiciones para
     * iniciar el juego.
     */
    public void iniciar() {
        if (!puedeIniciar()) throw new IllegalStateException("No se pueden cumplir condiciones para iniciar el juego");
        this.enEjecucion = true;
    }

    /**
     * Finaliza la partida (marca como no en ejecución).
     */
    public void terminar() {
        this.enEjecucion = false;
    }

    /**
     * Indica si la partida está en ejecución.
     *
     * @return {@code true} si el juego está en ejecución.
     */
    public boolean estaEnEjecucion() {
        return enEjecucion;
    }

    /**
     * Intenta mover al jugador en la dirección indicada.
     *
     * Comprueba primero que la partida esté en ejecución y luego delega la
     * validación del movimiento a {@link validadores.Validador#puedeMover}.
     *
     * @param d dirección del movimiento
     * @return {@code true} si el movimiento fue realizado; {@code false} si estaba bloqueado o la partida no está en ejecución
     */
    public boolean moverJugador(Direccion d) {
        if (!enEjecucion) return false;
        if (Validador.puedeMover(mapa, jugador.getPosicion(), d)) {
            jugador.mover(d);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Obtiene la pregunta actualmente activa en la partida.
     *
     * @return instancia de {@link Pregunta} o {@code null} si no hay más preguntas.
     */
    public Pregunta obtenerPreguntaActual() {
        if (indicePreguntaActual < preguntas.length) {
            return preguntas[indicePreguntaActual];
        }
        return null;
    }

    /**
     * Procesa la respuesta proporcionada por el jugador.
     *
     * Si la respuesta es correcta, suma los puntos asociados a la pregunta y
     * avanza al siguiente índice de pregunta. Si es incorrecta, resta una vida al
     * jugador y, si no quedan vidas, termina la partida.
     *
     * @param indiceRespuesta índice de la opción seleccionada por el jugador
     * @return {@code true} si la respuesta fue correcta; {@code false} en caso contrario
     */
    public boolean procesarRespuesta(int indiceRespuesta) {
        Pregunta q = obtenerPreguntaActual();
        if (q == null) return false;
        boolean correcto = q.verificarRespuesta(indiceRespuesta);
        if (correcto) {
            jugador.sumarPuntos(q.getPuntos());
            indicePreguntaActual++;
        } else {
            jugador.perderVida();
            if (!jugador.estaVivo()) {
                terminar();
            }
        }
        return correcto;
    }

    /**
     * Devuelve una copia referencial del mapa actual del juego.
     *
     * @return matriz de {@link Celda} con el mapa.
     */
    public Celda[][] obtenerMapa() {
        return mapa;
    }

    /**
     * Devuelve el jugador asociado a la partida.
     *
     * @return {@link Jugador} de la partida.
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Devuelve el nivel asociado a la partida.
     *
     * @return {@link Nivel} utilizado por la partida.
     */
    public Nivel getNivel() {
        return nivel;
    }

    /**
     * Devuelve el nombre legible del nivel.
     *
     * @return nombre del nivel o cadena vacía si no existe.
     */
    public String obtenerNombreNivel() {
        return nivel != null ? nivel.nombreNivel() : "";
    }

    /**
     * Indica si el jugador se encuentra en la celda meta del mapa.
     *
     * @return {@code true} si la posición del jugador coincide con {@link Celda#META}
     */
    public boolean jugadorEnMeta() {
        Posicion p = jugador.getPosicion();
        if (mapa == null || mapa.length == 0) return false;
        return mapa[p.getFila()][p.getColumna()] == Celda.META;
    }

}