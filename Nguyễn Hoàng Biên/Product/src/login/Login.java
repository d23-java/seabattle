package login;

import main.Scan;
import manager.PlayerManager;
import user.Player;

public class Login  {
    public void choosePlayer() {
        System.out.println("1. player 1");
        System.out.println("2. player 2");
        System.out.println("3. exit");
        System.out.println("please choose your option:");
        PlayerManager manager = new PlayerManager();
        int option = Integer.parseInt(Scan.sc.nextLine());
        if(option == 1){
            System.out.println("please enter player name:");
            String name = Scan.sc.nextLine();
            Player player1 = new Player(name);
            manager.setupBattle(player1);
        }
        else if(option == 2){
            System.out.println("please enter player name:");
            String name = Scan.sc.nextLine();
            Player player2 = new Player(name);
            manager.setupBattle(player2);
        }
        else if(option == 3) {
            return;
        }
    }
}
