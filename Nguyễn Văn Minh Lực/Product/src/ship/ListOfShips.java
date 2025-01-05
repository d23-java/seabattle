package ship;

import java.util.ArrayList;

public class ListOfShips {
    public static ArrayList<Ship> list;
<<<<<<< HEAD
    private Ship patrolBoat1 = new Ship(2,"P");
    private Ship patrolBoat2 = new Ship(2,"p");
    private Ship destroyerBoat = new Ship(4,"D");
    private Ship submarineBoat = new Ship(3,"S");
    private Ship battleShip = new Ship(5,"B");
=======
    private Ship patrolBoat1 = new Ship(2,"\uD83D\uDC24");
    private Ship patrolBoat2 = new Ship(2,"\uD83D\uDC25");
    private Ship destroyerBoat = new Ship(4,"\uD83D\uDC26");
    private Ship SubmarineBoat = new Ship(3,"\uD83D\uDC27");
    private Ship battleShip = new Ship(5,"\uD83D\uDC14");
>>>>>>> c3c8aaf3e3b14f5686a0ca6104018351027dc829

    public ListOfShips() {
        list = new ArrayList<>();
        list.add(patrolBoat1);
        list.add(patrolBoat2);
        list.add(destroyerBoat);
<<<<<<< HEAD
        list.add(submarineBoat);
=======
        list.add(SubmarineBoat);
>>>>>>> c3c8aaf3e3b14f5686a0ca6104018351027dc829
        list.add(battleShip);
    }
}
