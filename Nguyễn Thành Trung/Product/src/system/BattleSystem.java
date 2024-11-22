package system;

import character.Player;

public class BattleSystem {
    private Player human1 = new Player();
    private Player human2 = new Player();
    private int playerTurn = 1;

    public void PVPmode() {
        System.out.println("It's player" + playerTurn + "'s turn!!!");
        playerTurn = 2;
        human1.insertShip();
        ComputerSystem.clearScreen();
        System.out.println("It's player" + playerTurn + "'s turn!!!");
        playerTurn = 1;
        human2.insertShip();
        ComputerSystem.clearScreen();

        while (!human1.checkLost() && !human2.checkLost()) {
            ComputerSystem.clearScreen();
            System.out.println("It's player" + playerTurn + "'s turn!!!");
            switch (playerTurn) {
                case 1:
                    human1.showEnemyFoggyBoard();
                    break;
                case 2:
                    human2.showEnemyFoggyBoard();
                    break;
            }
            System.out.print("Insert x axis you want to attack: ");
            int xAxisAttack = ComputerSystem.scanner.nextInt();
            System.out.print("Insert y axis you want to attack: ");
            int yAxisAttack = ComputerSystem.scanner.nextInt();
            switch (playerTurn) {
                case 1:
                    human1.checkHitOrMiss(xAxisAttack, yAxisAttack);
                    if (human1.getAttackMiss()) {
                        playerTurn = 2;
                    }
                    break;
                case 2:
                    human2.checkHitOrMiss(xAxisAttack, yAxisAttack);
                    if (human2.getAttackMiss()) {
                        playerTurn = 1;
                    }
                    break;
                
            }
            if (human1.checkLost()) {
                System.out.println("Player 2 win <3 <3 <3");
            } else if (human2.checkLost()) {
                System.out.println("Player 1 win <3 <3 <3");
            }
        }
    }
}