package System;

import MapResources.Board;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.setBoardCell(2, 2);
        board.showBoard();
    }
}