package System;

public class MenuList {
    public static void showMainMenu(){
        System.out.println("  ▄████████    ▄████████    ▄████████        ▀█████████▄     ▄████████     ███         ███      ▄█          ▄████████ ");
        System.out.println("  ███    ███   ███    ███   ███    ███         ███    ███   ███    ███ ▀█████████▄ ▀█████████▄ ███         ███    ███ ");
        System.out.println("  ███    █▀    ███    █▀    ███    ███         ███    ███   ███    ███    ▀███▀▀██    ▀███▀▀██ ███         ███    █▀ ");
        System.out.println("  ███         ▄███▄▄▄       ███    ███        ▄███▄▄▄██▀    ███    ███     ███   ▀     ███   ▀ ███        ▄███▄▄▄ ");
        System.out.println("▀███████████ ▀▀███▀▀▀     ▀███████████       ▀▀███▀▀▀██▄  ▀███████████     ███         ███     ███       ▀▀███▀▀▀ ");
        System.out.println("         ███   ███    █▄    ███    ███         ███    ██▄   ███    ███     ███         ███     ███         ███    █▄");
        System.out.println("   ▄█    ███   ███    ███   ███    ███         ███    ███   ███    ███     ███         ███     ███▌    ▄   ███    ███");
        System.out.println(" ▄████████▀    ██████████   ███    █▀        ▄█████████▀    ███    █▀     ▄████▀      ▄████▀   █████▄▄██   ██████████");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("                                                      1. New Game");
        System.out.println("                                                     2. Load Game");
        System.out.println("                                                      3. Settings");
        System.out.println("                                                    4. LeaderBoard");
        System.out.println("                                                       5. Exit");
        System.out.print("                                                    Your Choice: ");
    }
    public static void showGameModeMenu(){
        System.out.println("  ▄████████    ▄████████    ▄████████        ▀█████████▄     ▄████████     ███         ███      ▄█          ▄████████");
        System.out.println("  ███    ███   ███    ███   ███    ███         ███    ███   ███    ███ ▀█████████▄ ▀█████████▄ ███         ███    ███");
        System.out.println("  ███    █▀    ███    █▀    ███    ███         ███    ███   ███    ███    ▀███▀▀██    ▀███▀▀██ ███         ███    █▀");
        System.out.println("  ███         ▄███▄▄▄       ███    ███        ▄███▄▄▄██▀    ███    ███     ███   ▀     ███   ▀ ███        ▄███▄▄▄");
        System.out.println("▀███████████ ▀▀███▀▀▀     ▀███████████       ▀▀███▀▀▀██▄  ▀███████████     ███         ███     ███       ▀▀███▀▀▀");
        System.out.println("         ███   ███    █▄    ███    ███         ███    ██▄   ███    ███     ███         ███     ███         ███    █▄");
        System.out.println("   ▄█    ███   ███    ███   ███    ███         ███    ███   ███    ███     ███         ███     ███▌    ▄   ███    ███");
        System.out.println(" ▄████████▀    ██████████   ███    █▀        ▄█████████▀    ███    █▀     ▄████▀      ▄████▀   █████▄▄██   ██████████");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("                                                   1. Play with Player");
        System.out.println("                                                  2. Play with Computer");
        System.out.println("                                                        3. Exit");
        System.out.print("                                                      Your Choice: ");
    }
    public static void showShipChoice(){
        System.out.println("Choose your ship:");
        System.out.println("1. Patrol Boat");
        System.out.println("2. Destroyer Boat");
        System.out.println("3. Submarine");
        System.out.println("4. Battle Ship");
    }
    public static void showPlayerOption(){
        System.out.println("     PLAYER OPTION");
        System.out.println("+---------------------+");
        System.out.println("|       1. Shot       |");
        System.out.println("+---------------------+");
        System.out.println("|    2. View Board    |");
        System.out.println("+---------------------+");
        System.out.println("|     3. End turn     |");
        System.out.println("+---------------------+");
        System.out.println(" ");
    }
    public static void showPlayerTitle(int idPlayer){
        if(idPlayer == 1){
            System.out.println("  ▄███████▄  ▄█          ▄████████ ▄██   ▄      ▄████████    ▄████████         █████ ");
            System.out.println("  ███    ███ ███         ███    ███ ███   ██▄   ███    ███   ███    ███       ░░░███");
            System.out.println("  ███    ███ ███         ███    ███ ███▄▄▄███   ███    █▀    ███    ███         ░███ ");
            System.out.println("  ███    ███ ███         ███    ███ ▀▀▀▀▀▀███  ▄███▄▄▄      ▄███▄▄▄▄██▀         ░███ ");
            System.out.println("▀█████████▀  ███       ▀███████████ ▄██   ███ ▀▀███▀▀▀     ▀▀███▀▀▀▀▀           ░███ ");
            System.out.println("  ███        ███         ███    ███ ███   ███   ███    █▄  ▀███████████         ░███ ");
            System.out.println("  ███        ███▌    ▄   ███    ███ ███   ███   ███    ███   ███    ███         ░███");
            System.out.println(" ▄████▀      █████▄▄██   ███    █▀   ▀█████▀    ██████████   ███    ███         █████ ");
            System.out.println("             ▀                                                                 ░░░░░");
            System.out.println(" ");
        }

        else if(idPlayer == 2){
            System.out.println("  ▄███████▄  ▄█          ▄████████ ▄██   ▄      ▄████████    ▄████████         ████████");
            System.out.println("  ███    ███ ███         ███    ███ ███   ██▄   ███    ███   ███    ███       ███░░░░███");
            System.out.println("  ███    ███ ███         ███    ███ ███▄▄▄███   ███    █▀    ███    ███      ░░░    ░███");
            System.out.println("  ███    ███ ███         ███    ███ ▀▀▀▀▀▀███  ▄███▄▄▄      ▄███▄▄▄▄██▀         ███████");
            System.out.println("▀█████████▀  ███       ▀███████████ ▄██   ███ ▀▀███▀▀▀     ▀▀███▀▀▀▀▀          ███░░░░");
            System.out.println("  ███        ███         ███    ███ ███   ███   ███    █▄  ▀███████████       ███      ");
            System.out.println("  ███        ███▌    ▄   ███    ███ ███   ███   ███    ███   ███    ███      ░███     ██");
            System.out.println(" ▄████▀      █████▄▄██   ███    █▀   ▀█████▀    ██████████   ███    ███      ░██████████");
            System.out.println("             ▀                                                               ░░░░░░░░░░");
        }
    }
    public static void letStartTitle(){
        System.out.println(" ▄█          ▄████████     ███             ▄████████     ███        ▄████████    ▄████████     ███    ");
        System.out.println("███         ███    ███ ▀█████████▄        ███    ███ ▀█████████▄   ███    ███   ███    ███ ▀█████████▄");
        System.out.println("███         ███    █▀     ▀███▀▀██        ███    █▀     ▀███▀▀██   ███    ███   ███    ███    ▀███▀▀██");
        System.out.println("███        ▄███▄▄▄         ███   ▀        ███            ███   ▀   ███    ███  ▄███▄▄▄▄██▀     ███   ▀");
        System.out.println("███       ▀▀███▀▀▀         ███          ▀███████████     ███     ▀███████████ ▀▀███▀▀▀▀▀       ███    ");
        System.out.println("███         ███    █▄      ███                   ███     ███       ███    ███ ▀███████████     ███    ");
        System.out.println("███▌    ▄   ███    ███     ███             ▄█    ███     ███       ███    ███   ███    ███     ███    ");
        System.out.println("█████▄▄██   ██████████    ▄████▀         ▄████████▀     ▄████▀     ███    █▀    ███    ███    ▄████▀");
    }
    public static void setShipOptionMenu(){
        System.out.println("  +----Set up Ship----+");
        System.out.println("  | 1.    Basic       |");
        System.out.println("  | 2.    Auto        |");
        System.out.println("  +-------------------+");
    }
    public static void printNumber(int number){
        if(number == 1){
            System.out.println("█████ ");
            System.out.println("░░███");
            System.out.println(" ░███ ");
            System.out.println(" ░███ ");
            System.out.println(" ░███ ");
            System.out.println(" ░███ ");
            System.out.println(" ░███");
            System.out.println(" █████ ");
            System.out.println("░░░░░");
        }
        else if(number == 2){
            System.out.println("  ████████");
            System.out.println(" ███░░░░███");
            System.out.println("░░░    ░███");
            System.out.println("   ███████");
            System.out.println("  ███░░░░");
            System.out.println(" ███      ");
            System.out.println("░███     ██");
            System.out.println("░██████████");
            System.out.println("░░░░░░░░░░");
        }
        else if(number == 3){
            System.out.println("  ████████");
            System.out.println(" ███░░░░███");
            System.out.println("░░░    ░███");
            System.out.println("   ██████░");
            System.out.println("  ░░░░░░███");
            System.out.println(" ██    ░███");
            System.out.println(" ███   ░███");
            System.out.println("░░████████");
            System.out.println(" ░░░░░░░░");
        }
    }
    public static void hitNotify(){
        System.out.println("   ▄█    █▄     ▄█      ███");
        System.out.println("  ███    ███   ███  ▀█████████▄");
        System.out.println("  ███    ███   ███▌    ▀███▀▀██");
        System.out.println(" ▄███▄▄▄▄███▄▄ ███▌     ███   ▀");
        System.out.println("▀▀███▀▀▀▀███▀  ███▌     ███");
        System.out.println("  ███    ███   ███      ███");
        System.out.println("  ███    ███   ███      ███");
        System.out.println("  ███    █▀    █▀      ▄████▀");
    }
    public static void sunkNotify(){
        System.out.println("   ▄████████ ███    █▄  ███▄▄▄▄      ▄█   ▄█▄");
        System.out.println("  ███    ███ ███    ███ ███▀▀▀██▄   ███ ▄███▀");
        System.out.println("  ███    █▀  ███    ███ ███   ███   ███▐██▀");
        System.out.println("  ███        ███    ███ ███   ███  ▄█████▀");
        System.out.println("▀███████████ ███    ███ ███   ███ ▀▀█████▄");
        System.out.println("         ███ ███    ███ ███   ███   ███▐██▄");
        System.out.println("   ▄█    ███ ███    ███ ███   ███   ███ ▀███▄");
        System.out.println(" ▄████████▀  ████████▀   ▀█   █▀    ███   ▀█▀");
        System.out.println("                                    ▀");
    }
    public static void isWinnerNotify(){
        System.out.println("    ▄█     ▄████████");
        System.out.println("   ███    ███    ███");
        System.out.println("   ███▌   ███    █▀");
        System.out.println("   ███▌   ███");
        System.out.println("   ███▌ ▀███████████");
        System.out.println("   ███           ███");
        System.out.println("   ███     ▄█    ███");
        System.out.println("   █▀    ▄████████▀");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("    ▄█     █▄   ▄█  ███▄▄▄▄   ███▄▄▄▄      ▄████████    ▄████████");
        System.out.println("   ███     ███ ███  ███▀▀▀██▄ ███▀▀▀██▄   ███    ███   ███    ███");
        System.out.println("   ███     ███ ███▌ ███   ███ ███   ███   ███    █▀    ███    ███");
        System.out.println("   ███     ███ ███▌ ███   ███ ███   ███  ▄███▄▄▄      ▄███▄▄▄▄██▀");
        System.out.println("   ███     ███ ███▌ ███   ███ ███   ███ ▀▀███▀▀▀     ▀▀███▀▀▀▀▀");
        System.out.println("   ███     ███ ███  ███   ███ ███   ███   ███    █▄  ▀███████████");
        System.out.println("   ███ ▄█▄ ███ ███  ███   ███ ███   ███   ███    ███   ███    ███");
        System.out.println("    ▀███▀███▀  █▀    ▀█   █▀   ▀█   █▀    ██████████   ███    ███");
    }
}
