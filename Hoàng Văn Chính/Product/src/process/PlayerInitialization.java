package process;

import data.*;

public class PlayerInitialization {
    private Player player1;
    private Player player2;

    public void initPlayerName() {
        String name;
        System.out.println("Nhập tên người chơi 1: ");
        name = ScannerInput.scanner.nextLine();
        player1 = new Player(name);
        System.out.println("Nhập tên người chơi 2: ");
        name = ScannerInput.scanner.nextLine();
        player2 = new Player(name);
    }

    public void initPlayerShip() {

    }
}
