package niveles;

import modelos.*;

/**
 * Implementación del nivel Intermedio.
 *
 * Proporciona el mapa (6x6), preguntas aleatorias y respuestas esperadas.
 * Incluye un banco de 15+ preguntas de vocabulario intermedio.
 */
public class Intermedio implements Nivel {
    private BancoPreguntas banco;
    private Pregunta[] preguntasActuales;
    private String[] respuestasActuales;

    public Intermedio() {
        inicializarBanco();
        seleccionarPreguntasAleatorias();
    }

    /**
     * Inicializa el banco de preguntas del nivel intermedio.
     * Incluye vocabulario intermedio, matemáticas y curiosidades avanzadas.
     */
    private void inicializarBanco() {
        banco = new BancoPreguntas();

        // === INGLÉS - Verbos ===
        banco.agregarPregunta("Infinitivo de 'corro':", null, 0, 12, "run");
        banco.agregarPregunta("Infinitivo de 'camino':", null, 0, 12, "walk");
        banco.agregarPregunta("Infinitivo de 'como':", null, 0, 12, "eat");
        banco.agregarPregunta("Infinitivo de 'duermo':", null, 0, 12, "sleep");
        banco.agregarPregunta("Infinitivo de 'leo':", null, 0, 12, "read");
        banco.agregarPregunta("Pasado de 'go':", null, 0, 12, "went");
        banco.agregarPregunta("Pasado de 'eat':", null, 0, 12, "ate");

        // === INGLÉS - Sustantivos ===
        banco.agregarPregunta("Traduce 'libro' al inglés:", null, 0, 12, "book");
        banco.agregarPregunta("Traduce 'mesa' al inglés:", null, 0, 12, "table");
        banco.agregarPregunta("Traduce 'silla' al inglés:", null, 0, 12, "chair");
        banco.agregarPregunta("Traduce 'ventana' al inglés:", null, 0, 12, "window");
        banco.agregarPregunta("Traduce 'puerta' al inglés:", null, 0, 12, "door");
        banco.agregarPregunta("Traduce 'escuela' al inglés:", null, 0, 12, "school");
        banco.agregarPregunta("Traduce 'hospital' al inglés:", null, 0, 12, "hospital");

        // === INGLÉS - Adjetivos ===
        banco.agregarPregunta("Traduce 'grande' al inglés:", null, 0, 12, "big");
        banco.agregarPregunta("Traduce 'pequeño' al inglés:", null, 0, 12, "small");
        banco.agregarPregunta("Traduce 'hermoso' al inglés:", null, 0, 12, "beautiful");
        banco.agregarPregunta("Traduce 'rápido' al inglés:", null, 0, 12, "fast");
        banco.agregarPregunta("Traduce 'lento' al inglés:", null, 0, 12, "slow");
        banco.agregarPregunta("Traduce 'fuerte' al inglés:", null, 0, 12, "strong");
        banco.agregarPregunta("Traduce 'débil' al inglés:", null, 0, 12, "weak");
        banco.agregarPregunta("Traduce 'feliz' al inglés:", null, 0, 12, "happy");

        // === MATEMÁTICAS - Operaciones avanzadas ===
        banco.agregarPregunta("¿Cuánto es 15+25?:", null, 0, 18, "40");
        banco.agregarPregunta("¿Cuánto es 50-20?:", null, 0, 18, "30");
        banco.agregarPregunta("¿Cuánto es 7×8?:", null, 0, 18, "56");
        banco.agregarPregunta("¿Cuánto es 100÷5?:", null, 0, 18, "20");
        banco.agregarPregunta("¿Cuál es el 50% de 100?:", null, 0, 18, "50");

        // === MATEMÁTICAS - Geometría ===
        banco.agregarPregunta("¿Cuántos lados tiene un hexágono?:", null, 0, 18, "6");
        banco.agregarPregunta("¿Cuál es el área de un cuadrado de lado 5?:", null, 0, 18, "25");
        banco.agregarPregunta("¿Cuál es la fórmula del perímetro de un círculo?:", null, 0, 18, "2πr");

        // === CURIOSIDADES - Ciencia ===
        banco.agregarPregunta("¿Cuántos elementos tiene la tabla periódica?:", null, 0, 22, "118");
        banco.agregarPregunta("¿A cuántos Celsius equivale 32 Fahrenheit?:", null, 0, 22, "0");
        banco.agregarPregunta("¿Cuál es el símbolo químico del oro?:", null, 0, 22, "au");

        // === CURIOSIDADES - Astronomía ===
        banco.agregarPregunta("¿Cuánto tiempo tarda la Tierra en girar alrededor del Sol?:", null, 0, 22, "365");
        banco.agregarPregunta("¿Cuál es el planeta más cercano al Sol?:", null, 0, 22, "mercurio");
        banco.agregarPregunta("¿Cuántas lunas tiene Marte?:", null, 0, 22, "2");

        // === CURIOSIDADES - Literatura ===
        banco.agregarPregunta("¿Quién escribió La Odisea?:", null, 0, 22, "homero");
        banco.agregarPregunta("¿Cuántos libros tiene la serie de Harry Potter?:", null, 0, 22, "7");
        banco.agregarPregunta("¿Quién escribió Romeo y Julieta?:", null, 0, 22, "shakespeare");
    }

    /**
     * Selecciona 6 preguntas aleatorias para esta instancia del juego.
     */
    private void seleccionarPreguntasAleatorias() {
        preguntasActuales = banco.obtenerPreguntasAleatorias(6);
        respuestasActuales = banco.obtenerRespuestasAleatorias(6);
    }

    @Override
    public String nombreNivel() {
        return "Intermedio";
    }

    /**
     * Crea el mapa del nivel Intermedio (16x16 laberinto).
     * 20% preguntas, 10% datos curiosos.
     *
     * @return matriz de {@link modelos.Celda} representando el laberinto
     */
    @Override
    public Celda[][] crearMapa() {
        // Mapa 16x16 = 256 celdas
        // 20% preguntas = 51.2 → 51 preguntas
        // 10% datos = 25.6 → 25 datos
        return GeneradorLaberinto.generarLaberintoAleatorio(16, 51, 25);
    }

    /**
     * Provee las preguntas del nivel Intermedio (aleatorias).
     *
     * @return arreglo de {@link modelos.Pregunta}
     */
    @Override
    public Pregunta[] obtenerPreguntas() {
        return preguntasActuales;
    }

    /**
     * Respuestas esperadas para las preguntas del nivel Intermedio (en el mismo orden).
     *
     * @return arreglo de respuestas esperadas en minúsculas
     */
    @Override
    public String[] obtenerRespuestasEsperadas() {
        return respuestasActuales;
    }
}