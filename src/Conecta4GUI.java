
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Conecta4GUI extends JFrame implements ActionListener {

    private char[][] tablero;
    private JButton[][] botones;
    private char jugadorActual;

    public Conecta4GUI() {
        setTitle("Conecta 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);

        tablero = inicializarTablero();
        jugadorActual = 'X';

        JPanel panelTablero = new JPanel(new GridLayout(6, 7));
        botones = new JButton[6][7];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setBackground(Color.WHITE);
                botones[i][j].setActionCommand(Integer.toString(j));
                botones[i][j].addActionListener(this);
                panelTablero.add(botones[i][j]);
            }
        }

        add(panelTablero, BorderLayout.CENTER);
        setVisible(true);
    }

    private char[][] inicializarTablero() {
        return new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };
    }

    private boolean hayGanador(char jugador) {
        // Verificar horizontalmente
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <= 3; j++) {
                if (tablero[i][j] == jugador && tablero[i][j + 1] == jugador && tablero[i][j + 2] == jugador && tablero[i][j + 3] == jugador) {
                    return true;
                }
            }
        }

        // Verificar verticalmente
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j < 7; j++) {
                if (tablero[i][j] == jugador && tablero[i + 1][j] == jugador && tablero[i + 2][j] == jugador && tablero[i + 3][j] == jugador) {
                    return true;
                }
            }
        }

        // Verificar diagonalmente (ascendente)
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j <= 3; j++) {
                if (tablero[i][j] == jugador && tablero[i - 1][j + 1] == jugador && tablero[i - 2][j + 2] == jugador && tablero[i - 3][j + 3] == jugador) {
                    return true;
                }
            }
        }

        // Verificar diagonalmente (descendente)
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 7; j++) {
                if (tablero[i][j] == jugador && tablero[i - 1][j - 1] == jugador && tablero[i - 2][j - 2] == jugador && tablero[i - 3][j - 3] == jugador) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int columna = Integer.parseInt(e.getActionCommand());
        realizarJugada(columna);
        actualizarBotones();
        if (hayGanador(jugadorActual)) {
            JOptionPane.showMessageDialog(this, "¡El jugador " + jugadorActual + " ha ganado!");
            reiniciarJuego();
        } else if (tableroLleno()) {
            JOptionPane.showMessageDialog(this, "¡Empate!");
            reiniciarJuego();
        } else {
            jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
        }
    }

    private void realizarJugada(int columna) {
        for (int i = 5; i >= 0; i--) {
            if (tablero[i][columna] == ' ') {
                tablero[i][columna] = jugadorActual;
                break;
            }
        }
    }

    private boolean tableroLleno() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void actualizarBotones() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (tablero[i][j] == 'X') {
                    botones[i][j].setBackground(Color.RED);
                } else if (tablero[i][j] == 'O') {
                    botones[i][j].setBackground(Color.YELLOW);
                }
            }
        }
    }

    private void reiniciarJuego() {
        tablero = inicializarTablero();
        jugadorActual = 'X';
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                botones[i][j].setBackground(Color.WHITE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Conecta4GUI::new);
    }
}
