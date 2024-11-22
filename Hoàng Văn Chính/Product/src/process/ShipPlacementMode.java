package process;

import java.util.ArrayList;
import java.util.Random;

import data.*;
import display.SelectionScreen;


public class ShipPlacementMode {
//    public static void placeShipsRandomly() {
//        Random random = new Random();
//        int randomInt = 1 + random.nextInt(100);
//
//    }

    //Hàm tạo một thuyền (truyền vào tên thuyền tiếng Việt viết hoa chữ cái đầu)
    public static Ship initShipCustom(String name) {
        int leng = 0;
        switch (name) {
            case "Thuyền Tuần Tra":
                leng = 2;
                break;
            case "Tàu Khu Trục":
                leng = 4;
                break;
            case "Tàu Ngầm":
                leng = 3;
                break;
            case "Thiết giáp hạm":
                leng = 5;
                break;
        }
        System.out.print("Nhập vị trí đầu tàu: ");
        //VD: A 6
        int rowBegin = ScannerInput.scanner.next().charAt(0) - 'A' + 1;
        int columnBegin = ScannerInput.scanner.next().charAt(0) - '0';
        int rowEnd = rowBegin;
        int columnEnd = columnBegin;
        String orientation = null;
        System.out.println(">>   LỰA CHỌN HƯỚNG TÀU   <<");
        System.out.println("++ ---------------------- ++");
        System.out.println("| 1. Quay lên              |");
        System.out.println("| 2. Quay xuống            |");
        System.out.println("| 3. Quay trái             |");
        System.out.println("| 4. Quay phải             |");
        System.out.println("++ ---------------------- ++");
        System.out.println("Lựa chọn của bạn là: ");
        int feature = Integer.parseInt(ScannerInput.scanner.nextLine().trim());
        switch (feature) {
            case 1:
                orientation = "up";
                rowEnd = rowBegin - (leng - 1);
                break;
            case 2:
                orientation = "down";
                rowEnd = rowBegin + (leng - 1);
                break;
            case 3:
                orientation = "left";
                columnEnd = columnBegin - (leng - 1);
                break;
            case 4:
                orientation = "right";
                columnEnd = columnBegin + (leng - 1);
                break;
        }
        return new Ship(name, rowBegin, columnBegin, rowEnd, columnEnd, orientation, leng);
    }

    boolean isShipValid(Ship ship, ArrayList<Ship> ships) {
        return true;
    }

    public static void placeShipsCustom(Player player) {
        ArrayList<Ship> ships = new ArrayList<Ship>();
        //loop dùng để lặp các vòng lặp while
        //Thuyền Tuần Tra x2
        System.out.println("Đặt Thuyền Tuần Tra: ");
        for(int i = 1; i <= 2; ++i) {
            
        }
    }
}
