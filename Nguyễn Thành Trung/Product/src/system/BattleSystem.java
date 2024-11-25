package system;

import character.Player;
import character.RandomSetup;
import java.util.ArrayList;

public class BattleSystem {
    private Player humanFirst = new Player();
    private Player humanSecond = new Player();
    private int playerTurn = 1;

    private void autoSetup(Player player) {
        RandomSetup randomSetup = new RandomSetup();
        randomSetup.RandomInsertShip();
        player.autoSetup(randomSetup.getShipArrayList(), randomSetup.getPlayerBoard(), randomSetup.getPlayerObjectMap());
    }

    public void PVPmode() {
        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(humanFirst);
        playerList.add(humanSecond);
        System.out.println("It's player" + playerTurn + "'s turn!!!");
        playerList.get(playerTurn - 1).setName(ComputerSystem.insertPlayerName());

        System.out.print("If you want to enter autosetup mode, press 1, else press 0: ");
        int pressInput = ComputerSystem.scanner.nextInt();
        if (pressInput == 1) {
            autoSetup(humanFirst);
            System.out.println("This is your Board!!!");
            humanFirst.showPlayerBoard();
            ComputerSystem.waitScreen();
        } else {
            humanFirst.insertShip();
        }
        
        playerTurn = 2;
        ComputerSystem.clearScreen();
        System.out.println("It's player" + playerTurn + "'s turn!!!");
        playerList.get(playerTurn - 1).setName(ComputerSystem.insertPlayerName());
        System.out.print("If you want to enter autosetup mode, press 1, else press 0: ");
        pressInput = ComputerSystem.scanner.nextInt();
        if (pressInput == 1) {
            autoSetup(humanSecond);
            System.out.println("This is your Board!!!");
            humanSecond.showPlayerBoard();
            ComputerSystem.waitScreen();
        } else {
            humanFirst.insertShip();
        }
        playerTurn = 1;        
        ComputerSystem.clearScreen();

        while (!humanFirst.checkLost() && !humanSecond.checkLost()) {
            int yourEnemy = 0;
            switch (playerTurn) {
                case 1:
                    yourEnemy = 2;
                    break;
                case 2:
                    yourEnemy = 1;
                    break;
            }

            ComputerSystem.clearScreen();
            System.out.println("It's player" + playerTurn + "'s turn!!!");
            System.out.print("Press 0 to show your board, 1 to attack: ");
            int attackOrNot = ComputerSystem.scanner.nextInt();
            if (attackOrNot == 0) {
                ComputerSystem.clearScreen();
                playerList.get(playerTurn - 1).showPlayerBoard();
                System.out.print("Here is your board");
                ComputerSystem.scanner.nextLine();
                ComputerSystem.scanner.nextLine();
                continue;
            }

            playerList.get(yourEnemy - 1).showEnemyFoggyBoard();
            System.out.print("Insert x axis you want to attack: ");
            String xAttackString = ComputerSystem.scanner.next();
            int xAxisAttack = ComputerSystem.charToInt(xAttackString);
            System.out.print("Insert y axis you want to attack: ");
            int yAxisAttack = ComputerSystem.scanner.nextInt();

            playerList.get(yourEnemy - 1).checkHitOrMiss(xAxisAttack, yAxisAttack);
            ComputerSystem.clearScreen();
            playerList.get(yourEnemy - 1).showEnemyFoggyBoard();
            if (playerList.get(yourEnemy - 1).getAttackMiss()) {
                System.out.println("You miss!!!");
                ComputerSystem.waitScreen();
                playerTurn = yourEnemy;
            } else {
                System.out.println("You hit!!!");
                ComputerSystem.waitScreen();
            }
            
            if (humanFirst.checkLost()) {
                System.out.println("Player 2 win <3 <3 <3");
            } else if (humanSecond.checkLost()) {
                System.out.println("Player 1 win <3 <3 <3");
            }
        }
    }
}