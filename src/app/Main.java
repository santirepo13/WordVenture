package app;

import modelos.*;
import niveles.*;
import validadores.Validador;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Aplicación principal de WordVenture.
 *
 * Provee la entrada por consola para crear un jugador, seleccionar nivel y
 * ejecutar el bucle principal del juego usando W/A/S/D para mover al jugador.
 */
public class Main {
    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("WordVenture");
        String nombre;
        do {
            System.out.print("Ingrese nombre: ");
            nombre = sc.nextLine();
            if (!Validador.nombreJugadorValido(nombre)) {
                System.out.println("Nombre inválido. (max 20, sólo letras/dígitos/espacios)");
            }
        } while (!Validador.nombreJugadorValido(nombre));

        String nivel;
        do {
            System.out.println("Seleccione nivel:");
            System.out.println("  1) Basico");
            System.out.println("  2) Intermedio");
            System.out.println("  3) Avanzado");
            System.out.print("Ingrese 1/2/3 o escriba el nombre del nivel: ");
            nivel = sc.nextLine().trim();
            String norm = nivel.toLowerCase();
            boolean valido = norm.equals("1") || norm.equals("2") || norm.equals("3") || Validador.seleccionNivelValida(nivel);
            if (!valido) System.out.println("Nivel inválido. Use 1, 2 o 3, o Basico/Intermedio/Avanzado");
        } while (!(nivel.equals("1") || nivel.equals("2") || nivel.equals("3") || Validador.seleccionNivelValida(nivel)));
        
        String nivelNorm = nivel.trim().toLowerCase();
        Nivel nivelObj;
        if (nivelNorm.equals("1") || nivelNorm.equals("basico") || nivelNorm.equals("principiante")) {
            nivelObj = new Basico();
        } else if (nivelNorm.equals("2") || nivelNorm.equals("intermedio") || nivelNorm.equals("intermediate")) {
            nivelObj = new Intermedio();
        } else {
            nivelObj = new Avanzado();
        }

        Celda[][] mapa = nivelObj.crearMapa();
        Pregunta[] preguntas = nivelObj.obtenerPreguntas();
        String[] respuestasEsperadas = nivelObj.obtenerRespuestasEsperadas();

        Jugador jugador = new Jugador(nombre, nivelObj.nombreNivel());

        Posicion inicio = encontrarInicio(mapa);
        if (inicio == null) inicio = new Posicion(0,0);
        jugador.setPosicion(inicio);

        Juego juego = new Juego(jugador, nivelObj);

        if (!juego.puedeIniciar()) {
            System.out.println("No se puede iniciar el juego (verificar condiciones).");
            sc.close();
            return;
        }

        List<Posicion> posicionesPreguntas = new ArrayList<>();
        for (int r=0;r<mapa.length;r++){
            for (int c=0;c<mapa[r].length;c++){
                if (mapa[r][c] == Celda.PREGUNTA) posicionesPreguntas.add(new Posicion(r,c));
            }
        }

        juego.iniciar();
        System.out.println("Juego iniciado. Usa W A S D para mover. (W=ARRIBA S=ABAJO A=IZQUIERDA D=DERECHA)");

        while (juego.estaEnEjecucion()) {
            System.out.println("Posición: " + jugador.getPosicion() + " Vidas: " + jugador.getVidas() + " Puntaje: " + jugador.getPuntaje());
            System.out.print("Mover (W/A/S/D): ");
            String cmd = sc.nextLine().trim().toUpperCase();
            Direccion d = null;
            if ("W".equals(cmd)) d = Direccion.ARRIBA;
            else if ("S".equals(cmd)) d = Direccion.ABAJO;
            else if ("A".equals(cmd)) d = Direccion.IZQUIERDA;
            else if ("D".equals(cmd)) d = Direccion.DERECHA;
            else {
                System.out.println("Comando inválido.");
                continue;
            }

            boolean moved = juego.moverJugador(d);
            if (!moved) {
                System.out.println("Movimiento bloqueado o fuera de rango.");
                continue;
            }

            Posicion p = jugador.getPosicion();
            Celda tipo = juego.obtenerMapa()[p.getFila()][p.getColumna()];
            if (tipo == Celda.PREGUNTA) {
                int idx = indexOfPos(posicionesPreguntas, p);
                if (idx >= 0 && idx < preguntas.length) {
                    System.out.println(preguntas[idx].getEnunciado());
                    System.out.print("Respuesta (texto): ");
                    String resp = sc.nextLine().trim().toLowerCase();
                    if (resp.equals(respuestasEsperadas[idx])) {
                        System.out.println("Correcto!");
                        jugador.sumarPuntos(preguntas[idx].getPuntos());
                    } else {
                        System.out.println("Incorrecto!");
                        jugador.perderVida();
                        if (!jugador.estaVivo()) {
                            System.out.println("Te quedaste sin vidas. Fin del juego.");
                            juego.terminar();
                            break;
                        }
                    }
                    juego.obtenerMapa()[p.getFila()][p.getColumna()] = Celda.LIBRE;
                }
            } else if (tipo == Celda.META) {
                System.out.println("Has llegado a la meta! Puntaje: " + jugador.getPuntaje());
                juego.terminar();
                break;
            }
        }

        System.out.println("Juego finalizado. Resultado: " + jugador);
        sc.close();
    }

    /**
     * Busca la posición marcada como {@link modelos.Celda#INICIO} dentro del mapa.
     *
     * @param mapa Matriz de celdas donde buscar el inicio.
     * @return Posición de inicio si existe, {@code null} en caso contrario.
     */
    private static Posicion encontrarInicio(Celda[][] mapa) {
        if (mapa == null) return null;
        for (int r=0;r<mapa.length;r++){
            for (int c=0;c<mapa[r].length;c++){
                if (mapa[r][c] == Celda.INICIO) return new Posicion(r,c);
            }
        }
        return null;
    }

    /**
     * Devuelve el índice de la posición p dentro de la lista dada comparando por igualdad.
     *
     * @param list Lista de posiciones.
     * @param p    Posición a localizar.
     * @return Índice de la posición en la lista o -1 si no se encuentra.
     */
    private static int indexOfPos(List<Posicion> list, Posicion p) {
        for (int i=0;i<list.size();i++){
            if (list.get(i).equals(p)) return i;
        }
        return -1;
    }
}