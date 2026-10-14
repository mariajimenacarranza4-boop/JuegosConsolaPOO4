package juegos;

/**
 * Un jugador con nombre. Lleva la cuenta de cuántas partidas ha ganado.
 */
public class Jugador {
    private final String nombre;
    private int victorias;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVictorias() {
        return victorias;
    }

    public void sumarVictoria() {
        victorias++;
    }
}
