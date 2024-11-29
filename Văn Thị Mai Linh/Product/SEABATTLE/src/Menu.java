import java.util.Scanner;
public class Menu {
    private Player player;

    // khoi tao
    public Menu(Player player){
        this.player = player;
    }
    Scanner scanf = new Scanner(System.in);
    // tinh hinh hien tai
    public void currentStatus(){
        System.out.println("Tình hình hiện tại của bạn: ");
        System.out.println("Số tàu bạn đã phá của địch: ");
        System.out.println(player.getNumberOfDistroyedShip());
        System.out.println("Số ô đã bắn ở mặt trận địch: ");
        System.out.println(player.getNumberOfEnemyCellHited());
        System.out.println("Số tàu còn lại của bản thân: ");
        System.out.println(player.getNumberOfShipLeft());
    }
    // xem bang
    public void viewTable(){
        Board board = player.getBoard();
        board.displayBoard();
    }
    // tan cong
    public void attack(Player enemy){
        player.getBoard().attackShip(player, enemy);
    }
    // xem bang suong mu
    public void viewFogTable(){
        player.getBoard().displayFogBoard();
    }

}
