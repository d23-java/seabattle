package Ship;

import java.util.ArrayList;
import System.ComputerSystem;

public class Ship {
    private String name;
    private int size;
    private int shipID;
    private ArrayList<Integer> axisX;
    private ArrayList<Integer> axisY;

    public Ship(int shipID) {
        switch (shipID) {
            case 1:
                this.name = "numberOfPatrolBoat";
                this.size = 2;
                break;
            case 2:
                this.name = "numberOfDestroyerBoat";
                this.size = 4;
                break;
            case 3:
                this.name = "Submarine";
                this.size = 3;
            case 4:
                this.name = "BattleShip";
                this.size = 5;
        }
    }

    public void addCoordinate(Integer xAxis, Integer yAxis) {
        axisX.add(xAxis);
        axisY.add(yAxis);
    }

    public void addShipAndCheckValid() {
        int input = 1;

        while (input <= size) {
            System.out.print("Insert row: ");
            int xAxis = ComputerSystem.scanner.nextInt();
            System.out.print("Insert column: ");
            int yAxis = ComputerSystem.scanner.nextInt();
            addCoordinate(xAxis, yAxis);
            input++;
        }
    }

    public ArrayList<Integer> getAxisX() {
        return axisX;
    }

    public ArrayList<Integer> getAxisY() {
        return axisY;
    }

    public int getSize() {
        return size;
    }
}
