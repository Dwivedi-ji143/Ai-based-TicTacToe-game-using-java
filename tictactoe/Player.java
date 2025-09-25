package tictactoe;

import java.util.Scanner;

public class Player {
    protected String name;
    protected char symbol;
    private Scanner scanner = new Scanner(System.in);

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public void makeMove(Board board) {
        int row, col;
        while (true) {
            System.out.println(name + " (" + symbol + ") - Enter row and column (0-2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (board.isMoveValid(row, col)) {
                board.makeMove(row, col, symbol);
                break;
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }

    public char getSymbol() {
        return symbol;
    }
}
