public class Field {
    private char[][] myBoard;
    private char[][] displayBoard;
    private int size;

    // Constructor
    public Field(int size) {
        this.size = size;
        myBoard = new char[size][size];
        displayBoard = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                myBoard[i][j] = ' ';
                displayBoard[i][j] = ' ';
            }
        }
    }

    // Getter cho myBoard
    public char[][] getMyBoard(int i) {
        return myBoard;
    }

    // Getter cho displayBoard
    public char[][] getDisplayBoard() {
        return displayBoard;
    }


    public char getMyBoard(int row, int col) {
        return myBoard[row][col];
    }

    public char getDisplayBoard(int row, int col) {
        return displayBoard[row][col];
    }

    public void setMyBoard(int row, int col, char c) {
        myBoard[row][col] = c;
    }

    public void setDisplayBoard(int row, int col, char c) {
        displayBoard[row][col] = c;
    }

    // Hiển thị myBoard
    public void showMyBoard() {
        System.out.println("My Board:");
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                if (i == 0 && j == 0) System.out.print("   ");
                else if (i == 0) System.out.printf(" %2d ", j);
                else if (j == 0) System.out.printf("%2d |", i);
                else {
                    System.out.printf(" %c |", getMyBoard(i - 1, j - 1));
                }
            }
            System.out.print("\n");
        }
    }

    // Hiển thị displayBoard
    public void showDisplayBoard() {
        System.out.println("Display Board:");
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                if (i == 0 && j == 0) System.out.print("   ");
                else if (i == 0) System.out.printf(" %2d ", j);
                else if (j == 0) System.out.printf("%2d |", i);
                else {
                    System.out.printf(" %c |", getDisplayBoard(i - 1, j - 1));
                }
            }
            System.out.print("\n");
        }
    }
}
