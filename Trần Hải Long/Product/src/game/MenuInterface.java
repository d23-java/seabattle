package game;

import gamemanager.SoundController;

import java.io.File;

import static game.Main.scanner;


public class MenuInterface {
    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[31m";
    public static final String yellow = "\u001B[33m";
    public static final String green = "\u001B[32m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String gray = "\u001B[90m";
    public static File file = new File("tempGame.txt");
    public static SoundController soundController = SoundController.getInstance();

    public static void seaBattle(){
        System.out.print(
                " ███████╗███████╗ █████╗ ██████╗  █████╗ ████████╗████████╗██╗     ███████╗ \n" +
                " ██╔════╝██╔════╝██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║     ██╔════╝ \n" +
        blue +
                " ███████╗█████╗  ███████║██████╔╝███████║   ██║      ██║   ██║     █████╗   \n" +
                " ╚════██║██╔══╝  ██╔══██║██╔══██╗██╔══██║   ██║      ██║   ██║     ██╔══╝   \n" +
                " ███████║███████╗██║  ██║██████╔╝██║  ██║   ██║      ██║   ███████╗███████╗ \n" +
                " ╚══════╝╚══════╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝╚══════╝ \n" +
        blue +
                "                              1. Chơi mới                                   \n" +
        reset +
                "                       2. Hiển thị bảng xếp hạng                            \n" +
        blue +
                "                             3. Bật/Tắt âm                                  \n" +
        reset +
                "                             4. Luật chơi                                  \n" +
                reset);
        if (file.length() > 0) {
            System.out.print(green +
                "                              5. Tiếp tục                                   \n" +
                reset);
        }
        System.out.println(red +
                "                               0. Thoát                                     \n" +
                reset);
    }

    public static String gameplayPicker(){
        return green + "1. Chế độ 2 người chơi" + blue + "\n2. Đấu với máy" + reset +
                "\nHãy chọn chế độ chơi (Nếu bạn chọn số khác 1, bạn sẽ chơi với máy): ";
    }

    public static void alert(String message) {
        System.out.println(red + message + reset);
    }

    public static void start(){
        soundController.playSoundWithDurationAsync("/Sound/start.wav", false,4000, 1f);
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
        soundController.playSoundWithDurationAsync("/Sound/end.wav", false, 3000, 1f);
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
        soundController.playSoundWithDurationAsync("/Sound/hit.wav", false,3000, 1f);
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
        soundController.playSoundWithDurationAsync("/Sound/miss.wav", false,3000, 1f);
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
        soundController.playSoundWithDurationAsync("/Sound/sink.wav", false,3000, 1f);
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
        soundController.playSoundWithDurationAsync("/Sound/sink.wav", false,3000, 1f);
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

    public static void instructions() {
        System.out.println("+------------------------Hướng dẫn chơi-------------------------------------------+");
        System.out.println("| 1. Trò chơi có hai chế độ: Chơi đơn và Chơi hai người.                          |");
        System.out.println("|    - Chế độ Chơi đơn: Người chơi đấu với máy (Mức Dễ hoặc Khó).                 |");
        System.out.println("|    - Chế độ Chơi hai người: Hai người chơi đấu với nhau.                        |");
        System.out.println("| 2. Người chơi có thể chọn kích thước bảng phù hợp.                              |");
        System.out.println("| 3. Mỗi người chơi bí mật đặt tàu (tự động/thủ công) trên bảng của mình.         |");
        System.out.println("| 4. Người chơi luân phiên bắn nhau bằng cách chọn một ô trên bảng địch.          |");
        System.out.println("| 5. Khi một tàu bị bắn hết các ô, tàu đó bị chìm.                                |");
        System.out.println("| 6. Người chiến thắng là người bắn chìm hết tàu của đối phương.                  |");
        System.out.println("| 7. Hiển thị bảng xếp hạng để theo dõi điểm số của người chơi.                   |");
        System.out.println("| 8. Sử dụng các vật phẩm như Bom, Khiên và Đèn để hỗ trợ trong trò chơi.         |");
        System.out.println("|    - Bom: Phát nổ toàn bộ khu vực lân cận ô được chọn, diện tích 2x2            |");
        System.out.println("|    - Khiên: Bảo vệ tàu chứa ô được chọn, chỉ có tác dụng 1 lần                  |");
        System.out.println("|    - Đèn: Cho biết ô được chọn có tàu xuất hiện hay không                       |");
        System.out.println("| 9. Có thể bật hoặc tắt âm thanh theo ý muốn.                                    |");
        System.out.println("| 10. Bạn có thể lưu và tiếp tục tiến trình trò chơi để chơi sau.                 |");
        System.out.println("+---------------------------------------------------------------------------------+");
    }

    public static int getValidOptionWithPrompt(String prompt, String notification) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                alert(notification);
            }
        }
    }
}
