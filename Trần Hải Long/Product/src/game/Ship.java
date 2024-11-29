package game;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import enums.ShipType;
import enums.CellStatus;

public class Ship implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final ShipType type;
    private final int size;
    private final List<Cell> cells;
    private int hits;
    private boolean shielded = false;

    public void addShield() {
        shielded = true;
    }

    public boolean isShielded() {
        return shielded;
    }

    public void setShielded(boolean shielded) {
        this.shielded = shielded;
    }

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

    public int getSize() {
        return size;
    }

    public List<Cell> getCells() {
        return cells;
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
