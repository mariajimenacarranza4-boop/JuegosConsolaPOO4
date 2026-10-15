package juegos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Guarda las 3 mejores puntuaciones de cada juego.
 * Cada vez que se registra una puntuación se compara (con comparar())
 * contra las que ya están para ver si entra al Top 3 y en qué puesto.
 */
public class TablaTop3 {
    private static final int TAMANO_TOP = 3;

    /** Nombre del juego -> sus mejores puntuaciones, de la mejor a la peor. */
    private final Map<String, List<Puntuacion>> tops = new LinkedHashMap<>();

    /**
     * Registra una puntuación y la compara contra el Top 3 de su juego.
     *
     * @return el puesto en que quedó (1, 2 o 3), o 0 si no entró al Top 3
     */
    public int registrar(Puntuacion nueva) {
        List<Puntuacion> top = tops.computeIfAbsent(nueva.getJuego(), k -> new ArrayList<>());

        // Busca el primer lugar donde la nueva es mejor que la que ya está
        int puesto = 0;
        while (puesto < top.size() && top.get(puesto).comparar(nueva) >= 0) {
            puesto++;
        }
        if (puesto >= TAMANO_TOP) {
            return 0;   // no superó a ninguna del Top 3
        }

        top.add(puesto, nueva);
        if (top.size() > TAMANO_TOP) {
            top.remove(top.size() - 1);   // la cuarta sale del Top
        }
        return puesto + 1;
    }

    /** Devuelve el Top 3 actual de un juego (puede tener menos de 3). */
    public List<Puntuacion> getTop(String juego) {
        return tops.getOrDefault(juego, new ArrayList<>());
    }

    /** Muestra en consola el Top 3 actual de un juego. */
    public void mostrar(String juego) {
        System.out.println("\n----- TOP 3 de " + juego.toUpperCase() + " -----");
        List<Puntuacion> top = getTop(juego);
        if (top.isEmpty()) {
            System.out.println("  (sin puntuaciones todavía)");
            return;
        }
        for (int i = 0; i < top.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + top.get(i));
        }
    }

    /**
     * Escenario ficticio: puntuaciones inventadas para que el Top 3
     * ya tenga datos antes de jugar. Se registran igual que una partida real,
     * así que también se comparan (la cuarta de cada juego queda fuera).
     */
    public void cargarEscenarioFicticio() {
        registrar(new Puntuacion("Ana", "Snake", 120, 95.3));
        registrar(new Puntuacion("Luis", "Snake", 90, 70.2));
        registrar(new Puntuacion("Carla", "Snake", 90, 64.8));
        registrar(new Puntuacion("Pedro", "Snake", 40, 30.5));

        registrar(new Puntuacion("Sofía", "Pacman", 1450, 110.4));
        registrar(new Puntuacion("Diego", "Pacman", 980, 88.0));
        registrar(new Puntuacion("Mariana", "Pacman", 620, 52.7));
        registrar(new Puntuacion("Andrés", "Pacman", 300, 25.1));
    }
}