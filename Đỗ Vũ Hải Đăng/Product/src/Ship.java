public class Ship {
    int coordinatesHeadRow;
    int coordinatesHeadCol;
    int coordinatesEndRow;
    int coordinatesEndCol;
    boolean status = true;
    String nameShip;
    int shipLength;
    public Ship(String nameShip, String coordinates , int direction){
        this.nameShip = nameShip;
        if(nameShip.equals("Patrol Boat")){
            shipLength = 2;
        } else if (nameShip.equals("Submarine")){
            shipLength = 4;
        } else if (nameShip.equals("Destroyer Boat")){
            shipLength = 3;
        }
        else {
            shipLength = 5;
        }
        coordinatesHeadRow = (int)coordinates.charAt(0) - 'a';
        coordinatesHeadCol = (int)coordinates.charAt(1) - '0';
        if(direction == 1){
            coordinatesEndCol = coordinatesHeadCol;
            coordinatesEndRow = coordinatesHeadRow + shipLength - 1;
        }
        else if(direction == 2){
            coordinatesEndCol = coordinatesHeadCol + shipLength - 1;
            coordinatesEndRow = coordinatesHeadRow;
        }
    }
    boolean checkShipBeDestroyed(String[][] matrix){
        int numberOfBulletsHit = 0;
        if(coordinatesHeadRow == coordinatesEndRow){
            for(int i = coordinatesHeadCol; i <= coordinatesEndCol; ++i){
                if(matrix[coordinatesHeadRow][i].equals("[F]")) ++numberOfBulletsHit;
            }
        }
        else{
            for(int i = coordinatesHeadRow; i <= coordinatesEndRow; ++i){
                if(matrix[i][coordinatesHeadCol].equals("[F]")) ++numberOfBulletsHit;
            }
        }
        if(numberOfBulletsHit == shipLength) status = false;
        return status;
    }
    boolean getStatus(){
        return status;
    }
}
