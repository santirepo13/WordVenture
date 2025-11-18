package modelos;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase {@link GeneradorMapas}.
 *
 * Verifica:
 * - Colocación correcta de preguntas y salida
 * - No colisión de elementos
 * - Validez de mapas modificados
 */
public class GeneradorMapasTest {

    private Celda[][] mapa8x8;
    private Celda[][] mapa16x16;

    @Before
    public void setUp() {
        // Crear mapa de prueba 8x8
        mapa8x8 = new Celda[8][8];
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                mapa8x8[r][c] = Celda.LIBRE;
            }
        }
        mapa8x8[0][0] = Celda.INICIO;

        // Crear mapa de prueba 16x16
        mapa16x16 = new Celda[16][16];
        for (int r = 0; r < 16; r++) {
            for (int c = 0; c < 16; c++) {
                mapa16x16[r][c] = Celda.LIBRE;
            }
        }
        mapa16x16[0][0] = Celda.INICIO;
    }

    /**
     * Verifica que se colocan preguntas correctamente en un mapa 8x8.
     */
    @Test
    public void colocarPreguntasYSalida_coloca_preguntas_8x8() {
        Celda[][] mapa = GeneradorMapas.colocarPreguntasYSalida(mapa8x8, 3);
        int conteoPreguntas = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (mapa[r][c] == Celda.PREGUNTA) {
                    conteoPreguntas++;
                }
            }
        }
        assertEquals("Debe haber 3 preguntas", 3, conteoPreguntas);
    }

    /**
     * Verifica que se coloca exactamente una salida (META).
     */
    @Test
    public void colocarPreguntasYSalida_coloca_una_meta() {
        Celda[][] mapa = GeneradorMapas.colocarPreguntasYSalida(mapa8x8, 3);
        int conteoMeta = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (mapa[r][c] == Celda.META) {
                    conteoMeta++;
                }
            }
        }
        assertEquals("Debe haber exactamente una META", 1, conteoMeta);
    }

    /**
     * Verifica que el INICIO se preserva en (0, 0).
     */
    @Test
    public void colocarPreguntasYSalida_preserva_inicio() {
        Celda[][] mapa = GeneradorMapas.colocarPreguntasYSalida(mapa8x8, 3);
        assertEquals("Inicio debe permanecer en (0,0)", Celda.INICIO, mapa[0][0]);
    }

    /**
     * Verifica que la META no está en la posición del INICIO.
     */
    @Test
    public void colocarPreguntasYSalida_meta_no_en_inicio() {
        Celda[][] mapa = GeneradorMapas.colocarPreguntasYSalida(mapa8x8, 3);
        assertNotEquals("META no debe estar en (0,0)", Celda.META, mapa[0][0]);
    }

    /**
     * Verifica que no hay colisiones entre preguntas y META.
     */
    @Test
    public void colocarPreguntasYSalida_sin_colisiones() {
        Celda[][] mapa = GeneradorMapas.colocarPreguntasYSalida(mapa16x16, 3);
        
        int totalPreguntasYMeta = 0;
        for (int r = 0; r < 16; r++) {
            for (int c = 0; c < 16; c++) {
                if (mapa[r][c] == Celda.PREGUNTA || mapa[r][c] == Celda.META) {
                    totalPreguntasYMeta++;
                }
            }
        }
        assertEquals("3 preguntas + 1 META = 4 elementos", 4, totalPreguntasYMeta);
    }

    /**
     * Verifica que se lanzan excepciones con parámetros inválidos.
     */
    @Test(expected = IllegalArgumentException.class)
    public void colocarPreguntasYSalida_no_suficientes_celdas_falla() {
        // Un mapa 8x8 tiene 64 celdas. Con 1 INICIO + 63 solicitadas, falta espacio
        Celda[][] mapa = new Celda[8][8];
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                mapa[r][c] = Celda.PARED;
            }
        }
        mapa[0][0] = Celda.INICIO;
        GeneradorMapas.colocarPreguntasYSalida(mapa, 100);
    }

    /**
     * Verifica que múltiples llamadas generan posiciones diferentes.
     */
    @Test
    public void colocarPreguntasYSalida_aleatoriedad() {
        Celda[][] mapa1 = GeneradorMapas.colocarPreguntasYSalida(crearMapaLimpio(16), 3);
        Celda[][] mapa2 = GeneradorMapas.colocarPreguntasYSalida(crearMapaLimpio(16), 3);
        
        // Buscar posiciones diferentes (excepto en inicio)
        boolean diferente = false;
        for (int r = 0; r < 16; r++) {
            for (int c = 0; c < 16; c++) {
                if ((r != 0 || c != 0) && mapa1[r][c] != mapa2[r][c]) {
                    diferente = true;
                    break;
                }
            }
            if (diferente) break;
        }
        assertTrue("Llamadas múltiples deberían tener posiciones diferentes", diferente);
    }

    /**
     * Verifica que con un número de preguntas válido funciona.
     */
    @Test
    public void colocarPreguntasYSalida_varias_cantidades() {
        for (int i = 1; i <= 5; i++) {
            Celda[][] mapa = GeneradorMapas.colocarPreguntasYSalida(crearMapaLimpio(16), i);
            int conteoPreguntas = 0;
            for (int r = 0; r < 16; r++) {
                for (int c = 0; c < 16; c++) {
                    if (mapa[r][c] == Celda.PREGUNTA) {
                        conteoPreguntas++;
                    }
                }
            }
            assertEquals("Debe haber " + i + " preguntas", i, conteoPreguntas);
        }
    }

    /**
     * Helper: crea un mapa limpio de tamaño especificado.
     */
    private Celda[][] crearMapaLimpio(int tamanio) {
        Celda[][] mapa = new Celda[tamanio][tamanio];
        for (int r = 0; r < tamanio; r++) {
            for (int c = 0; c < tamanio; c++) {
                mapa[r][c] = Celda.LIBRE;
            }
        }
        mapa[0][0] = Celda.INICIO;
        return mapa;
    }
}
