package game;

import main.system;
import template.Template;
import ship.ListOfShips;

public class GameManager {
    ListOfShips ships = new ListOfShips();
    Player player1, player2;
    public static int kichThuoc = 10;

    public void start()
    {
        Template.showBattleMenu();
        int selection = Integer.parseInt(system.scanner.nextLine());

        System.out.println("Nhập kích thước bảng (10 -> 20): ");
        kichThuoc = Integer.parseInt(system.scanner.nextLine());

        if (selection == 1)       { // Play with computer
            OnePlayer onePlayer = new OnePlayer();
            onePlayer.play();
        }
        else if (selection == 2)    { // two player
            TwoPlayer twoPlayer = new TwoPlayer();
            twoPlayer.play();
        }
    }
}
