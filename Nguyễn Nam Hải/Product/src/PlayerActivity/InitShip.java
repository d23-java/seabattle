package PlayerActivity;

import Constructor.Ship;
import Constructor.ShipType;
import GamePlay.Menu;

import java.util.*;

public class InitShip {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Ship> ships;
    private Menu menu;
    public InitShip(ArrayList<Ship> ships) {
        this.ships = ships;
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
            char yChar = sc.nextLine().charAt(0);
            x = Integer.parseInt(xString) - 1;
            y = yChar - 'A';
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

    public void placeShips() {
        for (int turn = 1; turn <= 5; turn++) {
            ShipType ship = determineShipType(turn);
            String shipName = ship.getName();
            int area = ship.getArea();
            menu.directionShip(shipName);
            boolean enterTrue = true;
            int chooseOrientation = -1;
            while(enterTrue){
                System.out.print("Enter your choice: ");
                chooseOrientation = Integer.parseInt(sc.nextLine());
                if(chooseOrientation < 0 || chooseOrientation > 2){
                    System.out.println("Please choose again");
                }
                else enterTrue = false;
            }
            //
            String firstDirection = "", afterDirection = "";
            if(chooseOrientation == 1) {
                firstDirection = "TOP";
                afterDirection = "BOTTOM";
            }
            if(chooseOrientation == 2) {
                firstDirection = "LEFT";
                afterDirection = "RIGHT";
            }
            //
            System.out.printf("Enter coordinates for %s:\n", shipName);
            int[] beforeCoords = new int[2];
            int[] afterCoords = new int[2];
            System.out.printf("Enter the coordinates for the %s cell (x, y): \n", firstDirection);
            enterBeforeCoordinates(beforeCoords);

            //Check firstDirection
            if(firstDirection.equals("LEFT")) {
                int xNext = beforeCoords[0] + area;
                if(!(xNext >= 1 && xNext <= 10)){
                    System.out.println("Invalid coordinates, please try again!");
                    turn--;
                    continue;
                }
            }
            if(firstDirection.equals("TOP")) {
                int yBefore = beforeCoords[1] + area - 1;
                if(!(yBefore >= 0 && yBefore <= 9)){
                    System.out.println("Invalid coordinates, please try again!");
                    turn--;
                    continue;
                }
            }

            //
            if(chooseOrientation == 1) {//theo dang cot
                afterCoords[0] = beforeCoords[0];
                afterCoords[1] = beforeCoords[1] + area - 1;
            }
            if(chooseOrientation == 2) {//theo hang
                afterCoords[0] = beforeCoords[0] + area - 1;
                afterCoords[1] = beforeCoords[1];
            }
            System.out.printf("The coordinates for the %s cell: (%d, %c)\n", afterDirection, afterCoords[0] + 1, 'A' + afterCoords[1]);
            //
            if (validateCoordinates(beforeCoords, afterCoords, area)) {
                Ship newShip = new Ship(shipName, beforeCoords, afterCoords, false);
                if (newShip.hasOverlapWithOtherShip(ships)) {
                    System.out.println("These coordinates overlap with another ship. Please try again.");
                }
                else {
                    ships.add(newShip);
                    System.out.printf("%s added successfully!\n", shipName);
                    continue;
                }
            }
            else {
                System.out.println("Invalid coordinates, please try again!");
            }
            turn--;
        }
    }
}