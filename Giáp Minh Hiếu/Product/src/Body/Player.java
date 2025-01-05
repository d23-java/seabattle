package Body;
import Manager.GameManager;

import static Body.Board.EMPTY;
import static Body.Board.MISS;
import static Manager.GameManager.boardSize;

public class Player {
    public Board enemyBoard = new Board();
    public Board myBoard = new Board();
    public int aliveShip = GameManager.hasShip;
    public int destroyedShip = 0;
    public int hits = 0;
    public String name;
    public Player(String name, int hits, int aliveShip) {
        this.name = name;
        this.hits = hits;
        this.aliveShip = aliveShip;
    };

    public void displayMyBoard() {
        myBoard.display();
    }
    public void displayEnemyBoard() {
        enemyBoard.display();
    }

    public boolean checkWon(Player enemy) {
        return enemy.myBoard.ships.isEmpty();
    }

    public boolean checkShot(Board board, int x, int y) {
        if (x < 0 || y < 0 || x > boardSize || y > boardSize) {
            return false;
        }
        return board.getGrid()[x][y] == EMPTY;
    }
    public void Shot(Player enemy) {
        ++hits;
        displayEnemyBoard();
        System.out.println("Enter the coordinate you want to shot: ");
        String xChar;
        int x, y;
        while (true) {
            xChar = GameManager.scanner.nextLine();
            y = Integer.parseInt(xChar.substring(1)) - 1;
            x = xChar.charAt(0) - 'A';
            if (checkShot(enemyBoard, x, y)) {
                break;
            }
            System.out.println("You shot this before or Out of Board! Please try again.");
        }
        if (enemy.myBoard.getGrid()[x][y] == EMPTY) {
            System.out.println("Not exactly!");
            enemy.myBoard.getGrid()[x][y] = MISS;
            enemyBoard.getGrid()[x][y] = MISS;
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
    public int getHits() {
        return hits;
    }

    public int getAliveShip() {
        return aliveShip;
    }

    public String getName() {
        return name;
    }
}