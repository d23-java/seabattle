package System;

import Character.Player;

public class Main {
    public static void main(String[] args) {
        ComputerSystem.clearScreen();
        Player player1 = new Player();
        player1.addShip();
        player1.setPlayerBoard();
        player1.showPlayerBoard();
    }
}