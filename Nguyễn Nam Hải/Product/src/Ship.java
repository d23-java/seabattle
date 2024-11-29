import java.util.ArrayList;

public class Ship {
    private String nameShip;
    private ArrayList<int[]> coordinates;

    public Ship(String nameShip, int xLeft, int yLeft, int xRight, int yRight) {
        this.nameShip = nameShip;
        this.coordinates = new ArrayList<>();
        generateCoordinates(xLeft, yLeft, xRight, yRight);
    }

    private void generateCoordinates(int xLeft, int yLeft, int xRight, int yRight) {
        if (xLeft == xRight) {
            for (int y = yLeft; y <= yRight; ++y) {
                coordinates.add(new int[]{xLeft, y});
            }
        } else if (yLeft == yRight) {
            for (int x = xLeft; x <= xRight; ++x) {
                coordinates.add(new int[]{x, yLeft});
            }
        }
    }

    public String getNameShip() {
        return nameShip;
    }

    public void setNameShip(String nameShip) {
        this.nameShip = nameShip;
    }

    public ArrayList<int[]> getCoordinates() {
        return coordinates;
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
