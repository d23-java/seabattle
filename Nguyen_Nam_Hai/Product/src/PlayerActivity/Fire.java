package PlayerActivity;

import Constructor.Board;
import Constructor.Player;
import Constructor.Ship;
import GamePlay.Menu;
import GamePlay.ClearTerminal;
import java.util.*;
public class Fire {
    private Scanner sc = new Scanner(System.in);
    public ManagerPlayer managerPlayer = new ManagerPlayer();
    Menu menu = new Menu();
    CheckInit checkInit = new CheckInit();
    ClearTerminal clearTerminal = new ClearTerminal();
    private Player player = null, opponent = null;
    public void chooseRole(int turn){
        if (turn == 1) {
            player = PlayerData.player1;
            opponent = PlayerData.player2;
        } else if (turn == 2) {
            player = PlayerData.player2;
            opponent = PlayerData.player1;
        }
    }
    public void firstOption(){
        menu.showFireMenu();
        System.out.print("Your choice: ");
        String chooseeString = sc.nextLine();
        int choose = 5;
        if(checkInit.checkInt(chooseeString,1, 1))   choose = Integer.parseInt(chooseeString);
        while(choose >= 1 && choose <= 3){
            if(choose == 1)    managerPlayer.displayPlayerAndOpponentBoard(player.getPlayerBoard(), opponent.getPlayerBoard());
            if(choose == 2)    managerPlayer.displayPlayerCaption();
            if(choose == 3)    managerPlayer.displayOpponentCaption();
            System.out.print("If you want to see another, please press 4: ");
            String checkString = sc.nextLine();
            int check = 4;
            if(checkInit.checkInt(checkString, 1, 1)){
                check = Integer.parseInt(checkString);
            }
            else break;
            if(check != 4)  break;
            clearTerminal.clear(1000);
            menu.showFireMenu();
            System.out.print("Your choice: ");
            String chooseString = sc.nextLine();
            if(chooseString.isEmpty())  break;
            if(checkInit.checkInt(chooseString, 1, 1)){
                   choose = Integer.parseInt(chooseString);
            }
            else break;
        }
    }
    private boolean checkWin(Player opponent){
        boolean checkWin = true;
        for (Ship ship : opponent.getShips()) {
            if (!ship.isAttacked()) {
                checkWin = false;
                break;
            }
        }
        return checkWin;
    }
    public void attack(int turn) {
        boolean endGame = false;
        while (!endGame) {
            chooseRole(turn);
            System.out.print("\n");
            clearTerminal.clear(0);
            if(turn == 1){
                System.out.println("\u001B[36m"+"+=========================================================================+");
                System.out.println("| _____ _   _ ____  _   _      ____  _        _ __   _______ ____       _ |");
                System.out.println("||_   _| | | |  _ \\| \\ | |    |  _ \\| |      / \\\\ \\ / | ____|  _ \\     / ||");
                System.out.println("|  | | | | | | |_) |  \\| |    | |_) | |     / _ \\\\ V /|  _| | |_) |    | ||");
                System.out.println("|  | | | |_| |  _ <| |\\  |    |  __/| |___ / ___ \\\\|/ | |___|  _ <     | ||");
                System.out.println("|  |_|  \\___/|_| \\_|_| \\_|    |_|   |_____/_/   \\_|_| |_____|_| \\_\\    |_||");
                System.out.println("+=========================================================================+"+"\u001B[0m");
            }
            if(turn == 2){
                System.out.println("\u001B[36m"+"+=============================================================================+");
                System.out.println("| _____ _   _ ____  _   _      ____  _        _ __   _______ ____       ____  |");
                System.out.println("||_   _| | | |  _ \\| \\ | |    |  _ \\| |      / \\\\ \\ / | ____|  _ \\     |___ \\ |");
                System.out.println("|  | | | | | | |_) |  \\| |    | |_) | |     / _ \\\\ V /|  _| | |_) |      __) ||");
                System.out.println("|  | | | |_| |  _ <| |\\  |    |  __/| |___ / ___ \\\\| || |___|  _ <      / __/ |");
                System.out.println("|  |_|  \\___/|_| \\_|_| \\_|    |_|   |_____/_/   \\_|_| |_____|_| \\_\\    |_____||");
                System.out.println("+=============================================================================+"+"\u001B[0m");
            }
            firstOption();
            clearTerminal.clear(1000);
            System.out.println("Enter the coordinates you want to shoot: ");
            int x = 0, y = 0;
            boolean enterTrue = false;
            while (!enterTrue) {
                System.out.print("Enter x (1, 2,...): ");
                String xString = sc.nextLine().trim();
                System.out.print("Enter y (A, B,..): ");
                String yChar = sc.nextLine().trim();
                CheckInit checkInit = new CheckInit();
                if(!checkInit.checkChar(yChar) || !checkInit.checkInt(xString, 1, 2)){
                    menu.errorCoord();
                    continue;
                }
                x = Integer.parseInt(xString) - 1;
                char ch = yChar.charAt(0);
                if(ch >= 'a' && ch <= 'z')  ch = (char) (ch + ('A'-'a'));
                y = ch - 'A';
                if(opponent.getPlayerBoard().getValue(y, x, false) != ' '){
                    System.out.println("This coordinate has already been entered, please enter a different one.");
                    continue;
                }
                if(((0 <= x && x <= 9) && (0 <= y && y <= 9))) {
                    enterTrue = true;
                }
                if(!enterTrue) {
                    menu.errorCoord();
                }
            }

            //cot la x, hang la y
            boolean successAttack = false;
            boolean shipWreck = false;
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
            clearTerminal.clear(1000);
            System.out.println("2 board after FIRE:");
            managerPlayer.displayPlayerAndOpponentBoard(player.getPlayerBoard(), opponent.getPlayerBoard());
            if (successAttack) {
                System.out.println("\u001B[32m"+"=====YOU HAVE HIT A PART OF THE SHIP====="+"\u001B[0m");
            }
            else{
                System.out.printf("\u001B[31m"+"Player %d attack failed.\n"+"\u001B[0m", turn);
            }
            if (shipWreck) {
                System.out.println("\u001B[32m"+"===========YOU HAVE HIT A SHIP==========="+"\u001B[0m");
            }
            //Check win
            if (checkWin(opponent)) {
                System.out.printf("\u001B[32m"+"-------------PLAYER %d WIN---------------\n"+"\u001B[0m", turn);
                System.out.println("\u001B[33m"+"+=======================================================+");
                System.out.println("|  ____    _    __  __ _____    _____     _______ ____  |");
                System.out.println("| / ___|  / \\  |  \\/  | ____|  / _ \\ \\   / | ____|  _ \\ |");
                System.out.println("|| |  _  / _ \\ | |\\/| |  _|   | | | \\ \\ / /|  _| | |_) ||");
                System.out.println("|| |_| |/ ___ \\| |  | | |___  | |_| |\\ V / | |___|  _ < |");
                System.out.println("| \\____/_/   \\_|_|  |_|_____|  \\___/  \\_/  |_____|_| \\_\\|");
                System.out.println("+=======================================================+"+"\u001B[0m");
                endGame = true;
            }
            if(endGame == true)  break;
            //switch turn
            if(successAttack) {
                System.out.print("\u001B[34m"+"Please Enter to continue: "+"\u001B[0m");
                String enter = sc.nextLine();
                continue;
            }
            System.out.printf("Turn player %d end. Next turn\n", turn);
            if (turn == 1) turn = 2;
            else if (turn == 2) turn = 1;
            System.out.print("\u001B[34m"+"Please Enter to continue: "+"\u001B[0m");
            String enter = sc.nextLine();
        }
    }
}