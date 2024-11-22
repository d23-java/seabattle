package game;

import java.util.ArrayList;
import java.util.List;

import enums.ShipType;
import enums.CellStatus;

public class Ship {
    private ShipType type;
    private int size;
    private List<Cell> cells;
    private int hits;

    private int getSizeByType(ShipType type) {
        return switch (type) {
            case PATROL_BOAT -> 2;
            case DESTROYER -> 4;
            case SUBMARINE -> 3;
            case BATTLESHIP -> 5;
        };
    }

    public Ship(ShipType type) {
        this.type = type;
        this.size = getSizeByType(type);
        this.cells = new ArrayList<>();
        this.hits = 0;
    }

    public ShipType getType() {
        return type;
    }

    public void setType(ShipType type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void addCell(Cell cell) {
        cells.add(cell);
        cell.setShip(this);
        cell.setStatus(CellStatus.SHIP);
    }

    public void takeHit() {
        hits++;
    }

    public boolean isSunk() {
        return hits == size;
    }
}
