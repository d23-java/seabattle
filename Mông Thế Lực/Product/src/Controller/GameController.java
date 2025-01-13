package Controller;

import Model.Player;
import View.GameView;

public class GameController {
    private final GameStateController gameStateController;
    public GameController(GameStateController gameStateController) {
        this.gameStateController = gameStateController;
    }

    public void startGame() {
        while (true) {
            GameView.displayGameMenu();
            if (GameView.getActionFromUser() == 1) startPhase();
            else return;
        }
    }
    public void setupPhase() {
        gameStateController.getCurrentPlayer().placeShip();
        gameStateController.switchTurn();
        gameStateController.getCurrentPlayer().placeShip();
        gameStateController.switchTurn();
    }
    public void startPhase() {
        GameView.disPlayGameMap();
        setupPhase();
        Player currentPlayer = new Player();
        Player opponentPlayer = new Player();
        while (!gameStateController.isGameOver()) {
            currentPlayer = gameStateController.getCurrentPlayer();
            opponentPlayer = gameStateController.getOpponent();
            boolean fired =  false;
            boolean endTurn = false;
            while (!endTurn){
                System.out.println(currentPlayer.getName() + " choose option");
                GameView.displayOptionMenu();
                int option = GameView.getActionFromUser();
                switch (option){
                    case 1:
                        if(!fired){
                            opponentPlayer.fire();
                            System.out.println("opponent board");
                            GameView.displayFoggyBoard(opponentPlayer.getBoard());
                            fired = true;
                        }else{
                            System.out.println("Already fired ... ");
                        }
                        break;
                    case 2:
                        System.out.println("player board ");
                        GameView.displayBoard(currentPlayer.getBoard());
                        System.out.println("opponent board");
                        GameView.displayFoggyBoard(opponentPlayer.getBoard());
                        break;
                    case 3:
                        gameStateController.switchTurn();
                        endTurn = true;
                        break;
                    default:
                        System.out.println("Invalid option ... Try again");
                        break;
                }
            }
        }
    }


}
