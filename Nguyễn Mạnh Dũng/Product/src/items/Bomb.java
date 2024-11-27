//package items;
//
//import entities.Board;
//import main.Game;
//
//import static utilz.Constants.gameConstants.*;
//
//public class Bomb {
//    private Board board;
//    private Game game;
//
//    public Bomb(Board board, Game game) {
//        this.board = board;
//        this.game = game;
//    }
//
//    public static void placeBomb(String[][] opponentBoard, int I, int J) {
//        for(int i = -1; i <= 1; i++) {
//            for(int j = -1; j <= 1; j++) {
//                if(!opponentBoard[I-1+i][J-1+j].equals(MISS_CELL)) {
//                    opponentBoard[I-1+i][J-1+j] = DESTROYER_CELL;
//                }
//            }
//        }
//    }
//}
