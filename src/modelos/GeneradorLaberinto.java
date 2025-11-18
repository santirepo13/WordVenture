package modelos;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Generador de laberintos con algoritmo de construcción.
 * 
 * Crea laberintos con caminos garantizados desde inicio hasta salida.
 */
public class GeneradorLaberinto {
    private static final Random random = new Random();

    /**
     * Genera un laberinto con paredes y un camino hacia la salida.
     * 
     * @param tamaño dimensiones del mapa (tamaño x tamaño)
     * @param numPreguntas cantidad de preguntas a colocar
     * @param numDatos cantidad de celdas de datos curiosos
     * @return mapa con laberinto, preguntas y datos curiosos
     */
    public static Celda[][] generarLaberintoAleatorio(int tamaño, int numPreguntas, int numDatos) {
        Celda[][] mapa = new Celda[tamaño][tamaño];
        
        // Llenar todo con paredes inicialmente
        for (int r = 0; r < tamaño; r++) {
            for (int c = 0; c < tamaño; c++) {
                mapa[r][c] = Celda.PARED;
            }
        }
        
        // Generar laberinto usando recursive backtracking
        generarLaberintoDFS(mapa, 0, 0);
        
        // Marcar inicio
        mapa[0][0] = Celda.INICIO;
        
        // Colocar salida en celda aleatoria válida (no en inicio)
        Posicion salida = colocarSalidaAleatoria(mapa, tamaño);
        
        // Colocar preguntas en celdas libres
        colocarElementos(mapa, Celda.PREGUNTA, numPreguntas, salida);
        
        // Colocar datos curiosos en celdas libres
        colocarElementos(mapa, Celda.DATO, numDatos, salida);
        
        return mapa;
    }

    /**
     * Algoritmo DFS para generar laberinto (recursive backtracking).
     */
    private static void generarLaberintoDFS(Celda[][] mapa, int r, int c) {
        mapa[r][c] = Celda.LIBRE;
        
        // Direcciones en orden aleatorio
        int[][] direcciones = {{-2, 0}, {2, 0}, {0, -2}, {0, 2}};
        List<int[]> dirs = new ArrayList<>();
        for (int[] dir : direcciones) {
            dirs.add(dir);
        }
        Collections.shuffle(dirs);
        
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            
            if (nr >= 0 && nr < mapa.length && nc >= 0 && nc < mapa[0].length && 
                mapa[nr][nc] == Celda.PARED) {
                
                // Marcar la celda entre medio como libre
                mapa[r + dir[0] / 2][c + dir[1] / 2] = Celda.LIBRE;
                
                generarLaberintoDFS(mapa, nr, nc);
            }
        }
    }

    /**
     * Coloca la salida en una celda aleatoria válida.
     */
    private static Posicion colocarSalidaAleatoria(Celda[][] mapa, int tamaño) {
        Posicion salida;
        do {
            int r = random.nextInt(tamaño);
            int c = random.nextInt(tamaño);
            salida = new Posicion(r, c);
        } while ((salida.getFila() == 0 && salida.getColumna() == 0) || 
                 mapa[salida.getFila()][salida.getColumna()] == Celda.PARED);
        
        mapa[salida.getFila()][salida.getColumna()] = Celda.META;
        return salida;
    }

    /**
     * Coloca elementos (preguntas o datos) en celdas libres.
     */
    private static void colocarElementos(Celda[][] mapa, Celda tipo, int cantidad, Posicion salida) {
        int colocados = 0;
        int intentos = 0;
        int maxIntentos = cantidad * 10;
        
        while (colocados < cantidad && intentos < maxIntentos) {
            int r = random.nextInt(mapa.length);
            int c = random.nextInt(mapa[0].length);
            Posicion pos = new Posicion(r, c);
            
            if (mapa[r][c] == Celda.LIBRE && 
                !(r == 0 && c == 0) && 
                !pos.equals(salida)) {
                mapa[r][c] = tipo;
                colocados++;
            }
            intentos++;
        }
    }
}
