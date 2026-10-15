package juegos;

public class Puntuacion implements PuntComparable {
    private final String jugador;
    private final String juego;
    private final int puntos;
    private final double segundos;

    public Puntuacion(String jugador, String juego, int puntos, double segundos) {
        this.jugador = jugador;
        this.juego = juego;
        this.puntos = puntos;
        this.segundos = segundos;
    }

    public String getJugador()  { return jugador; }
    public String getJuego()    { return juego; }
    public int getPuntos()      { return puntos; }
    public double getSegundos() { return segundos; }

    @Override
    public int comparar(Puntuacion otra) {
        if (puntos > otra.puntos) return 1;
        if (puntos < otra.puntos) return -1;
        if (segundos < otra.segundos) return 1;
        if (segundos > otra.segundos) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%-15s %6d pts  %7.1fs", jugador, puntos, segundos);
    }
}