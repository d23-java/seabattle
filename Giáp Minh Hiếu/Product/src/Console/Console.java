package Console;
public class Console {
    public void clearConsole() {
        System.out.printf("\033[H\033[2J");
        System.out.flush();
    }
}
