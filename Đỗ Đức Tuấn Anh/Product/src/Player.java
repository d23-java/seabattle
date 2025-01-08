import java.util.Scanner;

public class Player {
    private String name;
    private Board board;
    private int shipsLeft;
    private int firedShots;  // Biến đếm số ô đã bắn

    public Player(String name) {
        this.name = name;
        this.board = new Board();
        this.shipsLeft = 16;
        this.firedShots = 0; // Mới bắt đầu, chưa bắn ô nào
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public int getShipsLeft() {
        return shipsLeft;
    }

    public void decreaseShipsLeft() {
        shipsLeft--;
    }

    public int getFiredShots() {
        return firedShots;
    }

    public void incrementFiredShots() {
        firedShots++;
    }

    public void placeShips() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + ", bạn hãy đặt các tàu của mình:");

        // Đặt 2 tàu 1x2
        for (int i = 0; i < 2; i++) {
            System.out.println("Đặt Thuyền Tuần Tra (1x2):");
            placeShip(scanner, 2);
        }

        // Đặt tàu 1x3
        System.out.println("Đặt Tàu Ngầm (1x3):");
        placeShip(scanner, 3);

        // Đặt tàu 1x4
        System.out.println("Đặt Tàu Khu Trục (1x4):");
        placeShip(scanner, 4);

        // Đặt tàu 1x5
        System.out.println("Đặt Thiết Giáp Hạm (1x5):");
        placeShip(scanner, 5);

        // Hiển thị bảng sau khi người chơi đặt xong tàu
        board.displayBoard(true);
    }

    private void placeShip(Scanner scanner, int size) {
        boolean placed = false;
        while (!placed) {
            System.out.println("Nhập vị trí tàu đầu tiên (ví dụ: A1): ");
            String startPosition = scanner.nextLine().toUpperCase();
            System.out.println("Nhập vị trí tàu cuối cùng (ví dụ: A2): ");
            String endPosition = scanner.nextLine().toUpperCase();

            // Chuyển tọa độ từ chữ cái và số thành chỉ số trong mảng
            int x1 = startPosition.charAt(0) - 'A';  // Chuyển từ chữ cái thành chỉ số cột (A -> 0, B -> 1, ...)
            int y1 = Integer.parseInt(startPosition.substring(1)) - 1; // Chuyển từ số thành chỉ số hàng (1 -> 0, 2 -> 1, ...)
            int x2 = endPosition.charAt(0) - 'A';
            int y2 = Integer.parseInt(endPosition.substring(1)) - 1;

            placed = board.placeShip(x1, y1, x2, y2, size);
            if (!placed) {
                System.out.println("Vị trí không hợp lệ. Thử lại.");
            } else {
                // Hiển thị bảng sau khi đặt tàu thành công
                board.displayBoard(true);
            }
        }
    }

    public void takeTurn(Player opponent) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + ", đến lượt bạn bắn:");
        System.out.println("Nhập vị trí bắn (ví dụ: A1): ");
        String target = scanner.nextLine().toUpperCase();

        int x = target.charAt(0) - 'A';
        int y = Integer.parseInt(target.substring(1)) - 1;

        String result = opponent.getBoard().fireAt(x, y);
        System.out.println("Kết quả: " + result);
        if (result.equals("Hit")) {
            System.out.println("Bạn đã bắn trúng tàu!");
            opponent.decreaseShipsLeft();
        }
        incrementFiredShots();  // Tăng số ô đã bắn
    }
}
