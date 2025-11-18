package niveles;

import modelos.*;

/**
 * Implementación del nivel Avanzado.
 *
 * Proporciona el mapa, preguntas aleatorias y respuestas esperadas.
 * Incluye un banco de 15+ preguntas de vocabulario avanzado.
 */
public class Avanzado implements Nivel {
    private BancoPreguntas banco;
    private Pregunta[] preguntasActuales;
    private String[] respuestasActuales;

    public Avanzado() {
        inicializarBanco();
        seleccionarPreguntasAleatorias();
    }

    /**
     * Inicializa el banco de preguntas del nivel avanzado.
     * Incluye vocabulario avanzado, matemáticas complejas y curiosidades especializadas.
     */
    private void inicializarBanco() {
        banco = new BancoPreguntas();

        // === INGLÉS - Vocabulario Avanzado ===
        banco.agregarPregunta("Sinónimo de 'afable' en inglés:", null, 0, 15, "amiable");
        banco.agregarPregunta("Traduce 'desafío' al inglés:", null, 0, 15, "challenge");
        banco.agregarPregunta("Traduce 'conocimiento' al inglés:", null, 0, 15, "knowledge");
        banco.agregarPregunta("Traduce 'experiencia' al inglés:", null, 0, 15, "experience");
        banco.agregarPregunta("Traduce 'responsabilidad' al inglés:", null, 0, 15, "responsibility");

        // === INGLÉS - Phrasal Verbs y expresiones ===
        banco.agregarPregunta("¿Cómo dices 'Buenos días' en inglés?:", null, 0, 15, "good morning");
        banco.agregarPregunta("¿Cómo dices 'Buenas noches' en inglés?:", null, 0, 15, "good night");
        banco.agregarPregunta("Traduce 'Por favor' al inglés:", null, 0, 15, "please");
        banco.agregarPregunta("Traduce 'Gracias' al inglés:", null, 0, 15, "thank you");
        banco.agregarPregunta("Traduce 'Lo siento' al inglés:", null, 0, 15, "i am sorry");

        // === INGLÉS - Tiempos verbales ===
        banco.agregarPregunta("Pasado de 'write':", null, 0, 15, "wrote");
        banco.agregarPregunta("Pasado participio de 'see':", null, 0, 15, "seen");
        banco.agregarPregunta("Presente continuo de 'run':", null, 0, 15, "running");
        banco.agregarPregunta("¿Cómo se dice 'él fue'?:", null, 0, 15, "he went");

        // === MATEMÁTICAS - Álgebra ===
        banco.agregarPregunta("¿Cuánto es x si 2x+5=15?:", null, 0, 20, "5");
        banco.agregarPregunta("¿Cuánto es (a+b)²?:", null, 0, 20, "a2+2ab+b2");
        banco.agregarPregunta("¿Cuál es la raíz cuadrada de 144?:", null, 0, 20, "12");
        banco.agregarPregunta("¿Cuánto es el 30% de 200?:", null, 0, 20, "60");

        // === MATEMÁTICAS - Trigonometría ===
        banco.agregarPregunta("¿Cuánto es sen(90°)?:", null, 0, 20, "1");
        banco.agregarPregunta("¿Cuánto es cos(0°)?:", null, 0, 20, "1");
        banco.agregarPregunta("¿Cuál es la suma de ángulos de un triángulo?:", null, 0, 20, "180");

        // === CURIOSIDADES - Física ===
        banco.agregarPregunta("¿Cuál es la velocidad de la luz en km/s?:", null, 0, 25, "300000");
        banco.agregarPregunta("¿Cuál es la constante de la gravedad en la Tierra?:", null, 0, 25, "9.8");
        banco.agregarPregunta("¿Quién desarrolló la teoría de la relatividad?:", null, 0, 25, "einstein");

        // === CURIOSIDADES - Historia Medieval ===
        banco.agregarPregunta("¿En qué año cayó el Imperio Romano?:", null, 0, 25, "476");
        banco.agregarPregunta("¿En qué siglo se descubrió América?:", null, 0, 25, "15");
        banco.agregarPregunta("¿Cuántos años duró la Guerra de los Cien Años?:", null, 0, 25, "116");

        // === CURIOSIDADES - Biología ===
        banco.agregarPregunta("¿Cuántos cromosomas tiene un humano?:", null, 0, 25, "46");
        banco.agregarPregunta("¿Cuántos huesos tiene el cuerpo humano?:", null, 0, 25, "206");
        banco.agregarPregunta("¿Cuál es la proteína más abundante del cuerpo?:", null, 0, 25, "colageno");

        // === CURIOSIDADES - Geografía avanzada ===
        banco.agregarPregunta("¿Cuál es la montaña más alta del mundo?:", null, 0, 25, "everest");
        banco.agregarPregunta("¿Cuál es el río más largo del mundo?:", null, 0, 25, "nilo");
        banco.agregarPregunta("¿En qué país está la mayor parte de la Amazonía?:", null, 0, 25, "brasil");
    }

    /**
     * Selecciona 8 preguntas aleatorias para esta instancia del juego.
     */
    private void seleccionarPreguntasAleatorias() {
        preguntasActuales = banco.obtenerPreguntasAleatorias(8);
        respuestasActuales = banco.obtenerRespuestasAleatorias(8);
    }

    @Override
    public String nombreNivel() {
        return "Avanzado";
    }

    /**
     * Crea el mapa del nivel Avanzado (32x32 laberinto).
     * 20% preguntas, 10% datos curiosos.
     *
     * @return matriz de {@link modelos.Celda} representando el laberinto
     */
    @Override
    public Celda[][] crearMapa() {
        // Mapa 32x32 = 1024 celdas
        // 20% preguntas = 204.8 → 204 preguntas
        // 10% datos = 102.4 → 102 datos
        return GeneradorLaberinto.generarLaberintoAleatorio(32, 204, 102);
    }

    /**
     * Provee las preguntas del nivel Avanzado (aleatorias).
     *
     * @return arreglo de {@link modelos.Pregunta}
     */
    @Override
    public Pregunta[] obtenerPreguntas() {
        return preguntasActuales;
    }

    /**
     * Respuestas esperadas para las preguntas del nivel Avanzado (en el mismo orden).
     *
     * @return arreglo de respuestas esperadas en minúsculas
     */
    @Override
    public String[] obtenerRespuestasEsperadas() {
        return respuestasActuales;
    }
}