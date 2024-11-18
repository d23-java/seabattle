package Ship;

import System.ComputerSystem;

import java.util.ArrayList;

public class ShipManager {
    private int numberOfPatrolBoat = 2;
    private int numberOfDestroyerBoat = 1;
    private int numberOfSubmarine = 1;
    private int numberOfBattleShip = 1;
    private ArrayList<Ship> shipArrayList = new ArrayList<>();

    public void insertTypeOfShip() {
        System.out.println("Insert type of ship: ");
        int shipID = ComputerSystem.scanner.nextInt();
        Ship newShip = new Ship(shipID);
        newShip.addShipAndCheckValid();
        shipArrayList.add(newShip);
    }

    public ArrayList<Ship> getShipArrayList() {
        return shipArrayList;
    }

}
