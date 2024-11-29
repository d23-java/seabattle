package gameManager;
import java.io.IOException; 
import enums.Color;
import gameObjects.*;

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

    public static void drawBorder(int size) {
        for (int row = 1; row <= size; row++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    public static void drawBoard(Boards myBoard) {
        int size = myBoard.getSize();
        System.out.print("    ");
        drawBorder(size);
        System.out.print("    ");
        for (int colum = 1; colum <= size; colum++) {
            Cell cellColum = new Cell(Color.ANSI_WHITE_BACKGROUND +Color.ANSI_BLACK + colum + Color.ANSI_RESET + Color.ANSI_RESET + "");
            System.out.print(cellColum);
            if (colum == size) {
                System.out.println("|");
            }
        }
        for (int row = 1; row <= size; row++) {
            Cell cellRow = new Cell(Color.ANSI_WHITE_BACKGROUND + Color.ANSI_BLACK + (char)('a'+row-1) +  Color.ANSI_RESET + Color.ANSI_RESET + "" );
            drawBorder(size+1);
            System.out.print(cellRow);
            for (int colum = 1; colum <= size; colum++) {
                System.out.print(myBoard.getCells()[row-1][colum-1]);
            }
            System.out.println("|");
        }
    }
}
