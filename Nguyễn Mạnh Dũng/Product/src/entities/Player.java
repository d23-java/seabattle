package entities;

import main.Game;

import static utilz.Constants.gameConstants.EMPTY_BOAT;

public class Player {
    private String name;
    private int score = 0;
    private int shipLeft = 5;
    private String[][] playerBoard;
    private String[][] playerFog;

    public void initBoards(int boardSize) {
        this.playerBoard = new String[Game.boardSize][Game.boardSize];
        this.playerFog = new String[Game.boardSize][Game.boardSize];
    }

    public void initializeBoards(String[][] board) {
        for (int i = 0; i < Game.boardSize; i++) {
            for (int j = 0; j < Game.boardSize; j++) {
                board[i][j] = EMPTY_BOAT;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[][] getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(String[][] playerBoard) {
        this.playerBoard = playerBoard;
    }

    public String[][] getPlayerFog() {
        return playerFog;
    }

    public void setPlayerFog(String[][] playerFog) {
        this.playerFog = playerFog;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getShipLeft() {
        return shipLeft;
    }

    public void setShipLeft(int shipLeft) {
        this.shipLeft = shipLeft;
    }

    @Override
    public String toString() {
        return name;
    }
}
