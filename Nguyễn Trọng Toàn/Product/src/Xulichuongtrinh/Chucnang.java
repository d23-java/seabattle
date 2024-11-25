package Xulichuongtrinh;

import bang.Bang;
import player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Chucnang {
    private ArrayList<Bang> bangList = new ArrayList<>(); // Danh sách các bảng
    private ArrayList<Player> players = new ArrayList<>(); // Danh sách người chơi
    private Scanner scanner = new Scanner(System.in);

    // Constructor khởi tạo danh sách bảng
    public Chucnang() {
        // Tạo sẵn 2 bảng (sau này có thể thêm nhiều hơn nếu cần)
        bangList.add(new Bang());
        bangList.add(new Bang());
    }

    public void menu() {
        System.out.println("Hãy chọn chức năng:");
        System.out.println("1. Bắt đầu game");
        System.out.println("2. Hãy chọn size bảng");
        System.out.println("3. Bảng xếp hạng");
        System.out.println("4. Nhạc nền");
        System.out.println("5. Thoát trò chơi");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                chonPlayer();
                break;
            case 2:
                for (Bang b : bangList) {
                    b.sizeBang(); // Cho phép nhập kích thước bảng riêng cho từng người chơi
                    b.taobang();  // Khởi tạo bảng sau khi chọn kích thước
                }
                menu();
                break;
            case 3:
                System.out.println("Hiển thị bảng xếp hạng (chưa triển khai).");
                menu();
                break;
            case 4:
                System.out.println("Phát nhạc nền (chưa triển khai).");
                menu();
                break;
            case 5:
                System.out.println("Thoát trò chơi.");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
                menu();
        }
    }

    public void chonPlayer() {
        System.out.println("1. Chơi với người");
        System.out.println("2. Chơi với bot");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                System.out.println("Chế độ chơi với người được chọn.");
                players.add(new Player(bangList.get(0))); // Người chơi 1 với bảng 1
                players.add(new Player(bangList.get(1))); // Người chơi 2 với bảng 2

                batDauGame();
                break;
            case 2:
                System.out.println("Chế độ chơi với bot được chọn.");
                players.add(new Player(bangList.get(0))); // Người chơi 1
//                players.add(new PlayerBot(bangList.get(1))); // Bot với bảng 2

                batDauGame();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                chonPlayer();
        }
    }

    public void batDauGame() {
        // Hiển thị bảng của tất cả người chơi
        System.out.println("\nBắt đầu trò chơi!");
        bangList.get(0).inBang();
        // Triển khai logic chơi game
        for(int i=0;i<2;++i){
            System.out.println("Hãy điền tên của bạn;");
            String ten= scanner.nextLine();
            players.get(i).setName(ten);
            bangList.get(i).inBang();
            players.get(i).datthuyen();
            bangList.get(i).inBang();
            players.get(i).dattaukhutruc();
            bangList.get(i).inBang();
            players.get(i).dattaungam();
            bangList.get(i).inBang();
            players.get(i).datthietgiap();
            bangList.get(i).inBang();
        }
        shoot();
    }
        public void shoot(){
        bangList.get(0).taolichsuban();
        bangList.get(1).taolichsuban();
            while (true){
                boolean ok1=true;
                boolean ok2=false;
                if(ok1){
                    System.out.println("Đến lượt của "+players.get(0).getName());
                    players.get(0).shoot(bangList.get(1));
                    ok2=true;
                    ok1=false;
                    if(bangList.get(1).kiemtra()){
                        System.out.println(players.get(0).getName()+" LÀ NGƯỜI CHIẾN THẮNG ");
                        break;
                    }
                }
                if(ok2){
                    System.out.println("Đến lượt của "+players.get(1).getName());
                    players.get(1).shoot(bangList.get(0));
                    if(bangList.get(0).kiemtra()){
                        System.out.println(players.get(1).getName()+" LÀ NGƯỜI CHIẾN THẮNG ");
                        break;
                    }
                    ok2=false;
                    ok1=true;
                }
            }
        }
}
