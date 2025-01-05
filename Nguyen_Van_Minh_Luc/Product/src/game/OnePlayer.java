package game;

import data.FileRank;
import main.system;
import template.Template;

public class OnePlayer {
    public void play() {
        // Bot constructor
        Bot bot = new Bot();
        PlaceShip.randomPlaceShip(bot);

        // Player enter information and placeship
        Player player;
        System.out.println("Nhập tên người chơi: ");
        String name = system.scanner.nextLine();
        player = new Player(name);

        System.out.println("Người chơi " + player.getName() + " đặt tàu: ");
        Template.showPlaceShipOption();
        while(true){
            int selection1 = Integer.parseInt(system.scanner.nextLine());
            if (selection1==1) {
                PlaceShip.placeShip(player);
                break;
            }
            else if (selection1==2){
                PlaceShip.randomPlaceShip((player));
                break;
            }
            else {
                Template.enterAgain();
            }
        }
        ShowBoard.showBoard(player);
        System.out.println("-------------------------------------------------");
        // InGame

        while(true) {
            System.out.println("Lượt người chơi " + player.getName());
            Turn.turn(player, bot);
            if (player.getSoTauDaPha() == 5) {
                System.out.println(player.getName() + " đã giành chiến thắng");
                System.out.println("-------------------------------------------------");
                FileRank.updateBxh(player);
                break;
            }
            System.out.println("-------------------------------------------------");
            Turn.botTurn(bot, player);
            //if (bot.soLanBan == 10) return;
            if (bot.getSoTauDaPha() == 5) {
                System.out.println("Máy đã giành chiến thắng");
                System.out.println("-------------------------------------------------");
                return;
            }
            System.out.println("-------------------------------------------------");
        }
    }
}
