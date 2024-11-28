package Body;
import Manager.GameManager;

import static Body.Board.EMPTY;
import static Body.Board.MISS;

public class Player {
    public Board enemyBoard = new Board();
    public Board myBoard = new Board();
    public int aliveShip = GameManager.hasShip;
    public int destroyedShip = 0;
    public int hits = 0;
    public String name;
    public Player() {};

    public void displayMyBoard() {
        myBoard.display();
    }
    public void displayEnemyBoard() {
        enemyBoard.display();
    }

    public boolean checkWon(Player enemy) {
        return enemy.myBoard.ships.isEmpty();
    }

    public void Shot(Player enemy) {
        ++hits;
        displayEnemyBoard();
        System.out.println("Enter the coordinate you want to shot: ");
        String xChar = GameManager.scanner.nextLine();
        int y = Integer.parseInt(xChar.substring(1)) - 1;
        int x = xChar.charAt(0) - 'A';
        if (enemy.myBoard.getGrid()[x][y] == EMPTY) {
            System.out.println("Not exactly!");
            enemy.myBoard.getGrid()[x][y] = MISS;
        }
        else {
            System.out.println("Successful shot!");
            enemy.myBoard.setGrid(x, y);
            enemyBoard.setGrid(x, y);
            if (enemy.myBoard.checkShipSunk(enemy.myBoard.getGrid())) {
                --enemy.aliveShip;
                ++destroyedShip;
            }
            System.out.println("You are given an extra shot!");
            if (checkWon(enemy)) {
                System.out.println(this.name + " won the game!!!");
                GameManager.endGame = true;
                return;
            }
            this.Shot(enemy);
        }
    }
}