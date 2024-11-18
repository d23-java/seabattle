package Character;

import MapResources.Board;
import Ship.Ship;
import Ship.ShipManager;

import java.util.ArrayList;

public class Player1 {
    public void setPlayer1Board() {
        Board player1Board = new Board();
        ShipManager player1ShipManager = new ShipManager();
        ArrayList<Ship> player1ShipList = player1ShipManager.getShipArrayList();
        for (Ship ship : player1ShipList) {
            ArrayList<Integer> XAxis = ship.getAxisX();
            ArrayList<Integer> YAxis = ship.getAxisY();
            int shipSize = ship.getSize();
            for (int coordinate = 0; coordinate < shipSize; coordinate++) {

            }
        }
    }
}
