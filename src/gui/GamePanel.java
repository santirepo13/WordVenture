package gui;

import modelos.*;
import niveles.*;
import validadores.Validador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Panel principal para mostrar el juego en tiempo real.
 * Muestra el mapa, posición del jugador, vidas y puntaje.
 */
public class GamePanel extends JPanel {
    private static final int TILE_SIZE = 40;
    private static final Color COLOR_PARED = new Color(50, 50, 50);
    private static final Color COLOR_LIBRE = new Color(200, 200, 200);
    private static final Color COLOR_INICIO = new Color(100, 255, 100);
    private static final Color COLOR_META = new Color(255, 100, 100);
    private static final Color COLOR_PREGUNTA = new Color(255, 255, 100);
    private static final Color COLOR_JUGADOR = new Color(50, 100, 255);

    private Juego juego;
    private GameWindow gameWindow;

    public GamePanel(Juego juego, GameWindow gameWindow) {
        this.juego = juego;
        this.gameWindow = gameWindow;
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (juego == null || juego.obtenerMapa() == null) {
            return;
        }

        // Dibujar mapa
        Celda[][] mapa = juego.obtenerMapa();
        for (int r = 0; r < mapa.length; r++) {
            for (int c = 0; c < mapa[r].length; c++) {
                int x = c * TILE_SIZE;
                int y = r * TILE_SIZE;
                drawTile(g2d, mapa[r][c], x, y);
            }
        }

        // Dibujar jugador
        Posicion posJugador = juego.getJugador().getPosicion();
        int playerX = posJugador.getColumna() * TILE_SIZE;
        int playerY = posJugador.getFila() * TILE_SIZE;
        drawPlayer(g2d, playerX, playerY);

        // Dibujar información
        drawInfo(g2d, mapa.length);
    }

    private void drawTile(Graphics2D g, Celda celda, int x, int y) {
        Color color = switch (celda) {
            case PARED -> COLOR_PARED;
            case LIBRE -> COLOR_LIBRE;
            case INICIO -> COLOR_INICIO;
            case META -> COLOR_META;
            case PREGUNTA -> COLOR_PREGUNTA;
            case DATO -> new Color(255, 200, 100); // Color naranja para datos curiosos
        };

        g.setColor(color);
        g.fillRect(x, y, TILE_SIZE, TILE_SIZE);

        // Borde
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(1));
        g.drawRect(x, y, TILE_SIZE, TILE_SIZE);

        // Etiqueta
        if (celda == Celda.INICIO || celda == Celda.META) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            String text = celda == Celda.INICIO ? "I" : "M";
            FontMetrics fm = g.getFontMetrics();
            int textX = x + (TILE_SIZE - fm.stringWidth(text)) / 2;
            int textY = y + ((TILE_SIZE - fm.getHeight()) / 2) + fm.getAscent();
            g.drawString(text, textX, textY);
        }
    }

    private void drawPlayer(Graphics2D g, int x, int y) {
        g.setColor(COLOR_JUGADOR);
        g.fillOval(x + 5, y + 5, TILE_SIZE - 10, TILE_SIZE - 10);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawOval(x + 5, y + 5, TILE_SIZE - 10, TILE_SIZE - 10);
    }

    private void drawInfo(Graphics2D g, int mapHeight) {
        int infoY = mapHeight * TILE_SIZE + 20;
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 14));

        Jugador j = juego.getJugador();
        String info = String.format(
                "Jugador: %s | Nivel: %s | Posición: %s | Vidas: %d | Puntaje: %d",
                j.getNombre(),
                j.getNivel(),
                j.getPosicion(),
                j.getVidas(),
                j.getPuntaje()
        );
        g.drawString(info, 10, infoY);
    }

    private void handleKeyPress(KeyEvent e) {
        if (!juego.estaEnEjecucion()) {
            return;
        }

        Direccion dir = switch (e.getKeyCode()) {
            // Teclas WASD
            case KeyEvent.VK_W -> Direccion.ARRIBA;
            case KeyEvent.VK_A -> Direccion.IZQUIERDA;
            case KeyEvent.VK_S -> Direccion.ABAJO;
            case KeyEvent.VK_D -> Direccion.DERECHA;
            // Flechas del teclado
            case KeyEvent.VK_UP -> Direccion.ARRIBA;
            case KeyEvent.VK_LEFT -> Direccion.IZQUIERDA;
            case KeyEvent.VK_DOWN -> Direccion.ABAJO;
            case KeyEvent.VK_RIGHT -> Direccion.DERECHA;
            default -> null;
        };

        if (dir != null) {
            juego.moverJugador(dir);

            // Verificar si está en una pregunta
            Celda[][] mapa = juego.obtenerMapa();
            Posicion pos = juego.getJugador().getPosicion();
            if (mapa[pos.getFila()][pos.getColumna()] == Celda.PREGUNTA) {
                Pregunta pregunta = juego.obtenerPreguntaActual();
                if (pregunta != null) {
                    gameWindow.mostrarPregunta(pregunta);
                }
            }

            // Verificar si está en meta
            if (juego.jugadorEnMeta()) {
                gameWindow.mostrarVictoria();
            }

            // Verificar si está muerto
            if (!juego.getJugador().estaVivo()) {
                gameWindow.mostrarDerrota();
            }

            repaint();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (juego == null || juego.obtenerMapa() == null) {
            return new Dimension(400, 300);
        }
        Celda[][] mapa = juego.obtenerMapa();
        return new Dimension(
                mapa[0].length * TILE_SIZE,
                mapa.length * TILE_SIZE + 50
        );
    }
}
