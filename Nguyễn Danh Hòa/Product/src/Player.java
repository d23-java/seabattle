import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Long.min;
import static java.lang.Math.max;

public class Player extends Field {
    private String namePlayer; // Tên của người chơi
    Scanner sc = new Scanner(System.in);
    public ArrayList<Ship> ships = new ArrayList<Ship>(); // Danh sách các tàu của người chơi

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
            String name; // Tên của tàu
            int size; // Kích cỡ của tàu
            if (numberShip < 2) { // Các điều kiện để xác định loại tàu
                name = "Thuyền Tuần Tra";
                size = 2;
            } else if (numberShip == 2) {
                name = "Tàu Khu Trục";
                size = 4;
            } else if (numberShip == 3) {
                name = "Tàu Ngầm";
                size = 3;
            } else {
                name = "Thiết Chiến Hạm";
                size = 5;
            }
            int checkShip = 1; // Biến để kiểm tra nếu tàu đã được đặt đúng
            while (checkShip == 1) {
                showMyBoard(); // Hiển thị bảng hiện tại của người chơi
                System.out.println("Đặt tàu: Thông tin " + name + " mang kích cỡ 1x" + size);
                System.out.print("Tọa độ đầu: ");
                int x_begin = sc.nextInt();
                int y_begin = sc.nextInt();
                System.out.print("Tọa độ cuối: ");
                int x_end = sc.nextInt();
                int y_end = sc.nextInt();
                sc.nextLine(); // Đọc ký tự dòng mới còn lại

                // Kiểm tra tọa độ nhập có hợp lệ với kích thước tàu không
                if (Math.abs(x_begin - x_end) != size - 1 && Math.abs(y_begin - y_end) != size - 1) {
                    System.out.println("Bạn đang nhập sai kích thước " + size + " của tàu " + name);
                    System.out.println("Vui lòng nhập lại");
                    continue; // Quay lại vòng lặp để nhập lại tọa độ
                }
                // Kiểm tra tọa độ có nằm trong kích thước bảng không
                if (x_begin > sizeField || x_end > sizeField || y_end > sizeField || y_begin > sizeField) {
                    System.out.println("Tọa độ vượt quá kích thước bảng! Vui lòng đặt lại tàu!");
                    continue;
                }

                // Kiểm tra khu vực có đủ không gian để đặt tàu không
                int cnt = 0; // Đếm số ô trống có thể đặt tàu
                for (int i = Math.min(y_end, y_begin) -1; i < Math.max(y_end, y_begin); i++) {
                    for (int j = Math.min(x_end, x_begin) -1 ; j < Math.max(x_end, x_begin); j++) {
                        if (getMyBoard(i, j) == ' ') cnt++;
                        else break; // Nếu trùng, dừng việc kiểm tra
                    }
                }

                if (cnt == size) { // Nếu đủ không gian để đặt tàu
                    for (int i = Math.min(y_end, y_begin) - 1; i < Math.max(y_end, y_begin); i++) {
                        for (int j = Math.min(x_end, x_begin) - 1; j < Math.max(x_end, x_begin); j++) {
                            char shipChar = (char) ('A' + numberShip); // Tạo ký tự đại diện cho tàu
                            setMyBoard(i, j, shipChar); // Đánh dấu tàu trên bảng của mình
                        }
                    }
                } else {
                    System.out.println("Bạn đã đặt trùng lên tàu, vui lòng đặt lại");
                    continue;
                }

                System.out.println("Bạn đã đặt tàu " + name + " thành công");
                ships.add(new Ship(name, x_begin, y_begin, x_end, y_end, size)); // Thêm tàu vào danh sách
                checkShip = 0; // Đánh dấu tàu đã được đặt xong
                numberShip++; // Tăng số lượng tàu đã đặt
            }
        }
        showMyBoard(); // Hiển thị bảng sau khi đặt tất cả tàu
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
                setDisplayBoard(x, y, 'o'); // Đánh dấu bắn trượt trên bảng
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
                        setMyBoard(i,j, 'x');
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
