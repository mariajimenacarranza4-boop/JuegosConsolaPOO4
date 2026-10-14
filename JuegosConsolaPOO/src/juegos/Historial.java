package juegos;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Guarda las estadísticas de todas las partidas jugadas mientras el programa está abierto.
 */
public class Historial {
    private final List<Estadistica> registros = new ArrayList<>();

    public void agregar(List<Estadistica> partida) {
        registros.addAll(partida);
    }

    public void mostrar() {
        System.out.println("\n============ ESTADÍSTICAS GENERALES ============");
        if (registros.isEmpty()) {
            System.out.println("Todavía no se ha jugado ninguna partida.");
            return;
        }
        System.out.println(Estadistica.encabezado());
        System.out.println("-".repeat(85));
        for (Estadistica e : registros) {
            System.out.println(e);
        }

        // Resumen de victorias por jugador
        Set<Jugador> jugadores = new LinkedHashSet<>();
        for (Estadistica e : registros) {
            jugadores.add(e.getJugador());
        }
        System.out.println("\nVictorias por jugador:");
        for (Jugador j : jugadores) {
            System.out.printf("  %-15s %d%n", j.getNombre(), j.getVictorias());
        }
    }
}
