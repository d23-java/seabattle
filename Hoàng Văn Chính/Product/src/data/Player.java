package data;

import java.util.ArrayList;

public class Player {
    private String name;
    public int numberOfShots;
    private char[][] myTable;
    private ArrayList<Ship> ships = new ArrayList<Ship>();

    public Player() {

    }
    public Player(String name) {
        this.name = name;
        this.numberOfShots = 0;
        this.myTable = new char[11][11];
        for(int j = 1; j < 11; ++j) {
            myTable[0][j] = (char)(j+'0');
        }
        for(int i = 1; i < 11; ++i) {
            myTable[i][0] = (char)(i+'0');
        }
    }
}
