package player;

import main.system;
import template.Template;
import ship.ListOfShips;

import java.util.TreeMap;

public class GameManager {
    ListOfShips ships = new ListOfShips();
    Player player1, player2;
    public static int kichThuoc = 10;

    public void start()
    {
//        System.out.println("Nhập kích thước bảng (10 -> 20): ");
//        kichThuoc = Integer.parseInt(system.scanner.nextLine());
        Template.showBattleMenu();
        int selection = Integer.parseInt(system.scanner.nextLine());
        if (selection == 1)       {

        }
        else if (selection == 2)    {
            TwoPlayer twoPlayer = new TwoPlayer();
            twoPlayer.play();
        }
    }
}
