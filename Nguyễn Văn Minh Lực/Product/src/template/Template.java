package template;

public class Template {
    public static String[] listNumberIcons = {"0\uFE0F⃣","1\uFE0F⃣","2\uFE0F⃣","3\uFE0F⃣","4\uFE0F⃣","5\uFE0F⃣","6\uFE0F⃣","7\uFE0F⃣","8\uFE0F⃣","9\uFE0F⃣","\uD83D\uDD1F"};
    public static String[] listAlphaIcons = {"Ａ","\uD83C\uDD71\uFE0F","\uD835\uDC02","\uD835\uDC03","\uD835\uDC04","\uD835\uDC05","\uD835\uDC06","\uD835\uDC07","\uD835\uDC08","\uD835\uDC09","\uD835\uDC0A","\uD835\uDC0B","\uD835\uDC0C","\uD835\uDC0D","\uD835\uDC0E","\uD835\uDC0F","\uD835\uDC10","\uD835\uDC11","\uD835\uDC12","\uD835\uDC13","\uD835\uDC14"};
    public static void showGameMenu(){
        System.out.println("MENU");
        System.out.println("1. Bảng xếp hạng.");
        System.out.println("2. Ván mới.");
        System.out.println("3. Thoát trò chơi.");
    }

    public static void showBattleMenu(){
        System.out.println("Chế độ:");
        System.out.println("1. Chơi với máy (Chưa cập nhật).");
        System.out.println("2. 2 người chơi.");
    }

    public static void showAllShips() {
        System.out.println("Có 4 loại thuyền:");
        System.out.println("2 Thuyền Tuần Tra (Patrol Boat) 1x2");
        System.out.println("1 Tàu Khu Trục (Destroyer Boat) 1x4");
        System.out.println("1 Tàu Ngầm (Submarine) 1x3");
        System.out.println("1 Thiết Giáp Hạm (Battle Ship) 1x5");
    }

    public static void showPlayerMenu() {
        System.out.println("Lựa chọn của người chơi.");
        System.out.println("1. Xem bảng của bản thân.");
        System.out.println("2. Xem bảng của đối thủ và khai hỏa.");
        System.out.println("3. Kết thúc lượt.");
    }
    public static void enterAgain() {
        System.out.println("Dữ liệu không hợp lệ! Vui lòng nhập lại.");
    }
    public static void printSeaBattle() {
        System.out.println("███████╗███████╗ █████╗     ██████╗  █████╗ ████████╗████████╗██╗     ███████╗");
        System.out.println("██╔════╝██╔════╝██╔══██╗    ██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║     ██╔════╝");
        System.out.println("███████╗█████╗  ███████║    ██████╔╝███████║   ██║      ██║   ██║     █████╗");
        System.out.println("╚════██║██╔══╝  ██╔══██║    ██╔══██╗██╔══██║   ██║      ██║   ██║     ██╔══╝");
        System.out.println("███████║███████╗██║  ██║    ██████╔╝██║  ██║   ██║      ██║   ███████╗███████╗");
        System.out.println("╚══════╝╚══════╝╚═╝  ╚═╝    ╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝╚══════╝");
    }
    public static void showPlaceShipOption() {
        System.out.println("Có 2 lựa chọn sắp xếp tàu: ");
        System.out.println("1. Tự sắp xếp.");
        System.out.println("2. Sắp xếp ngẫu nhiên.");
    }
}
