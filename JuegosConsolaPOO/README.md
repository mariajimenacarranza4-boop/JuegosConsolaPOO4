# JuegosConsolaPOO

Proyecto de Programación Orientada a Objetos en Java. Es una consola de juegos con un menú desde el que se lanzan dos juegos, **Snake** y **Pac-Man**, y que lleva las estadísticas y el **Top 3** de cada juego.

## Cómo ejecutarlo

1. Abrir la carpeta del proyecto en IntelliJ IDEA.
2. Ejecutar la clase `principal.Main`.

El menú se usa desde la consola y cada turno se juega en una ventana. Los controles son las flechas o W A S D, y Q o ESC para rendirse.

## Menú

| Opción | Qué hace |
|---|---|
| 1. Registrar jugadores | Pide la cantidad de jugadores (1 a 8) y sus nombres. |
| 2. Lanzar juego | Muestra los juegos disponibles y llama al `start()` del juego elegido. |
| 3. Ver estadísticas | Muestra el **Top 3 actual de cada juego** y el historial de partidas de la sesión. |
| 4. Salir | Cierra el programa. |

## Estadísticas y Top 3

1. Al terminar el turno de cada jugador se crea su **estadística** (puntos, movimientos, tiempo y resultado).
2. Esa estadística se convierte en una `Puntuacion` y se **registra** en la `TablaTop3`. Al registrarla se **compara** con las que ya están para saber si entra al Top 3 y en qué puesto.
3. La opción **Ver estadísticas** muestra el Top 3 actual de cada juego. El programa arranca con un **escenario ficticio**: puntuaciones inventadas cargadas en `TablaTop3.cargarEscenarioFicticio()`, para que el Top 3 tenga datos desde el inicio.

El orden de las puntuaciones es: gana la que tenga **más puntos** y, si empatan, la que se hizo en **menos tiempo**.

## Interfaces

### `Jugable`

Es el contrato que cumple todo juego. Gracias a esta interfaz el menú solo puede lanzar objetos que tengan `start()`.

```java
public interface Jugable {
    String getNombre();     // nombre que aparece en el menú
    Puntuacion start();     // método inicial fijo: juega la partida y devuelve la mejor puntuación
}
```

Lo implementan `JuegoSnake` y `JuegoPacman`, a través de la clase abstracta `Juego`.

### `PuntComparable`

Es el contrato para comparar puntuaciones. Lo usa `TablaTop3` para ordenar el Top 3.

```java
public interface PuntComparable {
    int comparar(Puntuacion otra);   // > 0 mejor, < 0 peor, 0 iguales
}
```

La implementa `Puntuacion`.

## Estructura de paquetes

```
src/
├── principal/
│   └── Main.java              Menú principal
└── juegos/
    ├── Jugable.java           Interfaz de los juegos (start)
    ├── PuntComparable.java    Interfaz para comparar puntuaciones
    ├── Puntuacion.java        Puntuación de un jugador en un juego
    ├── TablaTop3.java         Top 3 por juego + escenario ficticio
    ├── Juego.java             Clase base: partida por turnos
    ├── Estadistica.java       Resultado del turno de un jugador
    ├── Historial.java         Partidas jugadas en la sesión
    ├── Sesion.java            Jugadores, Scanner, historial y Top 3
    ├── Jugador.java, Posicion.java, Direccion.java
    ├── PanelJuego.java, VentanaJuego.java   Ventana y dibujo de los juegos
    ├── juego1/                Snake
    └── juego2/                Pac-Man
```

## Cómo agregar un juego nuevo

1. Crear un paquete nuevo, por ejemplo `juegos.juego3`.
2. Hacer que su clase principal implemente `Jugable` (o extienda `Juego`).
3. Agregarlo a la lista de juegos en `Main`: `juegos.add(new MiJuego(sesion));`

El menú y el Top 3 lo incluyen automáticamente.