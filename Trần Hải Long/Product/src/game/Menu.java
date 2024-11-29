package game;

import java.io.File;

import static game.Sound.playSoundWithDurationAsync;


public class Menu {
    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[31m";
    public static final String yellow = "\u001B[33m";
    public static final String green = "\u001B[32m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String gray = "\u001B[90m";
    File file = new File("tempGame.txt");

    public void seaBattle(){
        playSoundWithDurationAsync("Sound/ocean-waves.wav", 10000);
        System.out.print(
                " ███████╗███████╗ █████╗ ██████╗  █████╗ ████████╗████████╗██╗     ███████╗ \n" +
                " ██╔════╝██╔════╝██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║     ██╔════╝ \n" +
        blue +
                " ███████╗█████╗  ███████║██████╔╝███████║   ██║      ██║   ██║     █████╗   \n" +
                " ╚════██║██╔══╝  ██╔══██║██╔══██╗██╔══██║   ██║      ██║   ██║     ██╔══╝   \n" +
                " ███████║███████╗██║  ██║██████╔╝██║  ██║   ██║      ██║   ███████╗███████╗ \n" +
                " ╚══════╝╚══════╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝╚══════╝ \n" +
        reset +
                "                         1. Chế độ 2 người chơi                             \n" +
        blue +
                "                           2. Chơi với máy                                  \n" +
        reset +
                "                       3. Hiển thị bảng xếp hạng                            \n" +
        blue +
                "                             4. Bật/Tắt âm                                  \n" +
                reset);
        if (file.length() > 0) {
            System.out.print(green +
                "                              5. Tiếp tục                                   \n" +
                reset);
        }
        System.out.println(reset +
                "                               0. Thoát                                     \n" +
                reset);
    }
    public void start(){
        playSoundWithDurationAsync("Sound/start.wav", 4000);
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
        playSoundWithDurationAsync("Sound/end.wav", 3000);
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
        playSoundWithDurationAsync("Sound/hit.wav", 3000);
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
        playSoundWithDurationAsync("Sound/miss.wav", 3000);
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
        playSoundWithDurationAsync("Sound/sink.wav", 3000);
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
