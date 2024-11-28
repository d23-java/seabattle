package gameManager;

import java.io.IOException;

import enums.Color;
import gameObjects.*;
import Scanner.*;

public class ScreenManager {
    public static void clearScreen() {
        try {
            String operatingSystem = System.getProperty("os.name"); // Lấy hệ điều hành
            if (operatingSystem.contains("Windows")) {
                // Lệnh xóa màn hình cho Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Lệnh xóa màn hình cho Unix/Linux/macOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForEnter() {
        System.out.println(Color.ANSI_CYAN + "Press Enter to continue..." + Color.ANSI_RESET);
        Input.scanner.nextLine();
    }

    public static void drawBorder(int size) {
        for (int row = 1; row <= size; row++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    public static void drawBothBorder(int size) {
        for (int row = 1; row <= size; row++) {
            System.out.print("+---");
        }
        System.out.print("+");
    }

    public static void showCurrentStatus(Players currentPlayer) {
        System.out.println("The number of cells you have shot: " + Color.ANSI_YELLOW + currentPlayer.getShotCell() + Color.ANSI_RESET);
        System.out.println("The number of ship you have sunk: " + Color.ANSI_YELLOW + currentPlayer.getShotShip() + Color.ANSI_RESET);
        System.out.println("The number of your ship: " + Color.ANSI_YELLOW + currentPlayer.getShips().size() + Color.ANSI_RESET);
    }

    public static void drawMyBoard(Players currentPlayer, String title) {
        Boards myBoard = currentPlayer.getBoard();
        System.out.println(title);
        int size = myBoard.getSize();
        System.out.print("    ");
        drawBorder(size);
        System.out.print("    ");
        for (int colum = 1; colum <= size; colum++) {
            Cell cellColum = new Cell(colum + "", Color.ANSI_WHITE_BACKGROUND, Color.ANSI_BLACK);
            System.out.print(cellColum);
            if (colum == size) {
                System.out.println("|");
            }
        }
        for (int row = 1; row <= size; row++) {
            Cell cellRow = new Cell((char) ('a' + row - 1) + "", Color.ANSI_WHITE_BACKGROUND, Color.ANSI_BLACK);
            drawBorder(size + 1);
            System.out.print(cellRow);
            for (int colum = 1; colum <= size; colum++) {
                System.out.print(myBoard.getCells()[row - 1][colum - 1]);
            }
            System.out.println("|");
        }
    }

    public static void drawOpponentBoard(Players opponent, String title) {
        Boards opponentBoard = opponent.getBoard();
        System.out.println(title);
        int size = opponentBoard.getSize();
        System.out.print("    ");
        drawBorder(size);
        System.out.print("    ");
        for (int colum = 1; colum <= size; colum++) {
            Cell cellColum = new Cell(colum + "", Color.ANSI_WHITE_BACKGROUND, Color.ANSI_BLACK);
            System.out.print(cellColum);
            if (colum == size) {
                System.out.println("|");
            }
        }
        for (int row = 1; row <= size; row++) {
            Cell cellRow = new Cell((char) ('a' + row - 1) + "", Color.ANSI_WHITE_BACKGROUND, Color.ANSI_BLACK);
            drawBorder(size + 1);
            System.out.print(cellRow);
            for (int colum = 1; colum <= size; colum++) {
                System.out.print(opponentBoard.getCells()[row - 1][colum - 1].toOpponentBoard());
            }
            System.out.println("|");
        }
    }

    public static void drawBothBoard(Players firstPlayer, Players secondPlayer) {
        System.out.print("~~~~ " + firstPlayer.getPlayerName() + "'s board  ~~~~");
        int size = firstPlayer.getBoard().getSize();
        int lengthNameFirstPlayer = firstPlayer.getPlayerName().length();
        int distance = 30;
        for (int times = 1; times <= distance + size * 4 + 1 - lengthNameFirstPlayer; times++) {
            System.out.print(" ");
        }
        System.out.println("~~~~ " + secondPlayer.getPlayerName() + "'s board ~~~~");
        System.out.print("    ");
        drawBothBorder(size);
        //Ngăn cách
        for (int times = 1; times <= distance; times++) System.out.print(" ");
        //Bảng 1
        drawBothBorder(size);
        System.out.println();
        System.out.print("    ");
        for (int colum = 1; colum <= size; colum++) {
            //Đề mục
            Cell cellColum = new Cell(colum + "", Color.ANSI_WHITE_BACKGROUND, Color.ANSI_BLACK);
            System.out.print(cellColum);
            if (colum == size) {
                System.out.print("|");
            }
        }
        //Ngăn cách
        for (int times = 1; times <= distance; times++) System.out.print(" ");
        //Bảng 2
        for (int colum = 1; colum <= size; colum++) {
            //Đề mục
            Cell cellColum = new Cell(colum + "", Color.ANSI_WHITE_BACKGROUND, Color.ANSI_BLACK);
            System.out.print(cellColum);
            if (colum == size) {
                System.out.println("|");
            }
        }

        for (int row = 1; row <= size; row++) {
            Cell cellRow = new Cell((char) ('a' + row - 1) + "", Color.ANSI_WHITE_BACKGROUND, Color.ANSI_BLACK);
            drawBothBorder(size + 1);
            //Ngăn cách
            for (int times = 1; times <= distance-4; times++) System.out.print(" ");
            //
            drawBorder(size + 1);
            //Đề mục
            System.out.print(cellRow);
            //Bảng 1
            for (int colum = 1; colum <= size; colum++) {
                System.out.print(firstPlayer.getBoard().getCells()[row - 1][colum - 1]);
            }
            System.out.print("|");
            //Ngăn cách
            for (int times = 1; times <= distance - 4; times++) System.out.print(" ");
            //Đề mục
            System.out.print(cellRow);
            //Bảng 2
            for (int colum = 1; colum <= size; colum++) {
                System.out.print(secondPlayer.getBoard().getCells()[row - 1][colum - 1]);
            }
            System.out.println("|");
        }
        for (int times = 1; times <= 5; times++)
            System.out.println();
    }
}
