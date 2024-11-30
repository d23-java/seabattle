package gamemanager;

import java.util.List;
import java.util.Scanner;
import game.Player;
import game.Cell;
import enums.*;
import items.Item;

public class PlayerFunction {
    Scanner scanner = new Scanner(System.in);
    public BoardController boardController;
    public ShipPlacement shipPlacement = new ShipPlacement();

    public PlayerFunction(BoardController boardController) {
        this.boardController = boardController;
    }

    public void placeShips(Player player) {
        shipPlacement.placeShips(player, boardController);
    }

    public FireResult fireAt(Player player, Player opponent) {
        FireResult result = FireResult.MISS;
        char xAris;
        int yAris;
        boolean valid = false;
        do {
            System.out.println("(Nếu bạn muốn tạm dừng, hãy nhập tọa độ là \"00\")");
            System.out.print(player.getName() + ", hãy nhập tọa độ bắn (ví dụ: B5): ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("00")) {
                return null;
            }
            try {
                xAris = input.charAt(0);
                yAris = Integer.parseInt(input.substring(1));
                if (opponent.getBoard().isCoordinateValid(xAris, yAris)) {
                    Cell target = opponent.getBoard().getCell(xAris, yAris);
                    if (target.getStatus() != CellStatus.HIT && target.getStatus() != CellStatus.MISS) {
                        result = boardController.fireAt(target);
                        valid = true;
                    } else {
                        System.out.println("Bạn đã bắn điểm này trước đó.");
                    }
                } else System.out.println("Định dạng tọa độ không hợp lệ. Vui lòng thử lại.");
            } catch (Exception e) {
                System.out.println("Định dạng tọa độ không hợp lệ. Vui lòng thử lại.");
            }
        } while (!valid);
        return result;
    }

    public void useItem(Player currentTurn, int index, BoardController opponentBoardController) {
        List<Item> items = currentTurn.getItems();
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Bạn hãy nhập tọa độ kích hoạt vật phẩm (ví dụ: B5): ");
            String input = scanner.nextLine().toUpperCase();
            try {
                char x = input.charAt(0);
                int y = Integer.parseInt(input.substring(1));
                if (opponentBoardController.getBoard().isCoordinateValid(x, y))
                    item.activate(currentTurn, opponentBoardController, x, y);
            } catch (Exception e) {
                System.out.println("Định dạng tọa độ không hợp lệ. Vui lòng thử lại.");
            }
            items.remove(index);
        } else {
            System.out.println("Chuyển sang giao diện xem bản đồ...");
        }
    }
}