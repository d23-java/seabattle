package game;

import java.io.File;
import java.util.Scanner;
import gamemanager.*;

public class Main {
    private static final String SAVE_FILE = "tempGame.txt";
    Scanner scanner = new Scanner(System.in);
    private final Menu menu = new Menu();
    private final Leaderboard leaderboard = new Leaderboard();

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    public void run() {
        while (true) {
            try {
                menu.seaBattle();
                int option = getUserOption();

                switch (option) {
                    case 1:
                    case 2:
                        handleGameOption(option);
                        break;
                    case 3:
                        displayLeaderboard();
                        break;
                    case 4:
                        Sound.toggleSound();
                        break;
                    case 5:
                        continueGame();
                        break;
                    case 0:
                        exitGame();
                        return;
                    default:
                        showInvalidOptionMessage();
                }
            } catch (Exception e) {
                System.out.println("Lựa chọn không tồn tại, vui lòng nhập lại");
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    private int getUserOption() {
        System.out.print("Chọn một tùy chọn: ");
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập một số nguyên hợp lệ: ");
            }
        }
    }

    private void handleGameOption(int option) {
        int size = getBoardSize();
        String player1Name;
        String player2Name = "BotMadeByHaiLong";

        if (option == 1) {
            System.out.print("Nhập tên người chơi 1: ");
            player1Name = scanner.nextLine();
            System.out.print("Nhập tên người chơi 2: ");
            player2Name = scanner.nextLine();
        }
        else {
            System.out.print("Nhập tên của bạn: ");
            player1Name = scanner.nextLine();
        }

        boolean itemsEnabled = isItemsEnabled();

        Game game = new Game(player1Name, player2Name, size);
        BoardController boardController1 = new BoardController(game.getPlayer1().getBoard());
        BoardController boardController2 = new BoardController(game.getPlayer2().getBoard());
        PlayerFunction playerFunction1 = new PlayerFunction(boardController1);
        PlayerFunction playerFunction2 = (option == 1) ? new PlayerFunction(boardController2) : null;
        BotFunction botFunction = (option == 2) ? new BotFunction(boardController2) : null;

        GameFunction gameFunction = new GameFunction(game, leaderboard, playerFunction1,
                playerFunction2, botFunction,
                botFunction != null, 1, itemsEnabled);
        gameFunction.startGame();
    }

    private int getBoardSize() {
        System.out.print("Hãy chọn kích thước bảng: ");
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập một số nguyên hợp lệ: ");
            }
        }
    }

    private boolean isItemsEnabled() {
        System.out.print("Bạn có muốn bật chế độ vật phẩm không? Nếu có, hãy nhập \"Y\": ");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }

    private void displayLeaderboard() {
        leaderboard.display();
        System.out.println("Vui lòng nhập bất kì kí tự nào để thoát bảng xếp hạng.");
        scanner.nextLine();
    }

    private void continueGame() {
        File file = new File(SAVE_FILE);
        if (file.length() > 0) {
            GameState gameState = GameFunction.loadGame();
            if(gameState != null){
                PlayerFunction playerFunction1 = new PlayerFunction(new BoardController(gameState.getPlayer1().getBoard()));

                PlayerFunction playerFunction2 = (gameState.getPlayer2().getName().equals("BotMadeByHaiLong")) ?
                        null : new PlayerFunction(new BoardController(gameState.getPlayer2().getBoard()));

                BotFunction botFunction = (gameState.getPlayer2().getName().equals("BotMadeByHaiLong")) ?
                        new BotFunction(new BoardController(gameState.getPlayer2().getBoard())) : null;
                GameFunction gameFunction = new GameFunction(
                            new Game(gameState),
                            leaderboard,
                            playerFunction1,
                            playerFunction2,
                            botFunction,
                        botFunction != null,
                            gameState.getCurrentTurn(),
                            gameState.isItemEnabled()
                    );
                gameFunction.startGame();
            } else {
                System.out.println("Không thể tải game. File lưu có thể bị hỏng.");
            }
        } else {
            System.out.println("Không có game nào để tiếp tục.");
        }
    }

    private void exitGame() {
        System.out.println("Cảm ơn bạn đã chơi trò chơi!");
    }

    private void showInvalidOptionMessage() {
        System.out.println("Lựa chọn không tồn tại, vui lòng nhập lại");
    }
}