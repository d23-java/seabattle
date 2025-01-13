package Constructor;
import java.io.*;

public class Board {
    private char[][] boardPublic;
    private char[][] boardPrivate;

    public Board() {
        boardPublic = new char[10][10];
        boardPrivate = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardPublic[i][j] = ' ';
                boardPrivate[i][j] = ' ';
            }
        }
    }

    public void setValue(int x, int y, char value, boolean isPrivate) {
        if (isPrivate) {
            boardPrivate[x][y] = value;
        } else {
            boardPublic[x][y] = value;
        }
    }

    public char getValue(int x, int y, boolean isPrivate) {
        return isPrivate ? boardPrivate[x][y] : boardPublic[x][y];
    }

    private void displayBoard(char[][] board) {
        System.out.println("|___|  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10 |");
        System.out.println("|~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|");
        String ANSI_RESET = "\u001B[0m";
        String ANSI_WHITE = "\u001B[37m";
        String ANSI_BLUE_BACKGROUND = "\u001B[34m";
        for (int i = 0; i < 10; i++) {
            System.out.printf("| %c |", 'A'+i);
            for (int j = 0; j < 10; j++) {
                if(board[i][j] >= '1' && board[i][j] <= '9') {
                    System.out.print(ANSI_BLUE_BACKGROUND + " ["  + board[i][j]  + "] " + ANSI_RESET + "|");
                }
                else    System.out.print(" [" + board[i][j] + "] |");
            }
            System.out.println();
            System.out.println("|~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|");
        }
    }

    public void displayBoardPublic() {
        displayBoard(boardPublic);
        System.out.println("Chu thich: ");
        System.out.println("+ [ ]: O chua bi ban");
        System.out.println("+ [" + "\u001B[31m" + "Y" + "\u001B[0m" + "]: O bi ban nhung chua trung tau");
        System.out.println("+ [" + "\u001B[32m" + "R" + "\u001B[0m" + "]: Da ban trung 1 phan cua tau nao do");
        System.out.println("+ [" + "\u001B[34m" + "1" + "\u001B[0m" + "] or [" + "\u001B[34m" + "2" + "\u001B[0m" + "] or ..: Da ban trung tau 1, 2, 3, 4 hoac 5 (So thu tu tuong ung luc nhap tau)");

    }

    public void displayBoardPrivate() {
        displayBoard(boardPrivate);
        System.out.println("Chu thich: ");
        System.out.println("+ [ ]: O chua bi ban");
        System.out.println("+ [" + "\u001B[31m" + "Y" + "\u001B[0m" + "]: O bi ban nhung chua trung tau");
        System.out.println("+ [" + "\u001B[32m" + "R" + "\u001B[0m" + "]: Da bi ban trung 1 phan cua tau nao do");
        System.out.println("+ [" + "\u001B[34m" + "1" + "\u001B[0m" + "] or [" + "\u001B[34m" + "2" + "\u001B[0m" + "] or ..: Noi dat tau 1, 2, 3, 4 hoac 5 (So thu tu tuong ung luc nhap tau)");

    }
}