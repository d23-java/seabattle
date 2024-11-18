package utilz;

public class ClearScreen {
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Lệnh xóa màn hình trên Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Lệnh xóa màn hình trên Unix/Linux/MacOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
