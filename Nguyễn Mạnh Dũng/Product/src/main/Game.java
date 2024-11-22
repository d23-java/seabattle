package main;

import entities.Bot;
import entities.Player;
import entities.Board;
import system.Audio;
import system.ClearScreen;
import ui.TextEffect;
import ui.TextScreen;
import utilz.Constants;
import utilz.LoadSave;

import static utilz.Constants.textConstants.*;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean logout = false;
    private TextScreen textScreen;
    private Board board;

    public static int boardSize;

    public Game() {
        Audio.playSound(Constants.audioConstants.MAIN_SOUNDTRACK);
        player1 = new Player();
        player2 = new Player();
        currentPlayer = player1;
        textScreen = new TextScreen(this);
        board = new Board(this);

        while(true) {

            textScreen.gameTitle();
            if(textScreen.beginningMenu()) {
                ClearScreen.clearConsole();
                continue;
            }

            if(logout) {
                break;
            }

            player1.initBoards(Game.boardSize);
            player2.initBoards(Game.boardSize);
            player1.initializeBoards(player1.getPlayerBoard());
            player2.initializeBoards(player2.getPlayerBoard());
            player1.initializeBoards(player1.getPlayerFog());
            player2.initializeBoards(player2.getPlayerFog());

            textScreen.prepareChoice();

            currentPlayer = player1;

//            TextScreen.printBoard(player1.getPlayerBoard(), player1.getName());
            TextScreen.printBoard(player2.getPlayerBoard());
//        TextScreen.printBoard(player1.getPlayerFog(), player2.getName());
//        TextScreen.printBoard(player2.getPlayerFog(), player2.getName());

            startGame();

            if(!textScreen.playAgain())
                break;
        }

    }

    private void startGame() {
        while(true) {
            ClearScreen.clearConsole();
            if(currentPlayer == player1) {
                System.out.println(player1.getName() + " turn");
                if(board.playTurn(player2.getPlayerFog(), player2.getPlayerBoard(), currentPlayer)) {
                    player1.setScore(currentPlayer.getScore() + 1);
                    player1.setShipLeft(board.checkShipLeft(player1.getPlayerBoard()));
                    LoadSave.savePlayer(player1);
                    System.out.println();
                    System.out.println(GREEN_BACKGROUND + player1.getName() + " win !!!" + RESET);
                    System.out.println();
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
                System.out.println(player2.getName() + " turn");
                if(!player2.getName().equals("DungNguyen")) {
                    if(board.playTurn(player1.getPlayerFog(), player1.getPlayerBoard(), currentPlayer)) {
                        player2.setScore(currentPlayer.getScore() + 1);
                        player2.setShipLeft(board.checkShipLeft(player2.getPlayerBoard()));
                        LoadSave.savePlayer(player2);
                        System.out.println();
                        System.out.println(GREEN_BACKGROUND + player2.getName() + " win !!!" + RESET);
                        System.out.println();
                        break;
                    }
                }

                else {
                    System.out.println("OK let me think");
                    TextEffect.printWithEffect(".....");
                    Bot.botPlay(player1.getPlayerBoard());

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

    public boolean isLogout() {
        return logout;
    }

    public void setLogout(boolean logout) {
        this.logout = logout;
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public TextScreen getTextScreen() {
        return textScreen;
    }

    public void setTextScreen(TextScreen textScreen) {
        this.textScreen = textScreen;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
