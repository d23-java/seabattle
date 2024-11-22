package ship;

import java.util.ArrayList;

public class Ship {
    private String name;
    private int size;
    private int shipID;
    private ArrayList<Integer> axisX = new ArrayList<>();
    private ArrayList<Integer> axisY = new ArrayList<>();

    public Ship(int shipID) {
        this.shipID = shipID;
        switch (shipID) {
            case 1:
                this.name = "PatrolBoat";
                this.size = 2;
                break;
            case 2:
                this.name = "DestroyerBoat";
                this.size = 4;
                break;
            case 3:
                this.name = "Submarine";
                this.size = 3;
                break;
            case 4:
                this.name = "BattleShip";
                this.size = 5;
                break;
        }
    }

    public void addCoordinate(int xAxis, int yAxis) {
        axisX.add(xAxis);
        axisY.add(yAxis);
    }

    public ArrayList<Integer> getAxisX() {
        return axisX;
    }

    public ArrayList<Integer> getAxisY() {
        return axisY;
    }

    public void romveXAxis(int value) {
        axisX.remove(Integer.valueOf(value));
    }

    public void romveYAxis(int value) {
        axisY.remove(Integer.valueOf(value));
    }

    public int getSize() {
        return size;
    }

    public int getShipID() {
        return shipID;
    }

    public String getName() {
        return name;
    }
}
