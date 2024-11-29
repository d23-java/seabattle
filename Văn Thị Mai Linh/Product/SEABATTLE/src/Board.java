import java.util.ArrayList;
import java.util.Scanner;
public class Board {
    private int[][] board;
    private int[][] fogBoard;
    private int rows, cols;

    private ArrayList<int[]> ship;// danh sach toa do 1 thuyen
    private ArrayList<Ship> ships;// danh sach luu cac thuyen
    private ArrayList<Boolean>hitStatus = new ArrayList<Boolean>(); // luu trang thai cua cac o ( da bi ban trung hay chua)
    // khoi tao thong so ban dau
    public Board(int rows, int cols, ArrayList<Ship>ships){
        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows+5][cols+5];
        this.fogBoard = new int[rows+5][cols+5];
        this.ships = ships;
        System.out.println("Hang "+ rows + " cot " + cols);

    }

    public void setBoard(int rows, int cols){

        for(int i = 1; i <= rows; i++){
            for(int j = 1; j <= cols; j++)
                board[i][j] = 0; // ban dau bang chua co thuyen
        }
    }
    public void setFogBoard(int rows, int cols){

        for(int i = 1; i <= rows; i++){
            for(int j = 1; j <= cols; j++)
                fogBoard[i-1][j-1] = 0; // ban dau bang chua co thuyen
        }
    }

    Scanner scanf = new Scanner(System.in);
    // dat thuyen
    public void setShip(){

        System.out.println("Nhập tên của thuyền: ");
        String name = scanf.nextLine();

        System.out.println("Nhập toạ độ vị trí đầu của thuyền: ");
        int xBegin = Integer.parseInt(scanf.nextLine());
        int yBegin = Integer.parseInt(scanf.nextLine());


        System.out.println("Nhập toạ độ vị trí cuối của thuyền: ");
        int xEnd = Integer.parseInt(scanf.nextLine());
        int yEnd = Integer.parseInt(scanf.nextLine());

        ship = new ArrayList<int[]>();
        for (int i = xBegin; i <= xEnd; i++) {
            for (int j = yBegin; j <= yEnd; j++) {
                ship.add(new int[]{i, j});
                board[i][j] = 1;
            }
        }
        ships.add(new Ship(name, ship, hitStatus));
        System.out.println("Đã thêm thuyền thành công: ");
    }
    // tan cong
    public void attackShip(Player player, Player enemy){
            enemy.getBoard().displayFogBoard();
            boolean check = false;
            System.out.println("Nhập toạ độ ô mà bạn muốn bắn: ");
            int x = Integer.parseInt(scanf.nextLine());
            int y = Integer.parseInt(scanf.nextLine());

            Board enemyBoard = enemy.getBoard();
            for (Ship boat : enemyBoard.ships) {
                if(boat.isOccupyCoordinate(x,y)){
                    check = true;
                    enemyBoard.board[x][y] = 2;
                    enemyBoard.fogBoard[x][y] = 2;
                    //System.out.println("Kiểm tra cập nhật: " + boat.getHitStatus());
                    player.increaseCellEnermyHited();
                    if(boat.isSunk()){
                        System.out.println("Bạn đã bắn chìm tàu: " + boat.getNameShip());
                        player.increaseDestroyedShip();
                        enemy.decreaseShipLeft();
                        if(enemy.getNumberOfShipLeft() == 0) {
                            System.out.println("Toàn bộ tàu của đối thủ đã bị chìm. Bạn chiến thắng.");
                            return;
                        }
                        System.out.println("Xin mời lượt bắn tiếp theo");
                    }
                    else
                        System.out.println("Bạn đã bắn trúng tàu. Xin mời lượt bắn tiếp theo");
                    break;
                }
            }
            if(check){
                attackShip(player, enemy);
            }
            else{
                enemyBoard.board[x][y] = -1;
                enemyBoard.fogBoard[x][y] = -1;
                player.increaseCellEnermyHited();
                System.out.println("Bạn đã bắn trượt, đến lượt đối thủ.");
            }

    }

    // hien thi bang
    public void displayBoard(){
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 10; j++){
                if(board[i][j] == 0)
                    System.out.printf("~ ");
                else if(board[i][j] == 1)
                    System.out.printf("O ");
                else
                    System.out.printf("X ");
            }
            System.out.println();
        }
    }
    // hien thi bang an
    public void displayFogBoard(){
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 10; j++){
                if(fogBoard[i][j] == 0)
                    System.out.printf("~ ");
                else if(fogBoard[i][j] == -1)
                    System.out.printf("O ");
                else if(fogBoard[i][j] == 2)
                    System.out.printf("X ");
            }
            System.out.println();
        }
    }

}
