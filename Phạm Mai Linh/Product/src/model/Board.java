package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final char[][] grid;
    private final int size;

    public Board(int size) {
        this.size = size;
        grid = new char[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(grid[i], '-');
        }
    }

    public void printBoard(boolean fogOfWar) {
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < size; j++) {
                if (fogOfWar) {
                    if (grid[i][j] == 'X') {
                        System.out.print("X ");
                    } else if (grid[i][j] == 'O') {
                        System.out.print("O ");
                    } else {
                        System.out.print("- ");
                    }
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean placeShip(int x1, int y1, int x2, int y2, Ship ship) {
        if (!isValidPlacement(x1, y1, x2, y2, ship)) {
            return false;
        }

        char symbol = ship.getSymbol();
        if (x1 == x2) {
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                grid[x1][j] = symbol;
            }
        } else if (y1 == y2) {
            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                grid[i][y1] = symbol;
            }
        }
        return true;
    }

    public boolean isValidPlacement(int x1, int y1, int x2, int y2, Ship ship) {
        if (x1 < 0 || x1 >= size || y1 < 0 || y1 >= size || x2 < 0 || x2 >= size || y2 < 0 || y2 >= size) {
            return false;
        }

        int dx = (x2 - x1 == 0) ? 0 : (x2 - x1) / Math.abs(x2 - x1);
        int dy = (y2 - y1 == 0) ? 0 : (y2 - y1) / Math.abs(y2 - y1);


        if ((Math.abs(x2 - x1) + 1 != ship.getSize() && dx != 0) ||
                (Math.abs(y2 - y1) + 1 != ship.getSize() && dy != 0)) {
            return false;
        }


        for (int i = 0; i < ship.getSize(); i++) {
            int nx = x1 + i * dx;
            int ny = y1 + i * dy;
            if (grid[nx][ny] != '-') {
                return false;
            }
        }
        return true;
    }

    public boolean attack(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size || grid[x][y] == 'X' || grid[x][y] == 'O') {
            return false;
        }

        if (grid[x][y] != '-' && grid[x][y] != 'X' && grid[x][y] != 'O') {
            grid[x][y] = 'X';
            return true;
        } else {
            grid[x][y] = 'O';
            return false;
        }
    }

    public boolean allShipsSunk() {
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell != '-' && cell != 'X' && cell != 'O') return false;
            }
        }
        return true;
    }

    public int[][] getValidEndPositions(int x1, int y1, Ship ship) {
        List<int[]> validPositions = new ArrayList<>();
        int size = ship.getSize();

        if (y1 + size - 1 < this.size) {
            boolean valid = true;
            for (int j = y1; j < y1 + size; j++) {
                if (grid[x1][j] != '-') {
                    valid = false;
                    break;
                }
            }
            if (valid) validPositions.add(new int[]{x1, y1 + size - 1});
        }

        if (y1 - size + 1 >= 0) {
            boolean valid = true;
            for (int j = y1; j > y1 - size; j--) {
                if (grid[x1][j] != '-') {
                    valid = false;
                    break;
                }
            }
            if (valid) validPositions.add(new int[]{x1, y1 - size + 1});
        }

        if (x1 + size - 1 < this.size) {
            boolean valid = true;
            for (int i = x1; i < x1 + size; i++) {
                if (grid[i][y1] != '-') {
                    valid = false;
                    break;
                }
            }
            if (valid) validPositions.add(new int[]{x1 + size - 1, y1});
        }

        if (x1 - size + 1 >= 0) {
            boolean valid = true;
            for (int i = x1; i > x1 - size; i--) {
                if (grid[i][y1] != '-') {
                    valid = false;
                    break;
                }
            }
            if (valid) validPositions.add(new int[]{x1 - size + 1, y1});
        }

        return validPositions.toArray(new int[0][]);
    }

    public void removeShip(char shipSymbol) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == shipSymbol) {
                    grid[i][j] = '-';
                }
            }
        }
    }
}
