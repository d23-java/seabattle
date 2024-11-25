package ObjectList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import System.*;

public class Player {
    private String name;
    private int idPlayer;
    ArrayList<Ship> playerShip = new ArrayList<>();
    Screen playerScreen = new Screen();
    Screen shotScreen = new Screen();

    public Player(int idPlayer){
        this.idPlayer = idPlayer;
    }
    public boolean isDefeat(){
        return playerShip.isEmpty();
    }
    public void setInformation() throws InterruptedException {
        Terminal.clear();
        MenuList.showPlayerTitle(idPlayer);
        System.out.print("Enter name: ");
        name = InputSystem.sc.nextLine();
        playerScreen = new Screen();
        setShip();
    }
    void setShip(){
        Terminal.clear();
        MenuList.showPlayerTitle(idPlayer);
        MenuList.setShipOptionMenu();
        int setUpChoice = Integer.parseInt(InputSystem.sc.nextLine());
        if(setUpChoice == 1){
            for(int i = 0;i < 5;++i){
                MenuList.setShipOptionMenu();
                Terminal.clear();
                System.out.println("      +---------------+");
                System.out.println("      | Set up Screen |");
                System.out.println("      +---------------+\n\n");
                playerScreen.display();
                MenuList.showShipChoice();
                String nameShip;
                int shipChoice = Integer.parseInt(InputSystem.sc.nextLine());
                nameShip = switch (shipChoice){
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
                String coordinates = InputSystem.sc.nextLine();
                System.out.println("Will your ship be columnar or columnar: ");
                System.out.println("1. Col");
                System.out.println("2. Row");
                int directionChoice = Integer.parseInt(InputSystem.sc.nextLine());
                Ship ship = new Ship(nameShip, coordinates, directionChoice);
                playerShip.add(ship);
                playerScreen.addShip(ship);
                if(i == 4){
                    Terminal.clear();
                    playerScreen.display();
                    System.out.println("Press Enter to continue...");
                    InputSystem.sc.nextLine();
                }
            }
        }
        else if(setUpChoice == 2){
            ArrayList<String> nameTypeShip = new ArrayList<>();
            nameTypeShip.add("Patrol Boat");
            nameTypeShip.add("Patrol Boat");
            nameTypeShip.add("Destroyer Boat");
            nameTypeShip.add("Submarine");
            nameTypeShip.add("Battle Boat");
            for(int i = 0;i < 5;++i){
                while(true){
                    Random random = new Random();
                    String coordinatesFirstLetter = new String (String.valueOf((char) (random.nextInt(10)  + 'a')));
                    String coordinatesSecondNumber =  new String (String.valueOf((char) (random.nextInt(10)  + '0')));
                    Ship ship = new Ship(nameTypeShip.get(i),coordinatesFirstLetter + coordinatesSecondNumber, random.nextInt(2) + 1);
                    if(ship.checkShipPosition(playerScreen.getMatrix())){
                        playerShip.add(ship);
                        playerScreen.addShip(ship);
                        break;
                    }
                }
            }
            playerScreen.display();
            System.out.println("Press Enter to continue...");
            InputSystem.sc.nextLine();
        }
    }
    public void shotShip(Player Enemy){
        Terminal.clear();
        System.out.println("FIRE!!!");
        shotScreen.display();
        System.out.println("Enter the coordinates to fire:");
        String coordinates = InputSystem.sc.nextLine();
        if(Enemy.playerScreen.checkShot(coordinates)){
            MenuList.hitNotify();
            shotScreen.getMatrix()[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'] = "[F]";
            Enemy.playerScreen.getMatrix()[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'] = "[F]";
            int checkShipDown = Enemy.playerScreen.checkShipBeDestroyed();
            if(checkShipDown != -1){
                Enemy.playerShip.remove(checkShipDown);
            }
            System.out.println("Press Enter to continue...");
            InputSystem.sc.nextLine();
        }
        else{
            shotScreen.getMatrix()[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'] = "[F]";
            Enemy.playerScreen.getMatrix()[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'] = "[F]";
            System.out.println("You miss");
            System.out.println("Change Turn...");
            System.out.println("Press Enter to continue...");
            InputSystem.sc.nextLine();
        }
        shotScreen.checkShot(coordinates);
    }
    public String getName(){
        return name;
    }

    public Screen getPlayerScreen(){
        return playerScreen;
    }
}
