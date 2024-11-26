package Controller;

import Model.Player;
import View.GameView;

public class GameStateController {
    private final Player firstPlayer;
    private final Player secondPlayer;
    private int currentTurn;

    public GameStateController() {
        this.firstPlayer = new Player("Player 1");
        this.secondPlayer = new Player("Player 2");
        this.currentTurn = 1;
    }
    public Player getCurrentPlayer() {
        return currentTurn == 1 ? firstPlayer : secondPlayer;
    }

    public Player getOpponent() {
        return currentTurn == 1 ? secondPlayer : firstPlayer;
    }

    public boolean isGameOver() {
        boolean isOver = firstPlayer.isLoose()  || secondPlayer.isLoose();
        if(isOver) GameView.displayGameResult(firstPlayer, secondPlayer);
        return isOver;
    }

    public void switchTurn() {
        currentTurn = currentTurn == 1 ? 2 : 1;
    }

}