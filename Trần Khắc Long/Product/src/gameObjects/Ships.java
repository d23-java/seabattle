package gameObjects;

public class Ships {

    private String shipType;
    private int shipSize;
    private String shipStart;
    private int shipDirection;
    private boolean status;
    private String shipColor;
    public Ships(String shipType, int shipSize, String shipColor) {
        this.shipType = shipType;
        this.shipSize = shipSize;
        this.shipColor = shipColor;
        this.status = false;
    }
    public Ships(Ships other) {
        this.shipType = other.shipType;
        this.shipSize = other.shipSize;
        this.shipColor = other.shipColor;
        this.status = other.status;
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

    public void setShipStart(String shipStart) {
        this.shipStart = shipStart;
    }

    public int getShipDirection() {
        return shipDirection;
    }

    public void setShipDirection(int shipDirection) {
        this.shipDirection = shipDirection;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setShipColor(String shipColor) {
        this.shipColor = shipColor;
    }

    public String getShipColor() {
        return shipColor;
    }
    public boolean isSunk(Players player) {
        Cell[][] myBoard = player.getBoard().getCells();
        if(!status) {
            int row = shipStart.charAt(0) - 'a';
            int colum = shipStart.charAt(1) -'0';
            if(shipStart.length() == 3) colum = colum * 10 + shipStart.charAt(2) - '0' - 1;
            else colum--;
            boolean sunk = true;
            if(shipDirection == 1) {
                for(int index = colum; index < shipSize + colum; index++)
                    if(!myBoard[row][index].getHit())
                        sunk = false;
            } else
                for(int index = row; index < shipSize + row; index++)
                    if(!myBoard[index][colum].getHit())
                        sunk = false;
            if(sunk) status = true;
        }
        return status;
    }
}
