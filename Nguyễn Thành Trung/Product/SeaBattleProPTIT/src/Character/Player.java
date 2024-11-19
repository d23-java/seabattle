package Character;

import MapResources.Board;
import MapResources.ObjectMap;
import Ship.Ship;
import System.ComputerSystem;
import java.util.ArrayList;

public class Player {
    private Board playerBoard = new Board();
    private ObjectMap playerObjectMap = new ObjectMap();
    private int numberOfPatrolBoat = 2;
    private int numberOfDestroyerBoat = 1;
    private int numberOfSubmarine = 1;
    private int numberOfBattleShip = 1;
    private ArrayList<Ship> shipArrayList = new ArrayList<>();

    public void insertShip() {
        System.out.println("Insert type of ship: ");
        System.out.println("1. PatrolBoat");
        System.out.println("2. DestroyerBoat");
        System.out.println("3. Submarine");
        System.out.println("4. BattleShip");
        int shipID = ComputerSystem.scanner.nextInt();
        ComputerSystem.clearScreen();
        showPlayerBoard();

        Ship newShip = new Ship(shipID);
        int shipSize = newShip.getSize();
        for (int input = 0; input < shipSize; ++input) {
            System.out.print("Insert " + newShip.getName() + " row: ");
            int xAxis = ComputerSystem.scanner.nextInt();
            System.out.print("Insert " + newShip.getName() + " column: ");
            int yAxis = ComputerSystem.scanner.nextInt();
            newShip.addCoordinate(xAxis, yAxis);
            setPlayerBoard(xAxis, yAxis, newShip.getShipID(), "42");
            ComputerSystem.clearScreen();
            showPlayerBoard();
        }
        shipArrayList.add(newShip);
    }

    public void setPlayerBoard(int xAxis, int yAxis, int valueOfCell, String ANSIcode) {
        playerBoard.setBoardCell(xAxis, yAxis, "" + valueOfCell, ANSIcode);
        playerObjectMap.setObjectMapCell(xAxis, yAxis, 1);
    }

    public ObjectMap getPlayerObjectMap() {
        return playerObjectMap;
    }

    public void showPlayerBoard() {
        playerBoard.showBoard();
    }
}
