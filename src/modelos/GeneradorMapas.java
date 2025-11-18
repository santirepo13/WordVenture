package modelos;

import java.util.*;

/**
 * Generador de mapas con colocación aleatoria de preguntas y salida.
 * 
 * Proporciona métodos auxiliares para crear mapas con obstáculos específicos
 * y distribuir aleatoriamente las celdas de preguntas y meta.
 */
public class GeneradorMapas {
    private static final Random random = new Random();

    /**
     * Coloca aleatoriamente las preguntas y la salida en un mapa.
     * 
     * @param mapa mapa base con paredes y celdas libres
     * @param numPreguntas número de preguntas a colocar
     * @return mapa con preguntas y salida colocadas aleatoriamente
     */
    public static Celda[][] colocarPreguntasYSalida(Celda[][] mapa, int numPreguntas) {
        int filas = mapa.length;
        int columnas = mapa[0].length;
        
        // Crear lista de celdas libres disponibles (excluyendo INICIO)
        List<int[]> celdasDisponibles = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (mapa[i][j] == Celda.LIBRE) {
                    celdasDisponibles.add(new int[]{i, j});
                }
            }
        }
        
        if (celdasDisponibles.size() < numPreguntas + 1) {
            throw new IllegalArgumentException(
                "No hay suficientes celdas libres para colocar " + numPreguntas + 
                " preguntas y 1 salida"
            );
        }
        
        // Mezclar las celdas disponibles
        Collections.shuffle(celdasDisponibles, random);
        
        // Colocar preguntas
        for (int i = 0; i < numPreguntas; i++) {
            int[] celda = celdasDisponibles.get(i);
            mapa[celda[0]][celda[1]] = Celda.PREGUNTA;
        }
        
        // Colocar salida (meta) en la última celda disponible
        int[] celdaMeta = celdasDisponibles.get(numPreguntas);
        mapa[celdaMeta[0]][celdaMeta[1]] = Celda.META;
        
        return mapa;
    }

    /**
     * Crea un mapa base de tamaño especificado con inicio, celdas libres y paredes específicas.
     * 
     * @param filas número de filas del mapa
     * @param columnas número de columnas del mapa
     * @param paredesIndices lista de índices [fila, columna] donde colocar paredes
     * @return mapa base inicializado
     */
    public static Celda[][] crearMapaBase(int filas, int columnas, List<int[]> paredesIndices) {
        Celda[][] mapa = new Celda[filas][columnas];
        
        // Llenar con celdas libres
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                mapa[i][j] = Celda.LIBRE;
            }
        }
        
        // Colocar inicio en esquina superior izquierda
        mapa[0][0] = Celda.INICIO;
        
        // Colocar paredes en posiciones especificadas
        if (paredesIndices != null) {
            for (int[] pos : paredesIndices) {
                if (pos[0] >= 0 && pos[0] < filas && pos[1] >= 0 && pos[1] < columnas) {
                    if (!(pos[0] == 0 && pos[1] == 0)) { // No sobrescribir INICIO
                        mapa[pos[0]][pos[1]] = Celda.PARED;
                    }
                }
            }
        }
        
        return mapa;
    }

    /**
     * Crea un mapa base sin paredes adicionales.
     * 
     * @param filas número de filas del mapa
     * @param columnas número de columnas del mapa
     * @return mapa base inicializado
     */
    public static Celda[][] crearMapaBase(int filas, int columnas) {
        return crearMapaBase(filas, columnas, null);
    }

    /**
     * Genera un mapa completo con inicio, preguntas y salida aleatoria.
     * 
     * @param filas número de filas del mapa
     * @param columnas número de columnas del mapa
     * @param numPreguntas número de preguntas a colocar
     * @param celdaInicio tipo de celda para el inicio
     * @param celdaMeta tipo de celda para la meta
     * @return mapa generado completamente
     */
    public static Celda[][] generarMapaAleatorio(int filas, int columnas, int numPreguntas,
                                                   Celda celdaInicio, Celda celdaMeta) {
        Celda[][] mapa = crearMapaBase(filas, columnas);
        return colocarPreguntasYSalida(mapa, numPreguntas);
    }
}
