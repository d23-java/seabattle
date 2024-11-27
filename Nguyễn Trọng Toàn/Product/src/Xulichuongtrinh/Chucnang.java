package Xulichuongtrinh;

import bang.Bang;
import player.Player;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Chucnang {
    ClearScreen clearScreen = new ClearScreen();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";

    private ArrayList<Bang> bangList = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);


    public Chucnang() {
        bangList.add(new Bang());
        bangList.add(new Bang());
    }

    public void menu() {
        System.out.println(ANSI_BLUE + "Hãy chọn chức năng:" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1. Bắt đầu game" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "2. Hãy chọn size bảng" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "3. Bảng xếp hạng" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "4. Nhạc nền" + ANSI_RESET);
        System.out.println(ANSI_RED + "5. Thoát trò chơi" + ANSI_RESET);

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                chonPlayer();
                break;
            case 2:
                for (Bang b : bangList) {
                    b.sizeBang();
                    b.taobang();
                }
                menu();
                break;
            case 3:
                System.out.println(ANSI_CYAN + "Hiển thị bảng xếp hạng (chưa triển khai)." + ANSI_RESET);
                menu();
                break;
            case 4:
                System.out.println(ANSI_CYAN + "Phát nhạc nền (chưa triển khai)." + ANSI_RESET);
                menu();
                break;
            case 5:
                System.out.println(ANSI_RED + "Thoát trò chơi." + ANSI_RESET);
                break;
            default:
                System.out.println(ANSI_RED + "Lựa chọn không hợp lệ, vui lòng thử lại." + ANSI_RESET);
                menu();
        }
    }

    public void chonPlayer() {
        System.out.println(ANSI_GREEN + "1. Chơi với người" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "2. Chơi với bot" + ANSI_RESET);
        int choice = Integer.parseInt(scanner.nextLine());
        clearScreen.clearScreen();
        switch (choice) {
            case 1:
                System.out.println(ANSI_BLUE + "Chế độ chơi với người được chọn." + ANSI_RESET);
                players.add(new Player(bangList.get(0)));
                players.add(new Player(bangList.get(1)));

                batDauGame();
                break;
            case 2:
                System.out.println(ANSI_YELLOW + "Chế độ chơi với bot được chọn." + ANSI_RESET);
                players.add(new Player(bangList.get(0)));

                batDauGame();
                break;
            default:
                System.out.println(ANSI_RED + "Lựa chọn không hợp lệ." + ANSI_RESET);
                chonPlayer();
        }
    }

    public void batDauGame() {
        System.out.println(ANSI_PURPLE + "\nBắt đầu trò chơi!" + ANSI_RESET);
        bangList.get(0).inBang();

        for (int i = 0; i < 2; ++i) {
            System.out.println(ANSI_GREEN + "Hãy điền tên của bạn:" + ANSI_RESET);
            String ten = scanner.nextLine();
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
            clearScreen.clearScreen();
        }
        shoot();
    }

    public void shoot() {
        bangList.get(0).taolichsuban();
        bangList.get(1).taolichsuban();

        while (true) {
            System.out.println(ANSI_BLUE + "Đến lượt của " + players.get(0).getName() + ANSI_RESET);
            players.get(0).shoot(bangList.get(1));
            if (bangList.get(1).kiemtra()) {
                System.out.println(ANSI_GREEN + players.get(0).getName() + " LÀ NGƯỜI CHIẾN THẮNG!" + ANSI_RESET);
                break;
            }

            System.out.println(ANSI_BLUE + "Đến lượt của " + players.get(1).getName() + ANSI_RESET);
            players.get(1).shoot(bangList.get(0));
            if (bangList.get(0).kiemtra()) {
                System.out.println(ANSI_GREEN + players.get(1).getName() + " LÀ NGƯỜI CHIẾN THẮNG!" + ANSI_RESET);
                break;
            }
        }
    }
}
