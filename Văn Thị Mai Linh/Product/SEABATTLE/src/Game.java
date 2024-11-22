import java.util.Scanner;
import java.util.ArrayList;
public class Game {
    Scanner scanf = new Scanner(System.in);
    // nguoi choi 1
    private int [][] board1 = new int [15][15];
    private int[][] fogBoard1 = new int [15][15];
    private ArrayList<Ship>ships1 = new ArrayList<Ship>();
    Board boardA = new Board(board1, fogBoard1,ships1);
    Player playerA = new Player(boardA);
    Menu player1 = new Menu(playerA);

    // nguoi choi 2
    private int [][] board2 = new int [15][15];
    private int[][] fogBoard2 = new int [15][15];
    private ArrayList<Ship>ships2 = new ArrayList<Ship>();
    Board boardB = new Board(board2, fogBoard2, ships2);
    Player playerB = new Player(boardB);
    Menu player2 = new Menu(playerB);

    public void menu(){
        System.out.println("Bạn có các lựa chọn sau: ");
        System.out.println("1. Hiển thị tình hình hiện tại của bạn (bao gồm: số ô đã bắn đối thủ, số tàu đã phá của đối thủ, số tàu còn lại của mình)");
        System.out.println("2. Xem bảng đặt thuyền của bản thân.");
        System.out.println("3. Xem bảng dạng sương mù của đối thủ.");
        System.out.println("4. Đặt lệnh khai hoả (tấn công).");
        System.out.println("5. Kết thúc lượt.");
    }

    public void startGame(){
        playerA.getBoard().setBoard();
        playerA.getBoard().setFogBoard();
        playerB.getBoard().setBoard();
        playerB.getBoard().setFogBoard();
        System.out.println("Các người chơi đặt thuyền.");
        System.out.println("Người chơi thứ nhất đặt thuyền: ");
        int totleNum = 0;
        while(totleNum < 5 ){
            boardA.setShip();
            boardA.displayBoard();
            totleNum++;
        }
        System.out.println("Người chơi thứ 2 đặt thuyền: ");
        totleNum = 0;
        while(totleNum < 5){
            boardB.setShip();
            boardB.displayBoard();
            totleNum++;
        }
    }

    public void playGame() {
        boolean gameRunning = true;
        int currentPlayer = 1;
        int winner = 1;
        while (gameRunning) {
            if (currentPlayer == 1) {
                System.out.println("Lượt của người thứ nhất: ");
                menu();
                while (true) {
                    System.out.println("Nhập lựa chọn của bạn: ");
                    int choice = scanf.nextInt();
                    scanf.nextLine();
                    switch (choice) {
                        case 1:
                            player1.currentStatus();
                            break;
                        case 2:
                            player1.viewTable();
                            break;
                        case 3:
                            player2.viewFogTable();
                            break;
                        case 4:
                            player1.attack(playerB);
                            break;
                        case 5:
                            currentPlayer = 2;
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
                    }
                    if(choice == 5)
                        break;
                    if(playerB.getNumberOfShipLeft() == 0) {
                        gameRunning = false;
                        break;
                    }
                }
            }
            if (currentPlayer == 2) {
                System.out.println("Lượt của người thứ hai: ");
                menu();
                while (true) {
                    System.out.println("Nhập lựa chọn của bạn: ");
                    int choice = scanf.nextInt();
                    scanf.nextLine();

                    switch (choice) {
                        case 1:
                            player2.currentStatus();
                            break;
                        case 2:
                            player2.viewTable();
                            break;
                        case 3:
                            player1.viewFogTable();
                            break;
                        case 4:
                            player2.attack(playerA);
                            break;
                        case 5:
                            currentPlayer = 1;
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
                    }
                    if(choice == 5)
                        break;
                    if(playerA.getNumberOfShipLeft() == 0){
                        gameRunning = false;
                        break;
                    }
                }
            }
        }
        System.out.println("Trận chiến kết thúc, người chiến thắng là người thứ: " + currentPlayer);
        System.out.println("Bảng chơi của người chơi 1: ");
        boardA.displayBoard();
        System.out.println("Bảng chơi của người chơi 2: ");
        boardB.displayBoard();
    }

}
