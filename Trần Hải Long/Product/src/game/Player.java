package game;

import java.util.List;
import java.util.ArrayList;

import enums.ShipType;
import enums.CellStatus;

public class Player {
    public String name;
    public Board board;
    public List<Ship> ships;
    public Player(String name, int boardSize) {
        this.name = name;
        this.board = new Board(boardSize);
        this.ships = new ArrayList<>();
        initializeShips();
    }

    private void initializeShips() {
        ships.add(new Ship(ShipType.PATROL_BOAT));
        ships.add(new Ship(ShipType.PATROL_BOAT));
//        ships.add(new Ship(ShipType.DESTROYER));
//        ships.add(new Ship(ShipType.SUBMARINE));
//        ships.add(new Ship(ShipType.BATTLESHIP));
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public int getHits() {
        int hits = 0;
        for (Ship ship : ships) {
            hits += (int) ship.getCells().stream().filter(c -> c.getStatus() == CellStatus.HIT).count();
        }
        return hits;
    }

    public int getSunkShips() {
        int sunk = 0;
        for (Ship ship : ships) {
            if (ship.isSunk()) {
                sunk++;
            }
        }
        return sunk;
    }

    public int getRemainingShips() {
        return ships.size()-getSunkShips();
    }
}
