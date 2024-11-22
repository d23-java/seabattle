import java.util.Scanner;

public class GameView {

    static Scanner scanner = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN ="\u001B[32m";
    public static final String ANSI_RED ="\u001B[31m";

    static String gameMenu = String.format(
            "%s███████╗███████╗ █████╗ ██████╗  █████╗ ████████╗████████╗██╗     ███████╗%n" +
            "██╔════╝██╔════╝██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║     ██╔════╝%n" +
            "███████╗█████╗  ███████║██████╔╝███████║   ██║      ██║   ██║     █████╗  %n" +
            "╚════██║██╔══╝  ██╔══██║██╔══██╗██╔══██║   ██║      ██║   ██║     ██╔══╝  %n" +
            "███████║███████╗██║  ██║██████╔╝██║  ██║   ██║      ██║   ███████╗███████╗%n" +
            "╚══════╝╚══════╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝╚══════╝ %n %s",ANSI_BLUE,ANSI_RESET )

            + String.format("                  _%n" +
            "                    | %n" +
            "                     '.|%n" +
            "     _-   _-    _-  _-||    _-    _-  _-   _-    _-    _-%n" +
            "       _-    _-   - __||___    _-       _-    _-    _-%n" +
            "    _-   _-    _-  |   _   |       _-   _-    _-%n" +
            "      _-    _-    /_) (_) (_\\        _-    _-       _-%n" +
            "              _.-'           `-._      ________       _-%n" +
            "        _..--`                   `-..'       .'%n" +
            "    _.-'  o/o                     o/o`-..__.'           ~  ~%n" +
            " .-'      o|o                     o|o      `.._.  //    ~  ~%n" +
            " `-._     o|o                     o|o        |||<|||   ~  ~%n" +
            "     `-.__o|o                     o|o       .'-'  \\\\   ~  ~%n" +
            "          `-.________________________...-``'.          ~  ~%n" +
            "                                    `._______`. %n %n")
            + String.format("%s 1. Start game - Two player mode %n" +
            "%s 2. Exit game %n %s", ANSI_GREEN, ANSI_RED, ANSI_RESET);

    public static int getActionFromUser() {
        int inputFromUser;
        while (true){
            try {
                inputFromUser = Integer.parseInt(scanner.nextLine().strip());
                return inputFromUser;
            } catch (NumberFormatException e) {
                System.out.println("Invalid option..... Try again");;
            }
        }
    }


    public static Coord getCoordFromPlayer() {
        System.out.print("Enter row A -> J: ");
        char row =  scanner.next().charAt(0);
        System.out.print("Enter col 1 -> 10: ");
        char col = scanner.next().charAt(0);
        return new Coord((int)(row - 'A' + 1), (int)(col - '0' + 1));
    }
    public static char getPlaceShipDirection(){
        return scanner.next().strip().toLowerCase().charAt(0);
    }

    public static void displayGameMenu() {
        System.out.println(gameMenu);
    }

    public static void displayStartMenu() {

    }

    public static void displayPlayerStatus(Player player) {
        System.out.println("Player status for: " + player.getBoard());
    }

    public static void displayBoard(Board board) {

    }

    public static void displayFireResult(boolean hit) {
        System.out.println(hit ? "Hit!" : "Miss!");
    }
    public static void disPlayGameMap(){
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for(int rowIndex = 1; rowIndex <= 10; rowIndex++){
            char rowChar = (char)('A' + rowIndex - 1);
            System.out.print(rowChar + " ");;
            for(int colIndex = 1; colIndex <= 10; colIndex++){
                System.out.print("~ ");
            }
            System.out.println();
        }
    }
    public static void displayGameResult(Player winner) {
        System.out.println("Game over! Winner: " + winner);
    }

}
