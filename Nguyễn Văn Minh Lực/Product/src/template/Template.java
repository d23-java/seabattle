package template;

public class Template {
    public static void showGameMenu(){
        System.out.println("MENU");
        System.out.println("1. Bảng xếp hạng (Chưa cập nhật).");
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
        System.out.println("Player Menu.");
        System.out.println("1. Xem bảng của bản thân.");
        System.out.println("2. Xem bảng của đối thủ.");
        System.out.println("3. Xem bảng của đối thủ và khai hỏa.");
        System.out.println("4. Kết thúc lượt.");
    }
}
