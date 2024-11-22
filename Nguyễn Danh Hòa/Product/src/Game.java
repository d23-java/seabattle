import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public void showGame()
    {

        System.out.println("chào mừng bạn đến với trò chơi Sea-Battle");
        System.out.println("-----------------------------------------");
        System.out.println("------------------Luật chơi--------------");
        System.out.println("1. Hai người chơi lần lượt bắn vào bản đồ của đối phương.");
        System.out.println("2. Đánh trúng tất cả tàu của đối phương sẽ thắng.");
        System.out.println("3. Mỗi người sẽ có bản đồ nxn với 5 tàu chiến.");
        System.out.println("Hãy sẵn sàng!");
    }
    public void  menuGame()
    {
        while (true) {
            System.out.println("========== Menu ==========");
            System.out.println("1. Chế độ chơi");
            System.out.println("2. Thoát trò chơi");
            System.out.println("3. xem bảng xếp hạng ");
            System.out.println("==========================");
            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    while(true) {
                        System.out.println("1. chế độ người đấu với ngươi");
                        System.out.println("2. chế độ người đấu với máy");
                        System.out.println("3. chế độ máy đấu với máy (chỉ xem)");
                        System.out.println("4. thoát ra ngoài menu chọn");
                        System.out.println("nhập lựa chọn của bạn");
                        int k = Integer.parseInt(scanner.nextLine());
                        switch(k){
                            case 1:
                                gameMode();
                                break;
                            case 2:
                                System.out.println("chế độ chơi đang được phát triển trong thời gian tới");
                                break;
                            case 3:
                                System.out.println("chế độ chơi đang được phát triển trong thời gian tới");
                                break;
                            case 4:
                                menuGame();
                            default:
                                System.out.println("nhập không hợp lệ vui lòng thử lại");
                                break;
                        }
                    }
                case 2:
                    exit();
                    break;
                case 3:
                    System.out.println("chế độ bảng xếp hạng đang được bổ sung. vui lòng chọn lại");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
    public void gameMode()
    {
        System.out.println("đã vào trận sea-battle chế độ chơi 2 người:");
        System.out.println("vui lòng nhập kích thước bàn đấu");
        int size = Integer.parseInt(scanner.nextLine());
        System.out.println("nhập tên người chơi số 1:" );
        String name1 = scanner.nextLine();
        System.out.println("nhập tên người chơi số 2");
        String name2 = scanner.nextLine();
        Player player1 = new Player(size,name1);
        Player player2 = new Player(size,name2);
        System.out.println("xin chào người chơi "+name1+ "đây là giai đoạn đặt tàu chiến của người chơi 1");
        System.out.println("vui lòng đặt tàu:");
        player1.setShip();
        System.out.println("xin chào người chơi "+name2+ "đây là giai đoạn đặt tàu chiến của người chơi 2");
        System.out.println("vui lòng đặt tàu:");
        player2.setShip();
        int turn = 1;
        while(player1.check == 0 && player2.check == 0)
        {
            if(turn %2 == 1)
            {
                gameBattle(player1,player2);
            }
            else gameBattle(player2,player1);
            turn ++;
        }


    }
    public void gameBattle(Player player1,Player player2)
    {
        System.out.println("thông số hiện tại của "+player1.getNamePlayer()+ "là");
        System.out.println("số lượng tàu còn lại: "+player1.numberShip);
        System.out.println("số lượng tàu còn lại của quân địch là: "+ player2.numberShip);
        System.out.println("---------------------------------------------------------");
        while (true)
        {
            int check =0;
            System.out.println("hãy lựa chọn các chức năng sau");
            System.out.println("1. Xem bảng của bản thân");
            System.out.println("2. Tấn công");
            System.out.println("3.lưu và thoát trò chơi");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    player1.showMyBoard();
                    break;
                case 2:
                    player2.beHit();// tấn công ngươ chơi 2
                    check =1;
                    break;
                case 3:
                    exit();
                    break;
                default:
                    System.out.println("nhập sai vui lòng nhập lại");
                    break;
            }
            if(check == 1)break;
        }

    }
    public void exit() {
        System.out.println("Thoát trò chơi. Hẹn gặp lại!");
        System.exit(0);
    }
}
