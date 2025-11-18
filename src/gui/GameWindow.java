package gui;

import modelos.*;
import niveles.*;
import validadores.Validador;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de la aplicación worldVenture con interfaz gráfica.
 */
public class GameWindow extends JFrame {
    private GamePanel gamePanel;
    private Juego juego;
    private JLabel statusLabel;

    public GameWindow() {
        setTitle("worldVenture - Interfaz Gráfica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Mostrar panel de inicio
        mostrarPanelInicio();
    }

    private void mostrarPanelInicio() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titleLabel = new JLabel("worldVenture", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Panel central con campos
        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        centerPanel.add(new JLabel("Nombre:"));
        JTextField nameField = new JTextField();
        centerPanel.add(nameField);

        centerPanel.add(new JLabel("Nivel:"));
        JComboBox<String> levelCombo = new JComboBox<>(new String[]{"Basico", "Intermedio", "Avanzado"});
        centerPanel.add(levelCombo);

        JButton startButton = new JButton("Iniciar Juego");
        centerPanel.add(startButton);

        JButton exitButton = new JButton("Salir");
        centerPanel.add(exitButton);

        panel.add(centerPanel, BorderLayout.CENTER);

        startButton.addActionListener(e -> {
            String nombre = nameField.getText().trim();
            if (!Validador.nombreJugadorValido(nombre)) {
                JOptionPane.showMessageDialog(this,
                        "Nombre inválido. Max 20 caracteres, solo letras/dígitos/espacios.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nivelStr = (String) levelCombo.getSelectedItem();
            Nivel nivel = switch (nivelStr) {
                case "Intermedio" -> new Intermedio();
                case "Avanzado" -> new Avanzado();
                default -> new Basico();
            };

            Jugador jugador = new Jugador(nombre, nivel.nombreNivel());
            Posicion inicio = new Posicion(0, 0);
            jugador.setPosicion(inicio);

            juego = new Juego(jugador, nivel);
            if (juego.puedeIniciar()) {
                juego.iniciar();
                mostrarPanelJuego();
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se puede iniciar el juego.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        exitButton.addActionListener(e -> System.exit(0));

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void mostrarPanelJuego() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel del juego
        gamePanel = new GamePanel(juego, this);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        // Panel de estado
        statusLabel = new JLabel("Presiona W/A/S/D o Flechas para mover");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        gamePanel.requestFocusInWindow();
    }

    public void mostrarPregunta(Pregunta pregunta) {
        String[] opciones;
        if (pregunta.getOpciones().length > 0) {
            opciones = pregunta.getOpciones();
        } else {
            // Para preguntas de texto libre - pedir respuesta
            String respuestaUsuario = JOptionPane.showInputDialog(this,
                    pregunta.getEnunciado(),
                    "");

            if (respuestaUsuario != null) {
                // Obtener respuesta correcta y validar case-insensitive
                String respuestaEsperada = juego.getNivel() != null ? 
                    juego.getNivel().obtenerRespuestasEsperadas()[
                        juego.obtenerIndiceActual()
                    ] : "";
                
                boolean correcto = respuestaUsuario.toLowerCase().trim()
                        .equals(respuestaEsperada.toLowerCase().trim());

                juego.procesarRespuesta(correcto ? 0 : 999);

                if (correcto) {
                    JOptionPane.showMessageDialog(this,
                            "¡Correcto! +10 puntos",
                            "Resultado",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Incorrecto. Pierdes una vida.\nRespuesta correcta: " + 
                            respuestaEsperada,
                            "Resultado",
                            JOptionPane.WARNING_MESSAGE);
                }
            }

            if (!juego.getJugador().estaVivo()) {
                mostrarDerrota();
            }

            gamePanel.repaint();
            return;
        }

        // Preguntas con opciones múltiples
        int respuestaIdx = JOptionPane.showOptionDialog(this,
                pregunta.getEnunciado(),
                "Pregunta",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (respuestaIdx != JOptionPane.CLOSED_OPTION) {
            boolean correcto = juego.procesarRespuesta(respuestaIdx);

            if (correcto) {
                JOptionPane.showMessageDialog(this,
                        "¡Correcto! +" + pregunta.getPuntos() + " puntos",
                        "Resultado",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                String respuestaCorrect = juego.obtenerRespuestaCorrecta();
                JOptionPane.showMessageDialog(this,
                        "Incorrecto. Pierdes una vida.\nRespuesta correcta: " + 
                        (respuestaCorrect != null ? respuestaCorrect : 
                         pregunta.getOpciones()[pregunta.getIndiceCorrecto()]),
                        "Resultado",
                        JOptionPane.WARNING_MESSAGE);
            }

            if (!juego.getJugador().estaVivo()) {
                mostrarDerrota();
            }

            gamePanel.repaint();
        }
    }

    public void mostrarVictoria() {
        juego.terminar();
        Jugador j = juego.getJugador();
        int resultado = JOptionPane.showConfirmDialog(this,
                "¡Ganaste!\n\nPuntaje Final: " + j.getPuntaje() + "\n\n¿Jugar de nuevo?",
                "Victoria",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (resultado == JOptionPane.YES_OPTION) {
            reiniciar();
        } else {
            System.exit(0);
        }
    }

    public void mostrarDerrota() {
        juego.terminar();
        Jugador j = juego.getJugador();
        int resultado = JOptionPane.showConfirmDialog(this,
                "¡Perdiste!\n\nPuntaje Final: " + j.getPuntaje() + "\n\n¿Jugar de nuevo?",
                "Derrota",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (resultado == JOptionPane.YES_OPTION) {
            reiniciar();
        } else {
            System.exit(0);
        }
    }

    private void reiniciar() {
        getContentPane().removeAll();
        juego = null;
        mostrarPanelInicio();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow window = new GameWindow();
            window.setVisible(true);
        });
    }
}
