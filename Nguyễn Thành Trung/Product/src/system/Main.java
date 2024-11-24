package system;

public class Main {
    public static void main(String[] args) {
        ComputerSystem.displayScreen();
        ComputerSystem.scanner.nextLine();
        ComputerSystem.clearScreen();
        BattleSystem battleSystem = new BattleSystem();
        battleSystem.PVPmode();
    }
}