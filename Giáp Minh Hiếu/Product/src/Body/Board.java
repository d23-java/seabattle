package Body;
import java.util.ArrayList;
import Manager.GameManager;

public class Board {
    public static final int boardSize = 11;
    private final char[][] grid = new char[boardSize][boardSize];
    public Ship ship = new Ship();
    public ArrayList<Ship> ships = new ArrayList<>();
    public static final char HIT = 'X';
    public static final char MISS = '0';
    public static final char EMPTY = '.';

    public Board() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                grid[i][j] = EMPTY;
            }
        }
    }
    public boolean checkHasShip (int x1, int x2, int y1, int y2) {
        if (x1 < 0 || x2 > boardSize || y1 < 0 || y2 > boardSize) {
            return false;
        }
        if (x1 == x2) {
            for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                if (grid[x1][i] != EMPTY) {
                    return false;
                }
            }
        }
        else {
            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                if (grid[i][y1] != EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    public void setCoordinate(int x1, int x2, int y1, int y2, char type) {
        if (x1 == x2) {
            for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                grid[x1][i] = type;
            }
        }

        else {
            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                grid[i][y1] = type;
            }
        }
    }

    public char[][] getGrid() {
        return grid;
    }

    public void display() {
        System.out.print("   ");
        for (int i = 0; i < boardSize; i++) {
            System.out.printf("%3d", i + 1);
        }
        System.out.println();
        for (int i = 0; i < boardSize; i++) {
            System.out.print((char)('A' + i) + "  ");
            for (int j = 0; j < boardSize; j++) {
                System.out.print("  " + grid[i][j]);
            }
            System.out.println();
        }
    }

    public void placeShip() {
        display();
        for (int i = 0; i < Ship.shipTypes.length; i++) {
            System.out.println("Enter the start and end of " + Ship.shipTypes[i]);
            String xChar, yChar;
            xChar = GameManager.scanner.nextLine();
            yChar = GameManager.scanner.nextLine();
            int x1, y1, x2, y2;
            x1 = (int)(xChar.charAt(0)) - 'A';
            y1 = Integer.parseInt(xChar.substring(1)) - 1;
            x2 = (int)(yChar.charAt(0)) - 'A';
            y2 = Integer.parseInt(yChar.substring(1)) - 1;
            if (!checkHasShip(x1, x2, y1, y2)) {
                System.out.println("Has ship or Out of Board!! Please try again.");
                --i;
                continue;
            }
            int size;
            if (i == 0 || i == 1) size = 2;
            else size = i + 1;
            char type = ship.setShipType(Ship.shipTypes[i]);
            ships.add(new Ship(x1, y1, x2, y2, type, size));
            setCoordinate(x1, x2, y1, y2, type);
            display();
        }
    }

    public boolean checkShipSunk(char[][] sunk) {
        for (Ship value : ships) {
            if (value.isSunk(sunk)) {
                System.out.println("Defeated " + value.getShipType());
                ships.remove(value);
                return true;
            }
        }
        return false;
    }

    public void setGrid(int x, int y) {
        grid[x][y] = HIT;
    }
}
