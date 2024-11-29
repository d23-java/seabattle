package ui;

public class TextEffect {
    public static void printWithEffect(String text) {
        int delay = 250;
        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
}
