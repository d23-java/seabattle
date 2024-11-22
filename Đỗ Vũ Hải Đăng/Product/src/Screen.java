import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.ArrayList;

public class Screen {
    private int size = 10;
    private String[][] matrix = new String[10][10];
    private ArrayList<Ship> activeShip = new ArrayList<>();
    public Screen() {
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                matrix[i][j] = "[ ]";
            }
        }

    }

    void addShip(Ship ship){
        activeShip.add(ship);
        for(int i = ship.coordinatesHeadRow;i <= ship.coordinatesEndRow;++i){
            for(int j = ship.coordinatesHeadCol;j <= ship.coordinatesEndCol;++j){
                matrix[i][j] = "[B]";
            }
        }
    }

    void display(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.print("\n");
        }
    }

    boolean checkShot(String coordinates){
         return matrix[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'].equals("[B]");
    }

    int checkShipBeDestroyed(){
        for(int i = 0; i < activeShip.size(); i++){
            if(!activeShip.get(i).checkShipBeDestroyed(matrix)){
                System.out.println("Defeat " +activeShip.get(i).nameShip);
                activeShip.remove(i);
                return i;
            }
        }
        return -1;
    }

    String[][] getMatrix(){
        return matrix;
    }
}
