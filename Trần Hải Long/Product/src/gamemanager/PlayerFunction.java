package gamemanager;

import java.util.Random;
import java.util.Scanner;
import game.Player;
import game.Ship;
import game.Cell;
import enums.*;

public class PlayerFunction {
    Scanner scanner = new Scanner(System.in);
    public BoardController boardController;
    Random random = new Random();

    public PlayerFunction(BoardController boardController) {
        this.boardController = boardController;
    }

    public void placeShips(Player player) {
        for (Ship ship : player.getShips()) {
            boolean placed = false;
            while (!placed) {
                char x;
                int y;
                boolean valid;
                do {
                    x = (char) ('A'+random.nextInt(player.getBoard().getSize()));
                    y = random.nextInt(player.getBoard().getSize()) + 1;
                    valid = player.getBoard().isCoordinateValid(x, y);
                } while (!valid);

                Orientation orientation = random.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;

                Cell startCell = player.getBoard().getCell(x, y);
                placed = boardController.placeShip(ship, startCell, orientation);
            }
        }
    }

    public FireResult fireAt(Player player, Player opponent) {
        FireResult result = FireResult.MISS;
        char x;
        int y;
        boolean valid = false;
        do {
            System.out.print(player.getName() + ", hãy nhập tọa độ bắn (ví dụ: B5): ");
            String input = scanner.nextLine().toUpperCase();
            try {
                x = input.charAt(0);
                y = Integer.parseInt(input.substring(1));
                if (opponent.getBoard().isCoordinateValid(x, y)) {
                    Cell target = opponent.getBoard().getCell(x, y);
                    if (target.getStatus() != CellStatus.HIT && target.getStatus() != CellStatus.MISS) {
                        result = boardController.fireAt(target);
                        valid = true;
                    }
                    else {
                        System.out.println("Bạn đã bắn điểm này trước đó.");
                    }
                }
                else System.out.println("Định dạng tọa độ không hợp lệ. Vui lòng thử lại.");
            } catch (Exception e) {
                System.out.println("Định dạng tọa độ không hợp lệ. Vui lòng thử lại.");
            }
        } while (!valid);
        return result;
    }
}