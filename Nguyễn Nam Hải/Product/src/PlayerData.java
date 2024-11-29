import java.util.*;
public class PlayerData {
    public static Player player1;
    public static Player player2;
    public void initPlayer1() {
        ArrayList<Ship> ships = new ArrayList<Ship>();
        InitShip initShip = new InitShip(ships);
        initShip.placeShips();
        Board player1Board = new Board();
//        for(Ship ship:ships){
//            for(int[] coord:ship.getCoordinates()){
//                player1Board.setValue(coord[0], coord[1], 'O');
//            }
//        }
        player1 = new Player(ships, player1Board);

    }
    public void initPlayer2(){
        ArrayList<Ship> ships = new ArrayList<Ship>();
        InitShip initShip = new InitShip(ships);
        initShip.placeShips();
        Board player2Board = new Board();
//        for(Ship ship:ships){
//            for(int[] coord:ship.getCoordinates()){
//                player2Board.setValue(coord[0], coord[1], 'O');
//            }
//        }
        player2 = new Player(ships, player2Board);
    }

    public void displayPlayer1() {
        player1.getPlayerBoard().displayBoard();
    }
    public void displayPlayer2() {
        player2.getPlayerBoard().displayBoard();
    }
}