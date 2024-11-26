import java.util.Scanner;
import java.util.ArrayList;
public class Game {
    Scanner scanf = new Scanner(System.in);
    private int rows, cols;
    public Game(){
        System.out.println("Nhập vào số hàng của bảng chơi: ");
        this.rows = Integer.parseInt(scanf.nextLine());
        System.out.println("Nhập vào số cột của bảng chơi: ");
        this.cols = Integer.parseInt(scanf.nextLine());
        boardFirst = new int[rows + 5][cols +5];
        fogBoardFirst = new int[rows +5][cols+5];
        shipsFirst = new ArrayList<Ship>();
        boardPlayerFirst = new Board(rows, cols,shipsFirst);
        playerFirst = new Player(boardPlayerFirst);
        playerFirstMenu = new Menu(playerFirst);


        boardSecond  = new int[rows +5][cols+5];
        fogBoardSecond = new int[rows +5][cols+5];
        shipsSecond = new ArrayList<Ship>();
        boardPlayerSecond = new Board(rows, cols, shipsSecond);
        playerSecond = new Player(boardPlayerSecond);
        playerSecondMenu = new Menu(playerSecond);


    }
    // nguoi choi 1
    private int [][] boardFirst;
    private int[][] fogBoardFirst;
    private ArrayList<Ship>shipsFirst;
    private Board boardPlayerFirst;
    private Player playerFirst;
    private Menu playerFirstMenu;

    // nguoi choi 2
    private int [][] boardSecond;
    private int[][] fogBoardSecond;
    private ArrayList<Ship>shipsSecond;
    private Board boardPlayerSecond;
    private Player playerSecond;
    private Menu playerSecondMenu;

    public void menu(){
        System.out.println("Bạn có các lựa chọn sau: ");
        System.out.println("1. Hiển thị tình hình hiện tại của bạn (bao gồm: số ô đã bắn đối thủ, số tàu đã phá của đối thủ, số tàu còn lại của mình)");
        System.out.println("2. Xem bảng đặt thuyền của bản thân.");
        System.out.println("3. Xem bảng dạng sương mù của đối thủ.");
        System.out.println("4. Đặt lệnh khai hoả (tấn công).");
        System.out.println("5. Kết thúc lượt.");
    }

    public void startGame(){

        playerFirst.getBoard().setBoard(rows, cols);
        playerFirst.getBoard().setFogBoard(rows, cols);
        playerSecond.getBoard().setBoard(rows, cols);
        playerSecond.getBoard().setFogBoard(rows, cols);
        System.out.println("Các người chơi đặt thuyền.");
        System.out.println("Người chơi thứ nhất đặt thuyền: ");
        int totleNum = 0;
        while(totleNum < 5 ){
            boardPlayerFirst.setShip();
            boardPlayerFirst.displayBoard();
            totleNum++;
        }
        System.out.println("Người chơi thứ 2 đặt thuyền: ");
        totleNum = 0;
        while(totleNum < 5){
            boardPlayerSecond.setShip();
            boardPlayerSecond.displayBoard();
            totleNum++;
        }
    }

    public void playGame() {
        boolean gameRunning = true;
        int currentPlayer = 1;

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
                            playerFirstMenu.currentStatus();
                            break;
                        case 2:
                            playerFirstMenu.viewTable();
                            break;
                        case 3:
                            playerSecondMenu.viewFogTable();
                            break;
                        case 4:
                            playerFirstMenu.attack(playerSecond);
                            break;
                        case 5:
                            currentPlayer = 2;
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
                    }
                    if(choice == 5)
                        break;
                    if(playerSecond.getNumberOfShipLeft() == 0) {
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
                    int choice = Integer.parseInt(scanf.nextLine());

                    switch (choice) {
                        case 1:
                            playerSecondMenu.currentStatus();
                            break;
                        case 2:
                            playerSecondMenu.viewTable();
                            break;
                        case 3:
                            playerFirstMenu.viewFogTable();
                            break;
                        case 4:
                            playerSecondMenu.attack(playerFirst);
                            break;
                        case 5:
                            currentPlayer = 1;
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
                    }
                    if(choice == 5)
                        break;
                    if(playerFirst.getNumberOfShipLeft() == 0){
                        gameRunning = false;
                        break;
                    }
                }
            }
        }
        System.out.println("Trận chiến kết thúc, người chiến thắng là người thứ: " + currentPlayer);
        System.out.println("Bảng chơi của người chơi 1: ");
        boardPlayerFirst.displayBoard();
        System.out.println("Bảng chơi của người chơi 2: ");
        boardPlayerSecond.displayBoard();
    }

}
