package System;

import Character.Player;

public class Main {
    public static void main(String[] args) {
        ComputerSystem.clearScreen();
        Player player1 = new Player();
        player1.showPlayerBoard();
        player1.insertShip();
     }
}