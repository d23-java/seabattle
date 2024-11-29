package Constructor;

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
        for (int i = 0; i < 10; i++) {
            System.out.printf("| %c |", 'A'+i);
            for (int j = 0; j < 10; j++) {
                System.out.print(" [" + board[i][j] + "] |");
            }
            System.out.println();
            System.out.println("|~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|");
        }
    }

    public void displayBoardPublic() {
        displayBoard(boardPublic);
        System.out.println("Chú thích: ");
        System.out.println("+ [ ]: Ô chưa bị bắn");
        System.out.println("+ [Y]: Ô bị bắn nhưng chưa trúng tàu");
        System.out.println("+ [R]: Đã bắn trúng 1 phần của tàu nào đó");
        System.out.println("+ [1] or [2] or ..: Đã bắn trúng tàu 1, 2, 3, 4 hoặc 5 (Số thứ tự tương ứng lúc nhập tàu)");
    }

    public void displayBoardPrivate() {
        displayBoard(boardPrivate);
        System.out.println("Chú thích: ");
        System.out.println("+ [ ]: Ô chưa bị bắn");
        System.out.println("+ [Y]: Ô đã bị bắn nhưng chưa trúng tàu");
        System.out.println("+ [R]: Đã bị bắn trúng 1 phần của tàu nào đó");
        System.out.println("+ [1] or [2] or ..: Nơi đặt tàu 1, 2, 3, 4 hoặc 5 (Số thứ tự tương ứng lúc nhập tàu)");
    }
}