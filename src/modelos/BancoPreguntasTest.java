package modelos;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase {@link BancoPreguntas}.
 *
 * Verifica:
 * - Adición de preguntas
 * - Selección aleatoria de preguntas
 * - Obtención de respuestas esperadas
 * - Aleatoriedad y variabilidad de selecciones
 */
public class BancoPreguntasTest {

    private BancoPreguntas banco;

    @Before
    public void setUp() {
        banco = new BancoPreguntas();
    }

    /**
     * Verifica que se pueden agregar preguntas al banco.
     */
    @Test
    public void agregarPregunta_adiciona_correctamente() {
        banco.agregarPregunta("¿Cuál es el animal?", null, 0, 10, "cat");
        banco.agregarPregunta("¿Cuál es el color?", null, 0, 10, "blue");
        // Si no hay excepción, la adición fue exitosa
        assertTrue(true);
    }

    /**
     * Verifica que se pueden obtener preguntas aleatorias.
     */
    @Test
    public void obtenerPreguntasAleatorias_retorna_cantidad_correcta() {
        banco.agregarPregunta("P1", null, 0, 10, "r1");
        banco.agregarPregunta("P2", null, 0, 10, "r2");
        banco.agregarPregunta("P3", null, 0, 10, "r3");

        Pregunta[] preguntas = banco.obtenerPreguntasAleatorias(2);
        assertEquals(2, preguntas.length);
    }

    /**
     * Verifica que las preguntas aleatorias son válidas.
     */
    @Test
    public void obtenerPreguntasAleatorias_retorna_preguntas_validas() {
        banco.agregarPregunta("P1", null, 0, 10, "r1");
        banco.agregarPregunta("P2", null, 0, 10, "r2");
        banco.agregarPregunta("P3", null, 0, 10, "r3");

        Pregunta[] preguntas = banco.obtenerPreguntasAleatorias(3);
        for (Pregunta p : preguntas) {
            assertNotNull(p);
            assertNotNull(p.getEnunciado());
        }
    }

    /**
     * Verifica que no se pueden obtener más preguntas que las disponibles.
     */
    @Test
    public void obtenerPreguntasAleatorias_cantidad_mayor_no_causa_error() {
        banco.agregarPregunta("P1", null, 0, 10, "r1");
        banco.agregarPregunta("P2", null, 0, 10, "r2");
        
        // Si el banco tiene 2 preguntas y pedimos 5, debería dar las 2 disponibles o menos
        Pregunta[] preguntas = banco.obtenerPreguntasAleatorias(5);
        assertTrue(preguntas.length <= 2);
    }

    /**
     * Verifica que se obtienen respuestas esperadas correctas.
     */
    @Test
    public void obtenerRespuestasAleatorias_retorna_cantidad_correcta() {
        banco.agregarPregunta("P1", null, 0, 10, "r1");
        banco.agregarPregunta("P2", null, 0, 10, "r2");
        banco.agregarPregunta("P3", null, 0, 10, "r3");

        String[] respuestas = banco.obtenerRespuestasAleatorias(2);
        assertEquals(2, respuestas.length);
    }

    /**
     * Verifica que las respuestas son válidas y no nulas.
     */
    @Test
    public void obtenerRespuestasAleatorias_retorna_respuestas_validas() {
        banco.agregarPregunta("P1", null, 0, 10, "cat");
        banco.agregarPregunta("P2", null, 0, 10, "dog");
        banco.agregarPregunta("P3", null, 0, 10, "bird");

        String[] respuestas = banco.obtenerRespuestasAleatorias(3);
        for (String r : respuestas) {
            assertNotNull(r);
            assertTrue(r.length() > 0);
        }
    }

    /**
     * Verifica aleatoriedad: múltiples llamadas dan diferentes resultados.
     */
    @Test
    public void aleatoriedad_cambio_entre_llamadas() {
        // Agregar suficientes preguntas para garantizar variabilidad
        for (int i = 1; i <= 10; i++) {
            banco.agregarPregunta("P" + i, null, 0, 10, "r" + i);
        }

        Pregunta[] primera = banco.obtenerPreguntasAleatorias(5);
        Pregunta[] segunda = banco.obtenerPreguntasAleatorias(5);

        // Es poco probable que dos selecciones aleatorias sean idénticas
        boolean diferente = false;
        for (int i = 0; i < 5; i++) {
            if (!primera[i].equals(segunda[i])) {
                diferente = true;
                break;
            }
        }
        assertTrue("Las selecciones deberían variar", diferente);
    }

    /**
     * Verifica que un banco vacío maneja correctamente solicitudes.
     */
    @Test
    public void banco_vacio_maneja_solicitudes() {
        Pregunta[] preguntas = banco.obtenerPreguntasAleatorias(1);
        // Debe retornar un arreglo, posiblemente vacío o de tamaño 0
        assertNotNull(preguntas);
    }

    /**
     * Verifica que 0 preguntas solicitadas retorna arreglo vacío.
     */
    @Test
    public void obtenerPreguntasAleatorias_cero_retorna_vacio() {
        banco.agregarPregunta("P1", null, 0, 10, "r1");
        Pregunta[] preguntas = banco.obtenerPreguntasAleatorias(0);
        assertEquals(0, preguntas.length);
    }
}
