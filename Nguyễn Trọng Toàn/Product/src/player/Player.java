package player;

import Xulichuongtrinh.ClearScreen;
import bang.Bang;
import java.util.Scanner;

public class Player {
    ClearScreen clearScreen = new ClearScreen();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";

    private Bang bang;

    public Player(Bang bang) {
        this.bang = bang;
    }

    private String name;
    Scanner scanner = new Scanner(System.in);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int xuli(char s1, char s2) {
        int x = 0;
        x = (s1 - '0') * 10 + (s2 - '0');
        return x;
    }

    public boolean kiemtradattau(int x1, int y1, int x2, int y2) {
        if (x1 < 0 || x2 < 0 || y1 < 1 || y2 < 1 || x1 >= bang.getSize() || x2 >= bang.getSize() || y1 > bang.getSize() || y2 > bang.getSize()) {
            System.out.println(ANSI_RED + "Bạn đã nhập ngoài giới hạn, vui lòng nhập lại." + ANSI_RESET);
            return false;
        }

        if (x1 == x2) {
            for (int i = y1 - 1; i < y2; ++i) {
                if (bang.getPhanTuBang(x1, i) == 'B') {
                    System.out.println(ANSI_YELLOW + "Vị trí đã bị chiếm, vui lòng nhập lại." + ANSI_RESET);
                    return false;
                }
            }
        } else if (y1 == y2) {
            for (int i = x1; i <= x2; ++i) {
                if (bang.getPhanTuBang(i, y1 - 1) == 'B') {
                    System.out.println(ANSI_YELLOW + "Vị trí đã bị chiếm, vui lòng nhập lại." + ANSI_RESET);
                    return false;
                }
            }
        } else {
            System.out.println(ANSI_RED + "Vị trí thuyền không hợp lệ, chỉ đặt được theo hàng hoặc cột." + ANSI_RESET);
            return false;
        }
        return true;
    }

    public boolean kiemTraShoot(int x1, int y1) {
        if (x1 < 0 || y1 < 0 || x1 >= bang.getSize() || y1 >= bang.getSize()) {
            System.out.println(ANSI_RED + "Bạn đã bắn ngoài giới hạn, vui lòng nhập lại." + ANSI_RESET);
            return false;
        }
//        if (bang.getPhanTuBang(x1, y1) == 'X') {
//            System.out.println("Vị trí đã bị bắn, vui lòng nhập lại.");
//            return false;
//        }
        // anh chị của em cho em hỏi với là chỗ này sao em kiểm tra như này lại sai ạ em cảm ơn ạ
        return true;
    }

    public void datthuyen() {
        System.out.println(ANSI_BLUE + "Bạn hãy đặt 2 thuyền tuần tra (1*2)." + ANSI_RESET);
        String x, y;
        boolean ok = true;
        for (int k = 0; k < 2; ++k) {
            while (ok) {
                x = scanner.nextLine();
                y = scanner.nextLine();
                int x1 = x.charAt(0) - 'a';
                int y1 = (x.length() <= 2) ? x.charAt(1) - '0' : xuli(x.charAt(1), x.charAt(2));
                int x2 = y.charAt(0) - 'a';
                int y2 = (y.length() <= 2) ? y.charAt(1) - '0' : xuli(y.charAt(1), y.charAt(2));
                if (!kiemtradattau(x1, y1, x2, y2)) {
                    continue;
                }
                ok = false;
                if (x1 == x2) {
                    for (int i = y1 - 1; i < y2; ++i) {
                        bang.capNhatBang(x1, i, 'B');
                    }
                } else if (y1 == y2) {
                    for (int i = x1; i <= x2; ++i) {
                        bang.capNhatBang(i, y1 - 1, 'B');
                    }
                }
                System.out.println(ANSI_GREEN + "Bạn đã đặt thuyền thành công." + ANSI_RESET);
            }
            ok = true;
        }
    }

    public void dattaukhutruc() {
        System.out.println(ANSI_BLUE + "Bạn hãy đặt 1 tàu khu trục (1*4)." + ANSI_RESET);
        String x, y;
        boolean ok = true;
        while (ok) {
            x = scanner.nextLine();
            y = scanner.nextLine();
            int x1 = x.charAt(0) - 'a';
            int y1 = (x.length() <= 2) ? x.charAt(1) - '0' : xuli(x.charAt(1), x.charAt(2));
            int x2 = y.charAt(0) - 'a';
            int y2 = (y.length() <= 2) ? y.charAt(1) - '0' : xuli(y.charAt(1), y.charAt(2));
            if (!kiemtradattau(x1, y1, x2, y2)) {
                continue;
            }
            ok = false;
            if (x1 == x2) {
                for (int i = y1 - 1; i < y2; ++i) {
                    bang.capNhatBang(x1, i, 'B');
                }
            } else if (y1 == y2) {
                for (int i = x1; i <= x2; ++i) {
                    bang.capNhatBang(i, y1 - 1, 'B');
                }
            }
        }
    }

    public void dattaungam() {
        System.out.println(ANSI_BLUE + "Bạn hãy đặt 1 tàu ngầm (1*3)." + ANSI_RESET);
        String x, y;
        boolean ok = true;
        while (ok) {
            x = scanner.nextLine();
            y = scanner.nextLine();
            int x1 = x.charAt(0) - 'a';
            int y1 = (x.length() <= 2) ? x.charAt(1) - '0' : xuli(x.charAt(1), x.charAt(2));
            int x2 = y.charAt(0) - 'a';
            int y2 = (y.length() <= 2) ? y.charAt(1) - '0' : xuli(y.charAt(1), y.charAt(2));
            if (!kiemtradattau(x1, y1, x2, y2)) {
                continue;
            }
            ok = false;
            if (x1 == x2) {
                for (int i = y1 - 1; i < y2; ++i) {
                    bang.capNhatBang(x1, i, 'B');
                }
            } else if (y1 == y2) {
                for (int i = x1; i <= x2; ++i) {
                    bang.capNhatBang(i, y1 - 1, 'B');
                }
            }
        }
    }

    public void datthietgiap() {
        System.out.println(ANSI_BLUE + "Bạn hãy đặt 1 thiết giáp hạm (1*4)." + ANSI_RESET);
        String x, y;
        boolean ok = true;
        while (ok) {
            x = scanner.nextLine();
            y = scanner.nextLine();
            int x1 = x.charAt(0) - 'a';
            int y1 = (x.length() <= 2) ? x.charAt(1) - '0' : xuli(x.charAt(1), x.charAt(2));
            int x2 = y.charAt(0) - 'a';
            int y2 = (y.length() <= 2) ? y.charAt(1) - '0' : xuli(y.charAt(1), y.charAt(2));
            if (!kiemtradattau(x1, y1, x2, y2)) {
                continue;
            }
            ok = false;
            if (x1 == x2) {
                for (int i = y1 - 1; i < y2; ++i) {
                    bang.capNhatBang(x1, i, 'B');
                }
            } else if (y1 == y2) {
                for (int i = x1; i <= x2; ++i) {
                    bang.capNhatBang(i, y1 - 1, 'B');
                }
            }
        }
    }

    public void shoot(Bang bang) {
        System.out.println(ANSI_GREEN + "Hãy nhập ô bạn muốn bắn:" + ANSI_RESET);
        boolean ok = true;
        while (ok) {
            String x = scanner.nextLine();
            int x1 = x.charAt(0) - 'a';
            int y1 = (x.length() <= 2) ? x.charAt(1) - '0' : xuli(x.charAt(1), x.charAt(2));
            if (kiemTraShoot(x1, y1 - 1) == false) {
                continue;
            }
            if (bang.getPhanTuBang(x1, y1 - 1) == 'B') {
                System.out.println(ANSI_GREEN + "Bạn được bắn tiếp!" + ANSI_RESET);
            } else { // Nếu bắn trượt
                System.out.println(ANSI_RED + "Bạn đã bắn trượt. Chuyển lượt." + ANSI_RESET);
                ok = false; // Kết thúc lượt
            }
            bang.xoaViTri(x1, y1 - 1);
            bang.inLichSuBan();
        }
    }
}
