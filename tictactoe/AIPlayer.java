package tictactoe;

public class AIPlayer extends Player {

    public AIPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public void makeMove(Board board) {
        int[] bestMove = findBestMove(board);
        board.makeMove(bestMove[0], bestMove[1], symbol);
        System.out.println(name + " (" + symbol + ") played at [" + bestMove[0] + ", " + bestMove[1] + "]");
    }

    private int[] findBestMove(Board board) {
        int bestScore = Integer.MIN_VALUE;
        int[] move = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isMoveValid(i, j)) {
                    board.makeMove(i, j, symbol);
                    int score = minimax(board, 0, false);
                    board.undoMove(i, j);
                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }
        return move;
    }

    private int minimax(Board board, int depth, boolean isMaximizing) {
        char result = board.checkWinner();
        if (result == symbol) return 10 - depth;
        if (result != '\0' && result != 'D') return depth - 10;
        if (result == 'D') return 0;

        char opponent = (symbol == 'X') ? 'O' : 'X';
        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isMoveValid(i, j)) {
                    board.makeMove(i, j, isMaximizing ? symbol : opponent);
                    int score = minimax(board, depth + 1, !isMaximizing);
                    board.undoMove(i, j);

                    if (isMaximizing) {
                        bestScore = Math.max(bestScore, score);
                    } else {
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
        }
        return bestScore;
    }
}
