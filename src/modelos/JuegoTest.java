package modelos;

import niveles.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias completas para la clase {@link Juego}.
 *
 * Verifica:
 * - Ciclo de vida de la partida (crear, iniciar, terminar)
 * - Movimiento del jugador (válido, inválido, bloqueado)
 * - Procesamiento de respuestas a preguntas
 * - Validación de condiciones de meta
 * - Estado general del juego
 */
public class JuegoTest {

    private Jugador jugadorValido;
    private Juego juegoBasico;
    private Juego juegoIntermedio;

    @Before
    public void setUp() {
        jugadorValido = new Jugador("Alicia", "Basico");
        juegoBasico = new Juego(jugadorValido, new Basico());
        juegoIntermedio = new Juego(new Jugador("Bob", "Intermedio"), new Intermedio());
    }

    // =============== PRUEBAS: Ciclo de vida del juego ===============

    /**
     * Verifica que un juego nuevo no esté en ejecución.
     */
    @Test
    public void juego_nuevo_no_en_ejecucion() {
        assertFalse(juegoBasico.estaEnEjecucion());
    }

    /**
     * Verifica que iniciar un juego válido lo marca como en ejecución.
     */
    @Test
    public void iniciar_juego_valido_pasa() {
        assertTrue(juegoBasico.puedeIniciar());
        juegoBasico.iniciar();
        assertTrue(juegoBasico.estaEnEjecucion());
    }

    /**
     * Verifica que terminar la partida marca el juego como no en ejecución.
     */
    @Test
    public void terminar_juego_funciona() {
        juegoBasico.iniciar();
        assertTrue(juegoBasico.estaEnEjecucion());
        juegoBasico.terminar();
        assertFalse(juegoBasico.estaEnEjecucion());
    }

    /**
     * Verifica que iniciar un juego inválido lanza excepción.
     */
    @Test(expected = IllegalStateException.class)
    public void iniciar_juego_invalido_lanza_excepcion() {
        Jugador jInvalido = new Jugador("Mal@Nombre", "Basico");
        Juego juego = new Juego(jInvalido, new Basico());
        juego.iniciar();
    }

    // =============== PRUEBAS: Movimiento del jugador ===============

    /**
     * Verifica que mover sin iniciar la partida devuelve falso.
     */
    @Test
    public void mover_sin_iniciar_falla() {
        assertFalse(juegoBasico.moverJugador(Direccion.DERECHA));
    }

    /**
     * Verifica que mover con dirección null devuelve falso.
     */
    @Test
    public void mover_direccion_null_falla() {
        juegoBasico.iniciar();
        assertFalse(juegoBasico.moverJugador(null));
    }

    /**
     * Verifica que mover en dirección válida (partida iniciada, sin obstáculos) funciona.
     */
    @Test
    public void mover_valido_pasa() {
        juegoBasico.iniciar();
        Posicion inicial = jugadorValido.getPosicion();
        boolean movio = juegoBasico.moverJugador(Direccion.DERECHA);
        assertTrue(movio);
        assertNotEquals(inicial, jugadorValido.getPosicion());
    }

    /**
     * Verifica que el movimiento es hacia la dirección correcta.
     */
    @Test
    public void mover_direccion_correcta() {
        juegoBasico.iniciar();
        jugadorValido.setPosicion(new Posicion(1, 1));
        juegoBasico.moverJugador(Direccion.DERECHA);
        assertEquals(new Posicion(1, 2), jugadorValido.getPosicion());

        juegoBasico.moverJugador(Direccion.ABAJO);
        assertEquals(new Posicion(2, 2), jugadorValido.getPosicion());
    }

    /**
     * Verifica que mover hacia una pared (en nivel intermedio) falla.
     */
    @Test
    public void mover_hacia_pared_falla() {
        Jugador j = juegoIntermedio.getJugador();
        j.setPosicion(new Posicion(1, 0));
        juegoIntermedio.iniciar();
        // [1][1] es PARED en nivel intermedio
        assertFalse(juegoIntermedio.moverJugador(Direccion.DERECHA));
        assertEquals(new Posicion(1, 0), j.getPosicion());
    }

    /**
     * Verifica que mover fuera de límites falla.
     */
    @Test
    public void mover_fuera_de_limites_falla() {
        juegoBasico.iniciar();
        jugadorValido.setPosicion(new Posicion(0, 0));
        assertFalse(juegoBasico.moverJugador(Direccion.ARRIBA));
        assertFalse(juegoBasico.moverJugador(Direccion.IZQUIERDA));
        assertEquals(new Posicion(0, 0), jugadorValido.getPosicion());
    }

    // =============== PRUEBAS: Preguntas y respuestas ===============

    /**
     * Verifica que la pregunta actual sea válida después de iniciar.
     */
    @Test
    public void pregunta_actual_valida_tras_iniciar() {
        juegoBasico.iniciar();
        Pregunta q = juegoBasico.obtenerPreguntaActual();
        assertNotNull(q);
        assertNotNull(q.getEnunciado());
    }

    /**
     * Verifica que procesar una respuesta correcta suma puntos.
     */
    @Test
    public void respuesta_correcta_suma_puntos() {
        juegoBasico.iniciar();
        int puntajeInicial = jugadorValido.getPuntaje();
        Pregunta q = juegoBasico.obtenerPreguntaActual();
        int puntosEsperados = q.getPuntos();

        juegoBasico.procesarRespuesta(q.getIndiceCorrecto());

        assertEquals(puntajeInicial + puntosEsperados, jugadorValido.getPuntaje());
    }

    /**
     * Verifica que procesar una respuesta incorrecta reduce vidas.
     */
    @Test
    public void respuesta_incorrecta_reduce_vidas() {
        juegoBasico.iniciar();
        int vidasInicial = jugadorValido.getVidas();
        Pregunta q = juegoBasico.obtenerPreguntaActual();

        juegoBasico.procesarRespuesta(999); // Índice incorrecto

        assertEquals(vidasInicial - 1, jugadorValido.getVidas());
    }

    /**
     * Verifica que perder todas las vidas termina el juego.
     */
    @Test
    public void perder_todas_vidas_termina_juego() {
        juegoBasico.iniciar();
        Pregunta q = juegoBasico.obtenerPreguntaActual();

        // Responder incorrectamente 3 veces (vidas iniciales)
        juegoBasico.procesarRespuesta(999);
        juegoBasico.procesarRespuesta(999);
        juegoBasico.procesarRespuesta(999);

        assertFalse(juegoBasico.estaEnEjecucion());
        assertFalse(jugadorValido.estaVivo());
    }

    /**
     * Verifica que responder correctamente avanza la pregunta.
     */
    @Test
    public void respuesta_correcta_avanza_pregunta() {
        juegoBasico.iniciar();
        Pregunta q1 = juegoBasico.obtenerPreguntaActual();
        String enunciado1 = q1.getEnunciado();

        juegoBasico.procesarRespuesta(q1.getIndiceCorrecto());
        Pregunta q2 = juegoBasico.obtenerPreguntaActual();

        assertNotEquals(enunciado1, q2.getEnunciado());
    }

    /**
     * Verifica que después de todas las preguntas, obtenerPreguntaActual retorna null.
     */
    @Test
    public void todas_preguntas_respondidas_retorna_null() {
        juegoBasico.iniciar();

        // Responder todas las preguntas correctamente
        while (true) {
            Pregunta q = juegoBasico.obtenerPreguntaActual();
            if (q == null) break;
            juegoBasico.procesarRespuesta(q.getIndiceCorrecto());
        }

        assertNull(juegoBasico.obtenerPreguntaActual());
    }

    // =============== PRUEBAS: Condiciones de victoria/derrota ===============

    /**
     * Verifica que jugador en META retorna verdadero.
     */
    @Test
    public void jugador_en_meta_retorna_verdadero() {
        Celda[][] mapa = juegoBasico.obtenerMapa();
        // En nivel Basico, META está en [4][4]
        jugadorValido.setPosicion(new Posicion(4, 4));
        assertTrue(juegoBasico.jugadorEnMeta());
    }

    /**
     * Verifica que jugador fuera de META retorna falso.
     */
    @Test
    public void jugador_fuera_meta_retorna_falso() {
        jugadorValido.setPosicion(new Posicion(0, 0));
        assertFalse(juegoBasico.jugadorEnMeta());
    }

    // =============== PRUEBAS: Acceso a datos ===============

    /**
     * Verifica que obtener mapa devuelve matriz válida.
     */
    @Test
    public void obtener_mapa_valido() {
        Celda[][] mapa = juegoBasico.obtenerMapa();
        assertNotNull(mapa);
        assertTrue(mapa.length > 0);
        assertTrue(mapa[0].length > 0);
    }

    /**
     * Verifica que getJugador devuelve el jugador correcto.
     */
    @Test
    public void get_jugador_correcto() {
        assertEquals(jugadorValido, juegoBasico.getJugador());
    }

    /**
     * Verifica que getNivel devuelve el nivel correcto.
     */
    @Test
    public void get_nivel_correcto() {
        assertNotNull(juegoBasico.getNivel());
        assertEquals("Basico", juegoBasico.getNivel().nombreNivel());
    }

    /**
     * Verifica que obtenerNombreNivel devuelve el nombre correcto.
     */
    @Test
    public void obtener_nombre_nivel_correcto() {
        assertEquals("Basico", juegoBasico.obtenerNombreNivel());
        assertEquals("Intermedio", juegoIntermedio.obtenerNombreNivel());
    }

    // =============== PRUEBAS: Casos especiales ===============

    /**
     * Verifica que puedeMover se comporta correctamente con dirección null.
     */
    @Test
    public void mover_null_no_cambia_estado() {
        juegoBasico.iniciar();
        Posicion antes = jugadorValido.getPosicion();
        juegoBasico.moverJugador(null);
        assertEquals(antes, jugadorValido.getPosicion());
    }

    /**
     * Verifica que juego con nivel null es manejado.
     */
    @Test
    public void juego_con_nivel_null() {
        Juego juegoNull = new Juego(jugadorValido, null);
        assertNull(juegoNull.getNivel());
        assertEquals("", juegoNull.obtenerNombreNivel());
    }

    /**
     * Verifica múltiples movimientos consecutivos.
     */
    @Test
    public void multiples_movimientos_consecutivos() {
        juegoBasico.iniciar();
        jugadorValido.setPosicion(new Posicion(1, 1));

        juegoBasico.moverJugador(Direccion.DERECHA);
        assertEquals(new Posicion(1, 2), jugadorValido.getPosicion());

        juegoBasico.moverJugador(Direccion.DERECHA);
        assertEquals(new Posicion(1, 3), jugadorValido.getPosicion());

        juegoBasico.moverJugador(Direccion.ABAJO);
        assertEquals(new Posicion(2, 3), jugadorValido.getPosicion());
    }
}
