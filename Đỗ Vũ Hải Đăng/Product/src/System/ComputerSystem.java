package System;

import ObjectList.Player;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ComputerSystem {
    ArrayList<Player> players = new ArrayList<>();

    public void startGame() throws InterruptedException {
        while(true){
            Terminal.clear();
            MenuList.showMainMenu();
            int choice = Integer.parseInt(InputSystem.sc.nextLine());
            switch(choice) {
                case 1:
                    System.out.print("                                                       Starting");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    newGame();
                    break;
                case 2:
                case 3:
                case 4:
                    System.out.println("Updating....");
                    TimeUnit.SECONDS.sleep(1);
                    break;
                case 5:
                    System.out.println("See u soon!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
                    TimeUnit.SECONDS.sleep(1);
                    break;
            }
        }

    }

    void newGame() throws InterruptedException {
        while(true){
            Terminal.clear();
            MenuList.showGameModeMenu();
            int choice = Integer.parseInt(InputSystem.sc.nextLine());
            switch(choice) {
                case 1:
                    System.out.print("                                                       Starting");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    setUpPlayer();
                    inGame();
                    return;
                case 2:
                    System.out.println("Updating....");
                    TimeUnit.SECONDS.sleep(1);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice");
                    TimeUnit.SECONDS.sleep(1);
                    break;
            }
        }
    }

    void setUpPlayer() throws InterruptedException {
        int quantityPlayers = 2;
        for(int i = 1;i <= quantityPlayers;++i){
            Terminal.clear();
            System.out.println("Set up PLAYER " + i + " in \n");
            MenuList.printNumber(3);
            TimeUnit.SECONDS.sleep(1);
            Terminal.clear();
            System.out.println("Set up PLAYER " + i + " in \n");
            MenuList.printNumber(2);
            TimeUnit.SECONDS.sleep(1);
            Terminal.clear();
            System.out.println("Set up PLAYER " + i + " in \n");
            MenuList.printNumber(1);
            TimeUnit.SECONDS.sleep(1);
            Player player = new Player(i);
            Terminal.clear();
            player.setInformation();
            players.add(player);
            if(i == 2){
                Terminal.clear();
                MenuList.letStartTitle();
                TimeUnit.SECONDS.sleep(3);
            }
        }
    }

    void inGame(){
        int playerTurn = 1;
        while(!players.get(0).isDefeat() && !players.get(1).isDefeat()){
            playerTurn = (playerTurn == 1) ? 0 : 1;
            while(true){
                Terminal.clear();
                System.out.println(">>> " + players.get(playerTurn).getName() + "'s turn <<<<\n\n");
                MenuList.showPlayerOption();
                int playerChoice = Integer.parseInt(InputSystem.sc.nextLine());
                if(playerChoice == 1){
                    players.get(playerTurn).shotShip(players.get(1 - playerTurn));
                    break;
                }
                else if(playerChoice == 2){
                    Terminal.clear();
                    System.out.println("          Player's Board\n");
                    players.get(playerTurn).getPlayerScreen().display();
                    System.out.println(" ");
                    System.out.println("Press Enter to return");
                    InputSystem.sc.nextLine();
                }
                else if(playerChoice == 3){
                    break;
                }
            }
        }
        Terminal.clear();
        MenuList.showPlayerTitle(playerTurn + 1);
        System.out.println(" ");
        MenuList.isWinnerNotify();
        System.out.println(" ");
        System.out.println("\n\n\n\nPress Enter to return to the main menu");
        InputSystem.sc.nextLine();
    }
}
