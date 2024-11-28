package Manager;
import Body.Player;
import Menu.Menu;
import java.util.Scanner;
import Console.Console;
public class GameManager {
    private Player firstPlayer = new Player();
    private Player secondPlayer = new Player();
    public static Scanner scanner = new Scanner(System.in);
    public static int hasShip = 5;
    public static boolean endGame = false;
    private boolean isFirstPlayerTurn = true;
    public Menu menu = new Menu();
    public Console console = new Console();

    public void setUpGame() {
        while (true) {
            menu.displayMenuStart();
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 2) {
                System.out.println("Successfully Exit!!!");
                return;
            }
            menu.newGame();
            firstPlayer.name = scanner.nextLine();
            firstPlayer.myBoard.placeShip();
            menu.newGame();
            secondPlayer.name = scanner.nextLine();
            secondPlayer.myBoard.placeShip();
            loadGame();
        }
    }

    public void loadCurrentGame(Player player) {
        System.out.println(player.name + "'s turn");
        System.out.println("Hits: " + player.hits);
        System.out.println("Destroyed: " + player.destroyedShip);
        System.out.println("Alive: " + player.aliveShip);
        System.out.println("Enter your choice: ");
        System.out.println("1. Show my board: ");
        System.out.println("2. Shot: ");
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
    }
}
