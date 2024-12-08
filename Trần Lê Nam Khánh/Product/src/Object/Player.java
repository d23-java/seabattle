package Object;

import Color.ConsoleColors;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Player {
    private String name;
    private Board ownBoard;
    private Board fogBoard;
    private Ship[] ships;

    public Player(String name) {
        this.name = name;
        this.ownBoard = new Board();
        this.fogBoard = new Board();
        this.ships = new Ship[5];
    }

    public String getName(){
        return this.name;
    }
    public Board getOwnBoard(){
        return this.ownBoard;
    }
    public Board getFogBoard(){
        return this.fogBoard;
    }

    private boolean isValid(int value, int boardSize) {
        return value >= 0 && value < boardSize;
    }


    public boolean PlaceShip(Ship ship, String start, String end) {
        int startRow = Integer.parseInt(start.substring(1)) - 1; // Convert to zero-based index
        int startCol = start.charAt(0) - 'A';                   // Convert to zero-based index
        int endRow = Integer.parseInt(end.substring(1)) - 1;    // Convert to zero-based index
        int endCol = end.charAt(0) - 'A';                       // Convert to zero-based index

        // Validate the coordinates
        if(!isValid(startRow, endRow) || !isValid(startCol, endCol)){
            return false;
        }

        // Horizontal placement
        if (startRow == endRow) {
            startCol = Math.min(startCol, endCol);
            endCol = Math.max(startCol, endCol);

            // Check for overlap
            for (int i = startCol; i <= endCol; i++) {
                if (ownBoard.getCell(startRow, i).getState() == "S") {
                    return false; // Overlap detected
                }
            }

            // Place the ship
            for (int i = startCol; i <= endCol; i++) {
                ownBoard.getCell(startRow, i).setState("S");
            }
        }
        // Vertical placement
        else if (startCol == endCol) {
            startRow = Math.min(startRow, endRow);
            endRow = Math.max(startRow, endRow);

            // Check for overlap
            for (int i = startRow; i <= endRow; i++) {
                if (ownBoard.getCell(i, startCol).getState() == "S") {
                    return false; // Overlap detected
                }
            }

            // Place the ship
            for (int i = startRow; i <= endRow; i++) {
                ownBoard.getCell(i, startCol).setState("S");
            }
        } else {
            return false; // Invalid placement (not horizontal or vertical)
        }

        return true; // Placement successful
    }

    public boolean placeShip(Ship ship, String start, String end) {
        int startRow = start.charAt(0) - 'A';
        int startCol = Integer.parseInt(start.substring(1)) - 1;
        int endRow = end.charAt(0) - 'A';
        int endCol = Integer.parseInt(end.substring(1)) - 1;

        final int BOARD_SIZE = 10;

        // Validate bounds
        if (!isValid(startRow, BOARD_SIZE) ||
                !isValid(endRow, BOARD_SIZE) ||
                !isValid(startCol, BOARD_SIZE) ||
                !isValid(endCol, BOARD_SIZE)) {
            return false;
        }

        // Check for horizontal placement
        if (startRow == endRow) {
            int minCol = Math.min(startCol, endCol);
            int maxCol = Math.max(startCol, endCol);

            // Ensure the ship fits the size
            if (maxCol - minCol + 1 != ship.getSize()) {
                return false;
            }

            // Check if cells are free
            for (int col = minCol; col <= maxCol; col++) {
                if (ownBoard.getCell(startRow, col).getState().equals("S")) {
                    return false;
                }
            }

            // Place the ship
            for (int col = minCol; col <= maxCol; col++) {
                ownBoard.getCell(startRow, col).setState("S");
            }
        }
        // Check for vertical placement
        else if (startCol == endCol) {
            int minRow = Math.min(startRow, endRow);
            int maxRow = Math.max(startRow, endRow);

            // Ensure the ship fits the size
            if (maxRow - minRow + 1 != ship.getSize()) {
                return false;
            }

            // Check if cells are free
            for (int row = minRow; row <= maxRow; row++) {
                if (ownBoard.getCell(row, startCol).getState().equals("S")) {
                    return false;
                }
            }

            // Place the ship
            for (int row = minRow; row <= maxRow; row++) {
                ownBoard.getCell(row, startCol).setState("S");
            }
        } else {
            // Diagonal placement is not allowed
            return false;
        }

        return true;
    }




    public boolean attack(Player opponent, int row, int col){
        Cell cell = opponent.getOwnBoard().getCell(row, col);
        if(cell.getState() == "S"){
            cell.setState(ConsoleColors.GREEN_BACKGROUND + "X" + ConsoleColors.RESET);
            opponent.getFogBoard().getCell(row, col).setState(ConsoleColors.GREEN_BACKGROUND + "X" + ConsoleColors.RESET);
            return true;
        }
        else {
            cell.setState(ConsoleColors.RED_BACKGROUND + "O" + ConsoleColors.RESET);
            opponent.getFogBoard().getCell(row, col).setState(ConsoleColors.RED_BACKGROUND + "O" + ConsoleColors.RESET);
            return false;
        }
    }

    public boolean hasLost(){
        for(int i = 0 ; i < 10; i++){
            for(int j = 0; j  < 10; j++){
                if(ownBoard.getCell(i, j).getState() == "S"){
                    return false;
                }
            }
        }
        return true;
    }
}
