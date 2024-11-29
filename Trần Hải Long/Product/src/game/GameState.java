package game;

import java.io.Serial;
import java.io.Serializable;

public class GameState implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Player player1;
    private final Player player2;
    private final int currentTurn;
    private final boolean itemEnabled;

    public GameState(Player player1, Player player2, int currentTurn, boolean itemEnabled) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentTurn = currentTurn;
        this.itemEnabled = itemEnabled;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public boolean isItemEnabled() {
        return itemEnabled;
    }

}
