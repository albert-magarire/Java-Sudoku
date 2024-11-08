import java.util.Scanner;

public class SudokuPlayable {
    private int[][] board;
    private static final int SIZE = 8;
    private static final int EMPTY = 0;

    public SudokuPlayable() { //first constructor for default object
        this.board = new int[SIZE][SIZE];
    }

    public boolean solveSudoku() {
        int[] unassigned = findUnassignedLocation();
        if (unassigned == null) {
            return true; // no unassigned location, Sudoku solved
        }
        int row = unassigned[0];
        int col = unassigned[1];
        for (int num = 1; num <= SIZE; num++) {
            if (isSafe(row, col, num)) {
                board[row][col] = num;
                if (solveSudoku()) {
                    return true;
                }
                board[row][col] = EMPTY; // unassign if solution is not possible with this num
            }
        }
        return false; // backtrack
    }

    private boolean isSafe(int row, int col, int num) {
        return !usedInRow(row, num) &&
                !usedInColumn(col, num) &&
                !usedInBox(row - row % 2, col - col % 4, num);
    }

    private boolean usedInRow(int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInColumn(int col, int num) {
        for (int row = 0; row < SIZE; row++) {
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
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    unassigned[0] = row;
                    unassigned[1] = col;
                    return unassigned;
                }
            }
        }
        return null;
    }
//WE COULD MAKE SOME CHANGES HERE
    public void printBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the initial Sudoku board (use 0 for empty cells):");
        for (int i = 0; i < SIZE; i++) {
            System.out.println("Enter row " + (i + 1) + ":");
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Initial Sudoku board:");
        printBoard();

        if (solveSudoku()) {
            System.out.println("\nSudoku solved successfully:");
            printBoard();
        } else {
            System.out.println("\nNo solution exists.");
        }
    }

    public static void main(String[] args) {
        SudokuPlayable game = new SudokuPlayable();
        game.playGame();
    }
}
