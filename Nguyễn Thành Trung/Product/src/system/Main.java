package system;

public class Main {
    public static void main(String[] args) {
        ComputerSystem.displayScreen();
        ComputerSystem.scanner.nextLine();
        ComputerSystem.clearScreen();
        DisplayMenu displayMenu = new DisplayMenu();
        displayMenu.menuDisplay();
    }
}