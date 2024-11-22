import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SeaBattleGame {
    private Player player1;
    private Player player2;

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tên người chơi 1: ");
        player1 = new Player(scanner.nextLine());
        System.out.print("Nhập tên người chơi 2: ");
        player2 = new Player(scanner.nextLine());

        System.out.println("\n--- Vòng chuẩn bị ---");
        setupShips(player1, scanner);
        clearScreen();
        setupShips(player2, scanner);
        clearScreen();

        System.out.println("\n--- Bắt đầu trò chơi ---");
        playGame(scanner);
    }

    private void setupShips(Player player, Scanner scanner) {
        System.out.println("\n" + player.getName() + ", đặt các tàu của bạn:");
        setupShipsWithDynamicEndPosition(player, scanner);
    }

    private void playGame(Scanner scanner) {
        Player currentPlayer = player1;
        Player opponent = player2;

        while (true) {
            clearScreen();

            // Hiển thị bảng của người chơi hiện tại và đối thủ
            System.out.println("\n--- Lượt của " + currentPlayer.getName() + " ---");
            System.out.println("--- Bảng của bạn ---");
            currentPlayer.getBoard().printBoard(false); // Hiển thị đầy đủ bảng của người chơi

            System.out.println("\n--- Bảng của đối thủ (ẩn tàu) ---");
            opponent.getBoard().printBoard(true); // Hiển thị sương mù trên bảng đối thủ

            // Người chơi nhập tọa độ để bắn
            System.out.print("Nhập tọa độ để bắn (ví dụ: A1): ");
            String input = scanner.nextLine().toUpperCase();

            if (!isValidInput(input)) {
                System.out.println("Toạ độ không hợp lệ! Thử lại.");
                pauseAndContinue(scanner);
                continue;
            }

            int x = input.charAt(0) - 'A';
            int y = Integer.parseInt(input.substring(1)) - 1;

            // Tấn công và thông báo kết quả
            if (opponent.getBoard().attack(x, y)) {
                System.out.println("Trúng tàu!");
            } else {
                System.out.println("Trượt!");
            }

            pauseAndContinue(scanner);

            // Kiểm tra xem tất cả tàu của đối thủ đã bị bắn chìm chưa
            if (allShipsSunk(opponent.getBoard())) {
                System.out.println("\n--- " + currentPlayer.getName() + " đã chiến thắng! ---");
                break;
            }

            // Chuyển lượt
            Player temp = currentPlayer;
            currentPlayer = opponent;
            opponent = temp;
        }
    }

    private boolean isValidInput(String input) {
        if (input.length() < 2 || input.length() > 3) return false;
        char row = input.charAt(0);
        String col = input.substring(1);
        return row >= 'A' && row <= 'J' && col.matches("\\d+") && Integer.parseInt(col) >= 1 && Integer.parseInt(col) <= 10;
    }

    private boolean allShipsSunk(Board board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.getGrid()[i][j] != '-' && board.getGrid()[i][j] != 'X' && board.getGrid()[i][j] != 'O') {
                    // Nếu ô chứa tàu chưa bị bắn trúng, trò chơi vẫn tiếp tục
                    return false;
                }
            }
        }
        return true;
    }

    private void setupShipsWithDynamicEndPosition(Player player, Scanner scanner) {
        Board board = player.getBoard();
        Ship[] ships = {
                new Ship(5, 'A'), // Battle Ship
                new Ship(4, 'B'), // Destroyer
                new Ship(3, 'C'), // Submarine
                new Ship(2, 'D'), // Patrol Boat
                new Ship(2, 'E')  // Patrol Boat
        };

        for (Ship ship : ships) {
            while (true) {
                board.printBoard(false);
                System.out.println(player.getName() + ", đặt tàu kích thước " + ship.getSize() + " (" + ship.getSymbol() + "):");

                System.out.print("Nhập tọa độ bắt đầu (ví dụ: A1): ");
                String startInput = scanner.nextLine().toUpperCase();

                if (!isValidInput(startInput)) {
                    System.out.println("Tọa độ không hợp lệ! Thử lại.");
                    continue;
                }

                int x1 = startInput.charAt(0) - 'A';
                int y1 = Integer.parseInt(startInput.substring(1)) - 1;

                int[][] validEndPositions = getValidEndPositions(x1, y1, ship, board);
                if (validEndPositions.length == 0) {
                    System.out.println("Không có vị trí kết thúc hợp lệ! Thử lại.");
                    continue;
                }

                for (int i = 0; i < validEndPositions.length; i++) {
                    int x2 = validEndPositions[i][0];
                    int y2 = validEndPositions[i][1];
                    System.out.println((i + 1) + ". " + (char) ('A' + x2) + (y2 + 1));
                }

                System.out.print("Chọn một vị trí (1-" + validEndPositions.length + "): ");
                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > validEndPositions.length) {
                        System.out.println("Lựa chọn không hợp lệ! Thử lại.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lựa chọn không hợp lệ! Thử lại.");
                    continue;
                }

                int x2 = validEndPositions[choice - 1][0];
                int y2 = validEndPositions[choice - 1][1];

                if (board.placeShip(x1, y1, x2, y2, ship)) {
                    System.out.println("Đã đặt tàu thành công!");
                    break;
                } else {
                    System.out.println("Đặt tàu không hợp lệ! Thử lại.");
                }
            }
        }

        // Thay đổi tàu nếu cần
        while (true) {
            board.printBoard(false);
            System.out.print("Bạn có muốn thay đổi vị trí tàu nào không? (y/n): ");
            String confirm = scanner.nextLine().toLowerCase();

            if (confirm.equals("y")) {
                System.out.println("Danh sách tàu của bạn:");
                for (int i = 0; i < ships.length; i++) {
                    System.out.println((i + 1) + ". Tàu kích thước " + ships[i].getSize() + " (" + ships[i].getSymbol() + ")");
                }

                System.out.print("Chọn số thứ tự tàu muốn đặt lại (1-" + ships.length + "): ");
                int shipIndex;
                try {
                    shipIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    if (shipIndex < 0 || shipIndex >= ships.length) {
                        System.out.println("Lựa chọn không hợp lệ! Thử lại.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lựa chọn không hợp lệ! Thử lại.");
                    continue;
                }

                board.removeShip(ships[shipIndex].getSymbol());
                System.out.println("Đặt lại tàu kích thước " + ships[shipIndex].getSize() + " (" + ships[shipIndex].getSymbol() + ").");

                boolean done = false;
                while (!done) {
                    done = true;
                    // Gọi lại logic đặt tàu cho tàu đã xóa
                    if (!setupShipPlacement(ships[shipIndex], board, scanner)) {
                        done = false;
                    }
                }
            } else if (confirm.equals("n")) {
                System.out.println("Hoàn tất quá trình đặt tàu!");
                break;
            } else {
                System.out.println("Lựa chọn không hợp lệ! Thử lại.");
            }
        }
    }

    private boolean setupShipPlacement(Ship ship, Board board, Scanner scanner) {
        while (true) {
            board.printBoard(false);
            System.out.println("Đặt tàu kích thước " + ship.getSize() + " (" + ship.getSymbol() + "):");

            // Nhập tọa độ bắt đầu
            System.out.print("Nhập tọa độ bắt đầu (ví dụ: A1): ");
            String startInput = scanner.nextLine().toUpperCase();

            if (!isValidInput(startInput)) {
                System.out.println("Tọa độ không hợp lệ! Thử lại.");
                continue;
            }

            int x1 = startInput.charAt(0) - 'A';
            int y1 = Integer.parseInt(startInput.substring(1)) - 1;

            // Tìm các vị trí kết thúc hợp lệ
            int[][] validEndPositions = getValidEndPositions(x1, y1, ship, board);
            if (validEndPositions.length == 0) {
                System.out.println("Không có vị trí kết thúc hợp lệ! Thử lại.");
                continue;
            }

            // Hiển thị các lựa chọn kết thúc
            for (int i = 0; i < validEndPositions.length; i++) {
                int x2 = validEndPositions[i][0];
                int y2 = validEndPositions[i][1];
                System.out.println((i + 1) + ". " + (char) ('A' + x2) + (y2 + 1));
            }

            // Nhập lựa chọn vị trí kết thúc
            System.out.print("Chọn một vị trí kết thúc (1-" + validEndPositions.length + "): ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > validEndPositions.length) {
                    System.out.println("Lựa chọn không hợp lệ! Thử lại.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ! Thử lại.");
                continue;
            }

            int x2 = validEndPositions[choice - 1][0];
            int y2 = validEndPositions[choice - 1][1];

            // Đặt tàu trên bảng
            if (board.placeShip(x1, y1, x2, y2, ship)) {
                System.out.println("Đã đặt tàu thành công!");
                return true;
            } else {
                System.out.println("Đặt tàu không hợp lệ! Thử lại.");
            }
        }
    }

    private int[][] getValidEndPositions(int x1, int y1, Ship ship, Board board) {
        int size = ship.getSize();
        List<int[]> validPositions = new ArrayList<>();

        // Kiểm tra hướng ngang sang phải
        if (y1 + size - 1 < 10) {
            boolean valid = true;
            for (int j = y1; j < y1 + size; j++) {
                if (board.getGrid()[x1][j] != '-') {
                    valid = false;
                    break;
                }
            }
            if (valid) validPositions.add(new int[]{x1, y1 + size - 1});
        }

        // Kiểm tra hướng ngang sang trái
        if (y1 - size + 1 >= 0) {
            boolean valid = true;
            for (int j = y1; j > y1 - size; j--) {
                if (board.getGrid()[x1][j] != '-') {
                    valid = false;
                    break;
                }
            }
            if (valid) validPositions.add(new int[]{x1, y1 - size + 1});
        }

        // Kiểm tra hướng dọc xuống
        if (x1 + size - 1 < 10) {
            boolean valid = true;
            for (int i = x1; i < x1 + size; i++) {
                if (board.getGrid()[i][y1] != '-') {
                    valid = false;
                    break;
                }
            }
            if (valid) validPositions.add(new int[]{x1 + size - 1, y1});
        }

        // Kiểm tra hướng dọc lên
        if (x1 - size + 1 >= 0) {
            boolean valid = true;
            for (int i = x1; i > x1 - size; i--) {
                if (board.getGrid()[i][y1] != '-') {
                    valid = false;
                    break;
                }
            }
            if (valid) validPositions.add(new int[]{x1 - size + 1, y1});
        }

        return validPositions.toArray(new int[0][]);
    }

    // Hàm xóa màn hình
    private void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private void pauseAndContinue(Scanner scanner) {
        System.out.print("Nhấn Enter để tiếp tục...");
        scanner.nextLine();
    }

}
