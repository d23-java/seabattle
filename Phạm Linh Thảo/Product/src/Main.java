import game.Game;
import menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        MainMenu menu=  new MainMenu();
        menu.displayMenu();
        Game game = new Game();
        game.startGame();
    }
}
