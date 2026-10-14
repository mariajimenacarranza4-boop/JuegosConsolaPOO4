package principal;

import juegos.Jugable;
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
            int opcionEstadisticas = juegos.size() + 2;
            int opcionSalir = juegos.size() + 3;

            System.out.println("\n========== MENÚ DE JUEGOS ==========");
            System.out.println(" 1. Registrar jugadores");
            for (int i = 0; i < juegos.size(); i++) {
                System.out.println(" " + (i + 2) + ". Jugar " + juegos.get(i).getNombre());
            }
            System.out.println(" " + opcionEstadisticas + ". Ver estadísticas");
            System.out.println(" " + opcionSalir + ". Salir");
            System.out.println("====================================");
            System.out.println("Jugadores: " + sesion.nombresJugadores());

            int opcion = sesion.leerEntero("Opción: ", 1, opcionSalir);

            if (opcion == 1) {
                sesion.registrarJugadores();
            } else if (opcion == opcionEstadisticas) {
                sesion.getHistorial().mostrar();
            } else if (opcion == opcionSalir) {
                salir = true;
            } else {
                Jugable juego = juegos.get(opcion - 2);   // el juego elegido, de tipo Jugable
                lanzar(juego, sesion);
            }
        }
        System.out.println("¡Gracias por jugar!");
    }

    /** Lanza cualquier juego Jugable llamando a su método fijo start(). */
    private static void lanzar(Jugable juego, Sesion sesion) {
        if (!sesion.hayJugadores()) {
            System.out.println("Primero hay que registrar a los jugadores.");
            sesion.registrarJugadores();
        }
        juego.start();
    }
}