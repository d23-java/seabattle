package entities;

import main.Game;
import system.RandomCase;
import ui.TextScreen;

import java.util.HashSet;
import java.util.Set;

import static utilz.Constants.gameConstants.*;

public class Bot {
    private Board board;
    private int i;
    private int j;
    private final int[] dx = new int[]{-1, 0, 1, 0};
    private final int[] dy = new int[]{0, -1, 0, 1};
    private boolean greedy = false;
    private Set<String> visitedPoints = new HashSet<>();

    public Bot(Board board) {
        this.board = board;
    }

    public boolean botPlay(String[][] opponentBoard) {
        do {
            if(!greedy) {
                i = RandomCase.randomNumber(0,Game.boardSize-1);
                j = RandomCase.randomNumber(0,Game.boardSize-1);
            }
        } while(visitedPoints.contains(i + "," + j));

        visitedPoints.add(i + "," + j);

        System.out.println("I will make move " + (char)('A' + i) + String.valueOf(j+1));

//        TextScreen.printBoard(opponentBoard);

        if(!opponentBoard[i][j].equals(EMPTY_BOAT) && !opponentBoard[j][i].equals(DESTROYER_CELL) && !opponentBoard[i][j].equals(MISS_CELL)) {
            opponentBoard[i][j] = DESTROYER_CELL;
            System.out.println("Yay bull's eye");
            greedy = true;
            findCell(opponentBoard,opponentBoard[i][j]);
        }
        else {
            System.out.println("OMG I'm miss");
            greedy = false;
        }

        board.setSwitchTurn(true);

        return board.checkWin(opponentBoard);
    }

    private void findCell(String[][] opponentBoard, String cell) {
        for(int k = 0; k < 4; k++) {
            int i1 = i + dx[k];
            int j1 = j + dy[k];
            if (i1 >= 0 && i1 < Game.boardSize && j1 >= 0 && j1 < Game.boardSize && !visitedPoints.contains(i1 + "," + j1)) {
                if(opponentBoard[i1][j1].equals(cell)) {
                    i = i1;
                    j = j1;
                    visitedPoints.add(i1 + "," + j1);
                    greedy = true;
                    return;
                }
            }
        }
        greedy = false;
    }
}
