package game;

import java.io.File;
import java.util.Scanner;
import gamemanager.*;

public class Main {
    private static final String SAVE_FILE = "tempGame.txt";
    public static final Scanner scanner = new Scanner(System.in);
    private final Leaderboard leaderboard = new Leaderboard();

    public static void main(String[] args) {
        new Main().run();
        scanner.close();
    }

    public void run() {
        
        while (true) {
            try {
                Sound.stopAllClips();
                Sound.playSoundWithDurationAsync("/Sound/ready.wav", true, 900000, 0.3f);
                GameFunction.clearScreen(1);
                Menu.seaBattle();
                System.out.print("Chọn một tùy chọn: ");
                int option = Integer.parseInt(scanner.nextLine());

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
                        displayRule();
                        break;
                    case 6:
                        continueGame();
                        break;
                    case 0:
                        System.out.println("Cảm ơn bạn đã chơi trò chơi!");
                        return;
                    default:
                        System.out.println("Lựa chọn không tồn tại, vui lòng nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Lựa chọn không tồn tại, vui lòng nhập lại");
                System.out.println("Lỗi: " + e.getMessage());
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

        Game game = new Game(player1Name, player2Name, size);
        BoardController boardController1 = new BoardController(game.getPlayer1().getBoard());
        BoardController boardController2 = new BoardController(game.getPlayer2().getBoard());
        PlayerFunction playerFunction1 = new PlayerFunction(game.getPlayer1(), boardController1);
        PlayerFunction playerFunction2 = (option == 1) ? new PlayerFunction(game.getPlayer2(), boardController2) : null;
        BotFunction botFunction = (option == 2) ? new BotFunction(game.getPlayer2(), boardController2) : null;

        GameFunction gameFunction = new GameFunction(game, leaderboard, playerFunction1,
                playerFunction2, botFunction,
                botFunction != null, 1, isItemsEnabled());
        gameFunction.startGameLoop();
    }

    public static int getValidOptionWithPrompt(String prompt) {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print(prompt);
            }
        }
    }

    private int getBoardSize() {
        System.out.print("Hãy chọn kích thước bảng: ");
        return getValidOptionWithPrompt("Kích thước bảng vừa nhập không phải số nguyên, vui lòng nhập lại: ");
    }

    private boolean isItemsEnabled() {
        System.out.print("Bạn có muốn bật chế độ vật phẩm không? Nếu có, hãy nhập \"Y\": ");
        String input = scanner.nextLine().trim();
        return input.equalsIgnoreCase("y");
    }

    private void displayLeaderboard() {
        leaderboard.display();
        System.out.println("Vui lòng nhập bất kì kí tự nào để thoát bảng xếp hạng.");
        scanner.nextLine();
    }

    private void displayRule() {
        Menu.rule();
        System.out.println("Vui lòng nhập bất kì kí tự nào để thoát trang luật chơi.");
        scanner.nextLine();
    }

    private void continueGame() {
        File file = new File(SAVE_FILE);
        if (file.length() > 0) {
            GameState gameState = GameFunction.loadGame();
            if(gameState != null){
                PlayerFunction playerFunction1 = new PlayerFunction(gameState.player1(), new BoardController(gameState.player1().getBoard()));

                PlayerFunction playerFunction2 = (gameState.player2().getName().equals("BotMadeByHaiLong")) ?
                        null : new PlayerFunction(gameState.player2(), new BoardController(gameState.player2().getBoard()));

                BotFunction botFunction = (playerFunction2 == null) ?
                        new BotFunction(gameState.player2(), new BoardController(gameState.player2().getBoard())) : null;
                GameFunction gameFunction = new GameFunction(
                            new Game(gameState),
                            leaderboard,
                            playerFunction1,
                            playerFunction2,
                            botFunction,
                        botFunction != null,
                            gameState.currentTurn(),
                            gameState.itemEnabled()
                    );
                gameFunction.startGameLoop();
            } else {
                System.out.println("Không thể tải game. File lưu có thể bị hỏng.");
            }
        } else {
            System.out.println("Không có game nào để tiếp tục.");
        }
    }

}