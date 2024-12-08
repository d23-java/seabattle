package System;

import Color.ConsoleColors;
import Object.Player;
import java.util.Scanner;
import Object.Ship;

public class mainGame {
    private Player player1;
    private Player player2;
    private Scanner sc = new Scanner(System.in);

    public void displayMenu(){
        System.out.println("Welcome to SEA BATTLE, choose these options below:");
        System.out.println("+------------------+");
        System.out.println("| 1.Start new game |");
        System.out.println("+------------------+");
        System.out.println("| 2.Exit           |");
        System.out.println("+------------------+");
    }

    public void start() {
        displayMenu();
        int option = sc.nextInt();
        sc.nextLine();
        System.out.println();
        switch (option) {
            case 1:
                System.out.println("Enter Player 1's name: ");
                String player1Name = sc.nextLine();
                player1 = new Player(player1Name);

                System.out.println("Enter Player 2's name: ");
                String player2Name = sc.nextLine();
                player2 = new Player(player2Name);

                //System.out.println("Player1, set up your ships");
                setupShips(player1);
                setupShips(player2);
                playGame();

                break;

            case 2:
                String[] end = {
                        "  #####  ###### ######    #   #  ####  #    #    #        ##   ##### ###### ##### ",
                        " #       #      #          # #  #    # #    #    #       #  #    #   #      #    #",
                        "  #####  #####  #####       #   #    # #    #    #      #    #   #   #####  #    #",
                        "       # #      #           #   #    # #    #    #      ######   #   #      ##### ",
                        " #     # #      #           #   #    # #    #    #      #    #   #   #      #   # ",
                        "  #####  ###### ######      #    ####   ####     ###### #    #   #   ###### #    #",
                };
                for (String line : end) {
                    System.out.println(ConsoleColors.GREEN_BRIGHT + line + ConsoleColors.RESET);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.exit(0);
                break;

            default:
                System.out.println(ConsoleColors.RED + "Invalid option. Try again." + ConsoleColors.RESET);
                start();
        }
    }

    private void setupShips(Player player) {
        Ship[] ships = {
                new Ship("Patrol Boat", 2),
                new Ship("Patrol Boat", 2),
                new Ship("Destroyer Boat", 4),
                new Ship("Submarine", 3),
                new Ship("Battle Ship", 5),
        };

        System.out.println(player.getName() + ", place your ships");

        for (Ship ship : ships) {
            boolean placed = false;
            while (!placed) {
                // Display the player's board before placing each ship
                System.out.println(player.getName() + "'s current board:");
                player.getOwnBoard().printBoard(true); // Show ships on their board

                System.out.println("Enter coordinates for " + ship.getType() + " (" + ship.getSize() + ")");
                System.out.print("Enter start: ");
                String start = sc.nextLine().toUpperCase();
                System.out.print("Enter end: ");
                String end = sc.nextLine().toUpperCase();

                placed = player.placeShip(ship, start, end);
                if (!placed) System.out.println("Invalid placement. Try again.");
            }
        }
    }

    private void playGame(){
        Player currentPlayer = player1;
        Player opponent = player2;

        while(true){
            boolean keepShooting =true;
            while(keepShooting){
                System.out.println("\n" + currentPlayer.getName() + "'s turn");
                System.out.println("Your board:");
                currentPlayer.getOwnBoard().printBoard(true);

                System.out.println("\nEnemy, " + opponent.getName() + "'s board (Fog mode)");
                currentPlayer.getFogBoard().printBoard(false);
                System.out.println("Enter attack coordiantes");
                String attackCoor = sc.nextLine().toUpperCase();
                int row = attackCoor.charAt(1) - '1';
                int col = attackCoor.charAt(0) - 'A';
                // invalid attack coordinate
                if (row < 0 || row >= 10 || col < 0 || col >= 10) {
                    System.out.println("Invalid coordinates. Try again.");
                    continue;
                }

                boolean hit = currentPlayer.attack(player1, row, col);
                if(hit){
                    String[] HIT = {
                            "#    #  #  #####",
                            "#    #  #    #  ",
                            "######  #    #  ",
                            "#    #  #    #  ",
                            "#    #  #    #  ",
                            "#    #  #    #  "
                    };
                    for(String line : HIT){
                        System.out.println(ConsoleColors.GREEN_BRIGHT + line + ConsoleColors.RESET);
                    }
                    if(opponent.hasLost()){
                        System.out.println(currentPlayer.getName() + "WIN");
                        String[] VICTORY= {
                                " #     # ###  #####  ####### ####### ######  #     # ",
                                " #     #  #  #     #    #    #     # #     #  #   #  ",
                                " #     #  #  #          #    #     # #     #   # #   ",
                                " #     #  #  #          #    #     # ######     #    ",
                                "  #   #   #  #          #    #     # #   #      #    ",
                                "   # #    #  #     #    #    #     # #    #     #    ",
                                "    #    ###  #####     #    ####### #     #    #    "
                        };
                        for(String line : VICTORY){
                            System.out.println(ConsoleColors.YELLOW_BRIGHT + line + ConsoleColors.RESET);
                        }
                        start();
                    }
                }
                else{
                    String[] MISS = {
                            " #     # ###  #####   #####  ",
                            " ##   ##  #  #     # #     # ",
                            " # # # #  #  #       #       ",
                            " #  #  #  #   #####   #####  ",
                            " #     #  #        #       # ",
                            " #     #  #  #     # #     # ",
                            " #     # ###  #####   #####  "
                    };
                    for(String line : MISS){
                        System.out.println(ConsoleColors.RED_BRIGHT + line + ConsoleColors.RESET);
                    }
                    keepShooting = false;
                }

                //swap turn
                Player temp = currentPlayer;
                currentPlayer = opponent;
                opponent = temp;
            }
        }
    }
}
