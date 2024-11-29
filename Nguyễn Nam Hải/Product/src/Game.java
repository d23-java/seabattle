import java.util.*;

public class Game {
    Scanner sc = new Scanner(System.in);
    public PlayerData data = new PlayerData();
    public static Integer turn = 1;
    public void initTwoPlayer(){
        System.out.println("Enter data of Player 1:");
        data.initPlayer1();
        data.displayPlayer1();
        System.out.println("Enter data of Player 2:");
        data.initPlayer2();
        data.displayPlayer2();
    }
    public void switchTurn(){
        if(turn == 1){
            turn = 2;
            return;
        }
        turn = 1;
    }

    public void fire(){
        boolean endGame = false;
        while(!endGame) {
            Player player = null, opponent = null;
            if (turn == 1) {
                player = data.player1;
                opponent = data.player2;
            } else if (turn == 2) {
                player = data.player2;
                opponent = data.player1;
            }
            //View your board and the opponent's foggy board
            System.out.println("Your board:");
            player.getPlayerBoard().displayBoard();
            System.out.println("Opponent's foggy board:");
            opponent.getPlayerBoard().displayBoard();
            //Enter coordinates
            System.out.println("Enter the coordinates you want to shoot");
            int x, y;
            boolean enterTrue = false;
            while(!enterTrue) {
                System.out.print("Enter x (1, 2,...): ");
                x = Integer.parseInt(sc.nextLine()) - 1;
                System.out.print("Enter y (A, B,..): ");
                char yChar = sc.nextLine().charAt(0);
                y = yChar - 'A';
                if(((0 <= x && x <= 9) && (0 <= y && y <= 9))) {
                    enterTrue = true;
                }
                if(!enterTrue) {
                    System.out.println("Please enter the coordinates again");
                }
            }
            //Đoạn ý tưởng tiếp theo em xin phép viết bằng tiếng Việt ạ
            /*
            Tấn công vào bảng sương mù của đối thủ
            Thông báo bắn trúng/trượt (check qua toàn bộ)
            In ra bảng sương mù của đối thủ
            Nếu bắn trúng 1 điểm của tàu (check qua các điểm trong ship)
             |__Nếu bắn rụng 1 tàu -> Thông báo bắn rụng tàu (check qua hết, trong khi xét qua hết các tọa độ, tạo biến đếm, so sánh với size của ship để biết)
                 |__Nếu clear hết tàu -> endGame = true (Sẽ tạo 1 biến đếm xem có bao nhiêu con tàu bị rụng, nếu = 5 thì ok)
            (endGame != true) -> Gặp switchTurn, đổi lượt
            */
            switchTurn();
        }
        //Lúc này đã thắng, hiện ra thông báo người chơi thắng (Người chơi thứ turn là người thắng)
    }
}
