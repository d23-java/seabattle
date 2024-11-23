package gameObjects;

import java.util.ArrayList;

public class Players {
    private String playerName;
    private static ArrayList <Ships> ships;
    private static Boards board;
    public Players(String playerName, int size) {
        this.playerName = playerName;
        board = new Boards(size);
    }
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public  ArrayList <Ships> getShips() {
        return ships;
    }

    public  void setShips(ArrayList <Ships> ships) {
        Players.ships = ships;
    }

    public Boards getBoard() {
        return board;
    }
}
