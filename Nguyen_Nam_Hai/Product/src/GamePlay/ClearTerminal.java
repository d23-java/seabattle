package GamePlay;

public class ClearTerminal {
    public static void clear(int delay){
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}