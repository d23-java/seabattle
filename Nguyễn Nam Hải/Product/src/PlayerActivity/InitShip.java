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
    private boolean validateCoordinates(int xBefore, int yBefore, int xAfter, int yAfter, int area) {
        if (!((0 <= xBefore && xBefore <= xAfter && xAfter <= 9) && (0 <= yBefore && yBefore <= yAfter && yAfter <= 9))) {
            return false;
        }
        int calculatedArea = (Math.abs(xAfter - xBefore) + 1) * (Math.abs(yAfter - yBefore) + 1);
        return calculatedArea == area;
    }

    private void enterAfterCoordinates(int[] coordinates) {
        int x = 0, y = 0;
        boolean enterTrue = false;
        while(!enterTrue) {
            System.out.print("Enter x (1, 2,..., 10): ");
            String xString = sc.nextLine().trim();
            System.out.print("Enter y (A, B,..., J): ");
            char yChar = sc.nextLine().charAt(0);
            x = Integer.parseInt(xString);
            y = yChar - 'A';
            x-=1;
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
    private void enterBeforeCoordinates(int[] coordinates, String direction, int area) {
        int x = 0, y = 0;
        boolean enterTrue = false;
        while(!enterTrue) {
            System.out.print("Enter x (1, 2,...): ");
            String xString = sc.nextLine().trim();
            System.out.print("Enter y (A, B,..): ");
            char yChar = sc.nextLine().charAt(0);
            x = Integer.parseInt(xString);
            y = yChar - 'A';
            x-=1;
            if(((0 <= x && x <= 9) && (0 <= y && y <= 9))) {
                enterTrue = true;
            }
            if(!enterTrue) {
                menu.errorCoord();
            }
        }
        coordinates[0] = x;
        coordinates[1] = y;
        if(direction.equals("LEFT")){ //yLeft = yRight
            int xNext = x + area;
            if(xNext >= 1 && xNext <= 10){
                System.out.printf("Suggest enter the coordinate for RIGHT: (%d, %c)\n", xNext, 'A'+y);
            }
        }
        if(direction.equals("TOP")){
            int yBefore = y + area - 1;
            if(yBefore >= 0 && yBefore <= 9){
                System.out.printf("Suggest enter the coordinate for BOTTOM: (%d, %c)\n", x+1, 'A'+yBefore);
            }
        }
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

            String beforeCoord = "";
            String afterCoord = "";

            if(chooseOrientation == 1) {
                beforeCoord = "TOP";
                afterCoord = "BOTTOM";
            }
            if(chooseOrientation == 2) {
                beforeCoord = "LEFT";
                afterCoord = "RIGHT";
            }

            System.out.printf("Enter coordinates for %s:\n", shipName);
            // Coords[0] is x & Coords[1] is y
            int[] beforeCoords = new int[2];
            int[] afterCoords = new int[2];
            System.out.printf("Enter the coordinates for the %s cell (x, y): \n", beforeCoord);
            enterBeforeCoordinates(beforeCoords, beforeCoord, area);
            //Check beforeCoord
            if(beforeCoord.equals("LEFT")) {
                int xNext = beforeCoords[0] + area;
                if(!(xNext >= 1 && xNext <= 10)){
                    System.out.println("Invalid coordinates, please try again!");
                    turn--;
                    continue;
                }
            }
            if(beforeCoord.equals("TOP")) {
                int yBefore = beforeCoords[1] + area - 1;
                if(!(yBefore >= 0 && yBefore <= 9)){
                    System.out.println("Invalid coordinates, please try again!");
                    turn--;
                    continue;
                }
            }

            System.out.printf("Enter the coordinates for the %s cell (x, y): \n", afterCoord);
            enterAfterCoordinates(afterCoords);

            int xBefore = beforeCoords[0], yBefore = beforeCoords[1];
            int xAfter = afterCoords[0], yAfter = afterCoords[1];

            if (validateCoordinates(xBefore, yBefore, xAfter, yAfter, area)) {
                Ship newShip = new Ship(shipName, xBefore, yBefore, xAfter, yAfter, false);
                if((chooseOrientation == 2 && (yBefore != yAfter)) || (chooseOrientation == 1 && (xBefore != xAfter))) {
                    System.out.println("Invalid coordinates, please try again!");
                }
                else if (newShip.hasOverlapWithOtherShip(ships)) {
                    System.out.println("These coordinates overlap with another ship. Please try again.");

                } else {
                    ships.add(newShip);
                    System.out.printf("%s added successfully!\n", shipName);
                    continue;
                }
            } else {
                System.out.println("Invalid coordinates, please try again!");
            }
            turn--;
        }
    }
}