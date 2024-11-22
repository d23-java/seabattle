// File: src/Coordinate.java
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Coordinate fromString(String coord) {
        int x = coord.charAt(0) - 'A';
        int y = Integer.parseInt(coord.substring(1)) - 1;
        return new Coordinate(x, y);
    }
}