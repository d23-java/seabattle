public class Ship {
    private int xA, yA, xB, yB;
    private int Size;
    //Constructor
    public Ship(int xA, int yA, int xB, int yB) {
        this.xA = xA;
        this.yA = yA;
        this.xB = xB;
        this.yB = yB;
        Size = Math.abs(yA - yB);
    }


}
