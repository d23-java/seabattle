package game;

public class Menu {
    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String gray = "\u001B[90m";
    public void seaBattle(){
        System.out.println(blue +
                "╔════════════════════════════════════════════════════════════════════════════╗\n"+
                "║ ███████╗███████╗ █████╗ ██████╗  █████╗ ████████╗████████╗██╗     ███████╗ ║\n" +
                "║ ██╔════╝██╔════╝██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║     ██╔════╝ ║\n" +
                "║ ███████╗█████╗  ███████║██████╔╝███████║   ██║      ██║   ██║     █████╗   ║\n" +
                "║ ╚════██║██╔══╝  ██╔══██║██╔══██╗██╔══██║   ██║      ██║   ██║     ██╔══╝   ║\n" +
                "║ ███████║███████╗██║  ██║██████╔╝██║  ██║   ██║      ██║   ███████╗███████╗ ║\n" +
                "║ ╚══════╝╚══════╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝╚══════╝ ║\n" +
                "╠════════════════════════════════════════════════════════════════════════════╣\n" +
                "║ 1. Bắt đầu trò chơi                                                        ║\n" +
                "║ 2. Hiển thị bảng xếp hạng                                                  ║\n" +
                "║ 0. Thoát                                                                   ║\n" +
                "╚════════════════════════════════════════════════════════════════════════════╝\n" +
                reset);

    }
    public void start(){
        String[] lines = {
                " ____ _____  _    ____ _____ _ ",
                "/ ___|_   _|/ \\  |  _ \\_   _| |",
                "\\___ \\ | | / _ \\ | |_) || | | |",
                " ___) || |/ ___ \\|  _ < | | |_|",
                "|____/ |_/_/   \\_\\_| \\_\\|_| (_)"
        };

        for (String line : lines) {
            System.out.println(yellow + line + reset);
        }
    }
    public void end(){
        String[] lines = {
                " _____ _   _ ____  _ ",
                "| ____| \\ | |  _ \\| |",
                "|  _| |  \\| | | | | |",
                "| |___| |\\  | |_| |_|",
                "|_____|_| \\_|____/(_)"
        };

        for (String line : lines) {
            System.out.println(purple + line + reset);
        }
    }

    public void hit(){
        String[] lines = {
                " __    __   __  .___________. __ ",
                "|  |  |  | |  | |           ||  |",
                "|  |__|  | |  | `---|  |----`|  |",
                "|   __   | |  |     |  |     |  |",
                "|  |  |  | |  |     |  |     |__|",
                "|__|  |__| |__|     |__|     (__)"
        };

        for (String line : lines) {
            System.out.println(red + line + reset);
        }
    }
    public void miss(){
        String[] lines = {
                ".___  ___.  __       _______.     _______.",
                "|   \\/   | |  |     /       |    /       |",
                "|  \\  /  | |  |    |   (----`   |   (----`",
                "|  |\\/|  | |  |     \\   \\        \\   \\    ",
                "|  |  |  | |  | .----)   |   .----)   |   ",
                "|__|  |__| |__| |_______/    |_______/    "
        };
        for (String line : lines) {
            System.out.println(gray + line + reset);
        }
    }
    public void sunk(){
        String[] lines = {
                "     _______. __    __  .__   __.  __  ___  __   __   __ ",
                "    /       ||  |  |  | |  \\ |  | |  |/  / |  | |  | |  |",
                "   |   (----`|  |  |  | |   \\|  | |  '  /  |  | |  | |  |",
                "    \\   \\    |  |  |  | |  . `  | |    <   |  | |  | |  |",
                ".----)   |   |  `--'  | |  |\\   | |  .  \\  |__| |__| |__|",
                "|_______/     \\______/  |__| \\__| |__|\\__\\ (__) (__) (__)"
        };

        for (String line : lines) {
            System.out.println(yellow + line + reset);
        }
    }

}
