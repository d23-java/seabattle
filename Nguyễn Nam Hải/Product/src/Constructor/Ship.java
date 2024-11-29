package Constructor;

import java.util.ArrayList;

public class Ship {
    private String nameShip;
    private ArrayList<int[]> coordinates;
    private boolean attacked;
    public Ship(String nameShip, int xLeft, int yLeft, int xRight, int yRight, boolean attacked) {
        this.nameShip = nameShip;
        this.coordinates = new ArrayList<>();
        this.attacked = attacked;
        generateCoordinates(xLeft, yLeft, xRight, yRight);
    }

    private void generateCoordinates(int xLeft, int yLeft, int xRight, int yRight) {
        if (xLeft == xRight) {
            for (int y = yLeft; y <= yRight; ++y) {
                coordinates.add(new int[]{y, xLeft});
            }
        } else if (yLeft == yRight) {
            for (int x = xLeft; x <= xRight; ++x) {
                coordinates.add(new int[]{yLeft, x});
            }
        }
    }

    public ArrayList<int[]> getCoordinates() {
        return coordinates;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public boolean hasOverlapWithOtherShip(ArrayList<Ship> ships) {
        for (Ship ship : ships) {
            if (ship != this) {
                for (int[] coord1 : this.coordinates) {
                    for (int[] coord2 : ship.getCoordinates()) {
                        if (coord1[0] == coord2[0] && coord1[1] == coord2[1]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}