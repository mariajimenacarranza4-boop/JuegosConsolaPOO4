package juegos;

/**
 * Contrato para comparar puntuaciones entre sí.
 * Lo usa la tabla del Top 3 para decidir en qué puesto queda cada puntuación.
 *
 * El resultado de comparar() funciona así:
 *   mayor que 0  -> esta puntuación es MEJOR que la otra
 *   menor que 0  -> esta puntuación es PEOR que la otra
 *   igual a 0    -> las dos puntuaciones son iguales
 */
public interface PuntComparable {

    /**
     * Compara esta puntuación con otra.
     *
     * @param otra la puntuación contra la que se compara
     * @return &gt; 0 si esta es mejor, &lt; 0 si es peor, 0 si son iguales
     */
    int comparar(Puntuacion otra);
}