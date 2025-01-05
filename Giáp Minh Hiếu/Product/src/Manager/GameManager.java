package Manager;
import Body.Player;
import Menu.Menu;
import java.util.Scanner;
import Console.Console;
import Ranking.LeaderBoard;

public class GameManager {
    public static int boardSize;
    private Player firstPlayer;
    private Player secondPlayer;
    public static Scanner scanner = new Scanner(System.in);
    public static int hasShip = 5;
    public static boolean endGame = false;
    private boolean isFirstPlayerTurn = true;
    public Menu menu = new Menu();
    public Console console = new Console();
    private LeaderBoard leaderBoard;
    public GameManager() {
        leaderBoard = new LeaderBoard();
    }
    public void setUpGame() {
        while (true) {
            endGame = false;
            menu.displayMenuStart();
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 3) {
                System.out.println("Successfully Exit!!!");
                return;
            }
            if (choice == 2) {
                leaderBoard.displayLeaderBoard();
                continue;
            }
            System.out.println("Enter the size of the board: ");
            boardSize = Integer.parseInt(scanner.nextLine());
            menu.newGame();
            String name = scanner.nextLine();
            firstPlayer = new Player(name, 0, 5);
            menu.kindShip();
            int choice1 = Integer.parseInt(scanner.nextLine());
            if (choice1 == 1) {
                firstPlayer.myBoard.placeShip();
            }
            else {
                firstPlayer.myBoard.placeRandomShip();
            }
            menu.newGame();
            name = scanner.nextLine();
            secondPlayer = new Player(name, 0, 5);
            menu.kindShip();
            int choice2 = Integer.parseInt(scanner.nextLine());
            if (choice2 == 1) {
                secondPlayer.myBoard.placeShip();
            }
            else {
                secondPlayer.myBoard.placeRandomShip();
            }
            loadGame();
        }
    }

    public void loadCurrentGame(Player player) {
        System.out.println(player.name + "'s turn");
        System.out.println("Hits: " + player.hits);
        System.out.println("Destroyed: " + player.destroyedShip);
        System.out.println("Alive: " + player.aliveShip);
        System.out.println("Enter your choice: ");
        System.out.println("1. Show my board ");
        System.out.println("2. Shot ");
        System.out.println("3. End turn");
    }
    public void loadGame() {
        while (!endGame) {
            if (isFirstPlayerTurn) {
                loadCurrentGame(firstPlayer);
                isFirstPlayerTurn = false;
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        firstPlayer.displayMyBoard();
                    case 2:
                        firstPlayer.Shot(secondPlayer);
                        break;
                    default:
                        break;
                }
            }
            else {
                loadCurrentGame(secondPlayer);
                isFirstPlayerTurn = true;
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        secondPlayer.displayMyBoard();
                    case 2:
                        secondPlayer.Shot(firstPlayer);
                        break;
                    default:
                        break;
                }
            }
            console.clearConsole();
        }
        if (!isFirstPlayerTurn) {
            leaderBoard.addPlayer(firstPlayer);
        }
        else {
            leaderBoard.addPlayer(secondPlayer);
        }
    }
}
