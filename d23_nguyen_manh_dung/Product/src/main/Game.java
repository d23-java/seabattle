package classes;

import static utilz.Constants.textConstants.*;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int gameBoardSize = 10;
    private boolean isGameOver = false;

    public Game() {
        player1 = new Player();
        player2 = new Player();
        currentPlayer = player1;
        gameTitle();
        beginningMenu();
    }

    private void gameTitle() {
        String seeBattleArt = """
                %s ████████ ███████  █████      ██████   █████  ████████ ████████ ██      ███████ %s
                %s ██       ██      ██   ██     ██   ██ ██   ██    ██       ██    ██      ██      %s
                %s ████████ █████   ███████     ██████  ███████    ██       ██    ██      █████   %s
                %s       ██ ██      ██   ██     ██   ██ ██   ██    ██       ██    ██      ██      %s
                %s ████████ ███████ ██   ██     ██████  ██   ██    ██       ██    ███████ ███████ %s
                """;
        System.out.printf(seeBattleArt, RED_TEXT + BOLD, RESET, YELLOW_TEXT, RESET, GREEN_TEXT, RESET, CYAN_TEXT, RESET, BLUE_TEXT, RESET);

    }

    private void beginningMenu() {
        System.out.println();
        System.out.println(BLUE_BACKGROUND + "Made by DungNguyen" + RESET);
        System.out.println("Welcome to my game!");
        System.out.println();
        System.out.println("---MENU---");
        System.out.println("1. Play");
        System.out.println("2. Quit");
    }
}
