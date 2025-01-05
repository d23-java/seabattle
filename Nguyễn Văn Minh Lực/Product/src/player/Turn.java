package player;

import main.system;
import template.Template;

public class Turn {
    public static void turn(Player player, Player enemy)
    {
        // In trạng thái của người chơi
        java.lang.System.out.println("Trạng thái tàu của " + player.getName());
        java.lang.System.out.println("Số ô đã bắn ở mặt trận địch: " + player.getSoODaBan());
        java.lang.System.out.println("Số tàu đã phá: " + player.getSoTauDaPha());
        java.lang.System.out.println("Số tàu còn lại của người chơi: " + player.getSoTauConLai());
        int checkKhaiHoa = 0;
        while(true)
        {
            Template.showPlayerMenu();
            int selection = Integer.parseInt(system.scanner.nextLine());
            switch(selection)
            {
                case 1: ShowBoard.showBoard(player); break;
                case 2:
                    if (checkKhaiHoa == 1)
                    {
                        System.out.println("Bạn đã khai hỏa rồi!");
                        break;
                    }
                    checkKhaiHoa = 1;
                    while(true) {
                        if (player.getSoTauDaPha() == 5) {
                            return;
                        }
                        ShowBoard.showEnemyBoard(player);
                        ToaDo toaDo = player.toaDoShoot();
                        boolean continueShoot = CheckShoot.shoot(toaDo, player, enemy);
                        if (continueShoot == false) {
                            System.out.println("Bạn đã bắn trượt!");
                            break;
                        }
                        else {
                            System.out.println("Bạn đã bắn trúng!");
                        }
                    }
                    break;
                case 3: return;
            }
        }
    }
}
