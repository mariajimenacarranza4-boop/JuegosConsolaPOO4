package principal;

import juegos.Jugable;
import juegos.Puntuacion;
import juegos.Sesion;
import juegos.juego1.JuegoSnake;
import juegos.juego2.JuegoPacman;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Menú principal. Solo se pueden jugar los objetos de tipo Jugable.
 * El menú se arma solo a partir de la lista de juegos, así que para
 * agregar un juego nuevo basta con añadirlo a la lista.
 */
public class Main {

    public static void main(String[] args) {
        // Para que se vean bien las tildes y los símbolos en la consola
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));

        Sesion sesion = new Sesion(new Scanner(System.in));

        // ===== Juegos disponibles: todos se instancian como Jugable =====
        List<Jugable> juegos = new ArrayList<>();
        juegos.add(new JuegoSnake(sesion));
        juegos.add(new JuegoPacman(sesion));
        // juegos.add(new JuegoNuevo(sesion));   <- así se agregan más juegos

        boolean salir = false;
        while (!salir) {
            System.out.println("\n========== MENÚ DE JUEGOS ==========");
            System.out.println(" 1. Registrar jugadores");
            System.out.println(" 2. Lanzar juego");
            System.out.println(" 3. Ver estadísticas");
            System.out.println(" 4. Salir");
            System.out.println("====================================");
            System.out.println("Jugadores: " + sesion.nombresJugadores());

            int opcion = sesion.leerEntero("Opción: ", 1, 4);

            switch (opcion) {
                case 1:
                    sesion.registrarJugadores();
                    break;
                case 2:
                    lanzarJuego(juegos, sesion);
                    break;
                case 3:
                    verEstadisticas(juegos, sesion);
                    break;
                default:
                    salir = true;
                    break;
            }
        }
        System.out.println("¡Gracias por jugar!");
    }

    /**
     * Opción "Lanzar juego": muestra los juegos disponibles y lanza el elegido
     * llamando a su método fijo start(). Funciona con cualquier Jugable.
     */
    private static void lanzarJuego(List<Jugable> juegos, Sesion sesion) {
        System.out.println("\n----- LANZAR JUEGO -----");
        for (int i = 0; i < juegos.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + juegos.get(i).getNombre());
        }
        System.out.println(" 0. Volver");
        int opcion = sesion.leerEntero("¿Qué juego? ", 0, juegos.size());
        if (opcion == 0) {
            return;
        }

        if (!sesion.hayJugadores()) {
            System.out.println("Primero hay que registrar a los jugadores.");
            sesion.registrarJugadores();
        }

        Jugable juego = juegos.get(opcion - 1);   // el juego elegido, de tipo Jugable
        Puntuacion mejor = juego.start();          // aquí se usa el start()
        System.out.println("Mejor puntuación de la partida: " + mejor);
    }

    /**
     * Opción "Ver estadísticas": muestra el Top 3 actual de cada juego
     * (incluye el escenario ficticio) y el historial de partidas de esta sesión.
     */
    private static void verEstadisticas(List<Jugable> juegos, Sesion sesion) {
        System.out.println("\n=============== ESTADÍSTICAS ===============");
        for (Jugable juego : juegos) {
            sesion.getTop3().mostrar(juego.getNombre());
        }
        sesion.getHistorial().mostrar();
    }
}