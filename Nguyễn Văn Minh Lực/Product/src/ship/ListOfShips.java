package ship;

import java.util.ArrayList;

public class ListOfShips {
    public static ArrayList<Ship> list;
    Ship patrolBoat = new Ship(1,2);
    Ship destroyerBoat = new Ship(1,4);
    Ship SubmarineBoat = new Ship(1,3);
    Ship battleShip = new Ship(1,5);

    ListOfShips() {
        list.add(patrolBoat);
        list.add(patrolBoat);
        list.add(destroyerBoat);
        list.add(SubmarineBoat);
        list.add(battleShip);
    }
}
