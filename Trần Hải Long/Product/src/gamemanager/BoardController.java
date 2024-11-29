package gamemanager;

import game.*;
import enums.*;

public class BoardController {
    public Board board;

    public BoardController(Board board) {
        this.board = board;
    }

    public boolean placeShip(Ship ship, Cell start, Orientation orientation) {
        int dx = 0, dy = 0;
        if (orientation == Orientation.HORIZONTAL)
            dy = 1;
        else
            dx = 1;

        int row = start.getXAris() - 'A';
        int col = start.getYAris() - 1;

        int endRow = row + dx * (ship.getSize() - 1);
        int endCol = col + dy * (ship.getSize() - 1);
        if (endRow >= board.getSize() || endCol >= board.getSize()) {
            return false;
        }

        Cell[][] cells = board.getCells();
        for (int i = 0; i < ship.getSize(); i++) {
            int currentRow = row + dx * i;
            int currentCol = col + dy * i;
            if (cells[currentRow][currentCol].getStatus() != CellStatus.EMPTY) {
                return false;
            }
        }

        for (int i = 0; i < ship.getSize(); i++) {
            int currentRow = row + dx * i;
            int currentCol = col + dy * i;
            Cell cell = cells[currentRow][currentCol];
            ship.addCell(cell);
        }

        return true;
    }

    public FireResult fireAt(Cell target) {
        if (target.getStatus() == CellStatus.SHIP) {
            if(target.getShip().isShielded()){
                System.out.println("Tàu được bảo vệ bởi lá chắn!");
                target.getShip().setShielded(false);
                return FireResult.MISS;
            }
            target.setStatus(CellStatus.HIT);
            Ship ship = target.getShip();
            ship.takeHit();
            if (ship.isSunk()) {
                return FireResult.SUNK;
            }
            return FireResult.HIT;
        } else {
            target.setStatus(CellStatus.MISS);
            return FireResult.MISS;
        }
    }

    public Board getBoard() {
        return board;
    }
}