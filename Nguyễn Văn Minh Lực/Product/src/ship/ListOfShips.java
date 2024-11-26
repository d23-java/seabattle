package ship;

import java.util.ArrayList;

public class ListOfShips {
    public static ArrayList<Ship> list;
    private Ship patrolBoat1 = new Ship(2,"\uD83D\uDC24");
    private Ship patrolBoat2 = new Ship(2,"\uD83D\uDC25");
    private Ship destroyerBoat = new Ship(4,"\uD83D\uDC26");
    private Ship SubmarineBoat = new Ship(3,"\uD83D\uDC27");
    private Ship battleShip = new Ship(5,"\uD83D\uDC14");

    public ListOfShips() {
        list = new ArrayList<>();
        list.add(patrolBoat1);
        list.add(patrolBoat2);
        list.add(destroyerBoat);
        list.add(SubmarineBoat);
        list.add(battleShip);
    }
}
