import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private boolean isPlayer1Turn;

    public Game() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên người chơi 1: ");
        String name1 = scanner.nextLine();
        System.out.print("Nhập tên người chơi 2: ");
        String name2 = scanner.nextLine();

        player1 = new Player(name1);
        player2 = new Player(name2);
        isPlayer1Turn = true;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Trò chơi bắt đầu!");

        // Người chơi đặt tàu
        player1.placeShips();
        player2.placeShips();

        // Vòng chơi
        while (true) {
            if (isPlayer1Turn) {
                System.out.println("Lượt của " + player1.getName());
                playTurn(player1, player2);
                isPlayer1Turn = false;
            } else {
                System.out.println("Lượt của " + player2.getName());
                playTurn(player2, player1);
                isPlayer1Turn = true;
            }

            // Kiểm tra kết thúc trò chơi
            if (player1.getShipsLeft() == 0) {
                System.out.println(player1.getName() + " đã thua!");
                break;
            }
            if (player2.getShipsLeft() == 0) {
                System.out.println(player2.getName() + " đã thua!");
                break;
            }
        }
    }

    private void playTurn(Player currentPlayer, Player opponent) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Hiển thị thông tin hiện tại
            System.out.println("Tình hình hiện tại:");
            System.out.println("Số ô của đối phương đã bị phá: " + (16 - opponent.getShipsLeft()));
            System.out.println("Số ô đã bắn: " + currentPlayer.getFiredShots());

            // Menu lựa chọn
            System.out.println("Lựa chọn hành động:");
            System.out.println("1. Xem bảng và cách đặt thuyền của bản thân");
            System.out.println("2. Khai hỏa vào mặt trận đối phương");
            System.out.println("3. Kết thúc lượt");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Đọc dòng còn lại

            switch (choice) {
                case 1:
                    currentPlayer.getBoard().displayBoard(true);  // Hiển thị bảng của chính mình
                    break;
                case 2:
                    currentPlayer.takeTurn(opponent);
                    // Hiển thị bảng của đối phương ở dạng sương mù
                    opponent.getBoard().displayBoard(false);
                    return;  // Sau khi khai hỏa xong, kết thúc lượt chơi
                case 3:
                    return;  // Kết thúc lượt mà không làm gì thêm
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        }
    }
}
