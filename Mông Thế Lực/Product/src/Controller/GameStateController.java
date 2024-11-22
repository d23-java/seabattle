public class GameStateController {
    private final Player player1;
    private final Player player2;
    private int currentTurn;

    public GameStateController() {
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.currentTurn = 1;
    }

    public Player getCurrentPlayer() {

        return currentTurn == 1 ? player1 : player2;
    }

    public Player getOpponent() {
        return currentTurn == 1 ? player2 : player1;
    }

    public boolean isGameOver() {
        return player1.getBoard().isAllShipsSunk() || player2.getBoard().isAllShipsSunk();
    }

    public void switchTurn() {
        currentTurn = currentTurn == 1 ? 2 : 1;
    }
}