import static java.lang.Math.max;
import static java.lang.Math.min;

public class Ship  {

    private String name;
    // Tọa độ bắt đầu và kết thúc của tàu trên bảng
    private int x_begin, y_begin, x_end, y_end;
    // Kích thước của tàu
    private int sizeShip;

    // Constructor: khởi tạo một con tàu với các thông tin cơ bản
    public Ship  (String name, int x_begin, int y_begin, int x_end, int y_end, int sizeShip) {
        this.name = name;
        this.x_begin = x_begin;
        this.y_begin = y_begin;
        this.x_end = x_end;
        this.y_end = y_end;
        this.sizeShip = sizeShip;
    }

    // Lấy tên của tàu
    public String getName() {
        return name;
    }

    // Gán tên mới cho tàu
    public void setName(String name) {
        this.name = name;
    }

    // Lấy tọa độ bắt đầu theo trục X
    public int getX_begin() {
        return x_begin;
    }

    // Lấy tọa độ bắt đầu theo trục Y
    public int getY_begin() {
        return y_begin;
    }

    // Lấy tọa độ kết thúc theo trục X
    public int getX_end() {
        return x_end;
    }

    // Lấy tọa độ kết thúc theo trục Y
    public int getY_end() {
        return y_end;
    }

    // Lấy kích thước của tàu
    public int getSizeShip() {
        return sizeShip;
    }

    // Gán kích thước mới cho tàu
    public void setSizeShip(int sizeShip) {
        this.sizeShip = sizeShip;
    }
}
