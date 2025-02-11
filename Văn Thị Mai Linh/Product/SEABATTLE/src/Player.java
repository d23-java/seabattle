public class Player {
    private Board board;
    private int numberOfDistroyedShip;
    private int numberOfShipLeft;
    private int numberOfEnemyCellHited;
    // khoi tao cac thong so ban dau
    public Player(Board board){
        this.board = board;
        this.numberOfShipLeft = 5;
        this.numberOfDistroyedShip = 0;
        this.numberOfEnemyCellHited = 0;
    }
    public int getNumberOfDistroyedShip(){
        return numberOfDistroyedShip;
    }

    public int getNumberOfShipLeft(){
        return numberOfShipLeft;
    }

    public int getNumberOfEnemyCellHited(){
        return numberOfEnemyCellHited;
    }

    public Board getBoard(){
        return board;
    }
    // cap nhat so luong tau cua ban than bi pha huy
    public void increaseDestroyedShip(){
        numberOfDistroyedShip++;
    }
    // cap nhat so o ban trung vao mat tran dich
    public void increaseCellEnermyHited(){
        numberOfEnemyCellHited++;
    }
    // cap nhat so tau con lai cua ban than
    public void decreaseShipLeft(){
        if(numberOfShipLeft > 0)
            numberOfShipLeft--;
    }

}
