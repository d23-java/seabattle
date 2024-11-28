package Console;
public class Console {
    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clearConsole();
        System.out.println("Successfully!!!");
    }
}
