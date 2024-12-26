package gamemanager;

import enums.Orientation;
import game.Board;
import game.Cell;
import game.Main;
import game.Ship;

import java.util.List;
import java.util.Scanner;

import static gamemanager.GameFunction.alert;

public class ShipPlacement {
    static Scanner scanner = Main.scanner;

    public static void placeShipsRandomly(List<Ship> ships, BoardController boardController) {
        for (Ship ship : ships) {
            boolean placed = false;
            while (!placed) {
                char x = (char) ('A' + GameFunction.random.nextInt(boardController.getBoard().getSize()));
                int y = GameFunction.random.nextInt(boardController.getBoard().getSize()) + 1;
                Orientation orientation = GameFunction.random.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Cell startCell = boardController.getBoard().getCell(x, y);

                placed = boardController.placeShip(ship, startCell, orientation);
            }
        }
    }

    public static void placeShipsManually(List<Ship> ships, BoardController boardController) {
        for (Ship ship : ships) {
            boolean placed = false;
            while (!placed) {
                try {
                    System.out.printf("Bạn đang đặt vị trí cho tàu %s (Kích thước %dx1):\n", ship.getType(), ship.getSize());
                    Cell startCell = getStartCell(boardController);
                    Orientation orientation = getOrientation();

                    placed = boardController.placeShip(ship, startCell, orientation);
                    if (placed) {
                        displayBoard(boardController);
                    } else {
                        alert("Tàu bị đặt ở vị trí không hợp lệ, vui lòng đặt lại.");
                    }
                } catch (Exception e) {
                    alert("Toạ độ hoặc hướng không hợp lệ, vui lòng nhập lại.");
                }
            }
        }
    }

    private static Cell getStartCell(BoardController boardController) throws IllegalArgumentException {
        while (true) {
            System.out.print("Vui lòng nhập toạ độ góc trên bên trái của tàu (Ví dụ: A1)): ");
            String coordinate = scanner.nextLine().trim();
            if (coordinate.length() < 2) {
                throw new IllegalArgumentException("Toạ độ không hợp lệ.");
            }

            char xAxis = coordinate.charAt(0);
            int yAxis;

            try {
                yAxis = Integer.parseInt(coordinate.substring(1));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Toạ độ không hợp lệ.");
            }

            Cell startCell = boardController.getBoard().getCell(xAxis, yAxis);
            if (startCell != null) {
                return startCell;
            } else {
                alert("Toạ độ không hợp lệ, vui lòng nhập lại.");
            }
        }
    }

    private static Orientation getOrientation() throws IllegalArgumentException {
        while (true) {
            System.out.print("Vui lòng chọn hướng tàu (H: Đặt ngang, V: Đặt dọc): ");
            String orientationInput = scanner.nextLine().trim();
            if (orientationInput.equalsIgnoreCase("h")) {
                return Orientation.HORIZONTAL;
            } else if (orientationInput.equalsIgnoreCase("v")) {
                return Orientation.VERTICAL;
            } else {
                alert("Hướng bạn chọn không hợp lệ, vui lòng chọn lại.");
            }
        }
    }

    private static void displayBoard(BoardController boardController) {
        Board board = boardController.getBoard();
        board.displayBoardsSideBySide(board);
    }
}