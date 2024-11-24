import java.util.Scanner;

public class GameManager {
    private Player player1, player2, currentTurn;

    public GameManager(){
        this.player1 = new Player();
        this.player2 = new Player();
        currentTurn=player1;
    }
    public void gameStart(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Sea Battle!");
        System.out.print("Player 1! Enter your name: ");
        player1.setName(sc.nextLine());
        player1.placeShips();
        System.out.print("\033[H\033[2J");
        System.out.print("Player 2! Enter your name: ");
        player2.setName(sc.nextLine());
        player2.placeShips();
        System.out.print("\033[H\033[2J");
        while(true){
            int x,y;
            System.out.println(currentTurn.getName() + "'s Turn!");
            System.out.println("Your board:");
            currentTurn.getBoard().showOwnerBoard();
            System.out.println("Opponent's board:");
            currentTurn.getBoard().showOpponentBoard();
            while(true){
                System.out.print("Launch your attack at (x y): ");
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
                if(!currentTurn.receiveAttack(x,y)){
                    System.out.println("Cant attack this coordinates. Choose again!");
                    sc.nextLine();
                    continue;
                }
                break;
            }
            boolean hit = (currentTurn == player1) ? player2.receiveAttack(x, y) : player1.receiveAttack(x, y);

            System.out.println(hit ? "Hit!" : "Miss!");

            if (player1.hasLost() || player2.hasLost()) {
                System.out.println((player1.hasLost() ? player2.getName() : player1.getName()) + " wins!");
                break;
            }
            currentTurn = (currentTurn == player1) ? player2 : player1;
            System.out.print("\033[H\033[2J");
        }
    }
}
