import java.util.Scanner;

public class Conecta4 {

    public static void main(String[] args) {
        char[][] tablero = inicializarTablero();
        boolean continuarJuego = true;
        char jugadorActual = 'X';

        while (continuarJuego) {
            mostrarTablero(tablero);

            // Pedir al jugador que haga una jugada
            realizarJugada(tablero, jugadorActual);

            // Verificar si hay un ganador o un empate
            if (hayGanador(tablero, jugadorActual)) {
                mostrarTablero(tablero);
                System.out.println("¡El jugador " + jugadorActual + " ha ganado!");
                continuarJuego = false;
            } else if (tableroLleno(tablero)) {
                mostrarTablero(tablero);
                System.out.println("¡Empate!");
                continuarJuego = false;
            }

            // Cambiar al siguiente jugador
            jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
        }
    }

    // Inicializa el tablero
    private static char[][] inicializarTablero() {
        return new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };
    }

    // Muestra el tablero actual
    private static void mostrarTablero(char[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print("| " + tablero[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("  1   2   3   4   5   6   7 ");
        System.out.println();
    }

    // Realiza una jugada
    private static void realizarJugada(char[][] tablero, char jugador) {
        Scanner scanner = new Scanner(System.in);
        int columna;

        do {
            System.out.print("Jugador " + jugador + ", ingresa la columna (1-7): ");
            columna = scanner.nextInt() - 1;

            if (columna < 0 || columna >= tablero[0].length || tablero[0][columna] != ' ') {
                System.out.println("Jugada inválida. Inténtalo de nuevo.");
            } else {
                break;
            }
        } while (true);

        // Colocar la ficha en la columna seleccionada
        for (int i = tablero.length - 1; i >= 0; i--) {
            if (tablero[i][columna] == ' ') {
                tablero[i][columna] = jugador;
                break;
            }
        }
    }

    // Verifica si hay un ganador
    private static boolean hayGanador(char[][] tablero, char jugador) {
        // Verificar horizontalmente
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j <= tablero[i].length - 4; j++) {
                if (tablero[i][j] == jugador &&
                    tablero[i][j + 1] == jugador &&
                    tablero[i][j + 2] == jugador &&
                    tablero[i][j + 3] == jugador) {
                    return true;
                }
            }
        }

        // Verificar verticalmente
        for (int i = 0; i <= tablero.length - 4; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == jugador &&
                    tablero[i + 1][j] == jugador &&
                    tablero[i + 2][j] == jugador &&
                    tablero[i + 3][j] == jugador) {
                    return true;
                }
            }
        }

        // Verificar diagonalmente (ascendente)
        for (int i = 3; i < tablero.length; i++) {
            for (int j = 0; j <= tablero[i].length - 4; j++) {
                if (tablero[i][j] == jugador &&
                    tablero[i - 1][j + 1] == jugador &&
                    tablero[i - 2][j + 2] == jugador &&
                    tablero[i - 3][j + 3] == jugador) {
                    return true;
                }
            }
        }

        // Verificar diagonalmente (descendente)
        for (int i = 3; i < tablero.length; i++) {
            for (int j = 3; j < tablero[i].length; j++) {
                if (tablero[i][j] == jugador &&
                    tablero[i - 1][j - 1] == jugador &&
                    tablero[i - 2][j - 2] == jugador &&
                    tablero[i - 3][j - 3] == jugador) {
                    return true;
                }
            }
        }

        return false;
    }

    // Verifica si el tablero está lleno (empate)
    private static boolean tableroLleno(char[][] tablero) {
        for (int i = 0; i < tablero[0].length; i++) {
            if (tablero[0][i] == ' ') {
                return false;
            }
        }
        return true;
    }
}
