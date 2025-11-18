package app;

import gui.GameWindow;
import javax.swing.SwingUtilities;

/**
 * Punto de entrada de WorldVenture.
 *
 * Inicia la interfaz gráfica (GUI) con Swing.
 */
public class Main {
    /**
     * Punto de entrada principal.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Iniciar GUI en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            GameWindow window = new GameWindow();
            window.setVisible(true);
        });
    }
}