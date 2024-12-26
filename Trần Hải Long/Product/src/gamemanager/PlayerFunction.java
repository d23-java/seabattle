package gamemanager;

import java.util.List;

import game.Board;
import game.Main;
import game.Player;
import game.Cell;
import enums.*;
import items.Item;

import game.Menu;

import static gamemanager.GameFunction.alert;

public class PlayerFunction {
    public BoardController boardController;
    private final Player player;
    public PlayerFunction(Player player, BoardController boardController) {
        this.player = player;
        this.boardController = boardController;
    }

    public void placeShips() {
        System.out.println("Lượt đặt thuyền của " + Menu.blue + player.getName() + Menu.reset + " sẽ bắt đầu sau 3s");
        GameFunction.clearScreen(2);
        System.out.print("Nếu bạn muốn hệ thống tự động đặt thuyền, hãy nhập \"0\", còn không thì bạn hãy gõ bất kì kí tự khác: ");
        int option = Main.getValidOptionWithPrompt("Bạn đã chọn cách đặt không nằm trong hệ thống, vui lòng nhập lại: ");
        if(option == 0) {
            ShipPlacement.placeShipsRandomly(player.getShips(), boardController);
        }
        else {
            ShipPlacement.placeShipsManually(player.getShips(), boardController);
        }
    }

    public FireResult fireAt(Board opponentBoard) {
        FireResult result = FireResult.MISS;
        char xAris;
        int yAris;
        boolean valid = false;
        do {
            System.out.println(Menu.yellow + "⚠\uFE0F Nếu bạn muốn tạm dừng, hãy nhập tọa độ là \"00\"" + Menu.reset);
            System.out.print(player.getName() + ", hãy nhập tọa độ bắn (ví dụ: B5): ");
            String input = Main.scanner.nextLine().toUpperCase();
            if (input.equals("00")) {
                return null;
            }
            try {
                xAris = input.charAt(0);
                yAris = Integer.parseInt(input.substring(1));
                if (opponentBoard.isCoordinateValid(xAris, yAris)) {
                    Cell target = opponentBoard.getCell(xAris, yAris);
                    if (target.getStatus() != CellStatus.HIT && target.getStatus() != CellStatus.MISS) {
                        result = boardController.fireAt(target);
                        valid = true;
                    } else {
                        alert("Bạn đã bắn điểm này trước đó.");
                    }
                } else alert("Định dạng tọa độ không hợp lệ. Vui lòng thử lại.");
            } catch (Exception e) {
                alert("Định dạng tọa độ không hợp lệ. Vui lòng thử lại.");
            }
        } while (!valid);
        return result;
    }

    public void useItem(int index, BoardController opponentBoardController) {
        List<Item> items = player.getItems();
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);
            System.out.print("Bạn hãy nhập tọa độ kích hoạt vật phẩm (ví dụ: B5): ");
            String input = Main.scanner.nextLine().toUpperCase();
            try {
                char x = input.charAt(0);
                int y = Integer.parseInt(input.substring(1));
                if (opponentBoardController.getBoard().isCoordinateValid(x, y))
                    item.activate(player, opponentBoardController, x, y);
            } catch (Exception e) {
                alert("Định dạng tọa độ không hợp lệ. Vui lòng thử lại.");
            }
            items.remove(index);
        } else {
            System.out.println("Chuyển sang giao diện xem bản đồ...");
        }
    }
}