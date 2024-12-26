package gamemanager;

import enums.CellStatus;
import enums.FireResult;
import game.Cell;
import game.Player;

import java.util.ArrayList;
import java.util.List;


public class BotFunction {
    public BoardController boardController;
    private final Player player;
    private char lastHitX = '@';
    private int lastHitY = 99;
    private int direction = 0; // 0: not set, 1: up, 2: down, 3: left, 4: right
    public List<Integer> attemptedDirections = new ArrayList<>();

    public BotFunction(Player player, BoardController boardController) {
        this.player = player;
        this.boardController = boardController;
    }

    public void placeShips() {
        ShipPlacement.placeShipsRandomly(player.getShips(), boardController);
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public FireResult fireAt(Player opponent) {
        FireResult result;
        if (lastHitX == '@' && lastHitY == 99) {
            result = searchAndFire(opponent, true);
        } else {
            result = searchAndFire(opponent, false);
        }
        return result;
    }

    private FireResult searchAndFire(Player opponent, boolean scanMode) {
        FireResult result = FireResult.MISS;
        char targetX;
        int targetY;
        int boardSize = player.getBoard().getSize();

        if(!scanMode) {
            result = continueDirectionFire(opponent);
            if(result == null) scanMode = true;
        }

        if (scanMode) {
            for (int y = 1; y <= boardSize; y++) {
                for (char x = 'A'; x < 'A' + boardSize; x++) {
                    targetX = x;
                    targetY = y;
                    result = attemptFire(opponent, targetX, targetY);
                    if (result != null) {
                        direction = 0;
                        return result;
                    }
                }
            }
        }

        return result;
    }

    private FireResult continueDirectionFire(Player opponent) {
        FireResult result = null;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        char targetX;
        int targetY;

        if (direction == 0) {
            for (int i = 0; i < 4; i++) {

                if (attemptedDirections.contains(i)) {
                    continue;
                }

                targetX = (char) (lastHitX + dx[i]);
                targetY = lastHitY + dy[i];
                result = attemptFire(opponent, targetX, targetY);

                if (!player.getBoard().isCoordinateValid(targetX, targetY)) {
                    attemptedDirections.add(i);
                    continue;
                }

                if (result != null) {
                    if (result == FireResult.HIT) {
                        direction = i + 1;
                        attemptedDirections.clear();
                    } else if (result == FireResult.MISS) {
                        attemptedDirections.add(i);
                    }
                    return result;
                } else {
                    attemptedDirections.add(i);
                }
            }

            resetLastHit();
            attemptedDirections.clear();
        } else {
            targetX = (char) (lastHitX + dx[direction - 1]);
            targetY = lastHitY + dy[direction - 1];
            result = attemptFire(opponent, targetX, targetY);
        }

        return result;
    }

    private FireResult attemptFire(Player opponent, char targetX, int targetY) {
        if (player.getBoard().isCoordinateValid(targetX, targetY)) {
            Cell target = opponent.getBoard().getCell(targetX, targetY);
            if (target.getStatus() != CellStatus.HIT && target.getStatus() != CellStatus.MISS) {
                FireResult result = boardController.fireAt(target);
                if (result == FireResult.HIT) {
                    lastHitX = targetX;
                    lastHitY = targetY;

                    if (target.getShip() != null && target.getShip().isSunk()) {
                        resetLastHit();
                        attemptedDirections.clear();
                        direction = 0;
                    }
                }
                System.out.println("Bot đã bắn vào ô " + targetX + targetY);
                return result;
            }
        }
        return null;
    }

    private void resetLastHit() {
        lastHitX = '@';
        lastHitY = 99;
        direction = 0;
    }
}