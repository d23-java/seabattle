package ObjectList;

import java.util.ArrayList;

public class Screen{
    public static int sizeScreen = 10;
    private String[][] matrix = new String[10][10];
    private ArrayList<Ship> activeShip = new ArrayList<>();
    public Screen(){
        for(int i = 0; i < sizeScreen; i++){
            for(int j = 0; j < sizeScreen; j++){
                matrix[i][j] = "[ ]";
            }
        }

    }

    void addShip(Ship ship){
        activeShip.add(ship);
        for(int i = ship.getCoordinatesHeadRow();i <= ship.getCoordinatesEndRow();++i){
            for(int j = ship.getCoordinatesHeadCol();j <= ship.getCoordinatesEndCol();++j){
                switch (ship.getNameShip()){
                    case "Patrol Boat" -> matrix[i][j] = "[P]";
                    case "Destroyer Boat" -> matrix[i][j] = "[D]";
                    case "Submarine" -> matrix[i][j] = "[S]";
                    case "Battle Boat" -> matrix[i][j] = "[B]";
                }
            }
        }
    }

    public void display(){
        System.out.print("| |");
        for(int i = 0; i < sizeScreen; i++){
            System.out.print("|"+ i + "|");
        }
        System.out.print("\n");
        for(int i = 0; i < sizeScreen; i++){
            System.out.print("|" + (char)(i + 'a') + "|");
            for(int j = 0; j < sizeScreen; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.print("\n");
        }
    }

    boolean checkShot(String coordinates){
         return  (matrix[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'].equals("[B]")) ||
                 (matrix[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'].equals("[P]")) ||
                 (matrix[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'].equals("[D]")) ||
                 (matrix[(int)coordinates.charAt(0) - 'a'][(int)coordinates.charAt(1) - '0'].equals("[S]"));
    }

    int checkShipBeDestroyed(){
        for(int i = 0; i < activeShip.size(); i++){
            if(!activeShip.get(i).checkShipBeDestroyed(matrix)){
                activeShip.remove(i);
                return i;
            }
        }
        return -1;
    }

    public boolean checkCoordinates(String coordinates){
        if(coordinates.length() != 2) return false;
        if(coordinates.charAt(0) < 'a' || coordinates.charAt(0) > 'z'){
            return false;
        }
        if(coordinates.charAt(1) < '0' || coordinates.charAt(1) >= (char)(sizeScreen + '0') ) {
            return false;
        }
        return true;
    }

    String[][] getMatrix(){
        return matrix;
    }
}
