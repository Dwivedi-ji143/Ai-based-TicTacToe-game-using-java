package tictactoe;

import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Scanner scanner = new Scanner(System.in);

    public void startGame() {
        board = new Board();
        System.out.println("Choose Mode: 1. Human vs Human  2. Human vs AI");
        int mode = scanner.nextInt();

        if (mode == 1) {
            System.out.print("Enter name for Player 1: ");
            player1 = new Player(scanner.next(), 'X');
            System.out.print("Enter name for Player 2: ");
            player2 = new Player(scanner.next(), 'O');
        } else {
            System.out.print("Enter your name: ");
            player1 = new Player(scanner.next(), 'X');
            player2 = new AIPlayer("Computer", 'O');
        }

        Player currentPlayer = player1;
        while (true) {
            board.displayBoard();
            currentPlayer.makeMove(board);
            char winner = board.checkWinner();

            if (winner != '\0') {
                board.displayBoard();
                if (winner == 'D') {
                    System.out.println("It's a Draw!");
                } else {
                    System.out.println(currentPlayer.name + " (" + currentPlayer.getSymbol() + ") Wins!");
                }
                break;
            }
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }
}
