package modelos;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase {@link Juego}.
 *
 * Verifica comportamiento básico: iniciar/terminar partida y restricciones de movimiento.
 */
public class JuegoTest {

	/**
	 * Verifica que iniciar y terminar la partida cambia el estado de ejecución
	 * del juego correctamente.
	 */
	@Test
	public void iniciarJuego_funciona() {
		Jugador j = new Jugador("Alicia", "Basico");
		niveles.Basico nivel = new niveles.Basico();
		Juego juego = new Juego(j, nivel);
		assertFalse(juego.estaEnEjecucion());
		assertTrue(juego.puedeIniciar());
		juego.iniciar();
		assertTrue(juego.estaEnEjecucion());
		juego.terminar();
		assertFalse(juego.estaEnEjecucion());
	}

	/**
	 * Verifica que intentar mover sin iniciar la partida devuelve falso.
	 */
	@Test
	public void mover_sin_iniciar_devuelve_falso() {
		Jugador j = new Jugador("Bob", "Basico");
		niveles.Basico nivel = new niveles.Basico();
		Juego juego = new Juego(j, nivel);
		assertFalse(juego.moverJugador(null));
	}

}
