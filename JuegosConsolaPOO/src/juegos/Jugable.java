package juegos;

/**
 * Contrato que debe cumplir todo juego para poder lanzarse desde el menú.
 * Solo los objetos de tipo Jugable se pueden jugar.
 *
 * Para agregar un juego nuevo:
 *   1. Crear su paquete (por ejemplo juegos.juego3).
 *   2. Hacer que su clase principal implemente Jugable (o extienda Juego).
 *   3. Agregarlo a la lista de juegos en Main:  juegos.add(new MiJuego(sesion));
 */
public interface Jugable {

    /** Nombre que se muestra en el menú. */
    String getNombre();

    /**
     * Método inicial fijo: arranca la partida completa del juego.
     *
     * @return la mejor puntuación obtenida en la partida
     */
    Puntuacion start();
}