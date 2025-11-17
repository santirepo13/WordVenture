package modelos;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias completas para la clase {@link Pregunta}.
 *
 * Verifica:
 * - Creación de preguntas
 * - Verificación de respuestas
 * - Normalización de puntos
 * - Getters
 */
public class PreguntaTest {

    private Pregunta preguntaMultiple;
    private Pregunta preguntaTexto;
    private String[] opciones;

    @Before
    public void setUp() {
        opciones = new String[]{"París", "Londres", "Berlín"};
        preguntaMultiple = new Pregunta(
                "¿Cuál es la capital de Francia?",
                opciones,
                0,  // Índice correcto
                10  // Puntos
        );

        preguntaTexto = new Pregunta(
                "Traduce 'gato' al inglés:",
                null,  // Sin opciones
                0,     // No se usa
                15
        );
    }

    // =============== PRUEBAS: Creación ===============

    /**
     * Verifica que una pregunta con opciones se crea correctamente.
     */
    @Test
    public void crear_pregunta_con_opciones() {
        assertNotNull(preguntaMultiple);
        assertEquals("¿Cuál es la capital de Francia?", preguntaMultiple.getEnunciado());
        assertEquals(3, preguntaMultiple.getOpciones().length);
        assertEquals(10, preguntaMultiple.getPuntos());
    }

    /**
     * Verifica que una pregunta sin opciones se crea correctamente.
     */
    @Test
    public void crear_pregunta_sin_opciones() {
        assertNotNull(preguntaTexto);
        assertEquals("Traduce 'gato' al inglés:", preguntaTexto.getEnunciado());
        assertEquals(0, preguntaTexto.getOpciones().length);
        assertEquals(15, preguntaTexto.getPuntos());
    }

    /**
     * Verifica que las opciones se almacenan correctamente.
     */
    @Test
    public void opciones_almacenadas_correctamente() {
        String[] opts = preguntaMultiple.getOpciones();
        assertEquals(3, opts.length);
        assertEquals("París", opts[0]);
        assertEquals("Londres", opts[1]);
        assertEquals("Berlín", opts[2]);
    }

    /**
     * Verifica que el índice correcto se almacena.
     */
    @Test
    public void indice_correcto_almacenado() {
        assertEquals(0, preguntaMultiple.getIndiceCorrecto());

        Pregunta q2 = new Pregunta("Pregunta", new String[]{"a", "b", "c"}, 2, 10);
        assertEquals(2, q2.getIndiceCorrecto());
    }

    // =============== PRUEBAS: Normalización de puntos ===============

    /**
     * Verifica que puntos negativos se normalizan a 0.
     */
    @Test
    public void puntos_negativos_normalizados() {
        Pregunta q = new Pregunta("Pregunta", null, 0, -5);
        assertEquals(0, q.getPuntos());
    }

    /**
     * Verifica que puntos cero se mantienen.
     */
    @Test
    public void puntos_cero_mantenidos() {
        Pregunta q = new Pregunta("Pregunta", null, 0, 0);
        assertEquals(0, q.getPuntos());
    }

    /**
     * Verifica que puntos positivos se mantienen.
     */
    @Test
    public void puntos_positivos_mantenidos() {
        Pregunta q1 = new Pregunta("P1", null, 0, 5);
        Pregunta q2 = new Pregunta("P2", null, 0, 100);
        Pregunta q3 = new Pregunta("P3", null, 0, 1000);

        assertEquals(5, q1.getPuntos());
        assertEquals(100, q2.getPuntos());
        assertEquals(1000, q3.getPuntos());
    }

    // =============== PRUEBAS: Verificación de respuestas ===============

    /**
     * Verifica que respuesta correcta retorna verdadero.
     */
    @Test
    public void respuesta_correcta_verdadera() {
        assertTrue(preguntaMultiple.verificarRespuesta(0)); // París es correcta
    }

    /**
     * Verifica que respuesta incorrecta retorna falso.
     */
    @Test
    public void respuesta_incorrecta_falsa() {
        assertFalse(preguntaMultiple.verificarRespuesta(1)); // Londres es incorrecta
        assertFalse(preguntaMultiple.verificarRespuesta(2)); // Berlín es incorrecta
    }

    /**
     * Verifica que índice fuera de rango retorna falso.
     */
    @Test
    public void indice_fuera_de_rango_falso() {
        assertFalse(preguntaMultiple.verificarRespuesta(10));
        assertFalse(preguntaMultiple.verificarRespuesta(-1));
        assertFalse(preguntaMultiple.verificarRespuesta(100));
    }

    /**
     * Verifica que diferentes índices correctos funcionan.
     */
    @Test
    public void diferentes_indices_correctos() {
        Pregunta q0 = new Pregunta("Q", new String[]{"a", "b", "c"}, 0, 10);
        Pregunta q1 = new Pregunta("Q", new String[]{"a", "b", "c"}, 1, 10);
        Pregunta q2 = new Pregunta("Q", new String[]{"a", "b", "c"}, 2, 10);

        assertTrue(q0.verificarRespuesta(0));
        assertTrue(q1.verificarRespuesta(1));
        assertTrue(q2.verificarRespuesta(2));

        assertFalse(q0.verificarRespuesta(1));
        assertFalse(q1.verificarRespuesta(0));
    }

    /**
     * Verifica que preguntas diferentes son independientes.
     */
    @Test
    public void preguntas_independientes() {
        Pregunta p1 = new Pregunta("P1", new String[]{"a", "b"}, 0, 10);
        Pregunta p2 = new Pregunta("P2", new String[]{"x", "y"}, 1, 10);

        assertTrue(p1.verificarRespuesta(0));
        assertTrue(p2.verificarRespuesta(1));

        assertFalse(p1.verificarRespuesta(1));
        assertFalse(p2.verificarRespuesta(0));
    }

    // =============== PRUEBAS: Getters ===============

    /**
     * Verifica que getEnunciado retorna el enunciado correcto.
     */
    @Test
    public void get_enunciado_correcto() {
        assertEquals("¿Cuál es la capital de Francia?", preguntaMultiple.getEnunciado());
        assertEquals("Traduce 'gato' al inglés:", preguntaTexto.getEnunciado());
    }

    /**
     * Verifica que getOpciones retorna arreglo válido.
     */
    @Test
    public void get_opciones_validas() {
        String[] opts = preguntaMultiple.getOpciones();
        assertNotNull(opts);
        assertEquals(3, opts.length);
    }

    /**
     * Verifica que getOpciones devuelve arreglo vacío cuando es null.
     */
    @Test
    public void get_opciones_null_retorna_vacio() {
        String[] opts = preguntaTexto.getOpciones();
        assertNotNull(opts);
        assertEquals(0, opts.length);
    }

    /**
     * Verifica que getIndiceCorrecto retorna el índice correcto.
     */
    @Test
    public void get_indice_correcto() {
        assertEquals(0, preguntaMultiple.getIndiceCorrecto());
        assertEquals(0, preguntaTexto.getIndiceCorrecto());
    }

    /**
     * Verifica que getPuntos retorna puntos correctos.
     */
    @Test
    public void get_puntos_correcto() {
        assertEquals(10, preguntaMultiple.getPuntos());
        assertEquals(15, preguntaTexto.getPuntos());
    }

    // =============== PRUEBAS: toString ===============

    /**
     * Verifica que toString retorna una representación válida.
     */
    @Test
    public void tostring_valido() {
        String str = preguntaMultiple.toString();
        assertNotNull(str);
        assertTrue(str.contains("Pregunta"));
        assertTrue(str.contains("¿Cuál es la capital de Francia?"));
    }

    /**
     * Verifica que toString contiene información de opciones.
     */
    @Test
    public void tostring_contiene_opciones() {
        String str = preguntaMultiple.toString();
        assertTrue(str.contains("París"));
        assertTrue(str.contains("Londres"));
        assertTrue(str.contains("Berlín"));
    }

    // =============== PRUEBAS: Integración ===============

    /**
     * Verifica una pregunta en escenario completo.
     */
    @Test
    public void escenario_completo() {
        // Crear pregunta
        String[] opcs = new String[]{"Opción A", "Opción B", "Opción C"};
        Pregunta q = new Pregunta("Pregunta de ejemplo", opcs, 1, 20);

        // Verificar propiedades
        assertEquals("Pregunta de ejemplo", q.getEnunciado());
        assertEquals(20, q.getPuntos());
        assertEquals(1, q.getIndiceCorrecto());

        // Probar respuestas
        assertFalse(q.verificarRespuesta(0));
        assertTrue(q.verificarRespuesta(1));
        assertFalse(q.verificarRespuesta(2));
    }

    /**
     * Verifica que enunciados con caracteres especiales funcionan.
     */
    @Test
    public void enunciados_especiales() {
        Pregunta q1 = new Pregunta("¿Cuál es?", null, 0, 10);
        Pregunta q2 = new Pregunta("Traduce 'word' 'palabra'", null, 0, 10);
        Pregunta q3 = new Pregunta("Ejemplo #1: Test@2025", null, 0, 10);

        assertNotNull(q1.getEnunciado());
        assertNotNull(q2.getEnunciado());
        assertNotNull(q3.getEnunciado());
    }

    /**
     * Verifica que opciones largas se manejan correctamente.
     */
    @Test
    public void opciones_largas() {
        String[] opcsLargas = new String[]{
                "Esto es una opción muy larga con muchos caracteres",
                "Otra opción igualmente extensa",
                "Una tercera opción"
        };
        Pregunta q = new Pregunta("Pregunta", opcsLargas, 0, 10);

        assertTrue(q.verificarRespuesta(0));
        assertFalse(q.verificarRespuesta(1));
    }
}
