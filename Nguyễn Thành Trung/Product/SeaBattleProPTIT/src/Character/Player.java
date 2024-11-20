package Character;

import MapResources.Board;
import MapResources.ObjectMap;
import Ship.Ship;
import System.ComputerSystem;
import java.util.ArrayList;

public class Player {
    private Board playerBoard = new Board();
    private ObjectMap playerObjectMap = new ObjectMap();
    private Board enemyFoggyBoard = new Board();
    private int numberOfPatrolBoat = 2;
    private int numberOfDestroyerBoat = 1;
    private int numberOfSubmarine = 1;
    private int numberOfBattleShip = 1;
    private int totalShipNumber = 5;
    private String ANSIcode;
    private ArrayList<Ship> shipArrayList = new ArrayList<>();

    public void insertShip() {
        while (totalShipNumber == 5) {
            System.out.println("Insert type of ship: ");
            System.out.println("1. PatrolBoat");
            System.out.println("2. DestroyerBoat");
            System.out.println("3. Submarine");
            System.out.println("4. BattleShip");

            int shipID = ComputerSystem.scanner.nextInt();
            switch (shipID) {
                case 1:
                    if (numberOfPatrolBoat == 0) {
                        System.out.println("No Patrol Boat left!!!");
                        ComputerSystem.scanner.next();
                        continue;
                    }
                    numberOfPatrolBoat--;
                    ANSIcode = "100";
                    break;
                case 2:
                    if (numberOfDestroyerBoat == 0) {
                        System.out.println("No Destroyer Boat left!!!");
                        ComputerSystem.scanner.next();
                        continue;
                    }
                    numberOfDestroyerBoat--;
                    ANSIcode = "105";
                    break;
                case 3:
                    if (numberOfSubmarine == 0) {
                        System.out.println("No Submarine left!!!");
                        ComputerSystem.scanner.next();
                        continue;
                    }
                    numberOfSubmarine--;
                    ANSIcode = "103";
                    break;
                case 4:
                    if (numberOfBattleShip == 0) {
                        System.out.println("No Battle Ship left!!!");
                        ComputerSystem.scanner.next();
                        continue;
                    }
                    numberOfBattleShip--;
                    ANSIcode = "107";
                    break;
            }

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
                setPlayerBoard(xAxis, yAxis, newShip.getShipID(), ANSIcode);
                ComputerSystem.clearScreen();
                showPlayerBoard();
            }
            shipArrayList.add(newShip);
        }
    }

    public void setPlayerBoard(int xAxis, int yAxis, int valueOfCell, String ANSIcode) {
        playerBoard.setBoardCell(xAxis, yAxis, "" + valueOfCell, ANSIcode);
        playerObjectMap.setObjectMapCell(xAxis, yAxis, 2);
    }

    public void setEnemyFoggyBoard(int xAxis, int yAxis, String missCheck, String ANSIcode) {
        enemyFoggyBoard.setBoardCell(xAxis, yAxis, missCheck, ANSIcode);
    }

    public void showEnemyFoggyBoard() {
        enemyFoggyBoard.showBoard();
    }

    public ObjectMap getPlayerObjectMap() {
        return playerObjectMap;
    }

    public void showPlayerBoard() {
        playerBoard.showBoard();
    }

    public void checkHitOrMiss(int xAxis, int yAxis) {
        if (playerObjectMap.getObjectMapCell(xAxis, yAxis) == 2) {
            enemyFoggyBoard.setBoardCell(xAxis, yAxis, "O", "42");
        } else if (playerObjectMap.getObjectMapCell(xAxis, yAxis) == 0) {
            enemyFoggyBoard.setBoardCell(xAxis, yAxis, "X", "41");
        }
        playerObjectMap.setObjectMapCell(xAxis, yAxis, 3);
    }
}
