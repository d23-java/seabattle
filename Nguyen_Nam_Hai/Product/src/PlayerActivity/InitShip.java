package PlayerActivity;
import Constructor.Ship;
import Constructor.ShipType;
import GamePlay.Menu;
import java.util.*;
import GamePlay.ClearTerminal;
public class InitShip {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Ship> ships;
    private Menu menu;
    private CheckInit checkInit;
    private ClearTerminal clearTerminal;
    public InitShip(ArrayList<Ship> ships) {
        this.ships = ships;
        clearTerminal = new ClearTerminal();
        checkInit = new CheckInit();
        menu = new Menu();
    }
    private boolean validateCoordinates(int[] beforeCoords, int[] afterCoords, int area) {
        int xBefore = beforeCoords[0], yBefore = beforeCoords[1];
        int xAfter = afterCoords[0], yAfter = afterCoords[1];
        int calculatedArea = (Math.abs(xAfter - xBefore) + 1) * (Math.abs(yAfter - yBefore) + 1);
        return calculatedArea == area;
    }
    private void enterBeforeCoordinates(int[] coordinates) {
        int x = 0, y = 0;
        boolean enterTrue = false;
        while(!enterTrue) {
            System.out.print("Enter x (1, 2,...): ");
            String xString = sc.nextLine().trim();
            System.out.print("Enter y (A, B,..): ");
            String yChar = sc.nextLine().trim();
            if(!checkInit.checkChar(yChar) || !checkInit.checkInt(xString, 1, 2)){
                menu.errorCoord();
                continue;
            }
            x = Integer.parseInt(xString) - 1;
            char ch = yChar.charAt(0);
            if(ch >= 'a' && ch <= 'z')  ch = (char) (ch + ('A'-'a'));
            y = ch - 'A';
            if(((0 <= x && x <= 9) && (0 <= y && y <= 9))) {
                enterTrue = true;
            }
            if(!enterTrue) {
                menu.errorCoord();
            }
        }
        coordinates[0] = x;
        coordinates[1] = y;
    }

    private ShipType determineShipType(int turn) {
        ShipType shipType = null;
        if (turn == 1) shipType = new ShipType("Patrol Boat 1 (Size: 1x2)", 2);
        if (turn == 2) shipType = new ShipType("Patrol Boat 2 (Size: 1x2)", 2);
        if (turn == 3) shipType = new ShipType("Submarine (Size: 1x4)", 4);
        if (turn == 4) shipType = new ShipType("Destroyer Boat (Size: 1x3)", 3);
        if (turn == 5) shipType = new ShipType("Battle Ship (Size: 1x5)", 5);
        return shipType;
    }

    public void placeShips(int numberOfPlayer) {
        for (int turn = 1; turn <= 5; turn++) {
            ShipType ship = determineShipType(turn);
            String shipName = ship.getName();
            int area = ship.getArea();
            boolean enterTrue = true;
            String chooseOrientation = "";
            int choose = 0;
            while(enterTrue){
                clearTerminal.clear(2000);
                System.out.println("Enter data of Player "+ numberOfPlayer + ":");
                menu.directionShip(shipName);
                System.out.print("Enter your choice: ");
                chooseOrientation = sc.nextLine();
                if(!checkInit.checkInt(chooseOrientation,1 ,1)){
                    System.out.println("Please enter again. You need enter from 1 to 2.");
                    continue;
                }
                choose = Integer.parseInt(chooseOrientation);
                if(choose < 0 || choose > 2){
                    System.out.println("Please choose again");
                }
                else enterTrue = false;
            }
            String firstDirection = "", afterDirection = "";
            if(choose == 1) {
                firstDirection = "TOP";
                afterDirection = "BOTTOM";
            }
            if(choose == 2) {
                firstDirection = "LEFT";
                afterDirection = "RIGHT";
            }
            //
            System.out.printf("Enter coordinates for %s:\n", shipName);
            int[] beforeCoords = new int[2];
            int[] afterCoords = new int[2];
            System.out.printf("Enter the coordinates for the "+"\u001B[31m"+"%s"+"\u001B[0m"+" cell (x, y): \n", firstDirection);
            enterBeforeCoordinates(beforeCoords);
            //Check firstDirection
            if(firstDirection.equals("LEFT")) {
                int xNext = beforeCoords[0] + area;
                if(!(xNext >= 1 && xNext <= 10)){
                    System.out.println("\u001B[31m"+"Invalid coordinates, please try again!" + "\u001B[0m");
                    turn--;
                    continue;
                }
            }
            if(firstDirection.equals("TOP")) {
                int yBefore = beforeCoords[1] + area - 1;
                if(!(yBefore >= 0 && yBefore <= 9)){
                    System.out.println("\u001B[31m"+"Invalid coordinates, please try again!"+ "\u001B[0m");
                    turn--;
                    continue;
                }
            }
            //
            if(choose == 1) {//theo dang cot
                afterCoords[0] = beforeCoords[0];
                afterCoords[1] = beforeCoords[1] + area - 1;
            }
            if(choose == 2) {//theo hang
                afterCoords[0] = beforeCoords[0] + area - 1;
                afterCoords[1] = beforeCoords[1];
            }
            //
            if (validateCoordinates(beforeCoords, afterCoords, area)) {
                Ship newShip = new Ship(shipName, beforeCoords, afterCoords, false);
                if (newShip.hasOverlapWithOtherShip(ships)) {
                    System.out.println("\u001B[31m"+"These coordinates overlap with another ship. Please try again."+ "\u001B[0m");
                }
                else {
                    System.out.printf("The coordinates for the "+"\u001B[31m"+"%s"+"\u001B[0m"+" cell (x, y): (%d, %c)\n",afterDirection, afterCoords[0] + 1, 'A' + afterCoords[1]);
                    ships.add(newShip);
                    System.out.printf("\u001B[32m" + "%s added successfully!\n"+ "\u001B[0m", shipName);
                    continue;
                }
            }
            else {
                System.out.println("\u001B[31m"+"Invalid coordinates, please try again!"+ "\u001B[0m");
            }
            turn--;
        }
    }
}