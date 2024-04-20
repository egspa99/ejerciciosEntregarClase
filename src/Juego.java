import java.util.Scanner;

public class Juego {

    public static void main(String[] args) {
    	
    	//Lo que vamos a hacer es inicializar el tablero devolviendo un tablero vacío
        char[][] tablero = inicializarTablero();
        char jugadorActual = 'X';
        boolean continuarJuego = true;
        
        
        //Vamos a crear un bucle que lo que haga sea que mientras no se cumpla la condicion de Ganar o Empate se repita el pedir jugada
        while (continuarJuego) {
            mostrarTablero(tablero);

            // Pedir al jugador que haga una jugada
            realizarJugada(tablero, jugadorActual);

            // Verificar si hay un ganador o empate
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
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
    }

    // Muestra el tablero actual
    private static void mostrarTablero(char[][] tablero) {
        System.out.println("  | 0 | 1 | 2 | ");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " | ");
            }
            System.out.println();
        }
    }

    // Realiza una jugada
    private static void realizarJugada(char[][] tablero, char jugador) {
        Scanner scanner = new Scanner(System.in);
        int fila, columna;

        do {
            System.out.print("Jugador " + jugador + ", ingresa la fila (0-2): ");
            fila = scanner.nextInt();
            System.out.print("Jugador " + jugador + ", ingresa la columna (0-2): ");
            columna = scanner.nextInt();

            if (fila < 0 || fila > 2 || columna < 0 || columna > 2 || tablero[fila][columna] != ' ') {
                System.out.println("Jugada inválida. Inténtalo de nuevo.");
            } else {
                tablero[fila][columna] = jugador;
                break;
            }
        } while (true);
    }

    // Verifica si hay un ganador
    private static boolean hayGanador(char[][] tablero, char jugador) {
        for (int i = 0; i < 3; i++) {
            // Verificar filas
            if (tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador) {
                return true;
            }
            // Verificar columnas
            if (tablero[0][i] == jugador && tablero[1][i] == jugador && tablero[2][i] == jugador) {
                return true;
            }
        }
        // Verificar diagonales
        if ((tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador) ||
            (tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador)) {
            return true;
        }
        return false;
    }

    // Verifica si el tablero está lleno (empate)
    private static boolean tableroLleno(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

