package ship;

import java.util.ArrayList;

public class ListOfShips {
    public static ArrayList<Ship> list;
    private Ship patrolBoat1 = new Ship(2,"P");
    private Ship patrolBoat2 = new Ship(2,"p");
    private Ship destroyerBoat = new Ship(4,"D");
    private Ship submarineBoat = new Ship(3,"S");
    private Ship battleShip = new Ship(5,"B");

    public ListOfShips() {
        list = new ArrayList<>();
        list.add(patrolBoat1);
        list.add(patrolBoat2);
        list.add(destroyerBoat);
        list.add(submarineBoat);
        list.add(battleShip);
    }
}
