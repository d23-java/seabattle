package items;

import game.Cell;
import game.Player;
import gamemanager.BoardController;

import java.io.Serializable;

public class Shield extends Item implements Serializable {
    private static final long serialVersionUID = 1L;
    public Shield() {
        super("Shield");
    }

    @Override
    public void activate(Player player, BoardController boardController, char x, int y) {
        Cell cell = player.getBoard().getCell(x, y);
        if (cell != null && cell.getShip() != null) {
            cell.getShip().addShield();
        }
    }
}
