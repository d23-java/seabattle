import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ComputerSystem {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Player> players = new ArrayList<>();

    void setUpPlayer() throws InterruptedException {
        for(int i = 1;i <= 2;++i){
            TimeUnit.SECONDS.sleep(2);
            Terminal.clear();
            Player player = new Player(i);
            player.setInformation();
            players.add(player);
        }
    }

    void inGame(){
        int playerTurn = 1;
        while(!players.get(0).isDefeat() && !players.get(1).isDefeat()){
            Terminal.clear();
            playerTurn = (playerTurn == 1) ? 0 : 1;
            System.out.println(players.get(playerTurn).getName() + "'s turn: ");
            MenuList.showPlayerOption();
            int playerChoice = scanner.nextInt();
            if(playerChoice == 1){
                players.get(playerTurn).shotShip(players.get(1 - playerTurn));
            }
            else if(playerChoice == 2){
                players.get(playerTurn).playerScreen.display();
            }

        }
    }

    void newGame() throws InterruptedException {
        while(true){
            Terminal.clear();
            MenuList.showGameModeMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice) {
                case 1:
                    setUpPlayer();
                    inGame();
                    return;
                case 2:
                    System.out.println("Updating....");
                    TimeUnit.SECONDS.sleep(1);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice");
                    TimeUnit.SECONDS.sleep(1);
                    break;
            }
        }
    }
    void startGame() throws InterruptedException {
        while(true){
            Terminal.clear();
            MenuList.showMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice) {
                case 1:
                    newGame();
                    break;
                case 2:
                case 3:
                case 4:
                    System.out.println("Updating....");
                    TimeUnit.SECONDS.sleep(1);
                    break;
                case 5:
                    System.out.println("See u soon!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
                    TimeUnit.SECONDS.sleep(1);
                    break;
            }
        }

    }
}
