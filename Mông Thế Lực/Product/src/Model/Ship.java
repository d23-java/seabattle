package Model;

public class Ship {
    private String name;
    private char symbol;
    private char direction;
    private Coord position;
    private int rowSize;
    private int colSize;
    private boolean isSunk;

    public Ship(String name, Coord position, int rowSize, int colSize, char direction) {
        this.name = name;
        this.position = position;
        this.isSunk = false;
        this.direction = direction;
        this.symbol = name.toUpperCase().charAt(0);
        this.rowSize = rowSize;
        this.colSize = colSize;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setPosition(Coord position) {
        this.position = position;
    }
    public void swapRowCol(){
        int temp = this.rowSize;
        this.rowSize = this.colSize; this.colSize = temp;
    }
    public void setDirection(char direction) {
        this.direction = direction;
        if(Character.toLowerCase(direction) == 'h'){
           swapRowCol();
        }
    }

    public boolean isSunk() {
        return isSunk;
    }

    public void setSunk(boolean sunk) {
        isSunk = sunk;
    }

    public String getName() {
        return name;
    }

    public Coord getPosition() {
        return position;
    }
    public int getRowPosition(){
        return position.getRow();
    }
    public int getColPosition(){
        return position.getCol();
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }
}