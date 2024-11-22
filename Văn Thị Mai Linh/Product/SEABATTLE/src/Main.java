import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);
        Game game = new Game();
        // hien thi menu
        System.out.println("Hien thi menu: ");
        game.menu();
        System.out.println("Vòng chuẩn bị (đặt thuyền): ");
        game.startGame();
        System.out.println("Bắt đầu chơi game: ");
        game.playGame();
    }
}