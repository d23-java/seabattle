import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentPlayer = player1;
    }

    public void start() {
        System.out.println("Welcome to Sea Battle!");
        setupPhase(player1);
        clearScreen();
        setupPhase(player2);
        playGame();
    }

    private void setupPhase(Player player) {
        System.out.println(player.getName() + ", place your ships on the board.");
        player.placeShips(scanner);
    }

    private void playGame() {
        while (true) {
            clearScreen();
            System.out.println();
            System.out.println(currentPlayer.getName() + "'s turn.");
            currentPlayer.takeTurn(scanner, getOpponent());
            if (getOpponent().hasLost()) {
                System.out.println(currentPlayer.getName() + " wins!");
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

    private void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println("\n");
        }
    }
}