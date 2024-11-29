package PlayerActivity;

import Constructor.Board;
import Constructor.Player;
import Constructor.Ship;

import java.util.ArrayList;

public class ManagerPlayer {
    public void initPlayer1() {
        ArrayList<Ship> ships = new ArrayList<>();
        InitShip initShip = new InitShip(ships);
        initShip.placeShips();
        Board player1Board = new Board();
        PlayerData.player1 = new Player(ships, player1Board);

    }
    public void initPlayer2(){
        ArrayList<Ship> ships = new ArrayList<>();
        InitShip initShip = new InitShip(ships);
        initShip.placeShips();
        Board player2Board = new Board();
        PlayerData.player2 = new Player(ships, player2Board);
    }

    public void displayPlayer1Board() {
        int soThuTuCuaTau = 1;
        for(Ship ship: PlayerData.player1.getShips()) {
            for(int[] coordinates : ship.getCoordinates()) {
                int yCoord = coordinates[0];
                int xCoord = coordinates[1];
                char soThuTu = (char) ('0' + soThuTuCuaTau);
                PlayerData.player1.getPlayerBoard().setValue(yCoord,xCoord, soThuTu, true);
            }
            soThuTuCuaTau++;
        }
        PlayerData.player1.getPlayerBoard().displayBoardPrivate();
    }

    public void displayPlayer2Board() {
        int soThuTuCuaTau = 1;
        for(Ship ship: PlayerData.player2.getShips()) {
            for(int[] coordinates : ship.getCoordinates()) {
                int yCoord = coordinates[0];
                int xCoord = coordinates[1];
                char soThuTu = (char) ('0' + soThuTuCuaTau);
                PlayerData.player2.getPlayerBoard().setValue(yCoord, xCoord, soThuTu, true);
            }
            soThuTuCuaTau++;
        }
        PlayerData.player2.getPlayerBoard().displayBoardPrivate();
    }

    public void displayPlayerAndOpponentBoard(Board playerBoard, Board opponentBoard) {
        System.out.println("|_________________________PLAYER-BOARD__________________________|      |__________________________OPPONENT-BOARD_______________________|");
        System.out.println("|___|  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10 |      |___|  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10 |");
        System.out.println("|~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|      |~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|");
        for (int i = 0; i < 10; i++) {
            System.out.printf("| %c |", 'A'+i);
            for (int j = 0; j < 10; j++) {
                System.out.print(" [" + playerBoard.getValue(i, j, true) + "] |");
            }
            System.out.print("      ");
            System.out.printf("| %c |", 'A'+i);
            for(int j = 0; j < 10; j++){
                System.out.print(" [" + opponentBoard.getValue(i, j, false) + "] |");
            }
            System.out.println();
            System.out.println("|~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|      |~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|");
        }
    }

    public void displayPlayerCaption(){
        System.out.println("Chú thích: ");
        System.out.println("+ [ ]: Ô chưa bị bắn");
        System.out.println("+ [Y]: Ô bị bắn nhưng chưa trúng tàu");
        System.out.println("+ [R]: Đã bị bắn trúng 1 phần của tàu nào đó");
        System.out.println("+ [1] or [2] or ..: Nơi đặt tàu 1, 2, 3, 4 hoặc 5 (Số thứ tự tương ứng lúc nhập tàu)");
    }

    public void displayOpponentCaption(){
        System.out.println("Chú thích: ");
        System.out.println("+ [ ]: Ô chưa bị bắn");
        System.out.println("+ [Y]: Ô bị bắn nhưng chưa trúng tàu");
        System.out.println("+ [R]: Đã bắn trúng 1 phần của tàu nào đó");
        System.out.println("+ [1] or [2] or ..: Đã bắn trúng tàu 1, 2, 3, 4 hoặc 5 (Số thứ tự tương ứng lúc nhập tàu)");
    }
}