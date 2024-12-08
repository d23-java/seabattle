package Object;

public class Board {
    private static final int SIZE = 10;
    private Cell[][] grid;

    public Board() {
        grid = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public void printBoard(boolean showShips) {
        System.out.print("    ");
        for (int col = 0; col < SIZE; col++) {
            System.out.print((col + 1) + "   ");
        }
        System.out.println();

        System.out.print("  ╔");
        for (int col = 0; col < SIZE; col++) {
            System.out.print("═══");
            if (col < SIZE - 1) {
                System.out.print("╦");
            }
        }
        System.out.println("╗");

        for (int row = 0; row < SIZE; row++) {
            System.out.print((char) (row + 'A') + " ║");
            for (int col = 0; col < SIZE; col++) {
                String display = grid[row][col].getState();
                if (!showShips && display == "S") {
                    display = "~";
                } else if (!showShips && display == "~") {
                    display = "?";
                }
                System.out.print(" " + display + " ║");
            }
            System.out.println();

            if (row < SIZE - 1) {
                System.out.print("  ╠");
                for (int col = 0; col < SIZE; col++) {
                    System.out.print("═══");
                    if (col < SIZE - 1) {
                        System.out.print("╬");
                    }
                }
                System.out.println("╣");
            } else {
                System.out.print("  ╚");
                for (int col = 0; col < SIZE; col++) {
                    System.out.print("═══");
                    if (col < SIZE - 1) {
                        System.out.print("╩");
                    }
                }
                System.out.println("╝");
            }
        }
    }
}