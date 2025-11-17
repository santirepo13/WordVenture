package modelos;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias completas para la clase {@link Jugador}.
 *
 * Verifica:
 * - Creación y estado inicial del jugador
 * - Movimiento del jugador
 * - Sistema de puntos
 * - Sistema de vidas
 * - Getters y setters
 */
public class JugadorTest {

    private Jugador jugador;

    @Before
    public void setUp() {
        jugador = new Jugador("Alice", "Basico");
    }

    // =============== PRUEBAS: Creación e inicialización ===============

    /**
     * Verifica que un jugador nuevo tiene los valores iniciales correctos.
     */
    @Test
    public void jugador_nuevo_inicializado_correctamente() {
        assertEquals("Alice", jugador.getNombre());
        assertEquals("Basico", jugador.getNivel());
        assertEquals(3, jugador.getVidas());
        assertEquals(0, jugador.getPuntaje());
    }

    /**
     * Verifica que la posición inicial es (0,0).
     */
    @Test
    public void posicion_inicial_es_00() {
        Posicion p = jugador.getPosicion();
        assertNotNull(p);
        assertEquals(0, p.getFila());
        assertEquals(0, p.getColumna());
    }

    /**
     * Verifica que el jugador está vivo al inicio.
     */
    @Test
    public void jugador_nuevo_esta_vivo() {
        assertTrue(jugador.estaVivo());
    }

    /**
     * Verifica que se pueden crear jugadores con diferentes nombres y niveles.
     */
    @Test
    public void crear_jugadores_diferentes() {
        Jugador j1 = new Jugador("Bob", "Intermedio");
        Jugador j2 = new Jugador("Carlos 123", "Avanzado");

        assertEquals("Bob", j1.getNombre());
        assertEquals("Intermedio", j1.getNivel());
        assertEquals("Carlos 123", j2.getNombre());
        assertEquals("Avanzado", j2.getNivel());
    }

    // =============== PRUEBAS: Movimiento ===============

    /**
     * Verifica que mover en una dirección cambia la posición.
     */
    @Test
    public void mover_cambia_posicion() {
        Posicion inicial = jugador.getPosicion();
        jugador.mover(Direccion.DERECHA);
        Posicion nueva = jugador.getPosicion();

        assertNotEquals(inicial, nueva);
        assertEquals(0, nueva.getFila());
        assertEquals(1, nueva.getColumna());
    }

    /**
     * Verifica movimiento en todas las direcciones.
     */
    @Test
    public void mover_en_todas_direcciones() {
        jugador.setPosicion(new Posicion(2, 2));

        jugador.mover(Direccion.ARRIBA);
        assertEquals(new Posicion(1, 2), jugador.getPosicion());

        jugador.mover(Direccion.DERECHA);
        assertEquals(new Posicion(1, 3), jugador.getPosicion());

        jugador.mover(Direccion.ABAJO);
        assertEquals(new Posicion(2, 3), jugador.getPosicion());

        jugador.mover(Direccion.IZQUIERDA);
        assertEquals(new Posicion(2, 2), jugador.getPosicion());
    }

    /**
     * Verifica que mover con dirección null no cambia la posición.
     */
    @Test
    public void mover_null_no_cambia_posicion() {
        Posicion inicial = jugador.getPosicion();
        jugador.mover(null);
        assertEquals(inicial, jugador.getPosicion());
    }

    /**
     * Verifica múltiples movimientos consecutivos.
     */
    @Test
    public void multiples_movimientos_consecutivos() {
        jugador.mover(Direccion.DERECHA);
        jugador.mover(Direccion.DERECHA);
        jugador.mover(Direccion.ABAJO);

        assertEquals(new Posicion(1, 2), jugador.getPosicion());
    }

    // =============== PRUEBAS: Sistema de puntos ===============

    /**
     * Verifica que sumar puntos positivos aumenta el puntaje.
     */
    @Test
    public void sumar_puntos_positivos() {
        assertEquals(0, jugador.getPuntaje());
        jugador.sumarPuntos(10);
        assertEquals(10, jugador.getPuntaje());
        jugador.sumarPuntos(5);
        assertEquals(15, jugador.getPuntaje());
    }

    /**
     * Verifica que sumar puntos negativos no afecta el puntaje.
     */
    @Test
    public void sumar_puntos_negativos_no_se_restan() {
        jugador.sumarPuntos(10);
        jugador.sumarPuntos(-5);
        assertEquals(10, jugador.getPuntaje()); // El -5 se ignora
    }

    /**
     * Verifica que sumar cero no cambia el puntaje.
     */
    @Test
    public void sumar_cero_no_cambia_puntaje() {
        assertEquals(0, jugador.getPuntaje());
        jugador.sumarPuntos(0);
        assertEquals(0, jugador.getPuntaje());
    }

    /**
     * Verifica que sumar múltiples puntos acumula correctamente.
     */
    @Test
    public void acumular_multiples_sumas() {
        jugador.sumarPuntos(10);
        jugador.sumarPuntos(15);
        jugador.sumarPuntos(20);
        assertEquals(45, jugador.getPuntaje());
    }

    /**
     * Verifica que el puntaje nunca es negativo.
     */
    @Test
    public void puntaje_nunca_negativo() {
        jugador.sumarPuntos(5);
        jugador.sumarPuntos(-10); // Intenta restar más de lo que tiene

        assertTrue(jugador.getPuntaje() >= 0);
    }

    /**
     * Verifica sumar puntos grandes.
     */
    @Test
    public void sumar_puntos_grandes() {
        jugador.sumarPuntos(1000000);
        assertEquals(1000000, jugador.getPuntaje());
    }

    // =============== PRUEBAS: Sistema de vidas ===============

    /**
     * Verifica que perder una vida reduce el contador.
     */
    @Test
    public void perder_vida_reduce_contador() {
        assertEquals(3, jugador.getVidas());
        jugador.perderVida();
        assertEquals(2, jugador.getVidas());
        jugador.perderVida();
        assertEquals(1, jugador.getVidas());
    }

    /**
     * Verifica que perder todas las vidas hace que estaVivo retorne false.
     */
    @Test
    public void perder_todas_vidas_no_esta_vivo() {
        jugador.perderVida();
        jugador.perderVida();
        jugador.perderVida();
        assertFalse(jugador.estaVivo());
    }

    /**
     * Verifica que las vidas no bajan de 0.
     */
    @Test
    public void vidas_no_bajan_de_cero() {
        jugador.perderVida();
        jugador.perderVida();
        jugador.perderVida();
        jugador.perderVida(); // Intenta perder más vidas
        assertEquals(0, jugador.getVidas());
    }

    /**
     * Verifica que un jugador con 1 vida sigue vivo.
     */
    @Test
    public void una_vida_sigue_vivo() {
        jugador.perderVida();
        jugador.perderVida();
        assertTrue(jugador.estaVivo());
        assertEquals(1, jugador.getVidas());
    }

    // =============== PRUEBAS: Getters y setters ===============

    /**
     * Verifica que setPosicion cambia la posición.
     */
    @Test
    public void set_posicion_funciona() {
        jugador.setPosicion(new Posicion(5, 5));
        assertEquals(new Posicion(5, 5), jugador.getPosicion());
    }

    /**
     * Verifica que todos los getters devuelven valores correctos.
     */
    @Test
    public void todos_getters_correctos() {
        assertNotNull(jugador.getNombre());
        assertNotNull(jugador.getNivel());
        assertTrue(jugador.getVidas() > 0);
        assertTrue(jugador.getPuntaje() >= 0);
        assertNotNull(jugador.getPosicion());
    }

    // =============== PRUEBAS: toString ===============

    /**
     * Verifica que toString retorna una representación válida.
     */
    @Test
    public void tostring_valido() {
        String str = jugador.toString();
        assertNotNull(str);
        assertTrue(str.contains("Alice"));
        assertTrue(str.contains("Basico"));
    }

    /**
     * Verifica que toString contiene información completa.
     */
    @Test
    public void tostring_contiene_info_completa() {
        jugador.sumarPuntos(50);
        String str = jugador.toString();

        assertTrue(str.contains("Jugador"));
        assertTrue(str.contains("Alice"));
        assertTrue(str.contains("Basico"));
        assertTrue(str.contains("3")); // Vidas
        assertTrue(str.contains("50")); // Puntaje
    }

    // =============== PRUEBAS: Integración ===============

    /**
     * Verifica una secuencia completa de acciones.
     */
    @Test
    public void secuencia_completa() {
        // Mover
        jugador.mover(Direccion.DERECHA);
        jugador.mover(Direccion.ABAJO);

        // Sumar puntos
        jugador.sumarPuntos(100);

        // Perder vida
        jugador.perderVida();

        // Verificar estado
        assertEquals(new Posicion(1, 1), jugador.getPosicion());
        assertEquals(100, jugador.getPuntaje());
        assertEquals(2, jugador.getVidas());
        assertTrue(jugador.estaVivo());
    }

    /**
     * Verifica el estado del jugador después de derrota.
     */
    @Test
    public void estado_tras_derrota() {
        jugador.sumarPuntos(75);
        jugador.perderVida();
        jugador.perderVida();
        jugador.perderVida();

        assertEquals(75, jugador.getPuntaje());
        assertEquals(0, jugador.getVidas());
        assertFalse(jugador.estaVivo());
    }

    /**
     * Verifica que múltiples jugadores mantienen estado independiente.
     */
    @Test
    public void multiples_jugadores_independientes() {
        Jugador j1 = new Jugador("Alice", "Basico");
        Jugador j2 = new Jugador("Bob", "Intermedio");

        j1.sumarPuntos(50);
        j2.sumarPuntos(100);

        j1.perderVida();

        assertEquals(50, j1.getPuntaje());
        assertEquals(100, j2.getPuntaje());
        assertEquals(2, j1.getVidas());
        assertEquals(3, j2.getVidas());
    }
}
