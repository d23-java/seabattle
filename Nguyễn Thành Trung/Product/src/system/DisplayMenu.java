package system;

import mapresources.Board;

public class DisplayMenu {
    public void setBoardSize() {
        System.out.print("Insert size of your sea: ");
        int boardSize = ComputerSystem.scanner.nextInt();
        Board.boardSize = boardSize + 1;
    }
}
