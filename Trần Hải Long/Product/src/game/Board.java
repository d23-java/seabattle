package game;

import java.io.Serial;
import java.io.Serializable;

import static game.Menu.reset;

public class Board implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public int size;
    public Cell[][] cells;
    String greenBackground = "\u001B[42m";
    String yellowBackground = "\u001B[43m";
    String blueBackground = "\u001B[44m";
    String purpleBackground = "\u001B[45m";

    public void printShip(String shipIcon, Cell[][] cells, int i, int j) {
        switch (cells[i][j].getShip().getType()) {
            case PATROL_BOAT:
                System.out.print(blueBackground + shipIcon);
                break;
            case DESTROYER:
                System.out.print(purpleBackground + shipIcon);
                break;
            case SUBMARINE:
                System.out.print(yellowBackground + shipIcon);
                break;
            case BATTLESHIP:
                System.out.print(greenBackground + shipIcon);
                break;
        }
        if (j+1>=size || cells[i][j+1].getShip() != cells[i][j].getShip()) {
            System.out.print(reset);
        }
        System.out.print(" "+reset);
    }

    public Board(int size) {
        this.size = size;
        cells = new Cell[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            char x = (char) ('A' + i);
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(x, j + 1);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean isCoordinateValid(char x, int y) {
        if (x < 'A' || x >= ('A' + size)) return false;
        return y >= 1 && y <= size;
    }

    public Cell getCell(char x, int y) {
        if (!isCoordinateValid(x, y)) return null;
        int row = x - 'A';
        int col = y - 1;
        return cells[row][col];
    }

    public void displayBoardsSideBySide(Board fogBoard) {
        String headerFormat = String.format("%%-%ds     %%-%ds%n", size * 3 + 3, size * 3 + 3);
        System.out.printf(headerFormat, "Bảng của bạn", "Bảng sương mù");

        System.out.print("  ");
        for (int i = 1; i <= size; i++) System.out.print(" " + i + " ");
        System.out.print("  ~~~   ");
        for (int i = 1; i <= fogBoard.getSize(); i++) System.out.print(" " + i + " ");
        System.out.println();

        System.out.print(" +");
        for (int k = 1; k <= size; k++) {
            System.out.print("---");
        }
        System.out.print("+");
        System.out.print(" ~~~ ");
        System.out.print(" +");
        for (int k = 1; k <= fogBoard.getSize(); k++) {
            System.out.print("---");
        }
        System.out.println("+");

        for (int i = 0; i < size; i++) {
            char x = (char) ('A' + i);

            System.out.print(x + "| ");
            for (int j = 0; j < size; j++) {
                Cell cell = this.cells[i][j];
                switch (cell.getStatus()) {
                    case HIT:
                        if(cell.getShip().isSunk())
                            printShip("\uD83D\uDC7B", cells, i, j);
                        else
                            printShip("\uD83D\uDCA5", cells, i, j);
                        break;
                    case MISS:
                        System.out.print("❌ ");
                        break;
                    case EMPTY:
                        System.out.print("🌊 ");
                        break;
                    case SHIP:
                        printShip("🚢", cells, i, j);
                }
            }

            System.out.print(" ~~~ ");

            System.out.print(x + "| ");
            for (int j = 0; j < fogBoard.getSize(); j++) {
                Cell cell = fogBoard.getCell((char) ('A' + i), j + 1);
                Cell[][] cells = fogBoard.getCells();
                switch (cell.getStatus()) {
                    case HIT:
                        if(cell.getShip().isSunk())
                            printShip("\uD83D\uDC7B", cells, i, j);
                        else System.out.print("💥 ");
                        break;
                    case MISS:
                        System.out.print("❌ ");
                        break;
                    default:
                        System.out.print("🌊 ");
                }
            }
            System.out.println();
        }
    }
}