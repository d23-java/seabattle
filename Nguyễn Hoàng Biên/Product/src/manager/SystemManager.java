package manager;

import data.Ship;
import login.Menu;
import main.Scan;
import user.Player;

public class SystemManager {
    Ship patrolBoat = new Ship(2,2);
    Ship destroyerBoat = new Ship(4,1);
    Ship submarineBoat = new Ship(3,1);
    Ship battleShip = new Ship(5,1);

    public boolean check(){
        if(patrolBoat.getQuantity() == 0 && destroyerBoat.getQuantity() == 0
            && submarineBoat.getQuantity() == 0 && battleShip.getQuantity() == 0) return false;
        return true;
    }
    public void enterLocation(Player player, PlayerManager manager, Ship ship){
        System.out.println("please enter location you want to put");
        System.out.print("X = ");
        int x = Scan.sc.nextInt();
        System.out.print("Y = ");
        int y = Scan.sc.nextInt();
        System.out.println("please enter direction \nV or H");
        String direction = Scan.sc.next();
        manager.putShip(player,ship,x,y,direction);
    }
    public void setupBattle(Player player, PlayerManager manager){
        int h = 1;
        while(h-- != 0){
            Menu.showBoat();
            int option = Scan.sc.nextInt();
            switch (option){
                case 1:
                    if(patrolBoat.getQuantity() == 0){
                        System.out.println("out of boat");
                        System.out.println("please choose another boat");
                        break;
                    }
                    else {
                        enterLocation(player,manager,patrolBoat);
                        patrolBoat.setQuantity(patrolBoat.getQuantity() - 1);
                        Menu.showBoard(player.getBoard());
                        break;
                    }
                case 2:
                    if(destroyerBoat.getQuantity() == 0){
                        System.out.println("out of boat");
                        System.out.println("please choose another boat");
                        break;
                    }
                    else {
                        enterLocation(player,manager,destroyerBoat);
                        patrolBoat.setQuantity(destroyerBoat.getQuantity() - 1);
                        Menu.showBoard(player.getBoard());
                        break;
                    }
                case 3:
                    if(submarineBoat.getQuantity() == 0){
                        System.out.println("out of boat");
                        System.out.println("please choose another boat");
                        break;
                    }
                    else {
                        enterLocation(player,manager,submarineBoat);
                        patrolBoat.setQuantity(submarineBoat.getQuantity() - 1);
                        Menu.showBoard(player.getBoard());
                        break;
                    }
                case 4:
                    if(battleShip.getQuantity() == 0){
                        System.out.println("out of boat");
                        System.out.println("please choose another boat");
                        break;
                    }
                    else {
                        enterLocation(player,manager,battleShip);
                        patrolBoat.setQuantity(battleShip.getQuantity() - 1);
                        Menu.showBoard(player.getBoard());
                        break;
                    }
                default:
                    System.out.println();
            }
        }
    }
    public void startGame(PlayerManager manager, Player firstPlayer, Player secondPlayer){
        int x,y;
        int yourTotalShot = 0, opponentTotalShot = 0;
        while (true){
            while (true){
                System.out.println(firstPlayer.getName() + " turn:");
                Menu.showOption();
                int option = Scan.sc.nextInt();
                if(option == 1){
                    Menu.showBoard(firstPlayer.getBoard());
                } else if (option == 2) {
                    Menu.showBoard(firstPlayer.getEnemyBoard());
                }
                else if(option == 3) {
                    System.out.println("please enter coordinate you want to fire:");
                    System.out.print("X = ");
                    x = Scan.sc.nextInt();
                    System.out.println("Y = ");
                    y = Scan.sc.nextInt();
                    manager.attack(firstPlayer, secondPlayer, x, y);
                    if (manager.haveShip(secondPlayer.getBoard(), x, y)) {
                        yourTotalShot++;
                        if (yourTotalShot == secondPlayer.getBoard().getTotalPositions()) {
                            Menu.showResult(1);
                            return;
                        }
                    }
                    break;
                }
                else System.out.println("please choose again");
            }
            while (true){
                System.out.println(secondPlayer.getName() + " turn:");
                Menu.showOption();
                int secondPlayerOption = Scan.sc.nextInt();
                if(secondPlayerOption == 1){
                    Menu.showBoard(secondPlayer.getBoard());
                } else if (secondPlayerOption == 2) {
                    Menu.showBoard(secondPlayer.getEnemyBoard());
                }
                else if(secondPlayerOption == 3) {
                    System.out.println("please enter coordinate you want to fire:");
                    System.out.print("X = ");
                    x = Scan.sc.nextInt();
                    System.out.println("Y = ");
                    y = Scan.sc.nextInt();
                    manager.attack(secondPlayer, firstPlayer, x, y);
                    if (manager.haveShip(firstPlayer.getBoard(), x, y)) {
                        opponentTotalShot++;
                        if (opponentTotalShot == firstPlayer.getBoard().getTotalPositions()) {
                            Menu.showResult(0);
                            return;
                        }
                    }
                    break;
                }
                else System.out.println("please choose again");
            }
        }
    }
}
