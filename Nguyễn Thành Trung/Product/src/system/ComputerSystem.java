package system;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import mapresources.Board;

public class ComputerSystem {

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    public static Random random = new Random();

    public static Scanner scanner = new Scanner(System.in);

    public static int charToInt(String xAxisString) {
        char c = xAxisString.charAt(0);
        return c - 'A' + 1;
    }

    public static boolean checkValidCoordinate(int xAxis, int yAxis) {
        if (xAxis < 1 || yAxis < 1 || xAxis >= Board.boardSize || yAxis >= Board.boardSize) {
            return false;
        }
        return true;
    }

    public static void invalidAnnouncement() {
        System.out.println("Invalid coordinate!!!");
        System.out.println("Insert coordiante again please...");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }

    public static void waitScreen() {
        System.out.print("Press Enter to continue...");
        ComputerSystem.scanner.nextLine();
        ComputerSystem.scanner.nextLine();
    }

    public static void displayScreen() {
        clearScreen();
        try {
            Intro intro = new Intro();
            intro.displayIntro();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String insertPlayerName() {
        System.out.print("Let me know your name: ");
        String name = scanner.nextLine();
        return name;
    }
}
