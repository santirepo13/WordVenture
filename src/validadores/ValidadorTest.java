package validadores;

import modelos.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias completas para la clase {@link Validador}.
 *
 * Verifica reglas de validación para:
 * - Nombres de jugador (formato, longitud, caracteres)
 * - Selección de niveles (español/inglés, sensibilidad)
 * - Movimientos en el mapa (límites, obstáculos)
 * - Condiciones para iniciar una partida
 */
public class ValidadorTest {

    private Celda[][] mapaBasico;

    @Before
    public void setUp() {
        // Crear un mapa de prueba simple 5x5
        mapaBasico = new Celda[5][5];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                mapaBasico[r][c] = Celda.LIBRE;
            }
        }
        // Agregar algunas paredes
        mapaBasico[1][1] = Celda.PARED;
        mapaBasico[2][2] = Celda.PARED;
        mapaBasico[3][1] = Celda.PARED;
    }

    // =============== PRUEBAS: nombreJugadorValido ===============

    /**
     * Verifica que nombres null se rechacen.
     */
    @Test
    public void nombreJugadorValido_null_falla() {
        assertFalse(Validador.nombreJugadorValido(null));
    }

    /**
     * Verifica que nombres vacíos se rechacen.
     */
    @Test
    public void nombreJugadorValido_vacio_falla() {
        assertFalse(Validador.nombreJugadorValido(""));
        assertFalse(Validador.nombreJugadorValido("   "));
    }

    /**
     * Verifica que nombres excesivamente largos se rechacen.
     */
    @Test
    public void nombreJugadorValido_demasiado_largo_falla() {
        assertFalse(Validador.nombreJugadorValido("NombreDemasiadoLargoParaAceptar123"));
        String exactoMas1 = "a".repeat(21);
        assertFalse(Validador.nombreJugadorValido(exactoMas1));
    }

    /**
     * Verifica que caracteres especiales invaliden el nombre.
     */
    @Test
    public void nombreJugadorValido_caracteres_especiales_falla() {
        assertFalse(Validador.nombreJugadorValido("Mal@Nombre"));
        assertFalse(Validador.nombreJugadorValido("Bob#123"));
        assertFalse(Validador.nombreJugadorValido("Alice$"));
        assertFalse(Validador.nombreJugadorValido("User_1"));
    }

    /**
     * Verifica que nombres válidos sean aceptados.
     */
    @Test
    public void nombreJugadorValido_validos_pasan() {
        assertTrue(Validador.nombreJugadorValido("Alicia"));
        assertTrue(Validador.nombreJugadorValido("Alicia 123"));
        assertTrue(Validador.nombreJugadorValido("Bob"));
        assertTrue(Validador.nombreJugadorValido("User 1 2"));
        assertTrue(Validador.nombreJugadorValido("A"));
        assertTrue(Validador.nombreJugadorValido("a".repeat(20)));
    }

    /**
     * Verifica que la validación sea insensible a mayúsculas.
     */
    @Test
    public void nombreJugadorValido_insensible_a_mayusculas() {
        assertTrue(Validador.nombreJugadorValido("JUAN"));
        assertTrue(Validador.nombreJugadorValido("juan"));
        assertTrue(Validador.nombreJugadorValido("JuAn"));
    }

    // =============== PRUEBAS: seleccionNivelValida ===============

    /**
     * Verifica que null se rechace en selección de nivel.
     */
    @Test
    public void seleccionNivelValida_null_falla() {
        assertFalse(Validador.seleccionNivelValida(null));
    }

    /**
     * Verifica que niveles en español se acepten.
     */
    @Test
    public void seleccionNivelValida_espanol_pasa() {
        assertTrue(Validador.seleccionNivelValida("Basico"));
        assertTrue(Validador.seleccionNivelValida("basico"));
        assertTrue(Validador.seleccionNivelValida("BASICO"));
        assertTrue(Validador.seleccionNivelValida("intermedio"));
        assertTrue(Validador.seleccionNivelValida("Intermedio"));
        assertTrue(Validador.seleccionNivelValida("avanzado"));
        assertTrue(Validador.seleccionNivelValida("Avanzado"));
        assertTrue(Validador.seleccionNivelValida("leyenda"));
        assertTrue(Validador.seleccionNivelValida("Leyenda"));
        assertTrue(Validador.seleccionNivelValida("principiante"));
    }

    /**
     * Verifica que niveles en inglés se acepten.
     */
    @Test
    public void seleccionNivelValida_ingles_pasa() {
        assertTrue(Validador.seleccionNivelValida("beginner"));
        assertTrue(Validador.seleccionNivelValida("Beginner"));
        assertTrue(Validador.seleccionNivelValida("BEGINNER"));
        assertTrue(Validador.seleccionNivelValida("intermediate"));
        assertTrue(Validador.seleccionNivelValida("Intermediate"));
        assertTrue(Validador.seleccionNivelValida("advanced"));
        assertTrue(Validador.seleccionNivelValida("Advanced"));
        assertTrue(Validador.seleccionNivelValida("legend"));
        assertTrue(Validador.seleccionNivelValida("Legend"));
        assertTrue(Validador.seleccionNivelValida("basic"));
    }

    /**
     * Verifica que niveles desconocidos se rechacen.
     */
    @Test
    public void seleccionNivelValida_desconocidos_fallan() {
        assertFalse(Validador.seleccionNivelValida("experto"));
        assertFalse(Validador.seleccionNivelValida("nivel4"));
        assertFalse(Validador.seleccionNivelValida(""));
        assertFalse(Validador.seleccionNivelValida("   "));
    }

    /**
     * Verifica tolerancia a espacios al inicio/final.
     */
    @Test
    public void seleccionNivelValida_tolera_espacios() {
        assertTrue(Validador.seleccionNivelValida("  basico  "));
        assertTrue(Validador.seleccionNivelValida("\tintermedio\t"));
    }

    // =============== PRUEBAS: puedeMover ===============

    /**
     * Verifica que mover a derecha desde posición válida sea permitido.
     */
    @Test
    public void puedeMover_derecha_valido() {
        Posicion p = new Posicion(0, 0);
        assertTrue(Validador.puedeMover(mapaBasico, p, Direccion.DERECHA));
    }

    /**
     * Verifica que mover abajo desde posición válida sea permitido.
     */
    @Test
    public void puedeMover_abajo_valido() {
        Posicion p = new Posicion(0, 0);
        assertTrue(Validador.puedeMover(mapaBasico, p, Direccion.ABAJO));
    }

    /**
     * Verifica que mover hacia una pared sea rechazado.
     */
    @Test
    public void puedeMover_hacia_pared_falla() {
        Posicion p = new Posicion(1, 0);
        assertFalse(Validador.puedeMover(mapaBasico, p, Direccion.DERECHA)); // [1][1] es PARED
    }

    /**
     * Verifica que mover fuera de límites sea rechazado.
     */
    @Test
    public void puedeMover_fuera_de_limites_falla() {
        Posicion p = new Posicion(0, 0);
        assertFalse(Validador.puedeMover(mapaBasico, p, Direccion.ARRIBA)); // Fila -1
        assertFalse(Validador.puedeMover(mapaBasico, p, Direccion.IZQUIERDA)); // Columna -1

        Posicion p2 = new Posicion(4, 4);
        assertFalse(Validador.puedeMover(mapaBasico, p2, Direccion.ABAJO)); // Fila 5
        assertFalse(Validador.puedeMover(mapaBasico, p2, Direccion.DERECHA)); // Columna 5
    }

    /**
     * Verifica que parámetros null sean rechazados.
     */
    @Test
    public void puedeMover_parametros_null_fallan() {
        Posicion p = new Posicion(0, 0);
        assertFalse(Validador.puedeMover(null, p, Direccion.DERECHA));
        assertFalse(Validador.puedeMover(mapaBasico, null, Direccion.DERECHA));
        assertFalse(Validador.puedeMover(mapaBasico, p, null));
    }

    /**
     * Verifica que mapa vacío sea rechazado.
     */
    @Test
    public void puedeMover_mapa_vacio_falla() {
        Celda[][] mapVacio = new Celda[0][0];
        Posicion p = new Posicion(0, 0);
        assertFalse(Validador.puedeMover(mapVacio, p, Direccion.DERECHA));
    }

    /**
     * Verifica movimiento en todas las direcciones.
     */
    @Test
    public void puedeMover_todas_direcciones() {
        Posicion p = new Posicion(2, 2);
        assertTrue(Validador.puedeMover(mapaBasico, p, Direccion.ARRIBA));
        assertTrue(Validador.puedeMover(mapaBasico, p, Direccion.ABAJO));
        assertTrue(Validador.puedeMover(mapaBasico, p, Direccion.IZQUIERDA));
        assertTrue(Validador.puedeMover(mapaBasico, p, Direccion.DERECHA));
    }

    // =============== PRUEBAS: puedeIniciarJuego ===============

    /**
     * Verifica que se pueda iniciar con parámetros válidos.
     */
    @Test
    public void puedeIniciarJuego_valido_pasa() {
        Jugador j = new Jugador("Ana", "Basico");
        String[] preguntas = new String[]{"p1", "p2"};
        assertTrue(Validador.puedeIniciarJuego(j, preguntas));
    }

    /**
     * Verifica que jugador null falle.
     */
    @Test
    public void puedeIniciarJuego_jugador_null_falla() {
        String[] preguntas = new String[]{"p1"};
        assertFalse(Validador.puedeIniciarJuego(null, preguntas));
    }

    /**
     * Verifica que nombre inválido falle.
     */
    @Test
    public void puedeIniciarJuego_nombre_invalido_falla() {
        Jugador bad = new Jugador("Mal@Nombre", "Basico");
        String[] preguntas = new String[]{"p1"};
        assertFalse(Validador.puedeIniciarJuego(bad, preguntas));
    }

    /**
     * Verifica que sin preguntas falle.
     */
    @Test
    public void puedeIniciarJuego_sin_preguntas_falla() {
        Jugador j = new Jugador("Bob", "Basico");
        assertFalse(Validador.puedeIniciarJuego(j, new String[0]));
        assertFalse(Validador.puedeIniciarJuego(j, null));
    }

    /**
     * Verifica que con una sola pregunta pase.
     */
    @Test
    public void puedeIniciarJuego_una_pregunta_pasa() {
        Jugador j = new Jugador("Charlie", "Intermedio");
        String[] preguntas = new String[]{"¿Pregunta?"};
        assertTrue(Validador.puedeIniciarJuego(j, preguntas));
    }
}
