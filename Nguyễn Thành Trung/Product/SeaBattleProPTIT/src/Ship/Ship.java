package Ship;

import System.ComputerSystem;
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
            case 4:
                this.name = "BattleShip";
                this.size = 5;
        }
    }

    public void addShipAndCheckValid() {
        int input = 1;

        while (input <= size) {
            System.out.print("Insert row: ");
            int xAxis = ComputerSystem.scanner.nextInt();
            System.out.print("Insert column: ");
            int yAxis = ComputerSystem.scanner.nextInt();
            axisX.add(xAxis);
            axisY.add(yAxis);
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

    public int getShipID() {
        return shipID;
    }
}
