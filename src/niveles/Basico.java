package niveles;

import modelos.*;

/**
 * Implementación del nivel Basico.
 *
 * Proporciona un mapa 8x8 con preguntas aleatorias y respuestas esperadas.
 * Incluye un banco de 19 preguntas variadas de vocabulario básico en inglés.
 */
public class Basico implements Nivel {
    private BancoPreguntas banco;
    private Pregunta[] preguntasActuales;
    private String[] respuestasActuales;

    public Basico() {
        inicializarBanco();
        seleccionarPreguntasAleatorias();
    }

    /**
     * Inicializa el banco de preguntas del nivel básico.
     * Incluye vocabulario inglés, matemáticas básicas y curiosidades.
     */
    private void inicializarBanco() {
        banco = new BancoPreguntas();

        // === INGLÉS - Animales ===
        banco.agregarPregunta("Traduce 'gato' al inglés:", null, 0, 10, "cat");
        banco.agregarPregunta("Traduce 'perro' al inglés:", null, 0, 10, "dog");
        banco.agregarPregunta("Traduce 'pajaro' al inglés:", null, 0, 10, "bird");
        banco.agregarPregunta("Traduce 'pez' al inglés:", null, 0, 10, "fish");
        banco.agregarPregunta("Traduce 'caballo' al inglés:", null, 0, 10, "horse");

        // === INGLÉS - Colores ===
        banco.agregarPregunta("¿De qué color es el cielo?:", null, 0, 10, "blue");
        banco.agregarPregunta("¿De qué color es la nieve?:", null, 0, 10, "white");
        banco.agregarPregunta("Traduce 'rojo' al inglés:", null, 0, 10, "red");
        banco.agregarPregunta("Traduce 'verde' al inglés:", null, 0, 10, "green");
        banco.agregarPregunta("Traduce 'amarillo' al inglés:", null, 0, 10, "yellow");

        // === INGLÉS - Números y Básico ===
        banco.agregarPregunta("Traduce 'uno' al inglés:", null, 0, 10, "one");
        banco.agregarPregunta("Traduce 'dos' al inglés:", null, 0, 10, "two");
        banco.agregarPregunta("Traduce 'tres' al inglés:", null, 0, 10, "three");
        banco.agregarPregunta("Traduce 'casa' al inglés:", null, 0, 10, "house");
        banco.agregarPregunta("Traduce 'agua' al inglés:", null, 0, 10, "water");

        // === INGLÉS - Más vocabulario ===
        banco.agregarPregunta("Traduce 'comida' al inglés:", null, 0, 10, "food");
        banco.agregarPregunta("Traduce 'día' al inglés:", null, 0, 10, "day");
        banco.agregarPregunta("Traduce 'noche' al inglés:", null, 0, 10, "night");
        banco.agregarPregunta("Traduce 'sol' al inglés:", null, 0, 10, "sun");
        banco.agregarPregunta("Traduce 'luna' al inglés:", null, 0, 10, "moon");
        banco.agregarPregunta("Traduce 'estrella' al inglés:", null, 0, 10, "star");
        banco.agregarPregunta("Traduce 'árbol' al inglés:", null, 0, 10, "tree");
        banco.agregarPregunta("Traduce 'flor' al inglés:", null, 0, 10, "flower");

        // === MATEMÁTICAS - Operaciones ===
        banco.agregarPregunta("¿Cuánto es 2+2?:", null, 0, 15, "4");
        banco.agregarPregunta("¿Cuánto es 5+3?:", null, 0, 15, "8");
        banco.agregarPregunta("¿Cuánto es 10-3?:", null, 0, 15, "7");
        banco.agregarPregunta("¿Cuánto es 3×2?:", null, 0, 15, "6");
        banco.agregarPregunta("¿Cuánto es 10÷2?:", null, 0, 15, "5");

        // === MATEMÁTICAS - Series y Lógica ===
        banco.agregarPregunta("¿Cuál es el siguiente número en la serie: 2, 4, 6, 8, ?:", null, 0, 15, "10");
        banco.agregarPregunta("¿Cuál es el siguiente número en la serie: 1, 4, 9, 16, ?:", null, 0, 15, "25");
        banco.agregarPregunta("¿Cuántos lados tiene un triángulo?:", null, 0, 15, "3");
        banco.agregarPregunta("¿Cuántos lados tiene un cuadrado?:", null, 0, 15, "4");

        // === CURIOSIDADES - Geografía ===
        banco.agregarPregunta("¿Cuál es la capital de Francia?:", null, 0, 20, "paris");
        banco.agregarPregunta("¿Cuál es el país más grande del mundo?:", null, 0, 20, "rusia");
        banco.agregarPregunta("¿Cuál es la capital de España?:", null, 0, 20, "madrid");

        // === CURIOSIDADES - Naturaleza ===
        banco.agregarPregunta("¿Cuántos planetas tiene el sistema solar?:", null, 0, 20, "8");
        banco.agregarPregunta("¿Cuál es el planeta más grande del sistema solar?:", null, 0, 20, "jupiter");
        banco.agregarPregunta("¿Cuántos continentes hay en la Tierra?:", null, 0, 20, "7");

        // === CURIOSIDADES - Historia ===
        banco.agregarPregunta("¿En qué año llegó el hombre a la Luna?:", null, 0, 20, "1969");
        banco.agregarPregunta("¿Cuántos años vivió Jesucristo?:", null, 0, 20, "33");
        banco.agregarPregunta("¿Quién escribió Don Quijote?:", null, 0, 20, "cervantes");
    }

    /**
     * Selecciona 5 preguntas aleatorias para esta instancia del juego.
     */
    private void seleccionarPreguntasAleatorias() {
        preguntasActuales = banco.obtenerPreguntasAleatorias(5);
        respuestasActuales = banco.obtenerRespuestasAleatorias(5);
    }

    @Override
    public String nombreNivel() {
        return "Basico";
    }

    /**
     * Crea el mapa del nivel Basico (8x8 laberinto).
     * 20% preguntas, 10% datos curiosos.
     *
     * @return matriz de {@link modelos.Celda} representando el laberinto
     */
    @Override
    public Celda[][] crearMapa() {
        // Mapa 8x8 = 64 celdas
        // 20% preguntas = 12.8 → 12 preguntas
        // 10% datos = 6.4 → 6 datos
        return GeneradorLaberinto.generarLaberintoAleatorio(8, 12, 6);
    }    /**
     * Provee las preguntas del nivel (aleatorias).
     *
     * @return arreglo de {@link modelos.Pregunta}
     */
    @Override
    public Pregunta[] obtenerPreguntas() {
        return preguntasActuales;
    }

    /**
     * Respuestas esperadas para las preguntas del nivel (en el mismo orden).
     *
     * @return arreglo de respuestas esperadas en minúsculas
     */
    @Override
    public String[] obtenerRespuestasEsperadas() {
        return respuestasActuales;
    }
}