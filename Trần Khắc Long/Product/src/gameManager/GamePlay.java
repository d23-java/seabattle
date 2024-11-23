package gameManager;

import gameObjects.Players;

import java.util.Scanner;

public class GamePlay {
    private static Scanner scanner = new Scanner(System.in);
    private static Players player1;
    private static Players player2;

    public static void setUp() {
        infoSetUp();
        shipSetUp();
    }

    private static void infoSetUp() {
        System.out.println("Enter the board's size (10 -> 20): ");
        int size = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Player 1's name: ");
        String player1Name = scanner.nextLine();
        System.out.println("Enter Player 1's name: ");
        String player2Name = scanner.nextLine();
        player1 = new Players(player1Name, size);
        player2 = new Players(player2Name, size);
        ScreenManager.clearScreen();
    }

    private static void shipSetUp() {
        System.out.println("~~~~ " + player1.getPlayerName() +" sets up the ship "+"~~~~");
        ScreenManager.drawBoard(player1.getBoard());
    }
    private static void patrolboatSetUp() {
        System.out.println("Choose the initial position's PatrolBoat: " );

    }
    private static void destroyerboatSetUp() {
        System.out.println("Choose the initial position's DestroyerBoat: " );

    }
    private static void submarineSetUp() {
        System.out.println("Choose the initial position's Submarine: " );

    }
    private static void battleshipSetUp() {
        System.out.println("Choose the initial position's Battle Ship: " );

    }
    private static void shipInput(){

    }
}
