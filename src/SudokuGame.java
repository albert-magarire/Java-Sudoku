public class SudokuGame {
    private int[][] board;

    public SudokuGame(int[][] initialBoard) {
        this.board = initialBoard;
    }

    public boolean solveSudoku() {
        int row, col;
        int[] unassigned = findUnassignedLocation();
        if (unassigned == null) {
            return true;
        }
        row = unassigned[0];
        col = unassigned[1];
        for (int num = 1; num <= board.length; num++) {
            if (isSafe(row, col, num)) {
                board[row][col] = num;
                if (solveSudoku()) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isSafe(int row, int col, int num) {
        return !usedInRow(row, num) &&
                !usedInColumn(col, num) &&
                !usedInBox(row - row % 2, col - col % 4, num);
    }

    private boolean usedInRow(int row, int num) {
        for (int col = 0; col < board.length; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInColumn(int col, int num) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInBox(int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row + boxStartRow][col + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[] findUnassignedLocation() {
        int[] unassigned = new int[2];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == 0) {
                    unassigned[0] = row;
                    unassigned[1] = col;
                    return unassigned;
                }
            }
        }
        return null;
    }

    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] initialBoard = {
                {0, 0, 3, 0, 0, 0, 0, 0},
                {4, 0, 0, 1, 0, 0, 6, 8},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 2, 0, 4},
                {0, 6, 0, 0, 0, 0, 0, 7},
                {7, 0, 0, 0, 0, 0, 0, 3},
                {0, 0, 0, 3, 2, 0, 0, 0},
                {0, 0, 0, 8, 0, 0, 4, 0}
        };
        SudokuGame game = new SudokuGame(initialBoard);
        if (game.solveSudoku()) {
            System.out.println("Sudoku solved successfully:");
            game.printBoard();
        } else {
            System.out.println("No solution exists.");
        }
    }
}
