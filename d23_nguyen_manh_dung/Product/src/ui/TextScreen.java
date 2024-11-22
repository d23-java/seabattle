package ui;

import entities.Player;
import system.ClearScreen;
import system.InputManager;
import main.Game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static utilz.Constants.gameConstants.*;
import static utilz.Constants.textConstants.*;
import static utilz.LoadSave.loadLeaderboard;

public class TextScreen {
    Scanner sc = InputManager.getScanner();
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

    public boolean beginningMenu() {
        System.out.println("Welcome to my game!");
        System.out.println();
        System.out.println("---MENU---");
        System.out.println("1. Play");
        System.out.println("2. Leaderboard");
        System.out.println("3. Quit");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        ClearScreen.clearConsole();
        switch (choice) {
            case 1:
                enterInfo();
                break;
            case 2:
                displayLeaderboard();
                System.out.println();
                System.out.println("return ???");
                System.out.println("1. YES");
                System.out.print("Enter your choice: ");
                int choice2 = sc.nextInt();
                switch (choice2) {
                    case 1:
                        return true;
                }
                break;
            case 3:
                TextEffect.printWithEffect("Good bye !!!");
                game.setLogout(true);
                break;
        }
        return false;
    }

    public void prepareChoice() {
        choiceGamePlay();
        int choice1 = sc.nextInt();
        switch (choice1) {
            case 1:
                prepareTurnManual();
                break;
            case 2:
                prepareTurnAuto();
                break;
        }
    }

    public static void printBoard(String[][] board) {
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
        System.out.println(MISS_CELL + ": Cell has been miss");
        System.out.println(DESTROYER_CELL + ": Cell has been destroyed");
    }

    private void enterInfo() {
        Scanner sc = InputManager.getScanner();
        System.out.print("Enter board size (5 to 20): ");
        Game.boardSize = sc.nextInt();
        ClearScreen.clearConsole();
        System.out.println("Play with ???");
        System.out.println("1. friend");
        System.out.println("2. me");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        ClearScreen.clearConsole();
        switch (choice) {
            case 1:

                System.out.print("Enter player 1 name (Don't use DungNguyen because that's my name): ");
                String player1Name = sc.next();
                ClearScreen.clearConsole();
                System.out.print("Enter player 2 name (Don't use DungNguyen because that's my name): ");
                String player2Name = sc.next();
                ClearScreen.clearConsole();
                game.getPlayer1().setName(player1Name);
                game.getPlayer2().setName(player2Name);
                break;
            case 2:
                System.out.print("Enter your name (Don't use DungNguyen because that's my name): ");
                String name = sc.next();
                ClearScreen.clearConsole();
                game.getPlayer1().setName(name);
                game.getPlayer2().setName("DungNguyen");
                break;
        }

    }

    private void choiceGamePlay() {
        System.out.println("You want to:");
        System.out.println("1. Setup ship manual");
        System.out.println("2. Setup ship auto");
    }

    private void prepareTurnManual() {
        ClearScreen.clearConsole();
        System.out.println("Player " + game.getCurrentPlayer() + " prepare turn");
        System.out.println();
        TextScreen.printBoard(game.getPlayer1().getPlayerBoard());
        game.getBoard().setupShips(game.getPlayer1().getPlayerBoard(), game.getTextScreen(), game.getCurrentPlayer());

        game.setCurrentPlayer(game.getPlayer2());
        ClearScreen.clearConsole();
        System.out.println("Player " + game.getCurrentPlayer() + " prepare turn");
        System.out.println();
        TextScreen.printBoard(game.getPlayer2().getPlayerBoard());
        game.getBoard().setupShips(game.getPlayer2().getPlayerBoard(), game.getTextScreen(), game.getCurrentPlayer());
        game.setCurrentPlayer(game.getPlayer1());
    }

    private void prepareTurnAuto() {
        game.getBoard().randomSetupShips(game.getPlayer1().getPlayerBoard(), game.getCurrentPlayer());
        game.setCurrentPlayer(game.getPlayer2());
        game.getBoard().randomSetupShips(game.getPlayer2().getPlayerBoard(), game.getCurrentPlayer());
        game.setCurrentPlayer(game.getPlayer1());
    }

    public boolean playAgain() {
        System.out.println("Play again ???");
        System.out.println("1. YES");
        System.out.println("2. NO");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                ClearScreen.clearConsole();
                return true;
            default:
                return false;
        }
    }

    public static void displayLeaderboard() {
        ClearScreen.clearConsole();
        ArrayList<String> leaderboard = loadLeaderboard();
        System.out.println("\n--- Leaderboard ---");
        if (leaderboard.isEmpty()) {
            System.out.println("No data available.");
        }
        else {
            ArrayList<Player> players = new ArrayList<>();
            for (String entry : leaderboard) {
                String[] data = entry.split(",");
                String name = data[0];
                int score = Integer.parseInt(data[1]);
                int shipsLeft = Integer.parseInt(data[2]);
                Player player = new Player();
                player.setName(name);
                player.setScore(score);
                player.setShipLeft(shipsLeft);
                players.add(player);
            }

            players.sort(Comparator.comparingInt(Player::getScore).reversed());

            System.out.printf("%-20s|%-20s|%-20s%n", "Player", "Score", "Ships Left");
            System.out.println("------------------------------------------------------------");
            for(Player player : players) {
                System.out.printf("%-20s|%-20s|%-20s%n", player.getName(), player.getScore(), player.getShipLeft());
            }
        }
        System.out.println("------------------------------------------------------------\n");
    }
}
