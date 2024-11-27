import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Math.max;

public class Player extends Field {
    public Scanner sc = new Scanner(System.in);
    private String namePlayer; // Tên của người chơi
    private ArrayList<Ship> ships = new ArrayList<Ship>(); // Danh sách các tàu của người chơi

    public int numberShip = 0; // Số lượng tàu hiện tại
    public int sizeField; // Kích thước bảng chơi
    public int check = 0; // Biến kiểm tra trạng thái thắng thua
    public int numberHit = 0; // Số lần bắn trúng tàu của đối thủ
    public int countHit = 0;// số lần đã bắn của đối thủ
    public Player(int size, String namePlayer) {
        super(size); // Gọi hàm khởi tạo của lớp cha Field
        sizeField = size;
        this.namePlayer = namePlayer;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public Scanner getSc() {
        return sc;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public int getNumberShip() {
        return numberShip;
    }

    public int getSizeField() {
        return sizeField;
    }

    public int getCheck() {
        return check;
    }

    public int getNumberHit() {
        return numberHit;
    }

    public int getCountHit() {
        return countHit;
    }

    public void setShip() {
        while (numberShip < 5) { // Lặp cho đến khi đặt đủ 5 tàu
            String name = "";
            int size = 0;

            // Gán tên và kích thước tàu dựa vào số lượng tàu hiện tại
            switch (numberShip) {
                case 0:
                case 1:
                    name = "Thuyền Tuần Tra";
                    size = 2;
                    break;
                case 2:
                    name = "Tàu Khu Trục";
                    size = 4;
                    break;
                case 3:
                    name = "Tàu Ngầm";
                    size = 3;
                    break;
                case 4:
                    name = "Thiết Chiến Hạm";
                    size = 5;
                    break;
            }

            int checkShip = 1; // Biến để kiểm tra nếu tàu đã được đặt đúng
            while (checkShip == 1) {
                showMyBoard(); // Hiển thị bảng hiện tại của người chơi
                System.out.println("Đặt tàu: Thông tin " + name + " mang kích cỡ 1x" + size);
                System.out.print("Tọa độ đầu: ");
                int xBegin = sc.nextInt();
                int yBegin = sc.nextInt();
                System.out.print("Tọa độ cuối: ");
                int xEnd = sc.nextInt();
                int yEnd = sc.nextInt();
                sc.nextLine(); // Đọc ký tự dòng mới còn lại

                // Kiểm tra điều kiện đặt tàu
                if (!checkShipPlacement(xBegin, yBegin, xEnd, yEnd, size, name)) {
                    continue; // Nếu không hợp lệ, yêu cầu nhập lại
                }

                // Đặt tàu trên bảng
                placeShip(xBegin, yBegin, xEnd, yEnd, size);
                System.out.println("Bạn đã đặt tàu " + name + " thành công");

                ships.add(new Ship(name, xBegin, yBegin, xEnd, yEnd, size)); // Thêm tàu vào danh sách
                checkShip = 0; // Đánh dấu tàu đã được đặt xong
                numberShip++; // Tăng số lượng tàu đã đặt
            }
        }
        showMyBoard(); // Hiển thị bảng sau khi đặt tất cả tàu
    }

    // Hàm kiểm tra điều kiện đặt tàu
    private boolean checkShipPlacement(int xBegin, int yBegin, int xEnd, int yEnd, int size, String name) {
        if (Math.abs(xBegin - xEnd) != size - 1 && Math.abs(yBegin - yEnd) != size - 1) {
            System.out.println("Bạn đang nhập sai kích thước " + size + " của tàu " + name);
            System.out.println("Vui lòng nhập lại");
            return false;
        }

        if (xBegin > sizeField || xEnd > sizeField || yEnd > sizeField || yBegin > sizeField) {
            System.out.println("Tọa độ vượt quá kích thước bảng! Vui lòng đặt lại tàu!");
            return false;
        }

        int count = 0; // Đếm số ô trống có thể đặt tàu
        for (int i = Math.min(yEnd, yBegin) - 1; i < Math.max(yEnd, yBegin); i++) {
            for (int j = Math.min(xEnd, xBegin) - 1; j < Math.max(xEnd, xBegin); j++) {
                if (getMyBoard(i, j) == ' ') {
                    count++;
                } else {
                    return false; // Nếu trùng, không thể đặt tàu
                }
            }
        }

        if (count != size) {
            System.out.println("Bạn đã đặt trùng lên tàu, vui lòng đặt lại");
            return false;
        }
        return true;
    }

    // Hàm đặt tàu trên bảng
    private void placeShip(int xBegin, int yBegin, int xEnd, int yEnd, int size) {
        for (int i = Math.min(yEnd, yBegin) - 1; i < Math.max(yEnd, yBegin); i++) {
            for (int j = Math.min(xEnd, xBegin) - 1; j < Math.max(xEnd, xBegin); j++) {
                char shipChar = (char) ('A' + numberShip); // Tạo ký tự đại diện cho tàu
                setMyBoard(i, j, shipChar); // Đánh dấu tàu trên bảng của mình
            }
        }
    }



    public void beHit() {
        while (true) { // Lặp để người chơi nhập tọa độ tấn công
            countHit++;
            System.out.println("Bản đồ đối thủ:");
            showDisplayBoard(); // Hiển thị bảng tấn công
            System.out.println("Nhập tọa độ bạn muốn tấn công:");

            // Đọc tọa độ x và y bằng nextInt()
            int y = sc.nextInt(); // bị lỗi đoạn này nó sẽ là nhập x
            int x = sc.nextInt(); //bị lỗi đoạn này nó sẽ là nhập y
            y = y-1;
            x= x-1;

            sc.nextLine(); // Xử lý ký tự xuống dòng sau nextInt()

            if (getDisplayBoard(x, y) != ' ') { // Nếu tọa độ đã được tấn công trước đó
                System.out.println("Tọa độ đã được bắn, vui lòng chọn lại.");
                continue;
            } else if (getMyBoard(x, y) != ' ') { // Nếu bắn trúng
                System.out.println("Bạn đã bắn trúng!");
                setDisplayBoard(x, y, 'x'); // Đánh dấu đã bắn trúng
                checkBoard(x, y); // Kiểm tra trạng thái của tàu bị bắn
                checkWin(); // Kiểm tra điều kiện thắng
                if (check == 1) break; // Thoát vòng lặp nếu đã thắng
            } else { // Nếu bắn trượt
                System.out.println("Bạn đã bắn trượt.");
                setDisplayBoard(x, y, 'O'); // Đánh dấu bắn trượt trên bảng
                break;
            }
        }
    }

    public void checkBoard(int x, int y) {
        for (Ship ship : ships) { // Duyệt qua tất cả các tàu để kiểm tra
            boolean hit = false; // Biến để kiểm tra tàu có bị bắn trúng không
            for (int i = Math.min(ship.getY_begin(), ship.getY_end())-1; i < Math.max(ship.getY_begin(), ship.getY_end()); i++) {
                for (int j = Math.min(ship.getX_begin(), ship.getX_end())-1; j < Math.max(ship.getX_begin(), ship.getX_end()); j++) {
                    if (i == x && j == y && getMyBoard(i, j) != ' ') { // Nếu tọa độ trùng với tàu
                        hit = true;
                        ship.setSizeShip(ship.getSizeShip() - 1); // Giảm kích thước tàu
                    }
                }
            }

            if (hit && ship.getSizeShip() == 0) { // Nếu tàu bị bắn chìm
                for (int i = Math.min(ship.getY_begin(), ship.getY_end())-1; i < Math.max(ship.getY_begin(), ship.getY_end()); i++) {
                    for (int j = Math.min(ship.getX_begin(), ship.getX_end())-1; j < Math.max(ship.getX_begin(), ship.getX_end()); j++) {
                        setDisplayBoard(i, j,getMyBoard(i,j)); // Đánh dấu toàn bộ tàu chìm
                        setMyBoard(i,j, 'X');
                    }
                }
                System.out.println("Tàu " + ship.getName() + " đã bị bắn hạ!");
                numberShip--; // Giảm số lượng tàu còn lại
                checkWin(); // Kiểm tra điều kiện thắng
                if (check == 1) break; // Thoát vòng lặp nếu đã thắng
            }
        }
    }

    public void checkWin() {
        if (numberShip == 0) { // Nếu không còn tàu nào
            showDisplayBoard(); // Hiển thị bảng tấn công
            System.out.println("Bạn " +namePlayer+" là người chiến thắng!");
            check = 1; // Đánh dấu trạng thái thắng
            System.exit(0);
        }
    }
}
