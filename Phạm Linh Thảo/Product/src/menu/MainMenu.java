package menu;

import game.Game;
import java.util.Scanner;

public class MainMenu {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("==========================");
            System.out.println("        BATTLESHIP        ");
            System.out.println("==========================");
            System.out.println("1. Start");
            System.out.println("2. Exit");
            System.out.println("==========================");
            System.out.print("Choose your option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Starting the game.....");
                    Game game = new Game();
                    game.startGame();
                    break;

                case 2:
                    System.out.println("Exiting the game. Goodbye! ");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    public void menuInGame() {
        System.out.println("==========================");
        System.out.println("        MENU        ");
        System.out.println("==========================");
        System.out.println("1. Show my board!");
        System.out.println("2. Show my fog board!");
        System.out.println("2. Shoot!");
        System.out.println("==========================");
        System.out.print("Choose your option: ");
    }
}
