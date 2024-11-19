package MapResources;

public class Board {
    public static final int boardSize = 11;
    private Cell[][] board = new Cell[boardSize][boardSize];

    public Board() {
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                board[row][column] = new Cell();
            }
        }

        for (int i = 1; i < boardSize; i++) {
            board[0][i].setCentreOfCell("" + i);
            if (i > 9) board[0][i].setLargeNumberColumnCell();
            board[i][0].setCentreOfCell(Character.toString((char) (i + 64)));
        }
    }

    public void showBoard() {
        for (int row = 0; row < 11; row++) {
            for (int column = 0; column < 11; column++) {
                board[row][column].showCell();
            }
            System.out.println();
        }
    }

    public void setBoardCell(int XAxis, int YAxis, String information, String ANSIcode) {
        board[XAxis][YAxis].setCellColor(ANSIcode, information);
    }

    public void addShip() {
        System.out.println();
    }
}
