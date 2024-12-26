package items;

import game.Board;
import game.Cell;
import game.Menu;
import game.Player;
import gamemanager.BoardController;

import java.io.Serializable;

public class Bomb extends Item implements Serializable {
    private static final long serialVersionUID = 1L;
    public Bomb() {
        super("Bomb 2x2");
    }

    @Override
    public void activate(Player player, BoardController opponentBoardController, char x, int y) {
        for (char i = x; i < x + 2; i++) {
            for (int j = y; j < y + 2; j++) {
                Board opponentBoard = opponentBoardController.getBoard();
                if (opponentBoard.isCoordinateValid(i, j)) {
                    Cell target = opponentBoard.getCell(i, j);
                    opponentBoardController.fireAt(target);
                }
            }
        }
        Menu.explosion();
    }
}
