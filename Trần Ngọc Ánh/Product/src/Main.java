import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameSystem game = new GameSystem();
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("1. Bắt đầu trò chơi mới");
            System.out.println("2. Thoát");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    game.GameSystem();
                    break;
                case 2:
                    return ;
            }
        }
    }
}
