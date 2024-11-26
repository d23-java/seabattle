package Model;

import java.util.ArrayList;
import View.GameView;

public class Player {
    private char[][] board;
    private String name;
    private PlayerMapManager mapManager;
    ArrayList<Ship> shipList;
    public Player(){

    }
    public Player(String name) {
        int boardSize = 11;
        this.name = name;
        this.board = new char[boardSize][boardSize];
        this.mapManager = new PlayerMapManager();
        shipList = new ArrayList<>();
        mapManager.createPlayerMap(board, shipList);
    }
    public void placeShip(){
        System.out.println(name + " placing ship");
        mapManager.placeAllShip(shipList, board);
    }
    public void fire() {
        boolean fireRes = mapManager.fire(GameView.getCoord(), board);
        GameView.displayFireResult(fireRes);
        mapManager.updateShipListStatus(shipList, board);
    }

    public boolean isLoose(){
        for(Ship ship: shipList){
            if(!ship.isSunk()) return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public char[][] getBoard() {
        return board;
    }


}

