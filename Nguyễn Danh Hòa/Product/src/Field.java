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
                    char cell = getMyBoard(i - 1, j - 1);
                    String color, bgColor;
                    if (cell == 'X') {
                        color = ColorManager.textRed;
                        bgColor = ColorManager.bgYellow; // Nền vàng cho 'X'
                    } else if (cell == ' ') {
                        color = ColorManager.textWhite;
                        bgColor = ColorManager.bgBlack; // Nền đen cho ô trống
                    } else {
                        color = ColorManager.textGreen;
                        bgColor = ColorManager.bgYellow; // Nền cyan cho 'O'
                    }
                    System.out.printf(" %s%s%c%s |", bgColor, color, cell, ColorManager.reset);
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
                    char cell = getDisplayBoard(i - 1, j - 1);
                    String color, bgColor;
                    if (cell == 'X') {
                        color = ColorManager.textRed;
                        bgColor = ColorManager.bgYellow; // Nền vàng cho 'X'
                    } else if (cell == ' ') {
                        color = ColorManager.textWhite;
                        bgColor = ColorManager.bgBlack; // Nền đen cho ô trống
                    } else {
                        color = ColorManager.textGreen;
                        bgColor = ColorManager.bgCyan; // Nền cyan cho 'O'
                    }
                    System.out.printf(" %s%s%c%s |", bgColor, color, cell, ColorManager.reset);
                }
            }
            System.out.print("\n");
        }
    }
    // bảng màu
    public class ColorManager {
        public static final String reset = "\u001B[0m";
        public static final String textBlack = "\u001B[30m";
        public static final String textRed = "\u001B[31m";
        public static final String textGreen = "\u001B[32m";
        public static final String textYellow = "\u001B[33m";
        public static final String textBlue = "\u001B[34m";
        public static final String textPurple = "\u001B[35m";
        public static final String textCyan = "\u001B[36m";
        public static final String textWhite = "\u001B[37m";

        public static final String bgBlack = "\u001B[40m";
        public static final String bgRed = "\u001B[41m";
        public static final String bgGreen = "\u001B[42m";
        public static final String bgYellow = "\u001B[43m";
        public static final String bgBlue = "\u001B[44m";
        public static final String bgPurple = "\u001B[45m";
        public static final String bgCyan = "\u001B[46m";
        public static final String bgWhite = "\u001B[47m";
    }

}
