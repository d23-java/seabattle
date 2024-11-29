package GamePlay;

public class Menu {
    public void start() {
        System.out.println("Choose one of the following options:");
        System.out.println("1. Start the game");
        System.out.println("2. Exit");
    }
    public void errorCoord(){
        System.out.println("x must be in the range from 1 to 10");
        System.out.println("y must be in the range from A to J");
        System.out.println("Please enter the coordinates again: ");
    }
    public void directionShip(String shipName){
        System.out.printf("Do you want to place the %s horizontally or vertically?\n", shipName);
        System.out.println("1. Vertically");
        System.out.println("2. Horizontally");
    }
    public void showFireMenu(){
        System.out.println("Choose one of the following options:");
        System.out.println("1. Show Player and Opponent's foggy board");
        System.out.println("2. Show Player caption");
        System.out.println("3. Show Opponent caption");
        System.out.println("Another number to attack");
    }
}