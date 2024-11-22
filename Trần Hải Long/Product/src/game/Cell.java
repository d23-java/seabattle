package game;

import enums.CellStatus;

public class Cell {
    public char x;
    public int y;
    private CellStatus status;
    private Ship ship;
    public Cell(char x, int y){
        this.x = x;
        this.y = y;
        this.status = CellStatus.EMPTY;
        this.ship = null;
    }

    public char getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}

