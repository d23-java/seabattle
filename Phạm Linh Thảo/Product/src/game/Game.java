package game;

import models.Player;
import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player opponentPlayer;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentPlayer = player1;
        opponentPlayer = player2;
    }

    public void switchPlayer() {
        Player temp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = temp;
    }

    public void placeShips() {
        System.out.println("Place your ships!");
        for (int i = 0; i < 2; i++) {
            System.out.println(currentPlayer.getName() + "'s turn to place ships.");
            currentPlayer.getBoard().displayBoard();
            for (int j = 0; j < 5; j++) {
                while(true) {
                    if(j==0 || j==1 ) System.out.println("Place Patrol Boat size 1x2");
                    else if(j==2)     System.out.println("Place Destroyer Boat size 1x4");
                    else if(j==3)     System.out.println("Place Submarine size 1x3");
                    else if(j==4)     System.out.println("Place Battle Ship 1x5");
                    System.out.println("Enter start X coordinate:");
                    int x = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter start Y coordinate:");
                    int y = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter ship size:");
                    int size = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter direction (H for horizontal, V for vertical):");
                    char direction = scanner.nextLine().toUpperCase().charAt(0);
                    if(currentPlayer.canPlaceShip(x, y, size, direction))
                    {
                        currentPlayer.placeShip(x, y, size, direction);
                        break;
                    }
                    else
                    {
                        System.out.println("Cannot place ship at this position.Enter again!");
                    }

                }
                currentPlayer.getBoard().displayBoard();
            }
            switchPlayer();
        }
    }

    public void shooting() {
        System.out.println("Phase: Shoot your opponent's ships!");
        while (player1.getShipsRemaining() > 0 && player2.getShipsRemaining() > 0) {
            System.out.println(currentPlayer.getName() + "'s turn to shoot.");
            currentPlayer.getBoard().displayBoard();
            System.out.println("Enter X coordinate to shoot:");
            int x = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter Y coordinate to shoot:");
            int y = Integer.parseInt(scanner.nextLine());
            boolean hit = currentPlayer.shoot(opponentPlayer, x, y);
            if (!hit) {
                System.out.println("Switching turn...");
                switchPlayer();
            }
        }
    }

    public void startGame() {
        placeShips();
        shooting();
        declareWinner();
    }

    private void declareWinner() {
        if (player1.getBoard().isCleanAllShip()) {
            System.out.println(player2.getName() + " wins!");
        } else if (player2.getBoard().isCleanAllShip()) {
            System.out.println(player1.getName() + " wins!");
        }
    }
}
