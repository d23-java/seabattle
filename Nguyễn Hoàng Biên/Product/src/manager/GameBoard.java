package manager;

import user.Player;

import java.util.ArrayList;
public class GameBoard {
    public static ArrayList<Player> player;
    protected String[][] grid;
    protected int size;

    public int getSize() {
        return size;
    }

    public GameBoard(int size) {
        this.size = size;
        grid = new String[size+5][size+5];
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                if(i == 0 && j != 0){
                    grid[i][j] = (Integer.toString(j));
//                    if(grid[i][j] == ':') grid[i][j] = "10";
                }
                else if(i != 0 && j == 0){
                    grid[i][j] = (String.valueOf((char) ('A' + i - 1)));
                }
                else if(i == 0 && j == 0) grid[i][j] = "";
                else grid[i][j] = "-";
            }
        }
    }

    public String[][] getGrid() {
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }
    public GameBoard(){
        player = new ArrayList<>();
    }

    public boolean haveShip(int x,int y){

        return grid[x][y] == "S";
    }
    public void markShot(int x, int y) {
        if(haveShip(x,y)) grid[x][y] = "X";
        else grid[x][y] = "0";
    }
}
