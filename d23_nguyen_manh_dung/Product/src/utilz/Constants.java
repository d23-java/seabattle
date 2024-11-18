package utilz;

public class Constants {

    public static class textConstants {
        public static final String RESET = "\u001B[0m";
        public static final String BOLD = "\u001B[1m";

        public static final String RED_TEXT = "\u001B[31m";
        public static final String GREEN_TEXT = "\u001B[32m";
        public static final String YELLOW_TEXT = "\u001B[33m";
        public static final String BLUE_TEXT = "\u001B[34m";
        public static final String CYAN_TEXT = "\u001B[36m";

        public static final String RED_BACKGROUND = "\u001B[41m";
        public static final String GREEN_BACKGROUND = "\u001B[42m";
        public static final String YELLOW_BACKGROUND = "\u001B[43m";
        public static final String BLUE_BACKGROUND = "\u001B[44m";
        public static final String CYAN_BACKGROUND = "\u001B[46m";
        public static final String WHITE_BACKGROUND = "\u001B[47m";
        public static final String PINK_BACKGROUND = "\033[45m";

    }

    public static class audioConstants {
        //todo
    }

    public static class gameConstants {
        public static final int BOARD_SIZE = 10;
        public static final String EMPTY_BOAT = "_0_";
        public static final String PATROL_BOAT = textConstants.BLUE_BACKGROUND + "_P_" + textConstants.RESET;
        public static final String DESTROYER_BOAT = textConstants.GREEN_BACKGROUND + "_D_" + textConstants.RESET;
        public static final String SUBMARINE = textConstants.CYAN_BACKGROUND + "_S_" + textConstants.RESET;
        public static final String BATTLE_SHIP = textConstants.PINK_BACKGROUND + "_B_" + textConstants.RESET;
        public static final String DESTROYER_CELL = textConstants.RED_BACKGROUND + "_A_" + textConstants.RESET;
        public static final String SHOTTED_CELL = textConstants.YELLOW_BACKGROUND + "_B_" + textConstants.RESET;

        public static final int PATROL_BOAT_SIZE = 2;
        public static final int DESTROYER_BOAT_SIZE = 4;
        public static final int SUBMARINE_SIZE = 3;
        public static final int BATTLE_SHIP_SIZE = 5;

    }
}
