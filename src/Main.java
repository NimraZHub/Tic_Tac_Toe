import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    // Declare variables
    static JFrame frame;
    static JTextField textField;
    static JPanel panel;
    static JButton[] buttons = new JButton[9];
    static boolean playerX = true; // true = Player X, false = Player O

    public static void main(String[] args) {
        // Create frame
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(500, 90, 500, 450);
        frame.setLayout(null);

        // Image Insert
        ImageIcon image = new ImageIcon("C:\\Users\\HP\\Documents\\Idea Projects\\Tic tactoe\\src\\tic-tac-toe.png");
        frame.setIconImage(image.getImage());

        // Text Field
        textField = new JTextField("Player X's Turn");
        textField.setBounds(27, 33, 430, 50);
        textField.setFont(new Font("Arial", Font.BOLD, 16));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        frame.add(textField);

        // Panel for the buttons
        panel = new JPanel();
        panel.setBounds(50, 100, 400, 300);
        panel.setLayout(new GridLayout(3, 3, 10, 10));
        frame.add(panel);

        // Initialize buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton) e.getSource();
                    if (buttonClicked.getText().equals("")) {
                        buttonClicked.setText(playerX ? "X" : "O");
                        playerX = !playerX;
                        textField.setText(playerX ? "Player X's Turn" : "Player O's Turn");
                        checkForWin();
                    }
                }
            });
            panel.add(buttons[i]);
        }

        frame.setVisible(true);
    }
    public static void checkForWin() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = buttons[i].getText();
        }
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2]) ||
                    checkLine(board[0][i], board[1][i], board[2][i])) {
                announceWinner(board[i][i]);
                return;
            }
        }
        if (checkLine(board[0][0], board[1][1], board[2][2]) ||
                checkLine(board[0][2], board[1][1], board[2][0])) {
            announceWinner(board[1][1]);
            return;
        }
        boolean draw = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                draw = false;
                break;
            }
        }
        if (draw) {
            textField.setText("It's a Draw!");
            disableButtons();
        }
    }
    // Helper method to check if all three values in a line are the same
    public static boolean checkLine(String a, String b, String c) {
        return !a.equals("") && a.equals(b) && b.equals(c);
    }
    public static void announceWinner(String winner) {
        textField.setText("Player " + winner + " Wins!");
        disableButtons();
    }
    public static void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
}
