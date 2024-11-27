package bang;

import Xulichuongtrinh.ClearScreen;

import java.util.Scanner;

public class Bang {
    ClearScreen clearScreen = new ClearScreen();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";

    private int size;
    private char[][] bang;
    private char[][] lichSuBan;
    Scanner scanner = new Scanner(System.in);


    public void sizeBang() {
        System.out.println(ANSI_YELLOW + "Hãy nhập size bảng bạn muốn (lưu ý size bảng nằm trong khoảng 10-20):" + ANSI_RESET);
        int choice = scanner.nextInt();
        while (choice < 10 || choice > 20) {
            System.out.println(ANSI_RED + "Bạn đã nhập sai yêu cầu, vui lòng nhập lại:" + ANSI_RESET);
            choice = scanner.nextInt();
        }
        size = choice;
        lichSuBan = new char[size][size];
        bang = new char[size][size];
        System.out.println(ANSI_GREEN + "Size bảng hiện tại là: " + choice + ANSI_RESET);
    }

    public char getPhanTuBang(int x, int y) {
        return bang[x][y];
    }

    public char getLichSuBan(int x, int y) {
        return lichSuBan[x][y];
    }

    public int getSize() {
        return size;
    }

    // Khởi tạo bảng
    public void taobang() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                bang[i][j] = ' '; // Gán giá trị rỗng cho từng phần tử
            }
        }
    }

    public void taolichsuban() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                lichSuBan[i][j] = ' '; // Gán giá trị rỗng cho từng phần tử
            }
        }
    }

    // In bảng
    public void inBang() {
        System.out.print(ANSI_BLUE + "  " + ANSI_RESET); // In khoảng trắng để căn lề
        for (int i = 1; i <= size; ++i) {
            if (i < 10)
                System.out.printf("%3d ", i);
            else
                System.out.printf("%4d", i);
        }
        System.out.println();

        char rowLabel = 'A';
        for (int i = 0; i < size; ++i) {
            System.out.printf(ANSI_CYAN + "%2c " + ANSI_RESET, rowLabel++); // In chữ cái đại diện cho hàng
            for (int j = 0; j < size; ++j) {
                System.out.print("[" + bang[i][j] + "] ");
            }
            System.out.println();
        }
    }

    public void inLichSuBan() {
        System.out.println(ANSI_PURPLE + "Bảng lịch sử các tọa độ đã bắn:" + ANSI_RESET);
        System.out.print("  ");
        for (int i = 1; i <= size; ++i) {
            System.out.printf("%4d", i);
        }
        System.out.println();

        char rowLabel = 'A';
        for (int i = 0; i < size; ++i) {
            System.out.printf(ANSI_CYAN + "%2c " + ANSI_RESET, rowLabel++);
            for (int j = 0; j < size; ++j) {
                System.out.print("[" + lichSuBan[i][j] + "] ");
            }
            System.out.println();
        }
    }

    public boolean kiemtra() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (bang[i][j] == 'B') return false;
            }
        }
        return true;
    }

    public void xoaViTri(int x1, int x2) {
        if (bang[x1][x2] == 'B') {
            lichSuBan[x1][x2] = 'X';
        } else {
            lichSuBan[x1][x2] = 'O';
        }
        bang[x1][x2] = 'X';
    }

    public void capNhatBang(int row, int col, char value) {
        bang[row][col] = value;
    }
}
