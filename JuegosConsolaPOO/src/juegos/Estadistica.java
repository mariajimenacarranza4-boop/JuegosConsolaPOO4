package juegos;

/**
 * Resultado del turno de un jugador: puntos, movimientos, tiempo y si ganó.
 */
public class Estadistica {
    private final Jugador jugador;
    private final String juego;
    private final int puntos;
    private final int movimientos;
    private final double segundos;
    private final String resultado; // "Completó", "Perdió" o "Abandonó"
    private boolean gano;

    public Estadistica(Jugador jugador, String juego, int puntos,
                       int movimientos, double segundos, String resultado) {
        this.jugador = jugador;
        this.juego = juego;
        this.puntos = puntos;
        this.movimientos = movimientos;
        this.segundos = segundos;
        this.resultado = resultado;
    }

    public Jugador getJugador()  { return jugador; }
    public String getJuego()     { return juego; }
    public int getPuntos()       { return puntos; }
    public int getMovimientos()  { return movimientos; }
    public double getSegundos()  { return segundos; }
    public String getResultado() { return resultado; }
    public boolean isGano()      { return gano; }

    /** Convierte este resultado en una Puntuacion que se puede comparar. */
    public Puntuacion getPuntuacion() {
        return new Puntuacion(jugador.getNombre(), juego, puntos, segundos);
    }

    public void marcarGanador() {
        this.gano = true;
        jugador.sumarVictoria();
    }

    public static String encabezado() {
        return String.format("%-8s | %-15s | %6s | %11s | %9s | %-9s | %s",
                "Juego", "Jugador", "Puntos", "Movimientos", "Tiempo", "Resultado", "Ganador");
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-15s | %6d | %11d | %8.1fs | %-9s | %s",
                juego, jugador.getNombre(), puntos, movimientos, segundos,
                resultado, gano ? "* SÍ" : "");
    }
}
