/**
 * Paquete con utilidades de validación para entradas y estado del juego.
 */
package validadores;

import modelos.Jugador;
import modelos.Posicion;
import modelos.Direccion;
import modelos.Celda;

/**
 * Validador contiene métodos estáticos para verificar reglas de entrada y
 * restricciones simples del juego (nombres, selección de nivel, movimientos, y
 * condiciones para iniciar el juego).
 */
public class Validador {

    /**
     * Valida el nombre del jugador.
     *
     * Reglas:
     * - No puede ser null.
     * - No puede estar vacío una vez recortado.
     * - Longitud máxima: 20 caracteres.
     * - Solo permite letras (A-Z, a-z), dígitos (0-9) y espacios.
     *
     * @param nombre Nombre a validar.
     * @return {@code true} si el nombre cumple las reglas; {@code false} en caso contrario.
     */
    public static boolean nombreJugadorValido(String nombre) {
        if (nombre == null) return false;
        nombre = nombre.trim();
        if (nombre.isEmpty() || nombre.length() > 20) return false;
        return nombre.matches("[A-Za-z0-9 ]+");
    }

    /**
     * Valida la selección de nivel.
     *
     * Acepta etiquetas en español o inglés, y algunas variantes numéricas:
     * - Español: "principiante", "intermedio", "avanzado", "basico"
     * - Inglés:  "beginner", "intermediate", "advanced", "basic"
     *
     * Esta validación es tolerante a mayúsculas/minúsculas y espacios en los extremos.
     *
     * @param nivel Texto con el nombre o número del nivel.
     * @return {@code true} si el texto corresponde a un nivel reconocido.
     */
    public static boolean seleccionNivelValida(String nivel) {
        if (nivel == null) return false;
        String n = nivel.trim().toLowerCase();
        return n.equals("beginner") || n.equals("intermediate") || n.equals("advanced") || n.equals("basic")
            || n.equals("principiante") || n.equals("intermedio") || n.equals("avanzado") || n.equals("basico");
    }

    /**
     * Valida si es posible mover desde una posición dada en una dirección
     * determinada sobre una matriz de celdas.
     *
     * Se considera {@link modelos.Celda#PARED} como obstáculo; cualquier otra celda
     * es transitable.
     *
     * @param mapa  Matriz de {@link modelos.Celda} que representa el mapa.
     * @param desde Posición actual del jugador.
     * @param d     Dirección del movimiento.
     * @return {@code true} si la posición de destino existe dentro del mapa y no es una pared.
     */
    public static boolean puedeMover(Celda[][] mapa, Posicion desde, Direccion d) {
        if (mapa == null || desde == null || d == null) return false;
        int filas = mapa.length;
        if (filas == 0) return false;
        int columnas = mapa[0].length;
        Posicion destino = desde.mover(d);
        int fr = destino.getFila();
        int col = destino.getColumna();
        if (fr < 0 || fr >= filas || col < 0 || col >= columnas) return false;
        return mapa[fr][col] != Celda.PARED;
    }

    /**
     * Verifica si se cumplen las condiciones mínimas para iniciar una partida:
     * - El objeto {@link modelos.Jugador} no es {@code null}.
     * - El nombre del jugador es válido.
     * - Existe al menos una pregunta disponible.
     *
     * @param j         Jugador a validar.
     * @param preguntas Arreglo de preguntas para el juego.
     * @return {@code true} si se puede iniciar el juego; {@code false} en caso contrario.
     */
    public static boolean puedeIniciarJuego(Jugador j, String[] preguntas) {
        if (j == null) return false;
        if (!nombreJugadorValido(j.getNombre())) return false;
        if (preguntas == null || preguntas.length == 0) return false;
        return true;
    }

}