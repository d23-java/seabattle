package gameObjects;

public class Ships {
    private String shipType;
    private int shipSize;
    private String shipStart;
    private String shipEnd;
    private String shipDirection;
    private boolean status;
    public Ships(String shipType, int shipSize, String shipStart, String shipEnd, String shipDirection) {
        this.shipType = shipType;
        this.shipSize = shipSize;
        this.shipStart = shipStart;
        this.shipEnd = shipEnd;
        this.shipDirection = shipDirection;
    }
    public String getShipType() {
        return shipType;
    }
    public void setShipType(String shipType) {
        this.shipType = shipType;
    }
    public void getShipSize(int shipSize) {
        this.shipSize = shipSize;
    }
    public int getShipSize() {
        return shipSize;
    }
    public String getShipStart()  {
        return shipStart;
    }
    public String getShipEnd()  {
        return shipEnd;
    }
    public String getShipDirection() {
        return shipDirection;
    }
    public void setShipDirection(String shipDirection) {
        this.shipDirection = shipDirection;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
