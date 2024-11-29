package Constructor;

public class ShipType {
    private String name;
    private int area;

    public ShipType(String name, int area) {
        this.name = name;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public int getArea() {
        return area;
    }
}