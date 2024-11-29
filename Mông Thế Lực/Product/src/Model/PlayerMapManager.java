package Model;

import View.GameView;

import java.util.ArrayList;

public class PlayerMapManager {
    public void insertShip(ArrayList<Ship> shipList){
        Coord initialCoord = new Coord(0,0);
        char initialDirection = 'V';
        shipList.add(new Ship("Patrol 1", initialCoord, 1, 2, initialDirection));
        shipList.add(new Ship("Patrol 2", initialCoord, 1, 2,initialDirection));
        shipList.add(new Ship("Destroyer ", initialCoord, 1, 4,initialDirection));
        shipList.add(new Ship("Submarine", initialCoord, 1, 3, initialDirection));
        shipList.add(new Ship("Battle Ship", initialCoord, 1, 5, initialDirection));
    }
    public void createPlayerMap(char [][] board, ArrayList<Ship> shipList){
        int sizeBoard = 11;
        for(int rowIndex = 1; rowIndex < sizeBoard; rowIndex++){
            for(int colIndex = 1; colIndex < sizeBoard; colIndex++){
                board[rowIndex][colIndex] = '~';
            }
        }
        insertShip(shipList);
    }
    public void placeAllShip(ArrayList<Ship> shipList,  char [][] board){
        for(Ship ship: shipList){
            System.out.println("PLacing " + ship.getName());
            placeShip(ship, GameView.getCoord(), GameView.getShipDirection());
        }
        for(Ship ship: shipList){
            int rowPosition = ship.getRowPosition();
            int colPosition = ship.getColPosition();
            int rowSize = ship.getRowSize();
            int colSize = ship.getColSize();
            for(int rowIndex = rowPosition; rowIndex < rowPosition + rowSize; rowIndex++){
                for(int colIndex = colPosition; colIndex < colPosition + colSize; colIndex++){
                    board[rowIndex][colIndex] = ship.getSymbol();
                }
            }
        }
        GameView.displayBoard(board);
    }
    public void placeShip(Ship ship, Coord shipPosition, char shipDirection){
        ship.setDirection(shipDirection);
        ship.setPosition(shipPosition);

    }
    public boolean fire(Coord coord, char [][] board){
        int colPos = coord.getCol(), rowPos = coord.getRow();
        if(board[rowPos][colPos] == 'O' || board[rowPos][colPos] == '~') {
          board[rowPos][colPos] = 'O';
          return false;
        }
        board[rowPos][colPos] = 'X';
        return true;
    }
    public void updateShipStatus(Ship ship, char [][] board){
        int hittedCell = 0;
        int rowPos = ship.getRowPosition();
        int colPos = ship.getColPosition();
        int rowSize =ship.getRowSize();
        int colSize = ship.getColSize();
        for(int rowIndex = rowPos; rowIndex < rowPos + rowSize; rowIndex ++ ){
            for(int colIndex = colPos; colIndex < colPos + colSize; colIndex ++){
                if(board[rowIndex][colIndex] == 'X') hittedCell++;
            }
        }
        ship.setSunk(hittedCell == rowSize * colSize);
    }
    public void updateShipListStatus(ArrayList<Ship> shipList, char [][] board){
        for(Ship ship: shipList){
            updateShipStatus(ship, board);
        }
    }
}
