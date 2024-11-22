import java.util.Arrays;

public class Board {
    private char[][] grid;
    private Ship[] ships;
    private int shipCount;
    private int firedCount;
    private int sunkCount;

    public Board() {
        grid = new char[10][10];
        for (char[] row : grid) {
            Arrays.fill(row, '~'); // Water
        }
        ships = new Ship[5];
        shipCount = 0;
        firedCount = 0;
        sunkCount = 0;
    }

    public void display() {
        System.out.println("  A B C D E F G H I J");
        for (int i = 0; i < 10; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean placeShip(Ship ship, Coordinate start, boolean isVertical) {
        int x = start.getX();
        int y = start.getY();
        int size = ship.getSize();

        if (isVertical) {
            if (y + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (grid[y + i][x] != '~') return false;
            }
            for (int i = 0; i < size; i++) {
                grid[y + i][x] = ship.getSymbol();
            }
        } else {
            if (x + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (grid[y][x + i] != '~') return false;
            }
            for (int i = 0; i < size; i++) {
                grid[y][x + i] = ship.getSymbol();
            }
        }
        ships[shipCount++] = ship;
        return true;
    }

    public boolean fireAt(Coordinate coord) {
        int x = coord.getX();
        int y = coord.getY();
        firedCount++;
        if (grid[y][x] == '~') {
            grid[y][x] = 'O'; // Miss
            return false;
        } else if (grid[y][x] != 'O' && grid[y][x] != 'X') {
            char shipSymbol = grid[y][x];
            grid[y][x] = 'X'; // Hit
            for (Ship ship : ships) {
                if (ship != null && ship.getSymbol() == shipSymbol) {
                    ship.hit();
                    if (ship.isSunk()) {
                        sunkCount++;
                    }
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public void update(Coordinate coord, boolean hit) {
        int x = coord.getX();
        int y = coord.getY();
        grid[y][x] = hit ? 'X' : 'O';
    }

    public boolean allShipsSunk() {
        return sunkCount == shipCount;
    }

    public int getFiredCount() {
        return firedCount;
    }

    public int getSunkCount() {
        return sunkCount;
    }

    public int getRemainingShips() {
        return shipCount - sunkCount;
    }
}