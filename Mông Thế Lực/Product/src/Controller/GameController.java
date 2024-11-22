// GameController.java
public class GameController {
    private final GameStateController gameStateController;
    public GameController(GameStateController gameStateController) {
        this.gameStateController = gameStateController;
    }

    public void startGame() {
        GameView.displayGameMenu();
        if(GameView.getActionFromUser() == 1) startPhase();
    }

    public void startPhase() {
        GameView.disPlayGameMap();
    }

    public void setupPhase() {
        gameStateController.getCurrentPlayer().placeShip();
        gameStateController.switchTurn();
        gameStateController.getCurrentPlayer().placeShip();
    }
}
