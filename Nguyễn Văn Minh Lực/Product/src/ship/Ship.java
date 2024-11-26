package ship;

public class Ship {
    private int size;
    private String symbol;
    public Ship(){};
    public Ship(int size, String symbol){
        this.size = size;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getSize() {
        return size;
    }

}
