import javax.swing.*;

public class TresEnRaya {
    private static final int FILAS = 3;
    private static final int COLUMNAS = 3;
    private static final String JUGADOR_X = "X";
    private static final String JUGADOR_O = "O";
    private static String[][] tablero = new String[FILAS][COLUMNAS];
    private static boolean turnoJugadorX = true; // Inicia el juego con el jugador X

    public static void main(String[] args) {
        inicializarTablero();
        jugar();
    }

    // Inicializa el tablero con espacios en blanco
    private static void inicializarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = " ";
            }
        }
    }

    // Muestra el tablero actual
    private static void mostrarTablero() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                sb.append(tablero[i][j]);
                if (j < COLUMNAS - 1) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if (i < FILAS - 1) {
                sb.append("-+-+-\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Comprueba si hay un ganador
    private static boolean hayGanador() {
        // Comprobar filas y columnas
        for (int i = 0; i < FILAS; i++) {
            if (tablero[i][0].equals(tablero[i][1]) && tablero[i][1].equals(tablero[i][2]) && !tablero[i][0].equals(" ")) {
                return true;
            }
            if (tablero[0][i].equals(tablero[1][i]) && tablero[1][i].equals(tablero[2][i]) && !tablero[0][i].equals(" ")) {
                return true;
            }
        }
        // Comprobar diagonales
        if (tablero[0][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][2]) && !tablero[0][0].equals(" ")) {
            return true;
        }
        if (tablero[0][2].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][0]) && !tablero[0][2].equals(" ")) {
            return true;
        }
        return false;
    }

    // Comprueba si el tablero está lleno (empate)
    private static boolean tableroLleno() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (tablero[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Pide al jugador la fila y la columna de su jugada
    private static void realizarJugada() {
        String jugadorActual = (turnoJugadorX) ? JUGADOR_X : JUGADOR_O;
        boolean jugadaValida = false;
        do {
            String filaStr = JOptionPane.showInputDialog("Jugador " + jugadorActual + ", ingresa la fila (0-2):");
            String columnaStr = JOptionPane.showInputDialog("Jugador " + jugadorActual + ", ingresa la columna (0-2):");
            int fila, columna;
            try {
                fila = Integer.parseInt(filaStr);
                columna = Integer.parseInt(columnaStr);
                if (fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS && tablero[fila][columna].equals(" ")) {
                    tablero[fila][columna] = jugadorActual;
                    jugadaValida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Jugada inválida. Inténtalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Ingresa un número válido.");
            }
        } while (!jugadaValida);
    }

    // El bucle principal del juego
    private static void jugar() {
        while (true) {
            mostrarTablero();
            realizarJugada();
            if (hayGanador()) {
                mostrarTablero();
                JOptionPane.showMessageDialog(null, "¡Jugador " + ((turnoJugadorX) ? JUGADOR_X : JUGADOR_O) + " ha ganado!");
                break;
            } else if (tableroLleno()) {
                mostrarTablero();
                JOptionPane.showMessageDialog(null, "¡Empate!");
                break;
            }
            turnoJugadorX = !turnoJugadorX;
        }
    }
}
