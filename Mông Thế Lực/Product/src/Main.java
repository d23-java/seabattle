import Controller.GameController;
import Controller.GameStateController;

public class Main {
    public static void main(String[] args) {
        GameStateController gameStateController = new GameStateController();
        GameController gameController = new GameController(gameStateController);
        gameController.startGame();
    }
}