package user;

import data.Ship;
import login.Menu;
import manager.GameBoard;

public class Player {
    private String name;
    private GameBoard board = new GameBoard(10);
    private GameBoard enemyBoard = new GameBoard(10);
    Menu menu;
    public Player(String name) {
        this.name = name;
    };
    String[][] grid = board.getGrid();
    public void putShip(Ship ship, int locationX, int locationY, String direction) {
        if(direction.equals("H")) {
            for (int i = 0; i < ship.getSize(); i++) {
                grid[i+locationX][locationY] = "S";
            }
        } else if (direction.equals("V")) {
            for (int i = 0; i < ship.getSize(); i++) {
                grid[locationX][locationY + i] = "S";
            }
        }
    }

    public GameBoard getBoard() {
        return board;
    }

    public Player(String name, GameBoard board) {
        this.name = name;
        this.board = board;
    }

    public void attack(int x, int y){
        enemyBoard.markShot(x,y);
        if(enemyBoard.haveShip(x,y)) menu.showBoard(enemyBoard);
    }
}
