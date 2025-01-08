public class Board {
    private char[][] board;      // Bảng chứa thông tin tàu
    private boolean[][] hitBoard; // Bảng chứa thông tin về các ô đã bắn vào

    public Board() {
        board = new char[10][10];  // Kích thước bảng 10x10
        hitBoard = new boolean[10][10]; // Bảng chứa thông tin về các ô đã bắn vào
        initializeBoard();
    }

    // Khởi tạo bảng với các ô trống
    private void initializeBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '.';  // Dấu chấm '.' cho ô chưa được đánh dấu
                hitBoard[i][j] = false; // Chưa bắn vào ô nào
            }
        }
    }

    // Hiển thị bảng
    public void displayBoard(boolean showShips) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char)(i + 65) + " ");  // In chữ cái hàng (A, B, C, ...)
            for (int j = 0; j < 10; j++) {
                if (hitBoard[i][j]) {  // Nếu đã bắn vào ô này
                    if (board[i][j] == 'S') {
                        System.out.print("X ");  // Bắn trúng tàu
                    } else {
                        System.out.print("O ");  // Bắn trượt
                    }
                } else {
                    if (showShips) {
                        System.out.print(board[i][j] + " ");  // Hiển thị tàu nếu `showShips` là true
                    } else {
                        System.out.print(". ");  // Hiển thị sương mù (chưa bắn)
                    }
                }
            }
            System.out.println();
        }
    }


    // Đặt tàu vào vị trí (theo chiều ngang hoặc dọc)
    public boolean placeShip(int x1, int y1, int x2, int y2, int size) {
        // Kiểm tra nếu tàu có thể đặt vào vị trí này
        if (x1 == x2) { // Đặt tàu theo chiều ngang
            if (y1 > y2) {
                int temp = y1;
                y1 = y2;
                y2 = temp;
            }
            if (y2 - y1 + 1 != size) {
                return false;
            }
            for (int i = y1; i <= y2; i++) {
                if (board[x1][i] != '.') {
                    return false; // Có tàu khác ở vị trí này
                }
            }
            for (int i = y1; i <= y2; i++) {
                board[x1][i] = 'S'; // Đánh dấu vị trí tàu
            }
        } else if (y1 == y2) { // Đặt tàu theo chiều dọc
            if (x1 > x2) {
                int temp = x1;
                x1 = x2;
                x2 = temp;
            }
            if (x2 - x1 + 1 != size) {
                return false;
            }
            for (int i = x1; i <= x2; i++) {
                if (board[i][y1] != '.') {
                    return false; // Có tàu khác ở vị trí này
                }
            }
            for (int i = x1; i <= x2; i++) {
                board[i][y1] = 'S'; // Đánh dấu vị trí tàu
            }
        } else {
            return false; // Nếu không phải theo chiều ngang hay dọc thì không hợp lệ
        }
        return true;
    }

    // Xử lý khi bắn vào một ô
    public String fireAt(int x, int y) {
        if (board[x][y] == 'S') {
            board[x][y] = 'X'; // Đánh dấu tàu đã bị trúng
            hitBoard[x][y] = true;  // Đánh dấu ô đã bị bắn
            return "Hit";
        } else {
            hitBoard[x][y] = true; // Đánh dấu ô đã bắn nhưng không trúng
            return "Miss";
        }
    }

}
