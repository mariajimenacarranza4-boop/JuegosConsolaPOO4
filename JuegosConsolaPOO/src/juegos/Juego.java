package juegos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase base para juegos por turnos. Implementa Jugable:
 * su start() hace que cada jugador juegue su turno, elige al ganador
 * y muestra las estadísticas. Cada juego solo dice cómo se juega UN turno.
 */
public abstract class Juego implements Jugable {
    private final String nombre;
    protected final Sesion sesion;

    protected Juego(String nombre, Sesion sesion) {
        this.nombre = nombre;
        this.sesion = sesion;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    /** Muestra cómo se juega. */
    protected abstract void mostrarInstrucciones();

    /** Juega el turno completo de un jugador y devuelve sus estadísticas. */
    protected abstract Estadistica jugarTurno(Jugador jugador);

    /** Método inicial fijo: partida completa por turnos. */
    @Override
    public void start() {
        Scanner sc = sesion.getScanner();
        List<Jugador> jugadores = sesion.getJugadores();

        System.out.println("\n################  " + nombre.toUpperCase() + "  ################");
        mostrarInstrucciones();

        List<Estadistica> partida = new ArrayList<>();
        for (Jugador j : jugadores) {
            System.out.println("\n>>> Turno de " + j.getNombre() + ". Presione ENTER para abrir la ventana del juego...");
            sc.nextLine();

            Estadistica e = jugarTurno(j);
            partida.add(e);

            System.out.printf("%n%s terminó: %s con %d puntos, %d movimientos en %.1f segundos.%n",
                    j.getNombre(), e.getResultado(), e.getPuntos(), e.getMovimientos(), e.getSegundos());
        }

        Estadistica ganadora = elegirGanador(partida);
        ganadora.marcarGanador();

        System.out.println("\n============ RESULTADOS DE " + nombre.toUpperCase() + " ============");
        System.out.println(Estadistica.encabezado());
        System.out.println("-".repeat(85));
        for (Estadistica e : partida) {
            System.out.println(e);
        }
        System.out.println("\nGANADOR: " + ganadora.getJugador().getNombre()
                + " con " + ganadora.getPuntos() + " puntos");

        sesion.getHistorial().agregar(partida);

        System.out.println("\nPresione ENTER para volver al menú...");
        sc.nextLine();
    }

    /** Gana quien tenga más puntos; si empatan, el que lo hizo en menos tiempo. */
    private Estadistica elegirGanador(List<Estadistica> partida) {
        Estadistica mejor = partida.get(0);
        for (Estadistica e : partida) {
            if (e.getPuntos() > mejor.getPuntos()
                    || (e.getPuntos() == mejor.getPuntos() && e.getSegundos() < mejor.getSegundos())) {
                mejor = e;
            }
        }
        return mejor;
    }
}