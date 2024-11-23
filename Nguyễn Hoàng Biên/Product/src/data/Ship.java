package data;

public class Ship {
    protected int size;
    protected int quantity;
    protected boolean ruined;
    protected int wasHit;
    public Ship(int size,int quantity) {
        this.size = size;
        this.ruined = false;
        this.wasHit = 0;
        this.quantity = quantity;
    }

    public int getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isRuined() {
        return ruined;
    }

    public int getWasHit() {
        return wasHit;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRuined(boolean ruined) {
        this.ruined = ruined;
    }

    public void setWasHit(int wasHit) {
        this.wasHit = wasHit;
    }
}
