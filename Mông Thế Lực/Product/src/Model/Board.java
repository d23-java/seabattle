import java.util.ArrayList;

public class Board {
    private char[][] board;
    protected ArrayList<Ship> shipList;
    public Board() {
        board = new char[11][11];
        board[0][0] = ' ';
        for(int rowIndex = 1; rowIndex  <= 10;  rowIndex++){
            board[rowIndex][0] = (char)('A' + (rowIndex - 1));
        }
        for(int colIndex = 1; colIndex  <= 10; colIndex++){
            board[0][colIndex] = (char)('0' + colIndex);
        }
        for(int rowIndex = 1; rowIndex <= 10; rowIndex++){
            for(int colIndex = 1; colIndex <= 10; colIndex++){
                board[rowIndex][colIndex] = '-';
            }
        }

        shipList = new ArrayList<>();
        Coord initialCoord = new Coord(0,0);
        char initialDirection = 'V';  
        shipList.add(new Ship("Patrol 1", initialCoord, 1, 2, initialDirection));
        shipList.add(new Ship("Patrol 2", initialCoord, 1, 2,initialDirection));
        shipList.add(new Ship("Destroyer ", initialCoord, 1, 4,initialDirection));
        shipList.add(new Ship("Patrol 2", initialCoord, 1, 3, initialDirection));
        shipList.add(new Ship("Patrol 2", initialCoord, 1, 5, initialDirection));
    }

    public boolean isAllShipsSunk() {
        for (Ship ship : shipList) {
            if (!ship.isSunk()) return false;
        }
        return true;
    }

    public void placeShip(Ship ship, Coord shipPosition, char shipDirection){
        ship.setDirection(shipDirection);
        ship.setPosition(shipPosition);
    }

    public boolean fire(Coord coord) {
        int col = coord.getCol(), row = coord.getRow();
        if(board[col][row] == 'O' || board[col][row] == '~') {
            board[col][row] = 'O';
            return true;
        }
        board[col][row] = 'X';
        return false;
    }
}