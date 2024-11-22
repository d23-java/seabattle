import java.util.Arrays;

public class Board {
    private char[][] grid;

    public Board() {
        grid = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = '-'; // Bảng trống
            }
        }
    }

    public char[][] getGrid() {
        return grid;
    }

    public void printBoard(boolean fogOfWar) {
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < 10; j++) {
                if (fogOfWar) {
                    if (grid[i][j] == 'X') {
                        System.out.print("X "); // Đã bắn trúng
                    } else if (grid[i][j] == 'O') {
                        System.out.print("O "); // Đã bắn trượt
                    } else {
                        System.out.print("- "); // Chưa bắn
                    }
                } else {
                    System.out.print(grid[i][j] + " "); // Hiển thị đầy đủ khi là bảng của mình
                }
            }
            System.out.println();
        }
    }

    public boolean placeShip(int x1, int y1, int x2, int y2, Ship ship) {
        int size = ship.getSize();
        char symbol = ship.getSymbol();

        if (x1 == x2) { // Horizontal placement
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                if (grid[x1][j] != '-') return false;
            }
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                grid[x1][j] = symbol;
            }
        } else if (y1 == y2) { // Vertical placement
            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                if (grid[i][y1] != '-') return false;
            }
            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                grid[i][y1] = symbol;
            }
        } else {
            return false; // Invalid placement
        }
        return true;
    }

    public boolean isValidPlacement(int x1, int y1, int x2, int y2, Ship ship) {
        if (x1 < 0 || x1 >= 10 || y1 < 0 || y1 >= 10 || x2 < 0 || x2 >= 10 || y2 < 0 || y2 >= 10) {
            return false; // Kiểm tra tọa độ có nằm trong bảng không
        }

        int dx = (x2 - x1 == 0) ? 0 : (x2 - x1) / Math.abs(x2 - x1);
        int dy = (y2 - y1 == 0) ? 0 : (y2 - y1) / Math.abs(y2 - y1);

        if ((Math.abs(x2 - x1) + 1 != ship.getSize() && dx != 0) ||
                (Math.abs(y2 - y1) + 1 != ship.getSize() && dy != 0)) {
            return false; // Chiều dài không khớp với kích thước tàu
        }

        for (int i = 0; i < ship.getSize(); i++) {
            int nx = x1 + i * dx;
            int ny = y1 + i * dy;
            if (grid[nx][ny] != '.') {
                return false; // Kiểm tra xem vị trí có bị chồng lấn không
            }
        }
        return true;
    }

    public boolean attack(int x, int y) {
        if (x < 0 || x >= 10 || y < 0 || y >= 10 || grid[x][y] == 'X' || grid[x][y] == 'O') {
            // Tọa độ không hợp lệ hoặc đã bắn trước đó
            return false;
        }

        if (grid[x][y] != '-' && grid[x][y] != 'X' && grid[x][y] != 'O') {
            // Nếu ô chứa tàu, cập nhật thành "trúng"
            grid[x][y] = 'X';
            return true;
        } else {
            // Nếu ô trống, cập nhật thành "trượt"
            grid[x][y] = 'O';
            return false;
        }
    }

    public void removeShip(char shipSymbol) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == shipSymbol) {
                    grid[i][j] = '-';
                }
            }
        }
    }


}
