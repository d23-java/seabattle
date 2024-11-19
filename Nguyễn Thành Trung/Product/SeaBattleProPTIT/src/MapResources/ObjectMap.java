package MapResources;

public class ObjectMap {
    private int mapsize = Board.boardSize;
    private int[][] objectMap = new int[11][11];

    public ObjectMap() {
        for (int row = 0; row < mapsize; row++) {
            for (int column = 0; column < mapsize; column++) {
                if (row == 0 || column == 0) {
                    objectMap[row][column] = 1;
                } else {
                    objectMap[row][column] = 0;
                }
            }
        }
    }

    public void setObjectMapCell(int row, int column, int value) {
        objectMap[row][column] = value;
    }
}
