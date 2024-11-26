package manager;

import data.Ship;
import login.Menu;
import main.Scan;
import user.Player;

public class PlayerManager {
    public void putShip(Player player, Ship ship, int locationX, int locationY, String direction) {
        direction = direction.toLowerCase();
        if(direction.equals("h")) {
            for (int i = 0; i < ship.getSize(); i++) {
                player.getBoard().getGrid()[i+locationX][locationY] = "S";
                player.getBoard().setTotalPositions(player.getBoard().getTotalPositions() + 1);
            }
        } else if (direction.equals("v")) {
            for (int i = 0  ; i < ship.getSize(); i++) {
                player.getBoard().getGrid()[locationX][locationY + i] = "S";
                player.getBoard().setTotalPositions(player.getBoard().getTotalPositions() + 1);
            }
        }
    }
    public boolean haveShip(GameBoard board,int x,int y){
        if (board.getGrid()[x][y].equals("S")) return true;
        return false;
    }
    public void markShot(Player firstPlayer, Player secondPlayer, int x, int y) {
        if(haveShip(secondPlayer.getBoard(),x,y)) firstPlayer.getEnemyBoard().getGrid()[x][y] = "X";
        else firstPlayer.getEnemyBoard().getGrid()[x][y] = "O";
    }
    public void attack(Player you, Player opponent, int x, int y){
        markShot(you,opponent,x,y);
        if(haveShip(opponent.getBoard(),x,y)) {
            System.out.println("You have hit the enemy's boat");
            Menu.showBoard(you.getEnemyBoard());
        }
    }

}
