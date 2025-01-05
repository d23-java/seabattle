package game;

public class Bot extends Player {
    private Integer[][] priority; // Bảng xét mức độ ưu tiên
    public int soLanBan = 0;
    // Constructor
    public Bot() {
        priority = new Integer[21][21];
        board = new String[21][21];
        enemyBoard = new String[21][21];
        for(int i = 1; i <= 20; i++) {
            for(int j = 1; j <= 20; j++) {
                priority[i][j] = 0;
                board[i][j] = ".";
                enemyBoard[i][j] = "?";
            }
        }
    }

    public void updatePriority(int hoanhdo, int tungdo, int value) {
        if (value == 100) priority[hoanhdo][tungdo] = value;
        else priority[hoanhdo][tungdo] += 1;
    }

    public Integer[][] getPriority() {
        return priority;
    }
}
