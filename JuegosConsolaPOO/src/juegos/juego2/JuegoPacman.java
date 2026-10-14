package juegos.juego2;

import juegos.Estadistica;
import juegos.Juego;
import juegos.Jugable;
import juegos.Jugador;
import juegos.Sesion;
import juegos.VentanaJuego;

/**
 * Juego 2: Pacman en una matriz. Es Jugable: se lanza con start().
 * Pacman es una bola amarilla, los puntos son +, el poder es *,
 * las paredes son rojas y los fantasmas son cuadrados azul, verde y rosa.
 * Cada turno se juega en una ventana donde Pacman se mueve solo.
 */
public class JuegoPacman extends Juego implements Jugable {

    public JuegoPacman(Sesion sesion) {
        super("Pacman", sesion);
    }

    @Override
    protected void mostrarInstrucciones() {
        System.out.println("Se abrirá una ventana. Mueva a Pacman (bola amarilla) con las FLECHAS o W A S D.");
        System.out.println("Coma todos los puntos + . Al comer un poder * los fantasmas (cuadrados)");
        System.out.println("se vuelven huecos, se mueven LENTO y puede comérselos.");
        System.out.println("Tiene " + PanelPacman.VIDAS + " vidas.  Q o ESC = rendirse.");
    }

    @Override
    protected Estadistica jugarTurno(Jugador jugador) {
        PanelPacman panel = VentanaJuego.mostrar("Pacman - " + jugador.getNombre(),
                () -> new PanelPacman(jugador));
        return panel.crearEstadistica(getNombre());
    }
}