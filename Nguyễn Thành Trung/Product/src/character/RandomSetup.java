package character;

import java.util.ArrayList;
import mapresources.Board;
import mapresources.ObjectMap;
import ship.Ship;
import system.ComputerSystem;

public class RandomSetup {

    private Board playerBoard = new Board();
    private ObjectMap playerObjectMap = new ObjectMap();
    ArrayList<Ship> shipArrayList = new ArrayList<Ship>();

    public void RandomInsertShip() {
        int[] shipSize = {2, 4, 3, 5};
        int[] numberOfShip = {2, 1, 1, 1};
        String[] ANSIcode = {"100", "105", "103", "107"};

        for (int indexOfShip = 0; indexOfShip < shipSize.length; indexOfShip++) {
            while (numberOfShip[indexOfShip] > 0) {
                Ship newShip = new Ship(indexOfShip + 1);
                boolean checkValid = true;
                int xStart = ComputerSystem.random.nextInt(Board.boardSize - 1) + 1;
                int yStart = ComputerSystem.random.nextInt(Board.boardSize - 1) + 1;

                //if true -> setup follow x axis
                //else -> setup follow y axis
                boolean direction  = ComputerSystem.random.nextBoolean();

                int xAxis = 0;
                int yAxis = 0;
                for (int cellMove = 0; cellMove < shipSize[indexOfShip]; ++cellMove) {
                    if (direction) {
                        xAxis = xStart + cellMove;
                        yAxis = yStart;
                    } else {
                        xAxis = xStart;
                        yAxis = yStart + cellMove;
                    }
                    if (!ComputerSystem.checkValidCoordinate(xAxis, yAxis)) {
                            checkValid = false;
                            break;
                    } else if (ComputerSystem.checkValidCoordinate(xAxis, yAxis)) {
                        if (playerObjectMap.getObjectMapCell(xAxis, yAxis) != 0) {
                            checkValid = false;
                            break;
                        }
                    }
                }
                if (checkValid == true) {
                    xAxis = 0;
                    yAxis = 0;
                    for (int cellMove = 0; cellMove < shipSize[indexOfShip]; ++cellMove) {
                        if (direction) {
                            xAxis = xStart + cellMove;
                            yAxis = yStart;
                        } else {
                            yAxis = yStart + cellMove;
                            xAxis = xStart;
                        }

                        playerObjectMap.setObjectMapCell(xAxis, yAxis, newShip.getShipID());
                        playerBoard.setBoardCell(xAxis, yAxis, "" + newShip.getShipID(), ANSIcode[indexOfShip]);
                        newShip.addCoordinate(xAxis, yAxis);
                    }
                    numberOfShip[indexOfShip]--;
                    shipArrayList.add(newShip);
                }
            }
        }
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public ObjectMap getPlayerObjectMap() {
        return playerObjectMap;
    }

    public ArrayList<Ship> getShipArrayList() {
        return shipArrayList;
    }

}
