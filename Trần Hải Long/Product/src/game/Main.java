package game;

import java.util.Scanner;
import gamemanager.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        Leaderboard leaderboard = new Leaderboard();
        while (true) {
            menu.seaBattle();
            System.out.print("Chọn một tùy chọn: ");
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 1) {
                System.out.print("Hãy chọn kích thước bảng: ");
                int size = Integer.parseInt(scanner.nextLine());
                System.out.println("+ Vui lòng nhập tên người chơi trước khi bắt đầu +");
                System.out.print("Nhập tên người chơi 1: ");
                String player1Name = scanner.nextLine();
                System.out.print("Nhập tên người chơi 2: ");
                String player2Name = scanner.nextLine();

                Game game = new Game(player1Name, player2Name, size);
                BoardController boardController1 = new BoardController(game.getPlayer1().getBoard());
                BoardController boardController2 = new BoardController(game.getPlayer2().getBoard());
                PlayerFunction playerFunction1 = new PlayerFunction(boardController1);
                PlayerFunction playerFunction2 = new PlayerFunction(boardController2);
                GameFunction gameFunction = new GameFunction(game, leaderboard, playerFunction1, playerFunction2);

                gameFunction.startGame();
            }
            else if (option == 2) {
                leaderboard.display();
                System.out.println("Vui lòng nhập bất kì kí tự nào để thoát bảng xếp hạng.");
                scanner.nextLine();
            }
            else if (option == 0){
                System.out.println("Cảm ơn bạn đã chơi trò chơi!");
                break;
            }
            else System.out.println("Lựa chọn không tồn tại, vui lòng nhập lại");
        }
    }
}