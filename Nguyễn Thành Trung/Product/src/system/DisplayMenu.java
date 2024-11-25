package system;

import mapresources.Board;

public class DisplayMenu {
    public void setBoardSize() {
        System.out.print("Insert size of your sea: ");
        int boardSize = ComputerSystem.scanner.nextInt();
        Board.boardSize = boardSize;
        ComputerSystem.scanner.nextLine();
    }

    public void menuDisplay() {
        System.out.println("Select mode:");
        System.out.println("1. PVP");
        System.out.println("2. PVE");
        int mode = ComputerSystem.scanner.nextInt();
        BattleSystem battleSystem = new BattleSystem();
        ComputerSystem.clearScreen();
        setBoardSize();
        ComputerSystem.clearScreen();
        switch (mode) {
            case 1:
                battleSystem.PVPmode();
                break;
            case 2:
                battleSystem.PVEmode();
                break;
        }
    }
}
