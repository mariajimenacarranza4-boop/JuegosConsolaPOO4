package juegos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Datos compartidos por todos los juegos mientras el programa está abierto:
 * los jugadores registrados, el teclado (Scanner) y el historial de estadísticas.
 * Cada juego Jugable recibe la sesión al crearse, así start() no necesita parámetros.
 */
public class Sesion {
    private final Scanner scanner;
    private final List<Jugador> jugadores = new ArrayList<>();
    private final Historial historial = new Historial();
    private final TablaTop3 top3 = new TablaTop3();

    public Sesion(Scanner scanner) {
        this.scanner = scanner;
        top3.cargarEscenarioFicticio();
    }

    public TablaTop3 getTop3() {
        return top3;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Historial getHistorial() {
        return historial;
    }

    public boolean hayJugadores() {
        return !jugadores.isEmpty();
    }

    /** Pide la cantidad de jugadores y sus nombres. */
    public void registrarJugadores() {
        int cantidad = leerEntero("¿Cuántos jugadores? (1 a 8): ", 1, 8);
        jugadores.clear();
        for (int i = 1; i <= cantidad; i++) {
            String nombre = "";
            while (nombre.isEmpty()) {
                System.out.print("Nombre del jugador " + i + ": ");
                nombre = scanner.nextLine().trim();
            }
            jugadores.add(new Jugador(nombre));
        }
    }

    public int leerEntero(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            try {
                int n = Integer.parseInt(scanner.nextLine().trim());
                if (n >= min && n <= max) return n;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Escriba un número entre " + min + " y " + max + ".");
        }
    }

    public String nombresJugadores() {
        if (jugadores.isEmpty()) return "(ninguno)";
        StringBuilder sb = new StringBuilder();
        for (Jugador j : jugadores) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(j.getNombre());
        }
        return sb.toString();
    }
}