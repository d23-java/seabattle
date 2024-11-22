package gamemanager;

import game.*;
import enums.FireResult;
import enums.GameStatus;


public class GameFunction {
    public Game game;
    public PlayerFunction playerFunction1;
    public PlayerFunction playerFunction2;
    public Menu menu;
    public Leaderboard leaderboard;

    public GameFunction(Game game, Leaderboard leaderboard, PlayerFunction playerFunction1, PlayerFunction playerFunction2) {
        this.game = game;
        this.leaderboard = leaderboard;
        this.playerFunction1 = playerFunction1;
        this.playerFunction2 = playerFunction2;
        this.menu = new Menu();
    }

    public void startGame() {
        ready();
        int turn=0;
        while (game.getStatus() == GameStatus.IN_PROGRESS) {
            ++turn;
            Player currentTurn = game.getCurrentTurn();
            Player opponent = (currentTurn == game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1();
            System.out.println("Lượt bắn của " + currentTurn.getName() + " sẽ bắt đầu sau 3 giây, hãy sẵn sàng!");
            clearScreen(3);
            PlayerFunction currentPlayerFunction = (currentTurn == game.getPlayer1()) ? playerFunction1 : playerFunction2;
            while (true){
                status(currentTurn, opponent);
                FireResult result = currentPlayerFunction.fireAt(currentTurn, opponent);
                switch (result) {
                    case HIT:
                        menu.hit();
                        break;
                    case MISS:
                        menu.miss();
                        break;
                    case SUNK:
                        menu.sunk();
                        break;
                }
                if (result == FireResult.MISS) {
                    break;
                }

                if (opponent.getRemainingShips() == 0) {
                    end(currentTurn, turn);
                    break;
                }
                System.out.println("Bạn đã trúng tàu địch, bạn được bắn thêm lần nữa!");
                sleep(1000);
            }
            game.setCurrentTurn(opponent);
        }
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void clearScreen(int seconds) {
        sleep(seconds*1000);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void ready() {
        menu.start();
        clearScreen(1);
        playerFunction1.placeShips(game.getPlayer1());
        playerFunction2.placeShips(game.getPlayer2());
        game.setStatus(GameStatus.IN_PROGRESS);
        System.out.println("===== Khai màn trận chiến =====");
    }

    public void status(Player currentTurn, Player opponent) {
        System.out.println("\n===== Lượt của " + currentTurn.getName() + " =====");
        System.out.println("Số tàu còn lại của địch: " + opponent.getRemainingShips());
        System.out.println("Số lần bắn trúng tàu địch: " + opponent.getHits());
        System.out.println("Số tàu của bạn đã bị phá hủy: " + currentTurn.getSunkShips());
        System.out.println("===== Bảng của " + currentTurn.getName() + " =====");
        currentTurn.getBoard().displayFullBoard();
        System.out.println("===== Bảng sương mù của " + opponent.getName() + " =====");
        opponent.getBoard().displayFogOfWar();
    }

    public void end(Player currentTurn, int turn){
        clearScreen(1);
        game.setStatus(GameStatus.FINISHED);
        menu.end();
        System.out.println(currentTurn.getName() + " chiến thắng!");
        System.out.println("Màn hình chính sẽ được mở sau 3s.");
        clearScreen(3);
        leaderboard.addRecord(new PlayerRecord(currentTurn.getName(), turn, currentTurn.getRemainingShips()));
    }
}