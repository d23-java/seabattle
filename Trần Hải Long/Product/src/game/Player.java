package game;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import enums.ShipType;
import enums.CellStatus;
import gamemanager.BoardController;
import items.Item;

public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public String name;
    public Board board;
    public List<Ship> ships;
    public List<Item> items;
    public Player(String name, int boardSize) {
        this.name = name;
        this.board = new Board(boardSize);
        this.ships = new ArrayList<>();
        this.items = new ArrayList<>();
        initializeShips();
    }

    private void initializeShips() {
        ships.add(new Ship(ShipType.PATROL_BOAT));
        ships.add(new Ship(ShipType.PATROL_BOAT));
        ships.add(new Ship(ShipType.DESTROYER));
        ships.add(new Ship(ShipType.SUBMARINE));
        ships.add(new Ship(ShipType.BATTLESHIP));
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public int getHits() {
        int hits = 0;
        for (Ship ship : ships) {
            hits += (int) ship.getCells().stream().filter(c -> c.getStatus() == CellStatus.HIT).count();
        }
        return hits;
    }

    public int getSunkShips() {
        int sunk = 0;
        for (Ship ship : ships) {
            if (ship.isSunk()) {
                sunk++;
            }
        }
        return sunk;
    }

    public int getRemainingShips() {
        return ships.size()-getSunkShips();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void useItem(int index, BoardController opponentBoardController) {
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Bạn hãy nhập tọa độ kích hoạt vật phẩm (ví dụ: B5): ");
            String input = scanner.nextLine().toUpperCase();
            try {
                char x = input.charAt(0);
                int y = Integer.parseInt(input.substring(1));
                if (opponentBoardController.getBoard().isCoordinateValid(x, y))
                    item.activate(this, opponentBoardController, x, y);
            } catch (Exception e) {
                System.out.println("Định dạng tọa độ không hợp lệ. Vui lòng thử lại.");
            }
            items.remove(index);
        } else {
            System.out.println("Chuyển sang giao diện xem bản đồ...");
        }
    }
}
