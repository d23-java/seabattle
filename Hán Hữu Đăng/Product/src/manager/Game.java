package manager;

import util.ScreenUtil;

import java.util.Scanner;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentPlayer = player1;
    }

    public void start() {
        System.out.println("\033[31m███████╗███████╗ █████╗     ██████╗  █████╗ ████████╗████████╗██╗     ███████╗");
        System.out.println("\033[31m██╔════╝██╔════╝██╔══██╗    ██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║     ██╔════╝");
        System.out.println("\033[32m███████╗█████╗  ███████║    ██████╔╝███████║   ██║      ██║   ██║     █████╗  ");
        System.out.println("\033[32m╚════██║██╔══╝  ██╔══██║    ██╔══██╗██╔══██║   ██║      ██║   ██║     ██╔══╝  ");
        System.out.println("\033[33m███████║███████╗██║  ██║    ██████╔╝██║  ██║   ██║      ██║   ███████╗███████╗");
        System.out.println("\033[33m╚══════╝╚══════╝╚═╝  ╚═╝    ╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝╚══════╝");
        System.out.println("\u001B[32mWelcome to Sea Battle!\u001B[0m");
        setupPhase(player1);
        ScreenUtil.waitForEnter();
        ScreenUtil.clearScreen();
        setupPhase(player2);
        playGame();
    }

    private void setupPhase(Player player) {
        System.out.println("\u001B[32m" + player.getName() + ", place your ships on the board.\u001B[0m");
        player.placeShips(scanner);
    }

    private void playGame() {
        while (true) {
            ScreenUtil.waitForEnter();
            ScreenUtil.clearScreen();
            System.out.println();
            System.out.println("\u001B[32m" + currentPlayer.getName() + "'s turn.\u001B[0m");
            currentPlayer.takeTurn(scanner, getOpponent());
            if (getOpponent().hasLost()) {
                System.out.println("\u001B[32m" + currentPlayer.getName() + " wins!\u001B[0m");
                break;
            }
            switchPlayer();
        }
    }

    private Player getOpponent() {
        return currentPlayer == player1 ? player2 : player1;
    }

    private void switchPlayer() {
        currentPlayer = getOpponent();
    }
}