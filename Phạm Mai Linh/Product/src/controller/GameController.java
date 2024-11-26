package controller;

import model.Board;
import model.Player;
import model.Ship;
import view.GameView;

import java.util.Scanner;

public class GameController {
    private Player player1;
    private Player player2;
    private int boardSize;
    private final GameView view = new GameView();

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        // Nhập kích thước bảng
        boardSize = view.getBoardSize(scanner);

        // Nhập thông tin người chơi
        player1 = new Player(view.getPlayerName(scanner, 1), boardSize);
        player2 = new Player(view.getPlayerName(scanner, 2), boardSize);

        // Giai đoạn chuẩn bị
        view.showMessage("\n--- Vòng chuẩn bị ---");
        setupShips(player1, scanner);
        view.clearScreen();
        setupShips(player2, scanner);
        view.clearScreen();

        // Bắt đầu trò chơi
        view.showMessage("\n--- Bắt đầu trò chơi ---");
        playGame(scanner);
    }

    private void setupShips(Player player, Scanner scanner) {
        view.showMessage("\n" + player.getName() + ", đặt các tàu của bạn:");
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

                int[][] validEndPositions = board.getValidEndPositions(x1, y1, ship);
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
            view.showMessage("Đặt tàu kích thước " + ship.getSize() + " (" + ship.getSymbol() + "):");
            String startInput = view.getCoordinateInput(scanner).toUpperCase();

            if (!isValidInput(startInput)) {
                view.showMessage("Tọa độ không hợp lệ! Vui lòng thử lại.");
                continue;
            }

            int x1 = startInput.charAt(0) - 'A';
            int y1 = Integer.parseInt(startInput.substring(1)) - 1;

            int[][] validEndPositions = board.getValidEndPositions(x1, y1, ship);

            if (validEndPositions.length == 0) {
                view.showMessage("Không có vị trí kết thúc hợp lệ! Thử lại.");
                continue;
            }

            view.showValidEndPositions(validEndPositions);

            int choice = view.getEndPositionChoice(scanner, validEndPositions.length);
            int x2 = validEndPositions[choice - 1][0];
            int y2 = validEndPositions[choice - 1][1];

            if (board.placeShip(x1, y1, x2, y2, ship)) {
                view.showMessage("Đã đặt tàu thành công!");
                return true;
            } else {
                view.showMessage("Đặt tàu không hợp lệ! Thử lại.");
            }
        }
    }

    private boolean isValidInput(String input) {
        if (input == null || input.length() < 2) {
            return false; // Nhập vào rỗng hoặc quá ngắn
        }

        char row = input.charAt(0); // Lấy ký tự hàng (A, B, C,...)
        String colPart = input.substring(1); // Lấy phần số cột (1, 2, 3,...)

        if (row < 'A' || row >= 'A' + boardSize) {
            return false; // Ký tự hàng không nằm trong phạm vi bảng
        }

        try {
            int col = Integer.parseInt(colPart);
            if (col < 1 || col > boardSize) {
                return false; // Số cột không nằm trong phạm vi bảng
            }
        } catch (NumberFormatException e) {
            return false; // Không thể chuyển đổi phần số
        }

        return true; // Đầu vào hợp lệ
    }

    private void playGame(Scanner scanner) {
        Player currentPlayer = player1;
        Player opponent = player2;

        while (true) {
            boolean hit = true;

            while (hit) {
                view.clearScreen();
                view.showPlayerTurn(currentPlayer.getName());
                currentPlayer.getBoard().printBoard(false);
                opponent.getBoard().printBoard(true);

                String input = view.getCoordinateInput(scanner);

                int x = input.charAt(0) - 'A';
                int y = Integer.parseInt(input.substring(1)) - 1;

                hit = opponent.getBoard().attack(x, y);
                view.showAttackResult(hit);

                view.pauseAndContinue(scanner);

                if (opponent.getBoard().allShipsSunk()) {
                    view.showWinner(currentPlayer.getName());
                    return;
                }
            }

            Player temp = currentPlayer;
            currentPlayer = opponent;
            opponent = temp;
        }
    }
}
