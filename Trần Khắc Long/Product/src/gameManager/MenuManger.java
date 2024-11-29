package gameManager;
import enums.Color;

import java.util.Scanner;
public class MenuManger {

    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("+-------------------+");
        System.out.println("|    Menu Manger    |");
        System.out.println("+-------------------+");
        System.out.println();
        System.out.println("+-------------------+");
        System.out.println("|      1. Play      |");
        System.out.println("+-------------------+");
        System.out.println("|   2. ScoreBoard   |");
        System.out.println("+-------------------+");
        System.out.println("|      3. End       |");
        System.out.println("+-------------------+");

        System.out.println(Color.ANSI_GREEN +  "Enter your choice : " + Color.ANSI_RESET);

        while (true) {
            int choice = choiceInput();
            ScreenManager.clearScreen();
            switch (choice) {
                case 1:
                    GamePlay.setUp();
                    break;
                case 2:
                    ScoreBoard.show();
                    break;
                case 3:
                    return;
                default:
                    System.out.println(Color.ANSI_RED + "Invalid choice" + Color.ANSI_RESET);
            }
        }
    }

    private int choiceInput(){
        return Integer.parseInt(scanner.nextLine());
    }
}
