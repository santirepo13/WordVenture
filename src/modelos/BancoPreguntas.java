package modelos;

import java.util.*;

/**
 * Banco de preguntas para cada nivel del juego.
 * Proporciona preguntas aleatorias y respuestas esperadas sincronizadas.
 */
public class BancoPreguntas {
    private static class ParPreguntaRespuesta {
        Pregunta pregunta;
        String respuesta;
        
        ParPreguntaRespuesta(Pregunta pregunta, String respuesta) {
            this.pregunta = pregunta;
            this.respuesta = respuesta;
        }
    }
    
    private List<ParPreguntaRespuesta> pares;
    private Random random;
    private List<Integer> indicesAleatorios;

    public BancoPreguntas() {
        this.pares = new ArrayList<>();
        this.random = new Random();
        this.indicesAleatorios = new ArrayList<>();
    }

    /**
     * Agrega una pregunta al banco.
     */
    public void agregarPregunta(String enunciado, String[] opciones, int indiceCorrecto, int puntos, String respuestaEsperada) {
        Pregunta pregunta = new Pregunta(enunciado, opciones, indiceCorrecto, puntos);
        pares.add(new ParPreguntaRespuesta(pregunta, respuestaEsperada.toLowerCase().trim()));
    }

    /**
     * Obtiene N preguntas aleatorias del banco sincronizadas con sus respuestas.
     */
    public Pregunta[] obtenerPreguntasAleatorias(int cantidad) {
        if (cantidad > pares.size()) {
            cantidad = pares.size();
        }

        // Generar índices aleatorios una sola vez
        indicesAleatorios.clear();
        for (int i = 0; i < pares.size(); i++) {
            indicesAleatorios.add(i);
        }
        Collections.shuffle(indicesAleatorios, random);

        Pregunta[] resultado = new Pregunta[cantidad];
        for (int i = 0; i < cantidad; i++) {
            resultado[i] = pares.get(indicesAleatorios.get(i)).pregunta;
        }
        return resultado;
    }

    /**
     * Obtiene las respuestas esperadas sincronizadas con las preguntas aleatorias.
     * IMPORTANTE: Debe llamarse DESPUÉS de obtenerPreguntasAleatorias() para asegurar sincronización.
     */
    public String[] obtenerRespuestasAleatorias(int cantidad) {
        if (cantidad > pares.size()) {
            cantidad = pares.size();
        }

        String[] resultado = new String[cantidad];
        for (int i = 0; i < cantidad; i++) {
            resultado[i] = pares.get(indicesAleatorios.get(i)).respuesta;
        }
        return resultado;
    }

    /**
     * Obtiene todas las preguntas del banco.
     */
    public Pregunta[] obtenerTodas() {
        Pregunta[] resultado = new Pregunta[pares.size()];
        for (int i = 0; i < pares.size(); i++) {
            resultado[i] = pares.get(i).pregunta;
        }
        return resultado;
    }

    /**
     * Obtiene el tamaño del banco.
     */
    public int size() {
        return pares.size();
    }
}
