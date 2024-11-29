package models;

public class Board {
    private char[][] board;
    private int size = 10;

    public Board() {
        board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '~';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void changeCell(int x, int y, char symbol) {
        board[x][y] = symbol;
    }

    public boolean isCellHaveNoShip(int x, int y) {
        return board[x][y] == '~';
    }
    public boolean isCleanAllShip()
    {
        for(int i = 0;i < 10; i++)
        {
            for(int j = 0 ; j < 10; j++)
            {
                if( board[i][j] == 'S' )
                    return false ;
            }
        }
        return true ;
    }

    public void displayBoard() {
        System.out.println("Player's Board:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("["+board[i][j] + "]");
            }
            System.out.println();
        }
    }
}
