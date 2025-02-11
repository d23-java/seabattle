package gameObjects;

public class Boards {
    private int size;
    private Cell[][] board;

    public Boards(int size) {
        this.size = size;
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    }
    public Cell[][] getCells() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
