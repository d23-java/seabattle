import java.util.ArrayList;

public class Ship {
    private int length, hits;
    private String name;
    private ArrayList<Coordinates> location;
    //contractor
    public Ship(){};
    public Ship(String name, int length){
        this.name = name;
        this.length = length;
        location = new ArrayList<>();
    }
    //setter
    public void addCoordinates(Coordinates coordinates){
        this.location.add(coordinates);
    }
    public void hit(){
        hits++;
    }
    //getter
    public String getShipName(){return name;}
    public int getShipLength(){return length;}
    public boolean checkHit(int x, int y) {
        for (Coordinates coordinates : location) {
            if (coordinates.attack()) return true;
        }
        return false;
    }
    public boolean isSunk(){
        return hits == length;
    }
}