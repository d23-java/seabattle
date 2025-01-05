package PlayerActivity;

import Constructor.Board;
import Constructor.Player;
import Constructor.Ship;
import GamePlay.ClearTerminal;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerPlayer {
    ClearTerminal clearTerminal = new ClearTerminal();
    private Scanner sc = new Scanner(System.in);

    public void initPlayer1() {
        ArrayList<Ship> ships = new ArrayList<>();
        InitShip initShip = new InitShip(ships);
        initShip.placeShips(1);
        Board player1Board = new Board();
        PlayerData.player1 = new Player(ships, player1Board);

    }
    public void initPlayer2(){
        ArrayList<Ship> ships = new ArrayList<>();
        InitShip initShip = new InitShip(ships);
        initShip.placeShips(2);
        Board player2Board = new Board();
        PlayerData.player2 = new Player(ships, player2Board);
    }

    public void displayPlayer1Board() {
        clearTerminal.clear(0);
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
        System.out.print("\u001B[34m"+"Please Enter to continue: "+"\u001B[0m");
        String enter = sc.nextLine();
        clearTerminal.clear(0);
    }

    public void displayPlayer2Board() {
        clearTerminal.clear(0);
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
        System.out.print("\u001B[34m"+"Please Enter to continue: "+"\u001B[0m");
        String enter = sc.nextLine();
        clearTerminal.clear(0);
    }

    public void displayPlayerAndOpponentBoard(Board playerBoard, Board opponentBoard) {
        System.out.println("|_________________________PLAYER-BOARD__________________________|      |__________________________OPPONENT-BOARD_______________________|");
        System.out.println("|___|  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10 |      |___|  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10 |");
        System.out.println("|~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|      |~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|");
        for (int i = 0; i < 10; i++) {
            System.out.printf("| %c |", 'A'+i);
            for (int j = 0; j < 10; j++) {
                char ch = playerBoard.getValue(i, j, true);
                if(ch == 'R')   System.out.print("\u001B[32m" + " [" + ch + "] " + "\u001B[0m" + "|");
                else if(ch == 'Y')  System.out.print("\u001B[31m" + " [" + ch + "] " + "\u001B[0m" + "|");
                else if(ch >= '1' && ch <= '5') System.out.print("\u001B[34m" + " [" + ch + "] " + "\u001B[0m" + "|");
                else System.out.print(" [" + ch + "] |");
            }
            System.out.print("      ");
            System.out.printf("| %c |", 'A'+i);
            for(int j = 0; j < 10; j++){
                char ch = opponentBoard.getValue(i, j, false);
                if(ch == 'R')   System.out.print("\u001B[32m" + " [" + ch + "] " + "\u001B[0m" + "|");
                else if(ch == 'Y')  System.out.print("\u001B[31m" + " [" + ch + "] " + "\u001B[0m" + "|");
                else if(ch >= '1' && ch <= '5') System.out.print("\u001B[34m" + " [" + ch + "] " + "\u001B[0m" + "|");
                else System.out.print(" [" + ch + "] |");
            }
            System.out.println();
            System.out.println("|~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|      |~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|~~~~~|");
        }
    }

    public void displayPlayerCaption(){
        System.out.println("Chu thich: ");
        System.out.println("+ [ ]: O chua bi ban");
        System.out.println("+ [" + "\u001B[31m" + "Y" + "\u001B[0m" + "]: O bi ban nhung chua trung tau");
        System.out.println("+ [" + "\u001B[32m" + "R" + "\u001B[0m" + "]: Da bi ban trung 1 phan cua tau nao do");
        System.out.println("+ [" + "\u001B[34m" + "1" + "\u001B[0m" + "] or [" + "\u001B[34m" + "2" + "\u001B[0m" + "] or ..: Noi dat tau 1, 2, 3, 4 hoac 5 (So thu tu tuong ung luc nhap tau)");

    }

    public void displayOpponentCaption(){
        System.out.println("Chu thich: ");
        System.out.println("+ [ ]: O chua bi ban");
        System.out.println("+ [" + "\u001B[31m" + "Y" + "\u001B[0m" + "]: O bi ban nhung chua trung tau");
        System.out.println("+ [" + "\u001B[32m" + "R" + "\u001B[0m" + "]: Da ban trung 1 phan cua tau nao do");
        System.out.println("+ [" + "\u001B[34m" + "1" + "\u001B[0m" + "] or [" + "\u001B[34m" + "2" + "\u001B[0m" + "] or ..: Da ban trung tau 1, 2, 3, 4 hoac 5 (So thu tu tuong ung luc nhap tau)");

    }
}