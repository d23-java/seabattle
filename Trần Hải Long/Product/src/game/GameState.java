package game;

import java.io.Serial;
import java.io.Serializable;

public record GameState(Player player1, Player player2, int currentTurn, boolean itemEnabled) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

}
