package game;
import data.FileRank;
import main.system;
import template.Template;

public class TwoPlayer {
    Player player1;
    Player player2;
    public void play()
    {
        System.out.println("Nhập tên người chơi thứ nhất: ");
        String name1 = system.scanner.nextLine();
        player1 = new Player(name1);
        System.out.println("Nhập tên người chơi thứ hai: ");
        String name2 = system.scanner.nextLine();
        player2 = new Player(name2);

        // Placeship
        System.out.println("Người chơi " + player1.getName() + " đặt tàu: ");
        Template.showPlaceShipOption();
        while(true){
            int selection1 = Integer.parseInt(system.scanner.nextLine());
            if (selection1==1) {
                PlaceShip.placeShip(player1);
                break;
            }
            else if (selection1==2){
                PlaceShip.randomPlaceShip((player1));
                break;
            }
            else {
                Template.enterAgain();
            }
        }
        ShowBoard.showBoards(player1);

        System.out.println("Người chơi " + player2.getName() + " đặt tàu: ");
        Template.showPlaceShipOption();
        while(true){
            int selection2 = Integer.parseInt(system.scanner.nextLine());
            if (selection2==1) {
                PlaceShip.placeShip(player2);
                break;
            }
            else if (selection2==2){
                PlaceShip.randomPlaceShip((player2));
                break;
            }
            else {
                Template.enterAgain();
            }
        }
        ShowBoard.showBoards(player2);

        int checkWin = 0;
        while(true)
        {
            System.out.println("Lượt người chơi " + player1.getName());
            Turn.turn(player1, player2);
            if (player1.getSoTauDaPha() == 5) {
                System.out.println(player1.getName() + " đã giành chiến thắng");
                FileRank.updateBxh(player1);
                break;
            }
            System.out.println("-------------------------------------------------");
            System.out.println("Lượt người chơi " + player2.getName());

            Turn.turn(player2, player1);
            if (player2.getSoTauDaPha() == 5) {
                System.out.println(player2.getName() + " đã giành chiến thắng");
                FileRank.updateBxh(player2);
                break;
            }

        }
    }
}

