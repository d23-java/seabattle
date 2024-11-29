import java.util.ArrayList;

public class Player  {
    private ArrayList<Ship> ships;
    private Board playerBoard;

    public Player(ArrayList<Ship> ships, Board playerBoard) {
        this.ships = ships;
        this.playerBoard = playerBoard;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(Board playerBoard) {
        this.playerBoard = playerBoard;
    }
}
