package main;

import entities.Player;
import entities.Board;
import system.ClearScreen;
import ui.TextScreen;

import static utilz.Constants.gameConstants.BOARD_SIZE;
import static utilz.Constants.textConstants.*;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean isGameOver = false;
    private TextScreen textScreen;
    private Board board;

    private static String[][] player1Board = new String[BOARD_SIZE][BOARD_SIZE];
    private static String[][] player2Board = new String[BOARD_SIZE][BOARD_SIZE];
    private static String[][] player1Fog = new String[BOARD_SIZE][BOARD_SIZE];
    private static String[][] player2Fog = new String[BOARD_SIZE][BOARD_SIZE];

    public Game() {
        player1 = new Player();
        player2 = new Player();
        currentPlayer = player1;
        textScreen = new TextScreen(this);
        board = new Board(this);

        textScreen.gameTitle();
        textScreen.beginningMenu();

        board.initializeBoards(player1Board);
        board.initializeBoards(player2Board);
        board.initializeBoards(player1Fog);
        board.initializeBoards(player2Fog);

        ClearScreen.clearConsole();
        System.out.println("Player " + currentPlayer + " prepare turn");
        System.out.println();
        TextScreen.printBoard(player1Board,currentPlayer.getName());
        board.setupShips(player1Board, textScreen, currentPlayer);

        currentPlayer = player2;
        ClearScreen.clearConsole();
        System.out.println("Player " + currentPlayer + " prepare turn");
        System.out.println();
        TextScreen.printBoard(player2Board,currentPlayer.getName());
        board.setupShips(player2Board, textScreen, currentPlayer);

        currentPlayer = player1;
        switchPlayer();

    }

    private void switchPlayer() {
        while(true) {
            if(currentPlayer == player1) {
                ClearScreen.clearConsole();
                System.out.println(player1.getName() + " turn");
                if(board.playTurn(player1Fog, player2Board, currentPlayer)) {
                    System.out.println();
                    System.out.println(GREEN_BACKGROUND + player1.getName() + " win !!!" + RESET);
                    break;
                }

                if(board.getContinueTurn())
                    currentPlayer = player2;
                else
                    currentPlayer = player1;

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                ClearScreen.clearConsole();
                System.out.println(player2.getName() + " turn");
                if(board.playTurn(player2Fog, player1Board, currentPlayer)) {
                    System.out.println(GREEN_BACKGROUND + player2.getName() + " win !!!" + RESET);
                    break;
                }

                if(board.getContinueTurn())
                    currentPlayer = player1;
                else
                    currentPlayer = player2;

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
