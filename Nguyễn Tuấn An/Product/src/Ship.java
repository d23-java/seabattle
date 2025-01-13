import java.util.ArrayList;

public class Ship {
    private String name;
    private int length;
    private int hits;
    private ArrayList<Coordinates> location;
    //contractor
    public Ship(){};
    public Ship(String name, int length){
        this.name = name;
        this.length = length;
        hits = 0;
        location = new ArrayList<>();
    }
    public String getShipName(){return name;}

    public void addCoordinates(Coordinates coordinates){
        this.location.add(coordinates);
    }

    public void hit(){
        hits++;
    }

    public int getShipLength(){return length;}

    public boolean checkHit(int r, int c) {
        for (Coordinates coordinates : location) {
            if (!coordinates.isHit() && coordinates.attack(r,c)) return true;
        }
        return false;
    }

    public boolean isSunk(){
        return hits >= length;
    }
}