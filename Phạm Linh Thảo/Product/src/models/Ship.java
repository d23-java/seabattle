package models;

import java.util.ArrayList;

public class Ship {
    private String name;
    private int size;
    private ArrayList<int[]> positions;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.positions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void setPositions(ArrayList<int[]> positions) {
        this.positions = positions;
    }

    public boolean hit(int x, int y) {
        for (int i = 0; i < positions.size(); i++) {
            int[] pos = positions.get(i);
            if (pos[0] == x && pos[1] == y) {
                positions.remove(i);
                size--;
                return true;
            }
        }
        return false;
    }
}
