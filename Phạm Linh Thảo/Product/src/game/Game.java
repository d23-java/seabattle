package game;

import models.Player;
import java.util.Scanner;
import static game.Terminal.clear;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player opponentPlayer;
    private Scanner scanner;

    public Game() {
        clear();
        scanner = new Scanner(System.in);
        System.out.println("Enter name for Player 1:");
        String player1Name = scanner.nextLine();
        player1 = new Player(player1Name);
        clear();
        System.out.println("Enter name for Player 2:");
        String player2Name = scanner.nextLine();
        player2 = new Player(player2Name);
        currentPlayer = player1;
        opponentPlayer = player2;
    }

    public void switchPlayer() {
        Player temp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = temp;
    }

    public void placeShips() {
        clear();
        System.out.println("Place your ships!");
        for (int i = 0; i < 2; i++) {
            System.out.println(currentPlayer.getName() + "'s turn to place ships.");
            currentPlayer.getBoard().displayBoard();
            for (int j = 0; j < 5; j++) {
                while(true) {
                    if(j==0 || j==1 ) System.out.println("Place Patrol Boat size 1x2");
                    else if(j==2)     System.out.println("Place Destroyer Boat size 1x4");
                    else if(j==3)     System.out.println("Place Submarine size 1x3");
                    else if(j==4 )     System.out.println("Place Battle Ship 1x5");
                    System.out.println("Enter start coordinate (e.g., A1, B3):");
                    String coordinate = scanner.nextLine().toUpperCase();
                    int x = coordinate.charAt(0) - 'A';
                    int y = Integer.parseInt(coordinate.substring(1)) - 1;

                    int size = 0;
                    if(j==0 || j==1) size =2;
                    else if(j==2)    size =4;
                    else if(j==3)    size = 3;
                    else if( j==4 ) size =5;

                    System.out.println("Enter direction (H for horizontal, V for vertical):");
                    char direction = scanner.nextLine().toUpperCase().charAt(0);
                    char symbol=' ';
                    if(j==0 || j==1) symbol ='P';
                    else if(j==2) symbol='D';
                    else if(j==3) symbol='S';
                    else if(j==4) symbol='B';

                    if(currentPlayer.canPlaceShip(x, y, size, direction))
                    {
                        currentPlayer.placeShip(x, y, size, direction, symbol);
                        break;
                    }
                    else
                    {
                        System.out.println("Cannot place ship at this position. Try again!");
                    }

                }
                currentPlayer.getBoard().displayBoard();
            }
            clear();
            switchPlayer();

        }
    }

    public void shooting() {
        while (!player1.getBoard().isCleanAllShip() && !player2.getBoard().isCleanAllShip()) {
            clear();
            System.out.println("Shoot " + currentPlayer.getName() + "'s opponent's ships!");
            while (true) {
                boolean check = true;
                System.out.println("==========================");
                System.out.println("        MENU        ");
                System.out.println("==========================");
                System.out.println("1. Show my board");
                System.out.println("2. Show fog board");
                System.out.println("3. Shoot");
                System.out.println("==========================");
                System.out.print("Choose your option: ");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Your board:");
                        currentPlayer.getBoard().displayBoard();
                        break;

                    case 2:
                        System.out.println("Opponent's fog board:");
                        currentPlayer.getFogBoard().displayBoard();
                        break;

                    case 3:
                        System.out.println(currentPlayer.getName() + "'s turn to shoot.");
                        System.out.println("Enter coordinate to shoot (e.g., A1, B3):");
                        String coordinate = scanner.nextLine().toUpperCase();

                        int x = coordinate.charAt(0) - 'A';
                        int y = Integer.parseInt(coordinate.substring(1)) - 1;

                        boolean hit = currentPlayer.shoot(opponentPlayer, x, y);
                        if (hit) {
                            System.out.println("Hit! You can choose another action.");
                        } else {
                            System.out.println("Miss! Switching turn...");
                            check = false;
                            break;
                        }
                        if (opponentPlayer.getBoard().isCleanAllShip()) {
                            return;
                        }
                        break;

                }
                if (!check) break;
            }
            switchPlayer();
        }

        }

    public void startGame() {
        placeShips();
        clear();
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
