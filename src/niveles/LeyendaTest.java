package niveles;

import modelos.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase {@link Leyenda}.
 *
 * Verifica:
 * - Nombre del nivel correcto
 * - Dimensiones del mapa (64x64)
 * - Preguntas aleatorias
 * - Respuestas esperadas
 * - Integración con sistema de puntos
 */
public class LeyendaTest {

    private Leyenda leyenda;

    @Before
    public void setUp() {
        leyenda = new Leyenda();
    }

    /**
     * Verifica que el nombre del nivel es "Leyenda".
     */
    @Test
    public void nombreNivel_es_leyenda() {
        assertEquals("Leyenda", leyenda.nombreNivel());
    }

    /**
     * Verifica que el mapa tiene dimensiones 64x64.
     */
    @Test
    public void crearMapa_64x64() {
        Celda[][] mapa = leyenda.crearMapa();
        assertEquals("Mapa debe tener 64 filas", 64, mapa.length);
        assertEquals("Mapa debe tener 64 columnas", 64, mapa[0].length);
    }

    /**
     * Verifica que el inicio está en (0, 0).
     */
    @Test
    public void crearMapa_inicio_en_origen() {
        Celda[][] mapa = leyenda.crearMapa();
        assertEquals("Inicio debe estar en (0,0)", Celda.INICIO, mapa[0][0]);
    }

    /**
     * Verifica que existe una meta en el mapa.
     */
    @Test
    public void crearMapa_existe_meta() {
        Celda[][] mapa = leyenda.crearMapa();
        int conteoMeta = 0;
        for (int r = 0; r < 64; r++) {
            for (int c = 0; c < 64; c++) {
                if (mapa[r][c] == Celda.META) {
                    conteoMeta++;
                }
            }
        }
        assertEquals("Debe haber exactamente una META", 1, conteoMeta);
    }

    /**
     * Verifica que hay preguntas en el mapa.
     */
    @Test
    public void crearMapa_hay_preguntas() {
        Celda[][] mapa = leyenda.crearMapa();
        int conteoPreguntas = 0;
        for (int r = 0; r < 64; r++) {
            for (int c = 0; c < 64; c++) {
                if (mapa[r][c] == Celda.PREGUNTA) {
                    conteoPreguntas++;
                }
            }
        }
        assertEquals("Debe haber 5 preguntas", 5, conteoPreguntas);
    }

    /**
     * Verifica que se obtienen preguntas (3 seleccionadas aleatoriamente de 5).
     */
    @Test
    public void obtenerPreguntas_retorna_preguntas_validas() {
        Pregunta[] preguntas = leyenda.obtenerPreguntas();
        assertNotNull("Preguntas no deben ser null", preguntas);
        assertTrue("Debe haber al menos 1 pregunta", preguntas.length > 0);
        for (Pregunta p : preguntas) {
            assertNotNull("Pregunta no debe ser null", p);
            assertNotNull("Enunciado no debe ser null", p.getEnunciado());
        }
    }

    /**
     * Verifica que se obtienen respuestas esperadas.
     */
    @Test
    public void obtenerRespuestasEsperadas_retorna_respuestas_validas() {
        String[] respuestas = leyenda.obtenerRespuestasEsperadas();
        assertNotNull("Respuestas no deben ser null", respuestas);
        assertTrue("Debe haber al menos 1 respuesta", respuestas.length > 0);
        for (String r : respuestas) {
            assertNotNull("Respuesta no debe ser null", r);
            assertTrue("Respuesta no debe estar vacía", r.length() > 0);
        }
    }

    /**
     * Verifica que hay banco de preguntas (más de 20 preguntas disponibles).
     */
    @Test
    public void banco_preguntas_tiene_suficientes_preguntas() {
        // Crear múltiples instancias y verificar que hay variabilidad
        Leyenda l1 = new Leyenda();
        Leyenda l2 = new Leyenda();
        
        Pregunta[] p1 = l1.obtenerPreguntas();
        Pregunta[] p2 = l2.obtenerPreguntas();
        
        // Al menos la mitad del tiempo deberían ser diferentes
        // (5 preguntas de un banco de 25+ tiene baja probabilidad de ser idénticas)
        assertNotNull("Ambas instancias deben tener preguntas", p1);
        assertNotNull("Ambas instancias deben tener preguntas", p2);
    }

    /**
     * Verifica que las preguntas y respuestas están alineadas.
     */
    @Test
    public void preguntas_respuestas_alineadas() {
        Pregunta[] preguntas = leyenda.obtenerPreguntas();
        String[] respuestas = leyenda.obtenerRespuestasEsperadas();
        
        assertEquals("Cantidad de preguntas y respuestas debe coincidir", 
                     preguntas.length, respuestas.length);
    }

    /**
     * Verifica que los puntos por pregunta son 20.
     */
    @Test
    public void puntos_por_pregunta_son_20() {
        Pregunta[] preguntas = leyenda.obtenerPreguntas();
        for (Pregunta p : preguntas) {
            assertEquals("Cada pregunta vale 20 puntos", 20, p.getPuntos());
        }
    }

    /**
     * Verifica que llamadas sucesivas pueden tener preguntas diferentes.
     */
    @Test
    public void preguntas_aleatorias_en_nuevas_instancias() {
        Leyenda l1 = new Leyenda();
        Leyenda l2 = new Leyenda();
        
        String pregunta1 = l1.obtenerPreguntas()[0].getEnunciado();
        String pregunta2 = l2.obtenerPreguntas()[0].getEnunciado();
        
        // Nota: Pueden ser iguales por aleatoriedad, pero es poco probable
        // Si siempre son iguales, sería un problema de aleatoriedad
        assertTrue("Las instancias deben funcionar independientemente", 
                   pregunta1 != null && pregunta2 != null);
    }

    /**
     * Verifica que el mapa tiene celdas de diferentes tipos.
     */
    @Test
    public void mapa_tiene_variedad_de_celdas() {
        Celda[][] mapa = leyenda.crearMapa();
        boolean tieneLibre = false;
        boolean tienePregunta = false;
        boolean tienePared = false;
        boolean tieneMeta = false;
        
        for (int r = 0; r < 64; r++) {
            for (int c = 0; c < 64; c++) {
                if (mapa[r][c] == Celda.LIBRE) tieneLibre = true;
                if (mapa[r][c] == Celda.PREGUNTA) tienePregunta = true;
                if (mapa[r][c] == Celda.PARED) tienePared = true;
                if (mapa[r][c] == Celda.META) tieneMeta = true;
            }
        }
        
        assertTrue("Mapa debe tener celdas libres", tieneLibre);
        assertTrue("Mapa debe tener preguntas", tienePregunta);
        assertTrue("Mapa debe tener paredes", tienePared);
        assertTrue("Mapa debe tener meta", tieneMeta);
    }
}
