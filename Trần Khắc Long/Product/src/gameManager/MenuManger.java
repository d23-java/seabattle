package gameManager;

import enums.Color;
import Scanner.*;
import gameObjects.Players;

public class MenuManger {


    public static void showMenuManger() {
        while (true) {
            System.out.println("+-------------------+");
            System.out.println("|     Sea Battle    |");
            System.out.println("+-------------------+");
            System.out.println();
            System.out.println("+-------------------+");
            System.out.println("|      1. Play      |");
            System.out.println("+-------------------+");
            System.out.println("|   2. ScoreBoard   |");
            System.out.println("+-------------------+");
            System.out.println("|      3. End       |");
            System.out.println("+-------------------+");

            System.out.println(Color.ANSI_GREEN + "Enter your choice : " + Color.ANSI_RESET);

            int choice = choiceInput();
            ScreenManager.clearScreen();
            switch (choice) {
                case 1:
                    GamePlay.playGame();
                    break;
                case 2:
                    ScoreBoard.show();
                    break;
                case 3:
                    return;
                default:
                    System.out.println(Color.ANSI_RED + "Invalid choice" + Color.ANSI_RESET);
            }
            ScreenManager.waitForEnter();
            ScreenManager.clearScreen();
        }
    }

    public static void showMenuSetUp(String title) {
        while (true) {
            System.out.println(title);
            System.out.println();
        System.out.println("+-------------------+");
        System.out.println("|    SetUp Option   |");
        System.out.println("+-------------------+");
        System.out.println();
        System.out.println("+-------------------+");
        System.out.println("|     1. SetUp      |");
        System.out.println("+-------------------+");
        System.out.println("|     2. Random     |");
        System.out.println("+-------------------+");
        int choice = choiceInput();
        ScreenManager.clearScreen();
        switch (choice) {
            case 1:
                GamePlay.setUp(1);
                return;
            case 2:
                GamePlay.setUp(2);
                return;
            default:
                System.out.println(Color.ANSI_RED + "Invalid choice" + Color.ANSI_RESET);
        }
    }
    }
    public static void showMenuIngame(Players currentPlayer) {
        while (true) {
            System.out.println("======> " + Color.ANSI_YELLOW + currentPlayer.getPlayerName() + "'s turn" + Color.ANSI_RESET + " <======");
            System.out.println();
            ScreenManager.showCurrentStatus(currentPlayer);
            System.out.println();
            System.out.println("+----------------------+");
            System.out.println("|" + "    " + Color.ANSI_PURPLE_BACKGROUND + "Player Options" + Color.ANSI_RESET + "    " + "|");
            System.out.println("+----------------------+");
            System.out.println();
            System.out.println("+----------------------+");
            System.out.println("|   1. View my board   |");
            System.out.println("+----------------------+");
            System.out.println("|     2. Open fire     |");
            System.out.println("+----------------------+");
            System.out.println("|     3. Skip turn     |");
            System.out.println("+----------------------+");

            System.out.println(Color.ANSI_GREEN + "Enter your choice: " + Color.ANSI_RESET);

            int choice = choiceInput();
            ScreenManager.clearScreen();
            switch (choice) {
                case 1:
                    ScreenManager.drawMyBoard(currentPlayer, "~~~~ " + "Your board" + " ~~~~");
                    ScreenManager.waitForEnter();
                    ScreenManager.clearScreen();
                    break;
                case 2:
                    GamePlay.fire();
                    return;
                case 3:
                    ScreenManager.clearScreen();
                    return;
                default:
                    System.out.println(Color.ANSI_RED + "Invalid choice" + Color.ANSI_RESET);
            }
        }
    }

    private static int choiceInput() {
        return Integer.parseInt(Input.scanner.nextLine());
    }
}
