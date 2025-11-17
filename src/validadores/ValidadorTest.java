package validadores;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase {@link Validador}.
 *
 * Verifica reglas de validación para nombres de jugador, selección de niveles
 * y las condiciones para iniciar una partida.
 */
public class ValidadorTest {

    /**
     * Verifica casos válidos e inválidos para {@link Validador#nombreJugadorValido}.
     */
    @Test
    public void nombreJugadorValido() {
        assertFalse(Validador.nombreJugadorValido(null));
        assertFalse(Validador.nombreJugadorValido(""));
        assertFalse(Validador.nombreJugadorValido("   "));
        assertFalse(Validador.nombreJugadorValido("NombreDemasiadoLargoParaAceptar123"));
        assertFalse(Validador.nombreJugadorValido("Mal@Nombre"));
        assertTrue(Validador.nombreJugadorValido("Alicia"));
        assertTrue(Validador.nombreJugadorValido("Alicia 123"));
    }

    /**
     * Verifica la validación de distintas formas de nombrar niveles
     * usando {@link Validador#seleccionNivelValida}.
     */
    @Test
    public void seleccionDeNivel_validaEInvalida() {
        assertTrue(Validador.seleccionNivelValida("Basico"));
        assertTrue(Validador.seleccionNivelValida("intermedio"));
        assertTrue(Validador.seleccionNivelValida("ADVANCED"));
        assertTrue(Validador.seleccionNivelValida("beginner"));
        assertFalse(Validador.seleccionNivelValida("desconocido"));
        assertFalse(Validador.seleccionNivelValida(null));
    }

    /**
     * Casos básicos para {@link Validador#puedeIniciarJuego}.
     * Comprueba jugador nulo, arreglo de preguntas vacío y nombres inválidos.
     */
    @Test
    public void puedeIniciarJuego_casosBasicos() {
        modelos.Jugador j = new modelos.Jugador("Ana", "Basico");
        String[] preguntas = new String[] { "p1" };
        assertTrue(Validador.puedeIniciarJuego(j, preguntas));
        assertFalse(Validador.puedeIniciarJuego(null, preguntas));
        assertFalse(Validador.puedeIniciarJuego(j, new String[0]));
        assertFalse(Validador.puedeIniciarJuego(j, null));
        modelos.Jugador bad = new modelos.Jugador("Mal@Nombre", "Basico");
        assertFalse(Validador.puedeIniciarJuego(bad, preguntas));
    }

}
