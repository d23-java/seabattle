//import java.util.*;

public class Board {
    public Coordinates[][] board = new Coordinates[10][10];
    public Board(){
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                board[i][j] = new Coordinates(i, j);
            }
        }
    }
    public boolean isCanPlaceShip(Ship ship, int x, int y, char direction){
        if(x<0 || x>=10 || y<0 || y>=10) return false;
        if(direction=='V' && x+ship.getShipLength()>10) return false;
        if(direction=='H' && y+ship.getShipLength()>10) return false;
        for(int i=0; i<ship.getShipLength(); i++){
            if(direction=='V' && board[x+i][y].isUsed()) return false;
            if(direction=='H' && board[x][y+i].isUsed()) return false;
        }
        return true;
    }
    public boolean placeShip(Ship ship, int x, int y, char direction){
        if(!isCanPlaceShip(ship,x,y,direction)) return false;

        for(int i=0; i<ship.getShipLength(); i++){
            if(direction=='V'){
                board[x+i][y].setUsed(true);
                ship.addCoordinates(board[x+i][y]);
            }
            else{
                board[x][y+i].setUsed(true);
                ship.addCoordinates(board[x][y+i]);
            }
        }
        return true;
    }
    public void showOwnerBoard(){
        System.out.print(' ');
        for(int i=1; i<=10; i++){
            System.out.print("  " + i);
        }
        System.out.println();
        for(int i=0; i<10; i++){
            System.out.printf("%c ",(char)('A'+i));
            for(int j=0; j<10; j++){
                System.out.print("[" + board[i][j].getOwnerChar() + "]");
            }
            System.out.println();
        }
    }
    public void showOpponentBoard(){
        System.out.print(' ');
        for(int i=1; i<=10; i++){
            System.out.print("  " + i);
        }
        System.out.println();
        for(int i=0; i<10; i++){
            System.out.printf("%c ",(char)('A'+i));
            for(int j=0; j<10; j++){
                System.out.print("[" + board[i][j].getOpponentChar() + "]");
            }
            System.out.println();
        }
    }
    public boolean attackCoordinates(int x, int y){
        return board[x][y].attack();
    }
}