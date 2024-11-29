import java.util.*;

public class Board {
    private char[][] board;
    public Board() {
        board = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void setValue(int x, int y, char value) {
        board[x][y] = value;
    }

    public char getValue(int x, int y) {
        return board[x][y];
    }

    public void displayBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print("[" + board[i][j] + "]");
            }
            System.out.printf("\n");
        }
    }
}
