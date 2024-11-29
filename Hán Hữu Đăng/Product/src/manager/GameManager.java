package manager;

import model.Player;
import util.ScreenUtil;

import java.util.Scanner;

public class GameManager {
    private final Player playerOne;
    private final Player playerTwo;
    private final PlayerManager playerOneManager;
    private final PlayerManager playerTwoManager;
    private PlayerManager currentPlayerManager;
    private final Scanner scanner;

    public GameManager() {
        scanner = new Scanner(System.in);
        playerOne = new Player("Player 1");
        playerTwo = new Player("Player 2");
        playerOneManager = new PlayerManager(playerOne);
        playerTwoManager = new PlayerManager(playerTwo);
        currentPlayerManager = playerOneManager;
    }

    public void start() {
        System.out.println("\033[31m███████╗███████╗ █████╗     ██████╗  █████╗ ████████╗████████╗██╗     ███████╗");
        System.out.println("\033[31m██╔════╝██╔════╝██╔══██╗    ██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║     ██╔════╝");
        System.out.println("\033[32m███████╗█████╗  ███████║    ██████╔╝███████║   ██║      ██║   ██║     █████╗  ");
        System.out.println("\033[32m╚════██║██╔══╝  ██╔══██║    ██╔══██╗██╔══██║   ██║      ██║   ██║     ██╔══╝  ");
        System.out.println("\033[33m███████║███████╗██║  ██║    ██████╔╝██║  ██║   ██║      ██║   ███████╗███████╗");
        System.out.println("\033[33m╚══════╝╚══════╝╚═╝  ╚═╝    ╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝╚══════╝");
        System.out.println("\u001B[32mWelcome to Sea Battle!\u001B[0m");
        setupPhase(playerOneManager);
        ScreenUtil.waitForEnter();
        ScreenUtil.clearScreen();
        setupPhase(playerTwoManager);
        playGame();
    }

    private void setupPhase(PlayerManager playerManager) {
        System.out.println("\u001B[32m" + playerManager.getPlayer().getName() + ", place your ships on the board.\u001B[0m");
        playerManager.placeShips(scanner);
    }

    private void playGame() {
        while (true) {
            ScreenUtil.waitForEnter();
            ScreenUtil.clearScreen();
            System.out.println();
            System.out.println("\u001B[32m" + currentPlayerManager.getPlayer().getName() + "'s turn.\u001B[0m");
            currentPlayerManager.takeTurn(scanner, getOpponentManager());
            if (getOpponentManager().hasLost()) {
                System.out.println("\u001B[32m" + currentPlayerManager.getPlayer().getName() + " wins!\u001B[0m");
                break;
            }
            switchPlayer();
        }
    }

    private PlayerManager getOpponentManager() {
        return currentPlayerManager == playerOneManager ? playerTwoManager : playerOneManager;
    }

    private void switchPlayer() {
        currentPlayerManager = getOpponentManager();
    }
}