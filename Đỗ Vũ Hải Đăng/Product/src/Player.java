import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    Scanner sc = new Scanner(System.in);
    private String name;
    private int idPlayer;
    ArrayList<Ship> playerShip = new ArrayList<>();
    Screen playerScreen = new Screen();
    Screen shotScreen = new Screen();
    private int score = 0;

    public Player(int idPlayer){
        this.idPlayer = idPlayer;
    }
    boolean isDefeat(){
        return playerShip.isEmpty();
    }
    void setInformation(){
        System.out.println("PLAYER " + idPlayer);
        System.out.print("Enter name:");
        name = sc.nextLine();
        playerScreen = new Screen();
        setShip();
    }
    void setShip(){
        for(int i = 0;i < 5;++i){
            Terminal.clear();
            playerScreen.display();
            MenuList.showShipChoice();
            String nameShip;
            int shipChoice = Integer.parseInt(sc.nextLine());
            nameShip = switch (shipChoice) {
                case 1 -> "Patrol Boat";
                case 2 -> "Destroyer Boat";
                case 3 -> "Submarine";
                case 4 -> "Battle Boat";
                default -> {
                    System.out.println("invalid ship choice");
                    yield "";
                }
            };
            System.out.println("Enter the coordinates of the boat's head:");
            String coordinates = sc.nextLine();
            System.out.println("Will your ship be columnar or columnar: ");
            System.out.println("1. Col");
            System.out.println("2. Row");
            int directionChoice = Integer.parseInt(sc.nextLine());
            Ship ship = new Ship(nameShip, coordinates, directionChoice);
            playerShip.add(ship);
            playerScreen.addShip(ship);
        }
    }
    void shotShip(Player Enemy){
        Terminal.clear();
        System.out.println("FIRE!!!");
        shotScreen.display();
        System.out.println("Enter the coordinates to fire:");
        String coordinates = sc.nextLine();
        if(Enemy.playerScreen.checkShot(coordinates)){
            System.out.println("you've hit!!");
            shotScreen.getMatrix()[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'] = "[F]";
            Enemy.playerScreen.getMatrix()[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'] = "[F]";
            int checkShipDown = Enemy.playerScreen.checkShipBeDestroyed();
            if(checkShipDown != -1){
                score++;
                Enemy.playerShip.remove(checkShipDown);
                System.out.println(Enemy.playerShip.size());
            }
        }
        else {
            shotScreen.getMatrix()[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'] = "[F]";
            Enemy.playerScreen.getMatrix()[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'] = "[F]";
            System.out.println("You miss");
            System.out.println("Change Turn...");
        }
        shotScreen.checkShot(coordinates);
    }
    String getName(){
        return name;
    }
}
