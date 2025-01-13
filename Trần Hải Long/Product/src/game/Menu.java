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
    public static File file = new File("tempGame.txt");

    public static void seaBattle(){
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
        reset +
                "                             5. Luật chơi                                  \n" +
                reset);
        if (file.length() > 0) {
            System.out.print(green +
                "                              6. Tiếp tục                                   \n" +
                reset);
        }
        System.out.println(red +
                "                               0. Thoát                                     \n" +
                reset);
    }
    public static void start(){
        playSoundWithDurationAsync("/Sound/start.wav", false,4000, 1f);
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
        System.out.println();
    }
    public static void end(){
        playSoundWithDurationAsync("/Sound/end.wav", false, 3000, 1f);
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

    public static void hit(){
        playSoundWithDurationAsync("/Sound/hit.wav", false,3000, 1f);
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
    public static void miss(){
        playSoundWithDurationAsync("/Sound/miss.wav", false,3000, 1f);
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
    public static void sunk(){
        playSoundWithDurationAsync("/Sound/sink.wav", false,3000, 1f);
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

    public static void explosion(){
        playSoundWithDurationAsync("/Sound/sink.wav", false,3000, 1f);
        String[] art = {
                "          _ ._  _ , _ ._",
                "        (_ ' ( `  )_  .__)",
                "      ( (  (    )   `)  ) _)",
                "     (__ (_   (_ . _) _) ,__)",
                "         `~~`\\ ' . /`~~`",
                "              ;   ;",
                "              /   \\",
                "_____________/_ __ \\_____________"
        };

        for (String line : art) {
            System.out.println(red + line + reset);
        }
    }

    public static void rule(){
        System.out.println("+------------------------Luật chơi----------------------------+");
        System.out.println("| 1. Mỗi người bí mật đặt tàu trên bảng của mình              |");
        System.out.println("| 2. Luân phiên bắn nhau bằng cách chọn một ô trên bảng địch  |");
        System.out.println("| 3. Khi một tàu bị bắn hết ô, tàu đó bị chìm                 |");
        System.out.println("| 4. Người chiến thắng là người bắn chìm hết tàu đối phương   |");
        System.out.println("+-------------------------------------------------------------+");
    }
}
