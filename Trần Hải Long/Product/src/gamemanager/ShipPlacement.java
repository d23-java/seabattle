package gamemanager;

import enums.Orientation;
import game.Cell;
import game.Player;
import game.Ship;

import java.util.Random;

public class ShipPlacement {
    private static final Random random = new Random();
    public void placeShips(Player player, BoardController boardController) {
        for (Ship ship : player.getShips()) {
            boolean placed = false;
            while (!placed) {
                char x = (char) ('A'+random.nextInt(player.getBoard().getSize()));
                int y = random.nextInt(player.getBoard().getSize()) + 1;
                Orientation orientation = random.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Cell startCell = player.getBoard().getCell(x, y);
                placed = boardController.placeShip(ship, startCell, orientation);
            }
        }
    }
}
