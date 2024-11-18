package entities;

import main.Game;

import static utilz.Constants.gameConstants.BOARD_SIZE;
import static utilz.Constants.gameConstants.EMPTY_BOAT;

public class Ship {

    Game game;

    public Ship(Game game) {
        this.game = game;
    }

    private static void initializeBoards(String[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_BOAT;
            }
        }
    }
}
