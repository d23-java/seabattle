package bang;

import java.util.Scanner;

public class Bang {
    private int size;
    private char[][] bang;
    private char[][] lichSuBan;
    Scanner scanner = new Scanner(System.in);
    private char [][]bangshoot;
    // Nhập kích thước bảng
    public void sizeBang() {
        System.out.println("Hãy nhập size bảng bạn muốn (lưu ý size bảng nằm trong khoảng 10-20):");
        int choice = scanner.nextInt();
        while (choice < 10 || choice > 20) { // Kiểm tra đúng phạm vi
            System.out.println("Bạn đã nhập sai yêu cầu, vui lòng nhập lại:");
            choice = scanner.nextInt(); // Nhập lại giá trị
        }
        size = choice;
        lichSuBan =new char[size][size];
        bang = new char[size][size]; // Khởi tạo mảng `bang` sau khi biết kích thước
        System.out.println("Size bảng hiện tại là: " + choice);
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
        System.out.print("  "); // In khoảng trắng để căn lề
        for (int i = 1; i <= size; ++i) {
            if(i<10)System.out.printf("%3d ", i);
            else System.out.printf("%4d",i);

        }
        System.out.println();

        char rowLabel = 'A';
        for (int i = 0; i < size; ++i) {
            System.out.printf("%2c ", rowLabel++); // In chữ cái đại diện cho hàng
            for (int j = 0; j < size; ++j) {
                System.out.print("[" + bang[i][j] + "] ");
            }
            System.out.println();
        }
    }
    public void inLichSuBan() {
        System.out.println("Bảng lịch sử các tọa độ đã bắn:");
        System.out.print("  ");
        for (int i = 1; i <= size; ++i) {
            System.out.printf("%4d", i);
        }
        System.out.println();

        char rowLabel = 'A';
        for (int i = 0; i < size; ++i) {
            System.out.printf("%2c ", rowLabel++);
            for (int j = 0; j < size; ++j) {
                System.out.print("[" + lichSuBan[i][j] + "] ");
            }
            System.out.println();
        }
    }
    public boolean kiemtra(){
        for(int i=0;i<size;++i){
            for(int j=0;j<size;++j){
                if(bang[i][j]!=' ')return false;
            }
        }
        return true;
    }
    public void xoaViTri(int x1,int x2) {
        boolean timThay = false;
        if (bang[x1][x2] == 'B') {
            bang[x1][x2] = ' ';
            lichSuBan[x1][x2]='X';
            timThay = true;
        }
        if(timThay){
            System.out.println(" Bạn đã bắn trúng ");

        }
        else {
            lichSuBan[x1][x2]='O';
            System.out.println("Bạn đã bắn trượt");
        }
        inLichSuBan();
    }

//    public void setSize(int size) {
//        this.size = size;
//        bang = new char[size][size];
//        lichSuBan =new char[size][size];
//    }
    public void capNhatBang(int row, int col, char value) {
        bang[row][col] = value; // Cập nhật giá trị
    }
}
