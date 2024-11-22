// File: src/ShipType.java
public enum ShipType {
    CARRIER(5, 'C'),
    BATTLESHIP(4, 'B'),
    CRUISER(3, 'R'),
    SUBMARINE(3, 'S'),
    DESTROYER(2, 'D');

    private final int size;
    private final char symbol;

    ShipType(int size, char symbol) {
        this.size = size;
        this.symbol = symbol;
    }

    public int getSize() {
        return size;
    }

    public char getSymbol() {
        return symbol;
    }
}