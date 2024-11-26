import java.util.ArrayList;
import java.util.*;
public class Player {
    private String name;
    private Board board;
    private ArrayList<Ship> ships ;
    //contractor
    public Player(){
        this.ships = new ArrayList<>();
        this.board = new Board();
        ships.add(new Ship("Patrol Boat", 2));
        ships.add(new Ship("Patrol Boat", 2));
        ships.add(new Ship("Destroyer Boat", 4));
        ships.add(new Ship("Submarine", 3));
        ships.add(new Ship("Battle Ship", 5));
    }
    //setter
    public void setName(String name){
        this.name=name;
    }
    //getter
    public Board getBoard(){
        return board;
    }
    public String getName(){
        return name;
    }
    public void placeShips(){
        Scanner sc = new Scanner(System.in);

        while(!ships.isEmpty()){
            System.out.print("\033[H\033[2J");
            board.showOwnerBoard();
            System.out.println(name + ", Place your ship(s): ");
            System.out.println("Available ships to place:");
            for(int i=0; i<ships.size(); i++){
                System.out.println((i+1) + ". " + ships.get(i).getShipName() + ": Size = " + ships.get(i).getShipLength());
            }
            System.out.print("Select ship number: ");
            int num = sc.nextInt();
            if(num<1 || num>ships.size()){
                System.out.println("Invalid! Choose again!");
                continue;
            }
            System.out.print("Enter coordinates of the ship's head (x,y): ");
            int x=sc.nextInt()-1;
            int y=sc.nextInt()-1;
            sc.nextLine();
            System.out.print("Enter direction of the ship (H for horizontal, V for vertical): ");
            char direction = sc.next().toUpperCase().charAt(0);
            if(board.placeShip(ships.get(num),x,y,direction)){
                ships.remove(ships.get(num));
            }
            else{
                System.out.println("Invalid! Try again!");
            }

        }
        sc.close();
    }
    public boolean receiveAttack(int x, int y){
        boolean hit = board.attackCoordinates(x,y);
        if(hit){
            for(Ship ship: ships){
                if(ship.checkHit(x,y)){
                    ship.hit();
                    if(ship.isSunk()){
                        System.out.println(name + " lost a ship!");
                    }
                    break;
                }
            }
        }
        return hit;
    }
    public boolean hasLost() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) return false;
        }
        return true;
    }
}
