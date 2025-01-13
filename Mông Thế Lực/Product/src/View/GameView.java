package View;

import Model.Player;
import Model.Coord;

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

    static String playerOptionMenu = String.format(
      "1. Fire %n" +
      "2. Show map %n" +
      "3. End turn %n"
    );
    public static void displayOptionMenu(){
        System.out.println(playerOptionMenu);
    }
    public static int getActionFromUser() {
        int inputFromUser;
        while (true){
            try {
                inputFromUser = Integer.parseInt(scanner.nextLine().strip());
                System.out.println();
                return inputFromUser;
            } catch (NumberFormatException e) {
                System.out.println("Invalid option..... Try again");;
            }
        }
    }

    public static Coord getCoord() {
        while(true) {
            try {
                System.out.print("Enter row A -> J: ");
                char row = scanner.nextLine().toUpperCase().charAt(0);
                if(row < 'A' || row > 'J'){
                    System.out.println("invalid row ....");
                    continue;
                }
                System.out.print("Enter col 1 -> 10: ");
                int col = Integer.parseInt(scanner.nextLine());
                if(col < 1 || col > 10){
                    System.out.println("invalid column");
                    continue;
                }
                return new Coord((int) (row - 'A' + 1), col);
            }catch (NumberFormatException e){
                System.out.println("invalid coord.... ");
            }

        }
    }
    public static char getShipDirection(){
        while (true){
            System.out.print("Enter ship direction (V - Vertical, H - Horizontal) : ");
            char direction = scanner.nextLine().strip().toLowerCase().charAt(0);
            if(direction == 'h' || direction == 'v') {
                System.out.println();
                return direction;
            }
            else System.out.println("Invalid direction...");
        }

    }

    public static void displayGameMenu() {
        System.out.println(gameMenu);
    }


    public static void displayBoard(char [][] board) {
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        for(int rowIndex = 1; rowIndex <= 10; rowIndex++){
            char rowChar = (char)('A' + rowIndex - 1);
            System.out.print(rowChar + "| ");
            for(int colIndex = 1; colIndex <= 10; colIndex++){
                System.out.print(board[rowIndex][colIndex] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void displayFoggyBoard(char [][] board){
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        for(int rowIndex = 1; rowIndex <= 10; rowIndex++){
            char rowChar = (char)('A' + rowIndex - 1);
            System.out.print(rowChar + "| ");
            for(int colIndex = 1; colIndex <= 10; colIndex++){
                char info = board[rowIndex][colIndex];
                if(info != 'X' && info != 'O') System.out.print('~' + " ");
                else System.out.print(info + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void displayFireResult(boolean hit) {
        String hitResult = String.format("%s Hit! %s %n", ANSI_GREEN,ANSI_RESET);
        String missResult = String.format("%s Miss! %s %n", ANSI_RED, ANSI_RESET);
        System.out.printf(hit ?  hitResult :missResult );
    }
    public static void disPlayGameMap(){
        System.out.printf("Game map %s %s %n",ANSI_GREEN, ANSI_RESET);
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        for(int rowIndex = 1; rowIndex <= 10; rowIndex++){
            char rowChar = (char)('A' + rowIndex - 1);
            System.out.print(rowChar + "| ");;
            for(int colIndex = 1; colIndex <= 10; colIndex++){
                System.out.print("~ ");
            }
            System.out.println();
        }
    }
    public static void displayGameResult(Player firstPlayer, Player secondPlayer) {
        if(firstPlayer.isLoose()) System.out.println(secondPlayer.getName() + " is the Winner!");
        else System.out.println(firstPlayer.getName() + " is the Winner!");
    }

}
