package system;

public class Intro {
    // Mã ANSI cho màu sắc
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";

    public void displayIntro() throws InterruptedException {
        String[] lines = {
            BLUE + BOLD + "███████╗███████╗ █████╗     ██████╗  █████╗ ████████╗████████╗██╗     ███████╗" + RESET,
            CYAN + BOLD + "██╔════╝██╔════╝██╔══██╗    ██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║     ██╔════╝" + RESET,
            WHITE + BOLD + "███████╗█████╗  ███████║    ██████╔╝███████║   ██║      ██║   ██║     █████╗  " + RESET,
            PURPLE + BOLD + "╚════██║██╔══╝  ██╔══██║    ██╔══██╗██╔══██║   ██║      ██║   ██║     ██╔══╝  " + RESET,
            RED + BOLD + "███████║███████╗██║  ██║    ██████╔╝██║  ██║   ██║      ██║   ███████╗███████╗" + RESET,
            YELLOW + BOLD + "╚══════╝╚══════╝╚═╝  ╚═╝    ╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝╚══════╝" + RESET,
            GREEN + "Made by " + UNDERLINE + "Sagito" + RESET
        };

        int totalDuration = 5000;
        int timePerLine = 400;
        int blinkInterval = 300;

        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < totalDuration) {
            for (String line : lines) {
                System.out.println(line);
                Thread.sleep(timePerLine);
            }

            Thread.sleep(blinkInterval);
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        for (String line : lines) {
            System.out.println(line);
        }
    }
}
