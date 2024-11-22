package game;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    private List<PlayerRecord> records;
    public Leaderboard() {
        records = new ArrayList<>();
    }
    public void addRecord(PlayerRecord record) {
        records.add(record);
    }
    public void display() {
        if (records.isEmpty()) {
            System.out.println("Hiện chưa có thành tích nào được lưu lại.");
        }
        else {
            System.out.println("╔══════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                             Bảng Xếp Hạng                                ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-32s | %-17s | %-17s ║\n", "Tên Người Chơi", "Số Lượt Bắn", "Số Tàu Còn Lại");
            for (PlayerRecord record : records) {
                System.out.printf("║ %-32s | %-17d | %-17d ║\n", record.getPlayerName(), record.getShotsTaken(), record.getRemainingShips());
            }
            System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");
        }
    }
}
