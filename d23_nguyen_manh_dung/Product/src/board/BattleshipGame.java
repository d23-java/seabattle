package classes;

import java.util.Scanner;

public class BattleshipGame {
    private static final int BOARD_SIZE = 10;
    private static final char EMPTY = '~'; // Ký tự đại diện cho ô trống
    private static final char SHIP = 'S';  // Ký tự đại diện cho tàu
    private static final char HIT = 'X';   // Ký tự đại diện cho ô trúng
    private static final char MISS = 'O';  // Ký tự đại diện cho ô trượt

    private static char[][] player1Board = new char[BOARD_SIZE][BOARD_SIZE];
    private static char[][] player2Board = new char[BOARD_SIZE][BOARD_SIZE];
    private static char[][] player1Fog = new char[BOARD_SIZE][BOARD_SIZE];
    private static char[][] player2Fog = new char[BOARD_SIZE][BOARD_SIZE];

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Chào mừng đến với trò chơi Bắn tàu chiến!");
        initializeBoards(player1Board);
        initializeBoards(player2Board);
        initializeBoards(player1Fog);
        initializeBoards(player2Fog);

        System.out.println("\n--- Vòng chuẩn bị ---");
        System.out.println("Người chơi 1 đặt tàu.");
        setupShips(player1Board);
        System.out.println("Người chơi 2 đặt tàu.");
        setupShips(player2Board);

        System.out.println("\n--- Bắt đầu trận chiến ---");
        boolean isPlayer1Turn = true;

        while (true) {
            if (isPlayer1Turn) {
                System.out.println("\nLượt của Người chơi 1");
                if (playTurn(player1Fog, player2Board)) {
                    System.out.println("Người chơi 1 thắng!");
                    break;
                }
            } else {
                System.out.println("\nLượt của Người chơi 2");
                if (playTurn(player2Fog, player1Board)) {
                    System.out.println("Người chơi 2 thắng!");
                    break;
                }
            }
            isPlayer1Turn = !isPlayer1Turn;
        }
    }

    private static void initializeBoards(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void setupShips(char[][] board) {
        int[] shipSizes = {2, 2, 3, 4, 5}; // Kích thước của các tàu
        for (int size : shipSizes) {
            while (true) {
                System.out.printf("Đặt tàu kích thước %d. Nhập tọa độ bắt đầu (A-J,1-10) và hướng (H/V): ", size);
                String input = scanner.nextLine().toUpperCase();
                if (placeShip(board, input, size)) {
                    break;
                } else {
                    System.out.println("Vị trí không hợp lệ, vui lòng thử lại.");
                }
            }
            printBoard(board, false);
        }
    }

    private static boolean placeShip(char[][] board, String input, int size) {
        try {
            char startRow = input.charAt(0);
            int startCol = Integer.parseInt(input.substring(1, input.length() - 1));
            char direction = input.charAt(input.length() - 1);

            int row = startRow - 'A';
            int col = startCol - 1;

            if (direction == 'H') {
                if (col + size > BOARD_SIZE) return false;
                for (int i = 0; i < size; i++) {
                    if (board[row][col + i] != EMPTY) return false;
                }
                for (int i = 0; i < size; i++) {
                    board[row][col + i] = SHIP;
                }
            } else if (direction == 'V') {
                if (row + size > BOARD_SIZE) return false;
                for (int i = 0; i < size; i++) {
                    if (board[row + i][col] != EMPTY) return false;
                }
                for (int i = 0; i < size; i++) {
                    board[row + i][col] = SHIP;
                }
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean playTurn(char[][] fog, char[][] opponentBoard) {
        printBoard(fog, true);
        System.out.print("Nhập tọa độ để khai hỏa (A-J,1-10): ");
        String input = scanner.nextLine().toUpperCase();
        try {
            char rowChar = input.charAt(0);
            int col = Integer.parseInt(input.substring(1)) - 1;

            int row = rowChar - 'A';
            if (fog[row][col] == HIT || fog[row][col] == MISS) {
                System.out.println("Bạn đã bắn vào vị trí này rồi, hãy thử lại.");
                return false;
            }

            if (opponentBoard[row][col] == SHIP) {
                System.out.println("Trúng tàu!");
                fog[row][col] = HIT;
                opponentBoard[row][col] = HIT;
            } else {
                System.out.println("Trượt!");
                fog[row][col] = MISS;
            }
        } catch (Exception e) {
            System.out.println("Tọa độ không hợp lệ, vui lòng thử lại.");
            return false;
        }

        return checkWin(opponentBoard);
    }

    private static boolean checkWin(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == SHIP) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printBoard(char[][] board, boolean fogOfWar) {
        System.out.print("  ");
        for (int i = 1; i <= BOARD_SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (fogOfWar && board[i][j] == SHIP) {
                    System.out.print(EMPTY + " ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
