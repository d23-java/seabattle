package manager;

import model.Coordinate;
import model.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final char[][] grid;
    private final int size = 10;
    private final List<Ship> ships;

    public Board() {
        grid = new char[size][size];
        ships = new ArrayList<>();
        for (char[] row : grid) {
            Arrays.fill(row, '~');
        }
    }

    public void display() {
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < size; j++) {
                char cell = grid[i][j];
                switch (cell) {
                    case '~':
                        System.out.print("\u001B[34m~\u001B[0m ");
                        break;
                    case 'O':
                        System.out.print("\u001B[36mO\u001B[0m ");
                        break;
                    case 'X':
                        System.out.print("\u001B[31mX\u001B[0m ");
                        break;
                    default:
                        System.out.print("\u001B[33m" + cell + "\u001B[0m ");
                        break;
                }
            }
            System.out.println();
        }
    }

    public boolean placeShip(Ship ship, Coordinate start, boolean isVertical) {
        int x = start.getX();
        int y = start.getY();
        int size = ship.getSize();

        if (isVertical) {
            if (x + size > this.size) return false;
            for (int i = 0; i < size; i++) {
                if (grid[x + i][y] != '~') return false;
            }
            for (int i = 0; i < size; i++) {
                grid[x + i][y] = ship.getSymbol();
            }
        } else {
            if (y + size > this.size) return false;
            for (int i = 0; i < size; i++) {
                if (grid[x][y + i] != '~') return false;
            }
            for (int i = 0; i < size; i++) {
                grid[x][y + i] = ship.getSymbol();
            }
        }
        ships.add(ship);
        return true;
    }

    public boolean fireAt(Coordinate target) {
        int x = target.getX();
        int y = target.getY();
        if (x < 0 || x >= size || y < 0 || y >= size) {
            System.out.println("Coordinates out of bounds. Try again.");
            return false;
        }
        if (grid[x][y] == '~') {
            grid[x][y] = 'O';
            return false;
        } else if (grid[x][y] != 'O' && grid[x][y] != 'X') {
            for (Ship ship : ships) {
                if (Character.valueOf(ship.getSymbol()).equals(grid[x][y])) {
                    ship.hit();
                    break;
                }
            }
            grid[x][y] = 'X';
            return true;
        } else {
            System.out.println("Already fired at this location. Try again.");
            return false;
        }
    }

    public void update(Coordinate target, boolean hit) {
        int x = target.getX();
        int y = target.getY();
        grid[x][y] = hit ? 'X' : 'O';
    }

    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public int getFiredCount() {
        int count = 0;
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == 'O' || cell == 'X') {
                    count++;
                }
            }
        }
        return count;
    }

    public int getSunkCount() {
        int count = 0;
        for (Ship ship : ships) {
            if (ship.isSunk()) {
                count++;
            }
        }
        return count;
    }

    public int getRemainingShips() {
        int count = 0;
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                count++;
            }
        }
        return count;
    }
}