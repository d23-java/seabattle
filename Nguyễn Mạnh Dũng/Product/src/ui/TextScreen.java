package ui;

import system.ClearScreen;
import system.InputManager;
import main.Game;

import java.util.Scanner;

import static utilz.Constants.gameConstants.*;
import static utilz.Constants.textConstants.*;

public class TextScreen {

    private Game game;

    public TextScreen(Game game) {
        this.game = game;
    }

    public void gameTitle() {
        String seeBattleArt = """
                %s ████████ ███████  █████      ██████   █████  ████████ ████████ ██      ███████ %s
                %s ██       ██      ██   ██     ██   ██ ██   ██    ██       ██    ██      ██      %s
                %s ████████ █████   ███████     ██████  ███████    ██       ██    ██      █████   %s
                %s       ██ ██      ██   ██     ██   ██ ██   ██    ██       ██    ██      ██      %s
                %s ████████ ███████ ██   ██     ██████  ██   ██    ██       ██    ███████ ███████ %s
                """;
        System.out.printf(seeBattleArt, RED_TEXT + BOLD, RESET, YELLOW_TEXT, RESET, GREEN_TEXT, RESET, CYAN_TEXT, RESET, BLUE_TEXT, RESET);
        System.out.println();
        System.out.println(BLUE_BACKGROUND + "Made by DungNguyen" + RESET);
        System.out.println();
    }

    public void beginningMenu() {
        System.out.println("Welcome to my game!");
        System.out.println();
        System.out.println("---MENU---");
        System.out.println("1. Play");
        System.out.println("2. Quit");
        System.out.print("Enter your choice: ");
        Scanner sc = InputManager.getScanner();
        int choice = sc.nextInt();
        ClearScreen.clearConsole();
        switch (choice) {
            case 1:
                enterInfo();
                break;
            case 2:
                TextEffect.printWithEffect("Good bye !");
                game.setGameOver(true);
                return;
        }
    }

    public static void printBoard(String[][] board, String currentPlayer) {
        System.out.print(WHITE_BACKGROUND + "    " + RESET);
        int n = 1;
        for(int i = 0; i < board.length; i++) {
            if(n < 10)
                System.out.print(WHITE_BACKGROUND + "|_" + n++ + "_" + RESET);
            else
                System.out.print(WHITE_BACKGROUND + "|_" + n++ + RESET);
        }
        System.out.print(WHITE_BACKGROUND + "|" + RESET);
        System.out.println();
        char k = 'a';
        for(int i = 0; i < board.length; i++) {
            System.out.print(WHITE_BACKGROUND + "|_" + k++ + "_|" + RESET);
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("REMEMBER !!!");
        System.out.println(PATROL_BOAT + ": Patrol Boat (1x2)");
        System.out.println(DESTROYER_BOAT + ": Destroyer Boat (1x4)");
        System.out.println(SUBMARINE + ": Submarine (1x3)");
        System.out.println(BATTLE_SHIP + ": Battle Ship (1x5)");
        System.out.println(SHOTTED_CELL + ": Cell has been miss");
        System.out.println(DESTROYER_CELL + ": Cell has been destroyed");
    }

    private void enterInfo() {
        Scanner sc = InputManager.getScanner();
        System.out.print("Enter player 1 name: ");
        String player1Name = sc.next();
        ClearScreen.clearConsole();
        System.out.print("Enter player 2 name: ");
        String player2Name = sc.next();
        ClearScreen.clearConsole();
        game.getPlayer1().setName(player1Name);
        game.getPlayer2().setName(player2Name);
    }

}
