package niveles;

import modelos.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para las implementaciones de {@link Nivel}.
 *
 * Verifica:
 * - Nombres de niveles correctos
 * - Mapas válidos (dimensiones, celdas especiales)
 * - Preguntas válidas
 * - Respuestas esperadas
 */
public class NivelTest {

    private Basico nivelBasico;
    private Intermedio nivelIntermedio;
    private Avanzado nivelAvanzado;
    private Leyenda nivelLeyenda;

    @Before
    public void setUp() {
        nivelBasico = new Basico();
        nivelIntermedio = new Intermedio();
        nivelAvanzado = new Avanzado();
        nivelLeyenda = new Leyenda();
    }

    // =============== PRUEBAS: Nombres de niveles ===============

    /**
     * Verifica que cada nivel tiene el nombre correcto.
     */
    @Test
    public void nombres_niveles_correctos() {
        assertEquals("Basico", nivelBasico.nombreNivel());
        assertEquals("Intermedio", nivelIntermedio.nombreNivel());
        assertEquals("Avanzado", nivelAvanzado.nombreNivel());
        assertEquals("Leyenda", nivelLeyenda.nombreNivel());
    }

    // =============== PRUEBAS NIVEL BASICO ===============

    /**
     * Verifica que el mapa básico tiene dimensiones 5x5.
     */
    @Test
    public void basico_mapa_dimensiones() {
        Celda[][] mapa = nivelBasico.crearMapa();
        assertEquals(5, mapa.length);
        assertEquals(5, mapa[0].length);
    }

    /**
     * Verifica que el nivel básico tiene celdas de inicio y meta.
     */
    @Test
    public void basico_mapa_tiene_inicio_y_meta() {
        Celda[][] mapa = nivelBasico.crearMapa();
        assertEquals(Celda.INICIO, mapa[0][0]);
        assertEquals(Celda.META, mapa[4][4]);
    }

    /**
     * Verifica que el nivel básico tiene preguntas.
     */
    @Test
    public void basico_tiene_preguntas() {
        Pregunta[] preguntas = nivelBasico.obtenerPreguntas();
        assertNotNull(preguntas);
        assertTrue(preguntas.length > 0);
        assertEquals(3, preguntas.length);
    }

    /**
     * Verifica que las preguntas básicas tienen enunciados no vacíos.
     */
    @Test
    public void basico_preguntas_con_enunciados() {
        Pregunta[] preguntas = nivelBasico.obtenerPreguntas();
        for (Pregunta p : preguntas) {
            assertNotNull(p.getEnunciado());
            assertFalse(p.getEnunciado().isEmpty());
        }
    }

    /**
     * Verifica que el nivel básico tiene respuestas esperadas.
     */
    @Test
    public void basico_tiene_respuestas() {
        String[] respuestas = nivelBasico.obtenerRespuestasEsperadas();
        assertNotNull(respuestas);
        assertEquals(3, respuestas.length);
    }

    /**
     * Verifica que las respuestas básicas no son vacías.
     */
    @Test
    public void basico_respuestas_no_vacias() {
        String[] respuestas = nivelBasico.obtenerRespuestasEsperadas();
        for (String r : respuestas) {
            assertNotNull(r);
            assertFalse(r.isEmpty());
        }
    }

    /**
     * Verifica que el nivel básico no tiene paredes.
     */
    @Test
    public void basico_sin_paredes() {
        Celda[][] mapa = nivelBasico.crearMapa();
        int contadorParedes = 0;
        for (int r = 0; r < mapa.length; r++) {
            for (int c = 0; c < mapa[r].length; c++) {
                if (mapa[r][c] == Celda.PARED) {
                    contadorParedes++;
                }
            }
        }
        assertEquals(0, contadorParedes);
    }

    /**
     * Verifica que el nivel básico tiene preguntas.
     */
    @Test
    public void basico_tiene_3_preguntas() {
        Pregunta[] preguntas = nivelBasico.obtenerPreguntas();
        assertEquals(3, preguntas.length);
    }

    // =============== PRUEBAS NIVEL INTERMEDIO ===============

    /**
     * Verifica que el mapa intermedio tiene dimensiones 6x6.
     */
    @Test
    public void intermedio_mapa_dimensiones() {
        Celda[][] mapa = nivelIntermedio.crearMapa();
        assertEquals(6, mapa.length);
        assertEquals(6, mapa[0].length);
    }

    /**
     * Verifica que el nivel intermedio tiene inicio y meta.
     */
    @Test
    public void intermedio_mapa_tiene_inicio_y_meta() {
        Celda[][] mapa = nivelIntermedio.crearMapa();
        assertEquals(Celda.INICIO, mapa[0][0]);
        assertEquals(Celda.META, mapa[5][5]);
    }

    /**
     * Verifica que el nivel intermedio tiene paredes.
     */
    @Test
    public void intermedio_tiene_paredes() {
        Celda[][] mapa = nivelIntermedio.crearMapa();
        int contadorParedes = 0;
        for (int r = 0; r < mapa.length; r++) {
            for (int c = 0; c < mapa[r].length; c++) {
                if (mapa[r][c] == Celda.PARED) {
                    contadorParedes++;
                }
            }
        }
        assertTrue(contadorParedes > 0);
    }

    /**
     * Verifica que el nivel intermedio tiene preguntas.
     */
    @Test
    public void intermedio_tiene_preguntas() {
        Pregunta[] preguntas = nivelIntermedio.obtenerPreguntas();
        assertEquals(3, preguntas.length);
    }

    /**
     * Verifica que las respuestas intermedias tienen la misma cantidad que preguntas.
     */
    @Test
    public void intermedio_respuestas_cantidad() {
        Pregunta[] preguntas = nivelIntermedio.obtenerPreguntas();
        String[] respuestas = nivelIntermedio.obtenerRespuestasEsperadas();
        assertEquals(preguntas.length, respuestas.length);
    }

    /**
     * Verifica que el nivel intermedio tiene dificultad mayor que básico.
     */
    @Test
    public void intermedio_mas_paredes_que_basico() {
        Celda[][] mapaBasico = nivelBasico.crearMapa();
        Celda[][] mapaIntermedio = nivelIntermedio.crearMapa();

        int paredesBasico = contarParedes(mapaBasico);
        int paredesIntermedio = contarParedes(mapaIntermedio);

        assertTrue(paredesIntermedio > paredesBasico);
    }

    // =============== PRUEBAS NIVEL AVANZADO ===============

    /**
     * Verifica que el mapa avanzado tiene dimensiones 7x7.
     */
    @Test
    public void avanzado_mapa_dimensiones() {
        Celda[][] mapa = nivelAvanzado.crearMapa();
        assertEquals(7, mapa.length);
        assertEquals(7, mapa[0].length);
    }

    /**
     * Verifica que el nivel avanzado tiene inicio y meta.
     */
    @Test
    public void avanzado_mapa_tiene_inicio_y_meta() {
        Celda[][] mapa = nivelAvanzado.crearMapa();
        assertEquals(Celda.INICIO, mapa[0][0]);
        assertEquals(Celda.META, mapa[6][6]);
    }

    /**
     * Verifica que el nivel avanzado tiene paredes.
     */
    @Test
    public void avanzado_tiene_paredes() {
        Celda[][] mapa = nivelAvanzado.crearMapa();
        assertTrue(contarParedes(mapa) > 0);
    }

    /**
     * Verifica que el nivel avanzado tiene preguntas.
     */
    @Test
    public void avanzado_tiene_preguntas() {
        Pregunta[] preguntas = nivelAvanzado.obtenerPreguntas();
        assertEquals(3, preguntas.length);
    }

    /**
     * Verifica que el nivel avanzado tiene más paredes que intermedio.
     */
    @Test
    public void avanzado_mas_paredes_que_intermedio() {
        Celda[][] mapaIntermedio = nivelIntermedio.crearMapa();
        Celda[][] mapaAvanzado = nivelAvanzado.crearMapa();

        int paredesIntermedio = contarParedes(mapaIntermedio);
        int paredesAvanzado = contarParedes(mapaAvanzado);

        assertTrue(paredesAvanzado > paredesIntermedio);
    }

    /**
     * Verifica progresión de dificultad: Basico < Intermedio < Avanzado.
     */
    @Test
    public void progresion_dificultad() {
        Celda[][] mapaBasico = nivelBasico.crearMapa();
        Celda[][] mapaIntermedio = nivelIntermedio.crearMapa();
        Celda[][] mapaAvanzado = nivelAvanzado.crearMapa();

        int paredesBasico = contarParedes(mapaBasico);
        int paredesIntermedio = contarParedes(mapaIntermedio);
        int paredesAvanzado = contarParedes(mapaAvanzado);

        assertTrue(paredesBasico < paredesIntermedio);
        assertTrue(paredesIntermedio < paredesAvanzado);
    }

    // =============== PRUEBAS: Respuestas esperadas ===============

    /**
     * Verifica que todas las respuestas son en minúsculas.
     */
    @Test
    public void respuestas_en_minusculas() {
        verificarRespuestasMinusculas(nivelBasico.obtenerRespuestasEsperadas());
        verificarRespuestasMinusculas(nivelIntermedio.obtenerRespuestasEsperadas());
        verificarRespuestasMinusculas(nivelAvanzado.obtenerRespuestasEsperadas());
        verificarRespuestasMinusculas(nivelLeyenda.obtenerRespuestasEsperadas());
    }

    /**
     * Verifica que no hay respuestas duplicadas en nivel.
     */
    @Test
    public void respuestas_unicas_por_nivel() {
        verificarRespuestasUnicas(nivelBasico.obtenerRespuestasEsperadas());
        verificarRespuestasUnicas(nivelIntermedio.obtenerRespuestasEsperadas());
        verificarRespuestasUnicas(nivelAvanzado.obtenerRespuestasEsperadas());
        verificarRespuestasUnicas(nivelLeyenda.obtenerRespuestasEsperadas());
    }

    // =============== PRUEBAS: Preguntas ===============

    /**
     * Verifica que los puntos aumentan progresivamente.
     */
    @Test
    public void puntos_aumentan_por_nivel() {
        int puntosBasico = obtenerPuntosPromedio(nivelBasico.obtenerPreguntas());
        int puntosIntermedio = obtenerPuntosPromedio(nivelIntermedio.obtenerPreguntas());
        int puntosAvanzado = obtenerPuntosPromedio(nivelAvanzado.obtenerPreguntas());
        int puntosLeyenda = obtenerPuntosPromedio(nivelLeyenda.obtenerPreguntas());

        assertTrue(puntosBasico < puntosIntermedio);
        assertTrue(puntosIntermedio < puntosAvanzado);
        assertTrue(puntosAvanzado < puntosLeyenda);
    }

    /**
     * Verifica que todas las preguntas tienen puntos válidos (> 0).
     */
    @Test
    public void preguntas_con_puntos_positivos() {
        verificarPuntosPositivos(nivelBasico.obtenerPreguntas());
        verificarPuntosPositivos(nivelIntermedio.obtenerPreguntas());
        verificarPuntosPositivos(nivelAvanzado.obtenerPreguntas());
        verificarPuntosPositivos(nivelLeyenda.obtenerPreguntas());
    }

    // =============== PRUEBAS: Mapas ===============

    /**
     * Verifica que los mapas son rectangulares (todas las filas tienen igual columnas).
     */
    @Test
    public void mapas_rectangulares() {
        verificarMapaRectangular(nivelBasico.crearMapa());
        verificarMapaRectangular(nivelIntermedio.crearMapa());
        verificarMapaRectangular(nivelAvanzado.crearMapa());
    }

    /**
     * Verifica que cada llamada a crearMapa retorna un nuevo mapa.
     */
    @Test
    public void cada_llamada_crea_nuevo_mapa() {
        Celda[][] mapa1 = nivelBasico.crearMapa();
        Celda[][] mapa2 = nivelBasico.crearMapa();

        assertNotSame(mapa1, mapa2);
    }

    // =============== UTILIDADES ===============

    /**
     * Cuenta el número de paredes en un mapa.
     */
    private int contarParedes(Celda[][] mapa) {
        int count = 0;
        for (int r = 0; r < mapa.length; r++) {
            for (int c = 0; c < mapa[r].length; c++) {
                if (mapa[r][c] == Celda.PARED) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Verifica que todas las respuestas son minúsculas.
     */
    private void verificarRespuestasMinusculas(String[] respuestas) {
        for (String r : respuestas) {
            assertEquals(r, r.toLowerCase());
        }
    }

    /**
     * Verifica que no hay respuestas duplicadas.
     */
    private void verificarRespuestasUnicas(String[] respuestas) {
        for (int i = 0; i < respuestas.length; i++) {
            for (int j = i + 1; j < respuestas.length; j++) {
                assertNotEquals(respuestas[i], respuestas[j]);
            }
        }
    }

    /**
     * Calcula el promedio de puntos de un arreglo de preguntas.
     */
    private int obtenerPuntosPromedio(Pregunta[] preguntas) {
        int total = 0;
        for (Pregunta p : preguntas) {
            total += p.getPuntos();
        }
        return total / preguntas.length;
    }

    /**
     * Verifica que todas las preguntas tienen puntos positivos.
     */
    private void verificarPuntosPositivos(Pregunta[] preguntas) {
        for (Pregunta p : preguntas) {
            assertTrue(p.getPuntos() > 0);
        }
    }

    /**
     * Verifica que un mapa es rectangular.
     */
    private void verificarMapaRectangular(Celda[][] mapa) {
        assertTrue(mapa.length > 0);
        int columnas = mapa[0].length;
        for (int r = 1; r < mapa.length; r++) {
            assertEquals(columnas, mapa[r].length);
        }
    }
}
