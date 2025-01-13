package models;

public class Board {
    private char[][] board;
    private int size = 10;

    public Board() {
        board = new char[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
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
        return board[x][y] == '~' || board[x][y] =='X';
    }
    public boolean isCleanAllShip()
    {
        for(int i = 0;i < 10; i++)
        {
            for(int j = 0 ; j < 10; j++)
            {
                if( board[i][j] != '~' &&  board[i][j] !='X' )
                    return false ;
            }
        }
        return true ;
    }

    public void displayBoard() {
        System.out.print("    ");
        for (int  c = 1; c <= 10 ; c++)
        {
            System.out.printf("%2d   ", c);
        }
        System.out.println();
        for (char c = 'A'; c <= 'J'; c++)
        {
            System.out.printf("%2c ",c  );
            for (int j = 0; j < size; j++)
            {
                System.out.print(getColoredSymbol(board[c-'A'][j]));
            }
            System.out.println();
        }
    }

    private String getColoredSymbol(char symbol) {
        String colorCode;
        switch (symbol) {
            case 'P': colorCode = "\u001B[33m"; break;
            case 'B': colorCode = "\u001B[35m"; break;
            case 'S': colorCode = "\u001B[34m"; break;
            case 'D': colorCode = "\u001B[31m"; break;
            case '~': colorCode = "\u001B[36m"; break;
            default: colorCode = "\u001B[0m"; break;
        }
        return String.format(" [%s%c\u001B[0m] ", colorCode, symbol);
    }

}
