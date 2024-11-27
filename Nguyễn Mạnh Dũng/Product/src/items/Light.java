//package items;
//
//import entities.Board;
//import entities.Player;
//import main.Game;
//
//import static utilz.Constants.gameConstants.DESTROYER_CELL;
//import static utilz.Constants.gameConstants.MISS_CELL;
//
//public class Light {
//    private Board board;
//    private Game game;
//
//    public Light(Board board, Game game) {
//        this.board = board;
//        this.game = game;
//    }
//
//    public static void placeLight(String[][] opponentBoard, int I, int J, Player opponent) {
//        for(int i = 0; i < opponentBoard.length; i++) {
//            for(int j = 0; j < opponentBoard[i].length; j++) {
//                if(i != I-1 && i != I && i != I+1 && j != J && j != J-1 && j != J+1) {
//                    System.out.println(opponent.getPlayerBoard());
//                }
//            }
//        }
//    }
//}
