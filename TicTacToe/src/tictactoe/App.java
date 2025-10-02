package tictactoe;

import javax.swing.*;
import java.awt.*;

public class App {
    static String turn = "X";
    static String[][] board = new String[3][3];
    static JButton[][] buttons = new JButton[3][3];

    static int xWins = 0;
    static int oWins = 0;
    static JLabel scoreLabel = new JLabel();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }

        updateScoreLabel();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton(" ");
                buttons[i][j] = button;
                panel.add(button);

                int row = i;
                int col = j;
                button.addActionListener(e -> {
                    if (board[row][col].equals("")) {
                        board[row][col] = turn;
                        button.setText(turn);
                        checkWinner(row, col);
                        // Switch turn if game is not won
                        turn = turn.equals("X") ? "O" : "X";
                        updateScoreLabel();
                    }
                });
            }
        }

        frame.add(scoreLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    static void checkWinner(int row, int col) {
        boolean win = false;

        if (board[row][0].equals(turn) && board[row][1].equals(turn) && board[row][2].equals(turn)
            || board[0][col].equals(turn) && board[1][col].equals(turn) && board[2][col].equals(turn)
            || board[0][0].equals(turn) && board[1][1].equals(turn) && board[2][2].equals(turn)
            || board[0][2].equals(turn) && board[1][1].equals(turn) && board[2][0].equals(turn)) {
            win = true;
        }

        if (win) {
            JOptionPane.showMessageDialog(null, "Player " + turn + " wins!");
            if (turn.equals("X")) xWins++;
            else oWins++;
            resetGame();
        }else if (isBoardFull()) {
        	JOptionPane.showMessageDialog(null, "It's a draw");
        	resetGame();
        }
    }

    static void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        turn = "X";
        updateScoreLabel();
    }

    static void updateScoreLabel() {
        scoreLabel.setText("Turn: " + turn + " | X: " + xWins + " | O: " + oWins);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
    }
    
    static boolean isBoardFull() {
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			if (board[i][j].equals("")) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
}
