import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        Game game = new Game();
        menu.start();
        int choose = Integer.parseInt(sc.nextLine());
        if(choose == 1){
            game.initTwoPlayer();
            game.fire();
        }
    }
}
