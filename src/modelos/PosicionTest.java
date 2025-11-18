package modelos;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias completas para la clase {@link Posicion}.
 *
 * Verifica:
 * - Creación de posiciones
 * - Movimiento en todas las direcciones
 * - Comparación de posiciones (equals)
 * - Representación en string
 */
public class PosicionTest {

    private Posicion posicion00;
    private Posicion posicion22;
    private Posicion posicion44;

    @Before
    public void setUp() {
        posicion00 = new Posicion(0, 0);
        posicion22 = new Posicion(2, 2);
        posicion44 = new Posicion(4, 4);
    }

    // =============== PRUEBAS: Creación de posiciones ===============

    /**
     * Verifica que una posición se crea con las coordenadas correctas.
     */
    @Test
    public void crear_posicion_valida() {
        assertEquals(0, posicion00.getFila());
        assertEquals(0, posicion00.getColumna());
        assertEquals(2, posicion22.getFila());
        assertEquals(2, posicion22.getColumna());
    }

    /**
     * Verifica que se pueden crear posiciones con coordenadas negativas.
     */
    @Test
    public void crear_posicion_negativa() {
        Posicion neg = new Posicion(-1, -1);
        assertEquals(-1, neg.getFila());
        assertEquals(-1, neg.getColumna());
    }

    /**
     * Verifica que se pueden crear posiciones con coordenadas grandes.
     */
    @Test
    public void crear_posicion_grande() {
        Posicion grande = new Posicion(1000, 1000);
        assertEquals(1000, grande.getFila());
        assertEquals(1000, grande.getColumna());
    }

    // =============== PRUEBAS: Movimiento ===============

    /**
     * Verifica que mover ARRIBA reduce la fila en 1.
     */
    @Test
    public void mover_arriba_reduce_fila() {
        Posicion nueva = posicion22.mover(Direccion.ARRIBA);
        assertEquals(1, nueva.getFila());
        assertEquals(2, nueva.getColumna());
    }

    /**
     * Verifica que mover ABAJO aumenta la fila en 1.
     */
    @Test
    public void mover_abajo_aumenta_fila() {
        Posicion nueva = posicion22.mover(Direccion.ABAJO);
        assertEquals(3, nueva.getFila());
        assertEquals(2, nueva.getColumna());
    }

    /**
     * Verifica que mover IZQUIERDA reduce la columna en 1.
     */
    @Test
    public void mover_izquierda_reduce_columna() {
        Posicion nueva = posicion22.mover(Direccion.IZQUIERDA);
        assertEquals(2, nueva.getFila());
        assertEquals(1, nueva.getColumna());
    }

    /**
     * Verifica que mover DERECHA aumenta la columna en 1.
     */
    @Test
    public void mover_derecha_aumenta_columna() {
        Posicion nueva = posicion22.mover(Direccion.DERECHA);
        assertEquals(2, nueva.getFila());
        assertEquals(3, nueva.getColumna());
    }

    /**
     * Verifica que mover con dirección null devuelve la misma posición.
     */
    @Test
    public void mover_null_retorna_misma_posicion() {
        Posicion nueva = posicion22.mover(null);
        assertEquals(posicion22, nueva);
    }

    /**
     * Verifica que mover devuelve una nueva instancia (no modifica la original).
     */
    @Test
    public void mover_devuelve_nueva_instancia() {
        Posicion nueva = posicion22.mover(Direccion.DERECHA);
        assertNotSame(posicion22, nueva);
        assertEquals(2, posicion22.getFila()); // Original sin cambios
        assertEquals(2, posicion22.getColumna());
    }

    /**
     * Verifica movimientos consecutivos.
     */
    @Test
    public void movimientos_consecutivos() {
        Posicion p = new Posicion(0, 0);
        p = p.mover(Direccion.DERECHA);
        assertEquals(new Posicion(0, 1), p);

        p = p.mover(Direccion.ABAJO);
        assertEquals(new Posicion(1, 1), p);

        p = p.mover(Direccion.DERECHA);
        assertEquals(new Posicion(1, 2), p);

        p = p.mover(Direccion.ARRIBA);
        assertEquals(new Posicion(0, 2), p);
    }

    /**
     * Verifica que mover en direcciones opuestas regresa a la posición original.
     */
    @Test
    public void movimientos_opuestos_regresan() {
        Posicion p = new Posicion(2, 2);
        p = p.mover(Direccion.DERECHA).mover(Direccion.IZQUIERDA);
        assertEquals(new Posicion(2, 2), p);

        p = new Posicion(5, 5);
        p = p.mover(Direccion.ABAJO).mover(Direccion.ARRIBA);
        assertEquals(new Posicion(5, 5), p);
    }

    /**
     * Verifica que mover desde posición (0,0) en direcciones negativas resulta en coordenadas negativas.
     */
    @Test
    public void mover_desde_origen_genera_negativas() {
        Posicion p = new Posicion(0, 0);
        Posicion arriba = p.mover(Direccion.ARRIBA);
        Posicion izquierda = p.mover(Direccion.IZQUIERDA);

        assertEquals(-1, arriba.getFila());
        assertEquals(-1, izquierda.getColumna());
    }

    // =============== PRUEBAS: Comparación (equals) ===============

    /**
     * Verifica que dos posiciones con mismas coordenadas son iguales.
     */
    @Test
    public void equals_mismas_coordenadas() {
        Posicion p1 = new Posicion(2, 2);
        Posicion p2 = new Posicion(2, 2);
        assertEquals(p1, p2);
    }

    /**
     * Verifica que dos posiciones con diferentes coordenadas no son iguales.
     */
    @Test
    public void equals_diferentes_coordenadas() {
        Posicion p1 = new Posicion(2, 2);
        Posicion p2 = new Posicion(2, 3);
        assertNotEquals(p1, p2);

        Posicion p3 = new Posicion(3, 2);
        assertNotEquals(p1, p3);
    }

    /**
     * Verifica que una posición es igual a sí misma.
     */
    @Test
    public void equals_consigo_misma() {
        assertTrue(posicion22.equals(posicion22));
    }

    /**
     * Verifica que posición no es igual a null.
     */
    @Test
    public void equals_null_falla() {
        assertNotEquals(posicion22, null);
        assertFalse(posicion22.equals(null));
    }

    /**
     * Verifica que posición no es igual a objeto de otro tipo.
     */
    @Test
    public void equals_tipo_diferente_falla() {
        assertNotEquals(posicion22, "2,2");
        assertNotEquals(posicion22, 22);
        assertNotEquals(posicion22, new Object());
    }

    /**
     * Verifica que equals es reflexiva (x.equals(x)).
     */
    @Test
    public void equals_reflexiva() {
        assertTrue(posicion22.equals(posicion22));
    }

    /**
     * Verifica que equals es simétrica (x.equals(y) == y.equals(x)).
     */
    @Test
    public void equals_simetrica() {
        Posicion p1 = new Posicion(1, 1);
        Posicion p2 = new Posicion(1, 1);
        assertTrue(p1.equals(p2));
        assertTrue(p2.equals(p1));
    }

    /**
     * Verifica que equals es transitiva (x.equals(y) && y.equals(z) => x.equals(z)).
     */
    @Test
    public void equals_transitiva() {
        Posicion p1 = new Posicion(1, 1);
        Posicion p2 = new Posicion(1, 1);
        Posicion p3 = new Posicion(1, 1);
        assertTrue(p1.equals(p2));
        assertTrue(p2.equals(p3));
        assertTrue(p1.equals(p3));
    }

    // =============== PRUEBAS: toString ===============

    /**
     * Verifica que toString retorna formato correcto.
     */
    @Test
    public void tostring_formato_correcto() {
        assertEquals("(0,0)", posicion00.toString());
        assertEquals("(2,2)", posicion22.toString());
        assertEquals("(4,4)", posicion44.toString());
    }

    /**
     * Verifica que toString funciona con coordenadas negativas.
     */
    @Test
    public void tostring_coordenadas_negativas() {
        Posicion neg = new Posicion(-1, -2);
        assertEquals("(-1,-2)", neg.toString());
    }

    /**
     * Verifica que toString es consistente con getters.
     */
    @Test
    public void tostring_consistente_con_getters() {
        Posicion p = new Posicion(5, 7);
        String esperado = String.format("(%d,%d)", p.getFila(), p.getColumna());
        assertEquals(esperado, p.toString());
    }

    // =============== PRUEBAS: Integración ===============

    /**
     * Verifica una secuencia compleja de movimientos.
     */
    @Test
    public void secuencia_movimientos_compleja() {
        Posicion inicio = new Posicion(0, 0);
        // Ruta: derecha 3, abajo 2, izquierda 1
        Posicion fin = inicio
                .mover(Direccion.DERECHA)
                .mover(Direccion.DERECHA)
                .mover(Direccion.DERECHA)
                .mover(Direccion.ABAJO)
                .mover(Direccion.ABAJO)
                .mover(Direccion.IZQUIERDA);

        assertEquals(new Posicion(2, 2), fin);
    }

    /**
     * Verifica que las coordenadas originales permanecen sin cambios después de movimientos.
     */
    @Test
    public void posicion_original_permanece_inalterada() {
        Posicion original = new Posicion(2, 2);
        Posicion inicio_fila = original.getFila();
        Posicion inicio_columna = original.getColumna();

        original.mover(Direccion.DERECHA);
        original.mover(Direccion.ABAJO);

        assertEquals(2, original.getFila());
        assertEquals(2, original.getColumna());
    }
}
