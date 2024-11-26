package models;

import java.util.Scanner;

public class Player {
    private String name;
    private Board board;
    private int shipsRemaining;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
        this.shipsRemaining = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public int getShipsRemaining() {
        return shipsRemaining;
    }
    public void setShipsRemaining(int shipsRemaining) {
        this.shipsRemaining = shipsRemaining;
    }
    public  boolean canPlaceShip(int x, int y, int size, char direction) {
        if (direction == 'H') {
            for (int i = 0; i < size; i++) {
                if (y + i >= 10 || !board.isCellHaveNoShip(x, y + i)) {
                    return false;
                }
            }
        } else if (direction == 'V') {
            for (int i = 0; i < size; i++) {
                if (x + i >= 10 || !board.isCellHaveNoShip(x + i, y)) {
                    return false;
                }
            }
        }
        return true;
    }
    public void placeShip(int x, int y, int size, char direction) {

            for (int i = 0; i < size; i++) {
                if (direction == 'H') {
                            board.changeCell(x,y + i, 'S');
                } else if (direction == 'V') {
                    board.changeCell(x + i, y, 'S');
                }
            }
            shipsRemaining++;

    }


    public boolean shoot(Player opponent, int x, int y) {
        if (opponent.getBoard().isCellHaveNoShip(x, y)) {
            System.out.println("Miss!");
            opponent.getBoard().changeCell(x, y, 'O');
            return false;
        } else {
            System.out.println("Hit!");
            opponent.getBoard().changeCell(x, y, 'X');
            opponent.shipsRemaining--;
            return true;
        }
    }
}
