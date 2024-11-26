package model;

public class Coordinate {
    private final int x;
    private final int y;

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

    public static Coordinate fromString(String input) {
        char letter = input.charAt(0);
        int number = Integer.parseInt(input.substring(1));
        return new Coordinate(letter - 'A', number - 1);
    }
}