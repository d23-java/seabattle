package enums;

import gameObjects.Ships;

public class defaultShip {

        public static  Ships PATROLBOAT = new Ships("Patrol Boat", 2, Color.ANSI_CYAN_BACKGROUND );
        public static  Ships DESTROYERBOAT = new Ships("Destroyer Boat", 4, Color.ANSI_YELLOW_BACKGROUND );
        public static  Ships SUBMARINE = new Ships("Submarine", 3, Color.ANSI_BLUE_BACKGROUND);
        public static  Ships BATTLESHIP = new Ships("Battleship", 5, Color.ANSI_PURPLE_BACKGROUND);
}
