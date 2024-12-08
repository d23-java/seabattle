package Object;

public class Ship {
    private String type;
    private int size;
    private int hits;

    public Ship(String type, int size) {
        this.type = type;
        this.size = size;
    }
    public String getType() {
        return type;
    }
    public int getSize() {
        return size;
    }
    public boolean isSunk(){
        return hits >= size;
    }
    public void hit(){
        hits++;
    }
}
