package juegos.juego1;

import juegos.Estadistica;
import juegos.Juego;
import juegos.Jugable;
import juegos.Jugador;
import juegos.Sesion;
import juegos.VentanaJuego;

/**
 * Juego 1: Snake en una matriz. Es Jugable: se lanza con start().
 * La serpiente está hecha de cuadrados verdes y las manzanas son bolitas rojas.
 * Cada turno se juega en una ventana donde la serpiente se mueve sola.
 */
public class JuegoSnake extends Juego implements Jugable {

    public JuegoSnake(Sesion sesion) {
        super("Snake", sesion);
    }

    @Override
    protected void mostrarInstrucciones() {
        System.out.println("Se abrirá una ventana. La serpiente (cuadrados verdes) avanza sola;");
        System.out.println("use las FLECHAS o W A S D para girar y coma las manzanas (bolitas rojas).");
        System.out.println("No choque con las paredes ni consigo misma. Meta: "
                + PanelSnake.META_MANZANAS + " manzanas.  Q o ESC = rendirse.");
    }

    @Override
    protected Estadistica jugarTurno(Jugador jugador) {
        PanelSnake panel = VentanaJuego.mostrar("Snake - " + jugador.getNombre(),
                () -> new PanelSnake(jugador));
        return panel.crearEstadistica(getNombre());
    }
}