package Body;
public class Ship {
    public int x1, x2, y1, y2;
    private int shipSize;
    private char shipType;
    public static String[] shipTypes = {
            "PatrolBoat",
            "PatrolBoat",
            "DestroyerBoat",
            "Submarine",
            "BattleShip"
    };
    public Ship() {};

    public Ship(int x1, int y1, int x2, int y2, char shipType, int shipSize) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.shipType = shipType;
        this.shipSize = shipSize;
    }

    public int getShipSize() {
        return shipSize;
    }

    public char setShipType(String name) {
        if (shipTypes[0].equals(name)) {
            return 'P';
        }
        if (shipTypes[2].equals(name)) {
            return 'D';
        }
        if (shipTypes[3].equals(name)) {
            return 'S';
        }
        if (shipTypes[4].equals(name)) {
            return 'B';
        }
        return '.';
    }

    public char getShipType() {
        return shipType;
    }

    public boolean isSunk(char[][] grid) {
        int countSunk = 0;
        if (x1 == x2) {
            for (int i = y1; i <= y2; i++) {
                if (grid[x1][i] == 'X') {
                    ++countSunk;
                }
            }
        }
        else {
            for (int i = x1; i <= x2; i++) {
                if (grid[i][y1] == 'X') {
                    ++countSunk;
                }
            }
        }
        return countSunk == shipSize;
    }
}
