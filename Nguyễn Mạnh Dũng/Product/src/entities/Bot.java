package entities;

import main.Game;
import system.RandomCase;

import static utilz.Constants.gameConstants.*;

public class Bot {
    private Board board;

    public static void botPlay(String[][] opponentBoard) {
        int i = RandomCase.randomNumber(1,Game.boardSize);
        int j = RandomCase.randomNumber(1,Game.boardSize);
        String cellName = opponentBoard[i][j];
        if(!opponentBoard[i][j].equals(EMPTY_BOAT) || !opponentBoard[j][i].equals(DESTROYER_CELL) || !opponentBoard[i][j].equals(MISS_CELL)) {
            opponentBoard[i][j] = DESTROYER_CELL;

            System.out.println("Yay bull's eye");
        }
        else {
            System.out.println("OMG I'm miss");
        }
    }

    private void findCell(String[][] opponentBoard, String cellName) {

    }
}
