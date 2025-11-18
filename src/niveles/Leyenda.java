package niveles;

import modelos.*;

/**
 * Implementación del nivel Leyenda.
 *
 * Proporciona el mapa (64x64), preguntas aleatorias y respuestas esperadas.
 * Incluye un banco de 25+ preguntas de vocabulario experto.
 */
public class Leyenda implements Nivel {
    private BancoPreguntas banco;
    private Pregunta[] preguntasActuales;
    private String[] respuestasActuales;

    public Leyenda() {
        inicializarBanco();
        seleccionarPreguntasAleatorias();
    }

    /**
     * Inicializa el banco de preguntas del nivel Leyenda.
     */
    private void inicializarBanco() {
        banco = new BancoPreguntas();

        // Vocabulario experto y idiomático
        banco.agregarPregunta("Sinónimo de 'perspicaz' en inglés:", null, 0, 20, "keen");
        banco.agregarPregunta("Traduce 'elocuencia' al inglés:", null, 0, 20, "eloquence");
        banco.agregarPregunta("Traduce 'efímero' al inglés:", null, 0, 20, "ephemeral");
        banco.agregarPregunta("Traduce 'paradoja' al inglés:", null, 0, 20, "paradox");
        banco.agregarPregunta("Traduce 'metáfora' al inglés:", null, 0, 20, "metaphor");

        // Verbos frasales y expresiones idiomáticas
        banco.agregarPregunta("¿Qué significa 'to break the ice'?:", null, 0, 20, "iniciar una conversación");
        banco.agregarPregunta("¿Qué significa 'to hit the books'?:", null, 0, 20, "estudiar");
        banco.agregarPregunta("¿Qué significa 'to cut to the chase'?:", null, 0, 20, "ir al grano");
        banco.agregarPregunta("¿Qué significa 'to burn the midnight oil'?:", null, 0, 20, "trabajar hasta tarde");
        banco.agregarPregunta("¿Qué significa 'to have your head in the clouds'?:", null, 0, 20, "estar distraído");

        // Vocabulario científico y técnico
        banco.agregarPregunta("Traduce 'fotosíntesis' al inglés:", null, 0, 20, "photosynthesis");
        banco.agregarPregunta("Traduce 'gravitación' al inglés:", null, 0, 20, "gravitation");
        banco.agregarPregunta("Traduce 'mutación' al inglés:", null, 0, 20, "mutation");
        banco.agregarPregunta("Traduce 'algoritmo' al inglés:", null, 0, 20, "algorithm");
        banco.agregarPregunta("Traduce 'inteligencia artificial' al inglés:", null, 0, 20, "artificial intelligence");

        // Vocabulario histórico y cultural
        banco.agregarPregunta("Traduce 'renacimiento' al inglés:", null, 0, 20, "renaissance");
        banco.agregarPregunta("Traduce 'ilustración' al inglés:", null, 0, 20, "enlightenment");
        banco.agregarPregunta("Traduce 'revolución' al inglés:", null, 0, 20, "revolution");
        banco.agregarPregunta("Traduce 'civilización' al inglés:", null, 0, 20, "civilization");
        banco.agregarPregunta("Traduce 'arqueología' al inglés:", null, 0, 20, "archaeology");

        // Vocabulario artístico y literario
        banco.agregarPregunta("Traduce 'sinfonía' al inglés:", null, 0, 20, "symphony");
        banco.agregarPregunta("Traduce 'tragedia' al inglés:", null, 0, 20, "tragedy");
        banco.agregarPregunta("Traduce 'narrativa' al inglés:", null, 0, 20, "narrative");
        banco.agregarPregunta("Traduce 'estética' al inglés:", null, 0, 20, "aesthetics");
        banco.agregarPregunta("Traduce 'caligrafía' al inglés:", null, 0, 20, "calligraphy");

        // Vocabulario filosófico
        banco.agregarPregunta("Traduce 'epistemología' al inglés:", null, 0, 20, "epistemology");
        banco.agregarPregunta("Traduce 'ontología' al inglés:", null, 0, 20, "ontology");

        // === MATEMÁTICAS AVANZADA ===
        banco.agregarPregunta("¿Cuál es el límite de 1/x cuando x tiende a infinito?:", null, 0, 25, "0");
        banco.agregarPregunta("¿Cuál es la derivada de x²?:", null, 0, 25, "2x");
        banco.agregarPregunta("¿Cuál es la integral de x?:", null, 0, 25, "x2/2");
        banco.agregarPregunta("¿Cuál es el número de Euler aproximadamente?:", null, 0, 25, "2.71");

        // === CURIOSIDADES ULTRAPROFUNDAS ===
        banco.agregarPregunta("¿Cuántos dígitos tiene el mayor número primo conocido?:", null, 0, 25, "24862048");
        banco.agregarPregunta("¿Quién ganó el Premio Nobel de Física en 1921?:", null, 0, 25, "einstein");
        banco.agregarPregunta("¿En qué año se inventó el primer transistor?:", null, 0, 25, "1947");
        banco.agregarPregunta("¿Cuántas especies de animales se extinguen diariamente?:", null, 0, 25, "137");
        banco.agregarPregunta("¿Cuál es la profundidad del océano más profundo?:", null, 0, 25, "11000");
    }

    /**
     * Selecciona 10 preguntas aleatorias para esta instancia del juego.
     */
    private void seleccionarPreguntasAleatorias() {
        preguntasActuales = banco.obtenerPreguntasAleatorias(10);
        respuestasActuales = banco.obtenerRespuestasAleatorias(10);
    }

    @Override
    public String nombreNivel() {
        return "Leyenda";
    }

    /**
     * Crea el mapa del nivel Leyenda (64x64 laberinto épico).
     * 20% preguntas, 10% datos curiosos.
     *
     * @return matriz de {@link modelos.Celda} representando el laberinto
     */
    @Override
    public Celda[][] crearMapa() {
        // Mapa 64x64 = 4096 celdas
        // 20% preguntas = 819.2 → 819 preguntas
        // 10% datos = 409.6 → 409 datos
        return GeneradorLaberinto.generarLaberintoAleatorio(64, 819, 409);
    }

    /**
     * Provee las preguntas del nivel Leyenda (aleatorias).
     *
     * @return arreglo de {@link modelos.Pregunta}
     */
    @Override
    public Pregunta[] obtenerPreguntas() {
        return preguntasActuales;
    }

    /**
     * Respuestas esperadas para las preguntas del nivel Leyenda (en el mismo orden).
     *
     * @return arreglo de respuestas esperadas en minúsculas
     */
    @Override
    public String[] obtenerRespuestasEsperadas() {
        return respuestasActuales;
    }
}
