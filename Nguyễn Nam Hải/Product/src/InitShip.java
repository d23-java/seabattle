import java.util.*;

public class InitShip {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Ship> ships;
    public InitShip(ArrayList<Ship> ships) {
        this.ships = ships;
    }
    private boolean validateCoordinates(int xLeft, int yLeft, int xRight, int yRight, int area) {
        if (!((0 <= xLeft && xLeft <= xRight && xRight <= 9) && (0 <= yLeft && yLeft <= yRight && yRight <= 9))) {
            return false;
        }
        int calculatedArea = (Math.abs(xRight - xLeft) + 1) * (Math.abs(yRight - yLeft) + 1);
        return calculatedArea == area;
    }

    private void enterCoordinates(int[] coordinates) {
        int x = 0, y = 0;
        boolean enterTrue = false;
        while(!enterTrue) {
            System.out.print("Enter x (1, 2,...): ");
            x = Integer.parseInt(sc.nextLine()) - 1;
            System.out.print("Enter y (A, B,..): ");
            char yChar = sc.nextLine().charAt(0);
            y = yChar - 'A';
            if(((0 <= x && x <= 9) && (0 <= y && y <= 9))) {
                enterTrue = true;
            }
            if(!enterTrue) {
                System.out.println("x must be in the range from 1 to 10");
                System.out.println("y must be in the range from A to K");
                System.out.println("Please enter the coordinates again: ");
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
        for (int turn = 1; turn <= 5; ++turn) {
            ShipType ship = determineShipType(turn);
            String shipName = ship.getName();
            int area = ship.getArea();

            System.out.printf("Enter coordinates for %s:\n", shipName);
            // Coords[0] is x & Coords[1] is y
            int[] leftCoords = new int[2];
            int[] rightCoords = new int[2];

            System.out.println("Enter the coordinates for the left cell (x, y): ");
            enterCoordinates(leftCoords);

            System.out.println("Enter the coordinates for the right cell (x, y): ");
            enterCoordinates(rightCoords);

            int xLeft = leftCoords[0], yLeft = leftCoords[1];
            int xRight = rightCoords[0], yRight = rightCoords[1];

            if (validateCoordinates(xLeft, yLeft, xRight, yRight, area)) {
                Ship newShip = new Ship(shipName, xLeft, yLeft, xRight, yRight);
                if (newShip.hasOverlapWithOtherShip(ships)) {
                    System.out.println("These coordinates overlap with another ship. Please try again.");
                    turn--;
                } else {
                    ships.add(newShip);
                    System.out.println("Ship added successfully!");
                }
            } else {
                System.out.println("Invalid coordinates, please try again!");
                turn--;
            }
        }
    }
}
