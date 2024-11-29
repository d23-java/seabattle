package gameObjects;

import java.util.ArrayList;

public class Players  {
    private String playerName;
    private  ArrayList <Ships> ships;
    private  Boards board;
    private int shotCell;
    private int shotShip;

    public Players(String playerName, int size) {
        this.playerName = playerName;
        board = new Boards(size);
        ships = new ArrayList<>();
        shotShip = 0;
        shotCell = 0;
    }
    // compareTo method to sort by name

    public void increaseShotCell() {
        shotCell++;
    }

    public void increaseShotShip() {
        shotShip++;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getShotCell() {
        return shotCell;
    }

    public int getShotShip() {
        return shotShip;
    }

    public  ArrayList <Ships> getShips() {
        return ships;
    }

    public Boards getBoard() {
        return board;
    }
}
