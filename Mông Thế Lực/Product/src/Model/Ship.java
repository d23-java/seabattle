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
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.isSunk = false;
        this.direction = direction;
        if(name.contains("Patrol")) symbol = 'P';
        else if(name.contains("Destroyer")) symbol = 'D';
        else if(name.contains("Submarine")) symbol = 'S';
        else if(name.contains("Battle")) symbol = 'B';

    }

    public void setPosition(Coord position) {
        this.position = position;
    }

    public void setDirection(char direction) {
        this.direction = direction;
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
    // Additional methods for ship logic can be added here
}