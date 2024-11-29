package PlayerActivity;

import Constructor.Board;
import Constructor.Player;
import Constructor.Ship;

import java.util.*;
public class Fire {
    private Scanner sc = new Scanner(System.in);
    public ManagerPlayer managerPlayer = new ManagerPlayer();
    public void attack(int turn) {
        boolean endGame = false;
        while (!endGame) {
            boolean successAttack = false;
            boolean shipWreck = false;
            System.out.printf("\n");
            System.out.printf("================================TURN PLAYER %d===============================\n", turn);
            Player player = null, opponent = null;
            if (turn == 1) {
                player = PlayerData.player1;
                opponent = PlayerData.player2;
            } else if (turn == 2) {
                player = PlayerData.player2;
                opponent = PlayerData.player1;
            }
            managerPlayer.displayPlayerAndOpponentBoard(player.getPlayerBoard(), opponent.getPlayerBoard());
            //Enter coordinates
            System.out.println("Enter the coordinates you want to shoot: ");
            int x = 0, y = 0;
            boolean enterTrue = false;
            while (!enterTrue) {
                System.out.print("Enter x (1, 2,...): ");
                String xString = sc.nextLine().trim();
                System.out.print("Enter y (A, B,..): ");
                char yChar = sc.nextLine().charAt(0);
                x = Integer.parseInt(xString);
                y = yChar - 'A';
                x -= 1;
                if (((0 <= x && x <= 9) && (0 <= y && y <= 9))) {
                    enterTrue = true;
                }
                if (!enterTrue) {
                    System.out.println("Please enter the coordinates again");
                }
            }
            //cot la x, hang la y
            successAttack = false;
            shipWreck = false;
            opponent.getPlayerBoard().setValue(y, x, 'Y', false);
            opponent.getPlayerBoard().setValue(y, x, 'Y', true);
            int soThuTuCuaTau = 1;
            for (Ship ship : opponent.getShips()) {
                for (int[] coordinates : ship.getCoordinates()) {
                    //coordinats[0] la hang va ngc lai
                    if (coordinates[0] == y && coordinates[1] == x) {
                        opponent.getPlayerBoard().setValue(coordinates[0], coordinates[1], 'R', false);
                        opponent.getPlayerBoard().setValue(coordinates[0], coordinates[1], 'R', true);
                        successAttack = true;
                        break;
                    }
                }
                if (successAttack) {
                    boolean check = true;
                    for (int[] coordinates : ship.getCoordinates()) {
                        int yCoord = coordinates[0];
                        int xCoord = coordinates[1];
                        if (opponent.getPlayerBoard().getValue(yCoord, xCoord, true) != 'R') {
                            check = false;
                            break;
                        }
                    }
                    if (check) {
                        shipWreck = true;
                        ship.setAttacked(true);
                        //true
                        for (int[] coordinates : ship.getCoordinates()) {
                            int yCoord = coordinates[0];
                            int xCoord = coordinates[1];
                            char soThuTu = (char) ('0' + soThuTuCuaTau);
                            opponent.getPlayerBoard().setValue(yCoord, xCoord, soThuTu, false);
                        }
                    }
                    break;
                }
                soThuTuCuaTau++;
            }
            System.out.println("2 board after FIRE:");
            managerPlayer.displayPlayerAndOpponentBoard(player.getPlayerBoard(), opponent.getPlayerBoard());
            if (successAttack) {
                System.out.println("=====YOU HAVE HIT A PART OF THE SHIP=====");
            }
            else{
                System.out.printf("Player %d attack failed.\n", turn);
            }
            if (shipWreck) {
                System.out.println("===========YOU HAVE HIT A SHIP===========");
            }
            //Check win
            boolean checkWin = true;
            for (Ship ship : opponent.getShips()) {
                if (!ship.isAttacked()) {// There is a ship that hasn’t been hit yet
                    checkWin = false;
                    break;
                }
            }
            if (checkWin) {
                System.out.printf( "-------------PLAYER %d WIN---------------\n", turn);
                System.out.println("================GAME OVER================");
                endGame = true;
            }
            if(!endGame)    System.out.printf("Turn player %d end. Next turn\n", turn);
            //switch turn
            if (turn == 1) turn = 2;
            else if (turn == 2) turn = 1;
        }
    }
}