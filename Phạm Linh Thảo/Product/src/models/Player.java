package models;

import java.util.ArrayList;

public class Player {
    private String name;
    private Board board;
    private Board fogBoard;
    private ArrayList<Ship> ships;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
        this.fogBoard = new Board();
        this.ships = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public Board getFogBoard() {
        return fogBoard;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    // Kiểm tra xem có thể đặt tàu không
    public boolean canPlaceShip(int x, int y, int size, char direction) {
        if (x < 0 || y < 0 || x >= 10 || y >= 10) return false;
        if (direction == 'H') {
            for (int i = 0; i < size; i++) {
                if (y + i >= 10 || !board.isCellHaveNoShip(x, y + i)) {
                    return false;
                }
            }
        } else if (direction == 'V') {
            for (int i = 0; i < size; i++) {
                if (x + i >= 10 || !board.isCellHaveNoShip(x + i, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placeShip(int x, int y, int size, char direction,char symbol ) {
        if (!canPlaceShip(x, y, size, direction)) {
            System.out.println("Cannot place ship at the specified location!");
            return;
        }

        Ship ship = new Ship("Ship-" + (ships.size() + 1), size);
        ArrayList<int[]> positions = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (direction == 'H') {
                board.changeCell(x, y + i, symbol);
                positions.add(new int[]{x, y + i});
            } else if (direction == 'V') {
                board.changeCell(x + i, y, symbol);
                positions.add(new int[]{x + i, y});
            }
        }
        ship.setPositions(positions);
        ships.add(ship);
    }

    public boolean shoot(Player opponent, int x, int y) {
        if (opponent.getBoard().isCellHaveNoShip(x, y)) {
            System.out.println("Miss!");
            fogBoard.changeCell(x, y, 'O');
            return false;
        } else {

            System.out.println("Hit!");
            for (Ship ship : opponent.getShips()) {
                if (ship.hit(x, y)) {
                    fogBoard.changeCell(x, y, 'X');
                    opponent.getBoard().changeCell(x, y, 'X');
                    System.out.println("You hit " + opponent.getName() + "'s " + ship.getName() + "!");
                    if (ship.getSize() == 0) {
                        System.out.println("You sunk " + opponent.getName() + "'s " + ship.getName() + "!");
                        opponent.getShips().remove(ship);
                    }
                    break;
                }
            }
            return true;
        }
    }

}
