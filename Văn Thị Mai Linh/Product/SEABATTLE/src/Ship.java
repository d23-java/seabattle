import java.util.ArrayList;

public class Ship {
    private String nameShip;
    private int size;
    private int x_begin;
    private int x_end;
    private int y_begin;
    private int y_end;
    private int hits;
    private ArrayList<int[]> ship = new ArrayList<int[]>();// danh sach luu cac toa do cua 1 thuyen, moi phan tu la mot mang 1 chieu gom 1 diem toa do x, y
    private ArrayList<Boolean>hitStatus = new ArrayList<Boolean>(); // luu trang thai cua cac o ( da bi ban trung hay chua)
    public Ship(String nameShip, ArrayList<int[]>ship, ArrayList<Boolean>hitStatus){
            this.nameShip = nameShip;
            this.ship = ship;
            this.hitStatus = hitStatus;
    }

    public void setHitStatus(ArrayList<Boolean> hitStatus) {
        for(int i  = 0; i < ship.size(); i++){
            hitStatus.add(false); // ban dau chua o nao trung
        }
    }

    public String getNameShip(){
        return nameShip;
    }

    public ArrayList<int[]> getShip(){
        return ship;
    }

    public boolean isSunk(){
        return !hitStatus.contains(false);// neu o nao cung bi ban thi thuyen chim
    }
    public boolean isOccupyCoordinate(int x, int y){ // kiem tra xem toa do cua dich ban co trung vao toa do cua thuyen minh khong
        for(int i = 0; i < ship.size(); i++){
            int[] coord = ship.get(i);
            if(coord[0] == x && coord[1] == y) {
                hitStatus.set(i, true);
                return true;
            }
        }
        return false;
    }
}
