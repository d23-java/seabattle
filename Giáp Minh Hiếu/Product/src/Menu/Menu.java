package Menu;
public class Menu {

    public Menu() {};

    public void displayMenuStart() {
        System.out.println("SEA BATTLE");
        System.out.println("Enter your choice: ");
        System.out.println("1. Start a new game");
        System.out.println("2. Ranking");
        System.out.println("3. Exit");
    }

    public void newGame() {
        System.out.println("Enter your name: ");
    }
    public void kindShip() {
        System.out.println("Enter your choice: ");
        System.out.println("1. Human placed Ship");
        System.out.println("2. Random Ship");
    }
}
