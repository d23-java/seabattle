package manager;

import data.Ship;
import login.Menu;
import main.Scan;
import user.Player;

public class PlayerManager {
    Menu menu = new Menu();
    public void setupBattle(Player player){

        Ship patrolBoat = new Ship(2,2);
        Ship destroyerBoat = new Ship(4,1);
        Ship submarineBoat = new Ship(3,1);
        Ship battleShip = new Ship(5,1);
        for(int i = 0; i < 5; ++i){
            menu.showBoat();
            int option = Scan.sc.nextInt();
            switch (option){
                case 1:
                    if(patrolBoat.getQuantity() == 0){
                        System.out.println("out of boat");
                        System.out.println("please choose another boat");
                        break;
                    }
                    else {
                        System.out.println("please enter location you want to put");
                        System.out.println("X = ");
                        int x = Scan.sc.nextInt();
                        System.out.println("Y = ");
                        int y = Scan.sc.nextInt();
                        System.out.println("please enter direction \nV or H");
                        String direction = Scan.sc.next();
                        player.putShip(patrolBoat,x,y,direction);
                        patrolBoat.setQuantity(patrolBoat.getQuantity() - 1);
                        menu.showBoard(player.getBoard());
                        break;
                    }
                case 2:
                    if(destroyerBoat.getQuantity() == 0){
                        System.out.println("out of boat");
                        System.out.println("please choose another boat");
                        break;
                    }
                    else {
                        System.out.println("please enter location you want to put");
                        System.out.println("X = ");
                        int x = Scan.sc.nextInt();
                        System.out.println("Y = ");
                        int y = Scan.sc.nextInt();
                        System.out.println("please enter direction \nV or H");
                        String direction = Scan.sc.next();
                        player.putShip(destroyerBoat,x,y,direction);
                        patrolBoat.setQuantity(destroyerBoat.getQuantity() - 1);
                         menu.showBoard(player.getBoard());
                        break;
                    }
                case 3:
                    if(submarineBoat.getQuantity() == 0){
                        System.out.println("out of boat");
                        System.out.println("please choose another boat");
                        break;
                    }
                    else {
                        System.out.println("please enter location you want to put");
                        System.out.println("X = ");
                        int x = Scan.sc.nextInt();
                        System.out.println("Y = ");
                        int y = Scan.sc.nextInt();
                        System.out.println("please enter direction \nV or H");
                        String direction = Scan.sc.next();
                        player.putShip(submarineBoat,x,y,direction);
                        patrolBoat.setQuantity(submarineBoat.getQuantity() - 1);
                         menu.showBoard(player.getBoard());
                        break;
                    }
                case 4:
                    if(battleShip.getQuantity() == 0){
                        System.out.println("out of boat");
                        System.out.println("please choose another boat");
                        break;
                    }
                    else {
                        System.out.println("please enter location you want to put");
                        System.out.println("X = ");
                        int x = Scan.sc.nextInt();
                        System.out.println("Y = ");
                        int y = Scan.sc.nextInt();
                        System.out.println("please enter direction \nV or H");
                        String direction = Scan.sc.next();
                        player.putShip(battleShip,x,y,direction);
                        patrolBoat.setQuantity(battleShip.getQuantity() - 1);
                         menu.showBoard(player.getBoard());
                        break;
                    }
            }

        }
    }
}
