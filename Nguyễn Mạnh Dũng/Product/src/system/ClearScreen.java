package system;

public class ClearScreen {
    public static void clearConsole() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
