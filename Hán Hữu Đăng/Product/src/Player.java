import java.util.Scanner;

public class Player {
    private String name;
    private Board ownBoard;
    private Board opponentBoard;

    public Player(String name) {
        this.name = name;
        ownBoard = new Board();
        opponentBoard = new Board();
    }

    public String getName() {
        return name;
    }

    public void placeShips(Scanner scanner) {
        System.out.println("Placing ships for " + name);
        ownBoard.display();
        for (ShipType type : ShipType.values()) {
            boolean placed = false;
            while (!placed) {
                System.out.println("Enter the starting coordinate (e.g., A1) and direction (H/V) for " + type);
                String input = scanner.nextLine();
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    Coordinate start = Coordinate.fromString(parts[0]);
                    boolean isVertical = parts[1].equalsIgnoreCase("V");
                    placed = ownBoard.placeShip(new Ship(type), start, isVertical);
                    if (!placed) {
                        System.out.println("Invalid placement. Try again.");
                    }
                } else {
                    System.out.println("Invalid input. Try again.");
                }
            }
            ownBoard.display();
        }
    }

    public void takeTurn(Scanner scanner, Player opponent) {
        while (true) {
            System.out.println(name + ", it's your turn.");
            System.out.println("1. View your board");
            System.out.println("2. Enter coordinates to attack");
            System.out.println("3. End turn");
            System.out.println("Current status: " + opponentBoard.getFiredCount() + " cells fired, " + opponentBoard.getSunkCount() + " ships sunk, " + ownBoard.getRemainingShips() + " ships remaining.");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    ownBoard.display();
                    break;
                case 2:
                    System.out.println("Enter the coordinate to fire at (e.g., A1):");
                    String input = scanner.nextLine();
                    Coordinate target = Coordinate.fromString(input);
                    boolean hit = opponent.ownBoard.fireAt(target);
                    if (hit) {
                        System.out.println("Hit!");
                    } else {
                        System.out.println("Miss!");
                    }
                    opponentBoard.update(target, hit);
                    opponentBoard.display();
                    return; // End turn after attack
                case 3:
                    return; // End turn
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public boolean hasLost() {
        return ownBoard.allShipsSunk();
    }
}