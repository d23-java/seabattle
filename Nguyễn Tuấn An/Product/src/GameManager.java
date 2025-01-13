import java.io.IOException;
import java.util.Scanner;

public class GameManager {
    private Player player1, player2, currentTurn;

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public GameManager(){
        this.player1 = new Player();
        this.player2 = new Player();
        currentTurn=player1;
    }
    public void gameStart() throws IOException {
        Scanner sc = new Scanner(System.in);
        //Set player name
        System.out.println("Welcome to Sea Battle!");
        System.out.print("Player 1! Enter your name: ");
        String name1 = sc.nextLine();
        player1.setName(name1);
        System.out.print("Player 2! Enter your name: ");
        String name2 = sc.nextLine();
        player2.setName(name2);

        System.in.read();
        clearScreen();
        //place ships
        player1.placeShips();
        player2.placeShips();

        System.in.read();
        clearScreen();
        //shoot each-other
        while(true){
            int r,c;
            System.out.println(currentTurn.getName() + "'s Turn!");
            System.out.println("Your board:");
            currentTurn.getBoard().showBoard("owner");
            System.out.println("Opponent's board:");
            ((currentTurn==player1)?player2:player1).getBoard().showBoard("opponent");
            Player opponent = (currentTurn == player1) ? player2 : player1;
            while(true){
                System.out.print("Launch your attack at (Ex:A 1): ");
                char tmp = sc.next().toUpperCase().charAt(0);
                r = tmp - 'A';
                c = sc.nextInt() - 1;
                sc.nextLine();
                if (r < 0 || r >= 10 || c < 0 || c >= 10) {
                    System.out.println("Coordinates out of bounds. Try again!");
                    continue;
                }
                boolean hit = opponent.receiveAttack(r, c);
                if(!hit){
                    System.out.println("Cant attack one coordinates twice. Try again!");
                    sc.nextLine();
                    continue;
                }
                System.out.println(hit ? "Hit!" : "Miss!");
                break;
            }


            if (player1.hasLost() || player2.hasLost()) {
                System.out.println((player1.hasLost() ? player2.getName() : player1.getName()) + " wins!");
                break;
            }
            currentTurn = (currentTurn == player1) ? player2 : player1;
            System.out.print("<<Press any key to end your turn!>>");
            System.in.read();
            clearScreen();
        }
        sc.close();
    }
}
