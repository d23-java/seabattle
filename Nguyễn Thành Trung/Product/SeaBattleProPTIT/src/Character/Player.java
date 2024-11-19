package Character;

import MapResources.Board;
import MapResources.ObjectMap;
import Ship.Ship;
import Ship.ShipManager;
import java.util.ArrayList;

public class Player {
    private Board playerBoard = new Board();
    private ObjectMap playerObjectMap = new ObjectMap();
    private ShipManager shipManager = new ShipManager();

    public void addShip() {
        shipManager.insertTypeOfShip();
    }

    public void setPlayerBoard() {
        ArrayList<Ship> playerShipList = shipManager.getShipArrayList();
        for (Ship ship : playerShipList) {
            ArrayList<Integer> XAxis = ship.getAxisX();
            ArrayList<Integer> YAxis = ship.getAxisY();
            int shipSize = ship.getSize();
            for (int coordinate = 0; coordinate < shipSize; coordinate++) {
                playerBoard.setBoardCell(XAxis.get(coordinate), YAxis.get(coordinate), ""+ship.getShipID(), "42");
                playerObjectMap.setObjectMapCell(XAxis.get(coordinate), YAxis.get(coordinate), 1);
            }
        }
    }

    public ObjectMap getPlayerObjectMap() {
        return playerObjectMap;
    }

    public void showPlayerBoard() {
        playerBoard.showBoard();
    }
}
