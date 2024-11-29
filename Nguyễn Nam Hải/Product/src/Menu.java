public class Menu {
    public void start() {
        System.out.println("Choose one of the following options:");
        System.out.println("1. Start the game");
        System.out.println("2. Exit");
    }

    public void chooseShip() {
        System.out.println("Choose a ship to add:");
        System.out.println("1. Patrol Boat");
        System.out.println("2. Destroyer Boat");
        System.out.println("3. Submarine");
        System.out.println("4. Battle Ship");
    }

    public void shipOrientation() {
        System.out.println("Do you want to place the ship horizontally or vertically?");
        System.out.println("1. Horizontally");
        System.out.println("2. Vertically");
    }
}
