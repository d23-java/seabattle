package system;

import character.Player;
import character.RandomSetup;

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
        System.out.println("It's player" + playerTurn + "'s turn!!!");
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
            ComputerSystem.clearScreen();
            System.out.println("It's player" + playerTurn + "'s turn!!!");
            switch (playerTurn) {
                case 1:
                    humanFirst.showEnemyFoggyBoard();
                    break;
                case 2:
                    humanSecond.showEnemyFoggyBoard();
                    break;
            }
            System.out.print("Insert x axis you want to attack: ");
            String xAttackString = ComputerSystem.scanner.next();
            int xAxisAttack = ComputerSystem.charToInt(xAttackString);
            System.out.print("Insert y axis you want to attack: ");
            int yAxisAttack = ComputerSystem.scanner.nextInt();
            switch (playerTurn) {
                case 1:
                    humanFirst.checkHitOrMiss(xAxisAttack, yAxisAttack);
                    ComputerSystem.clearScreen();
                    humanFirst.showEnemyFoggyBoard();
                    if (humanFirst.getAttackMiss()) {
                        System.out.println("You miss!!!");
                        playerTurn = 2;
                    } else {
                        System.out.println("You hit!!!");
                    }
                    ComputerSystem.waitScreen();
                    break;
                case 2:
                    humanSecond.checkHitOrMiss(xAxisAttack, yAxisAttack);
                    ComputerSystem.clearScreen();
                    humanSecond.showEnemyFoggyBoard();
                    if (humanSecond.getAttackMiss()) {
                        System.out.println("You miss!!!");
                        playerTurn = 1;
                    } else {
                        System.out.println("You hit!!!");
                    }
                    ComputerSystem.waitScreen();
                    break;
                
            }
            if (humanFirst.checkLost()) {
                System.out.println("Player 2 win <3 <3 <3");
            } else if (humanSecond.checkLost()) {
                System.out.println("Player 1 win <3 <3 <3");
            }
        }
    }
}