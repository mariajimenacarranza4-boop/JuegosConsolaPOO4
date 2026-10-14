package juegos;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Supplier;

/**
 * Abre la ventana del juego y espera hasta que se cierre.
 * Así el turno de cada jugador ocurre en la ventana y luego se vuelve a la consola.
 */
public final class VentanaJuego {

    private VentanaJuego() {
    }

    /** Crea el panel, lo muestra en una ventana y lo devuelve cuando la ventana se cierra. */
    public static <T extends PanelJuego> T mostrar(String titulo, Supplier<T> crearPanel) {
        Object[] resultado = new Object[1];
        try {
            SwingUtilities.invokeAndWait(() -> {
                T panel = crearPanel.get();
                resultado[0] = panel;

                // Ventana "modal": setVisible(true) no regresa hasta que se cierra
                JDialog ventana = new JDialog((java.awt.Frame) null, titulo, true);
                ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                ventana.add(panel);
                ventana.pack();
                ventana.setResizable(false);
                ventana.setLocationRelativeTo(null);
                ventana.setAlwaysOnTop(true);
                ventana.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowOpened(WindowEvent e) {
                        ventana.toFront();
                        panel.requestFocusInWindow();
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        panel.detener();
                    }
                });
                ventana.setVisible(true);
            });
        } catch (Exception e) {
            throw new RuntimeException("No se pudo abrir la ventana del juego", e);
        }
        @SuppressWarnings("unchecked")
        T panel = (T) resultado[0];
        return panel;
    }
}
