package gamemanager;

import game.*;
import enums.FireResult;
import enums.GameStatus;
import items.*;

import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class GameFunction {
    public int turn;
    public Game game;
    public PlayerFunction playerFunction1;
    public PlayerFunction playerFunction2;
    public BotFunction botFunction;
    public Menu menu;
    public Leaderboard leaderboard;
    public boolean isPvE;
    public boolean itemEnabled;
    public Random random = new Random();
    public static boolean isPaused = false;
    Scanner scanner = new Scanner(System.in);

    public GameFunction(Game game, Leaderboard leaderboard, PlayerFunction playerFunction1, PlayerFunction playerFunction2, BotFunction botFunction, boolean isPvE, int turn, boolean itemEnabled) {
        this.game = game;
        this.leaderboard = leaderboard;
        this.playerFunction1 = playerFunction1;
        this.playerFunction2 = playerFunction2;
        this.botFunction = botFunction;
        this.menu = new Menu();
        this.isPvE = isPvE;
        this.turn = turn;
        this.itemEnabled = itemEnabled;
        game.setStatus(GameStatus.IN_PROGRESS);
        clearTempGame();
    }

    public void startGame() {
        if(turn==1) ready();
        while (game.getStatus() == GameStatus.IN_PROGRESS) {
            ++turn;
            Player currentTurn = game.getCurrentTurn();
            Player opponent = (currentTurn == game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1();
            System.out.println("Lượt bắn của " + currentTurn.getName() + " sẽ bắt đầu sau 3 giây, hãy sẵn sàng!");
            if (isPvE && currentTurn == game.getPlayer2()) {
                botTurn(currentTurn, opponent);
            } else {
                playerTurn(currentTurn, opponent, turn);
            }
            if (opponent.getRemainingShips() == 0) {
                end(currentTurn, opponent, turn);
                break;
            }
            if(isPaused) break;
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
        System.out.print("\033[H\033[2J");
        System.out.flush();
        sleep(seconds*1000);
    }

    public void ready() {
        menu.start();
        clearScreen(1);
        playerFunction1.placeShips(game.getPlayer1());
        if (isPvE)
            botFunction.placeShips(game.getPlayer2());
        else
            playerFunction2.placeShips(game.getPlayer2());
        System.out.println("===== Khai màn trận chiến =====");
    }

    public void status(Player currentTurn, Player opponent) {
        System.out.println("\n===== Lượt của " + currentTurn.getName() + " =====");
        System.out.println("Số tàu còn lại của địch: " + opponent.getRemainingShips());
        System.out.println("Số lần bắn trúng tàu địch: " + opponent.getHits());
        System.out.println("Số tàu của bạn đã bị phá hủy: " + currentTurn.getSunkShips());
        System.out.println("===== Bảng của " + currentTurn.getName() + " và Bảng sương mù =====");
        currentTurn.getBoard().displayBoardsSideBySide(opponent.getBoard());
    }

    private void playerTurn(Player currentTurn, Player opponent, int turn) {
        PlayerFunction currentPlayerFunction = (currentTurn == game.getPlayer1()) ? playerFunction1 : playerFunction2;
        int consecutiveHits = 0;
        int rewardThreshold = 3;
        while (true) {
            clearScreen(2);
            if(itemEnabled && consecutiveHits % rewardThreshold == 0 && consecutiveHits >= rewardThreshold) {
                System.out.println("Bạn đã bắn trúng liên tiếp " + rewardThreshold + " lần! Nhận được một vật phẩm.");
                handleItemReward(currentTurn);
                consecutiveHits=0;
            }
            status(currentTurn, opponent);
            int choice=1;
            try {
                if(itemEnabled) {
                    System.out.println("0. Tạm dừng");
                    System.out.println("1. Tấn công");
                    System.out.println("2. Sử dụng vật phẩm");
                    System.out.print("Lựa chọn của bạn: ");
                    choice = Integer.parseInt(scanner.nextLine());
                }
                if(choice == 0){
                    saveGame(currentTurn, opponent, turn);
                    System.out.println("Game đã được lưu. Kết thúc lượt.");
                    isPaused = true;
                    break;
                }
                else if (choice == 1) {
                    FireResult result = currentPlayerFunction.fireAt(currentTurn, opponent);
                    if (result == null) {
                        saveGame(currentTurn, opponent, turn);
                        isPaused = true;
                        System.out.println("Game đã được lưu. Kết thúc lượt.");
                        break;
                    }
                    handleFireResult(result);
                    if (result == FireResult.MISS || opponent.getRemainingShips() == 0) {
                        break;
                    }
                    System.out.println("Bạn đã trúng tàu địch, bạn được bắn thêm lần nữa!");
                    ++consecutiveHits;
                } else if (choice == 2) {
                    if (currentTurn.getItems().isEmpty()) {
                        System.out.println("Bạn không có vật phẩm nào.");
                        sleep(2000);
                        continue;
                    }
                    System.out.println("Danh sách vật phẩm:");
                    for (int i = 0; i < currentTurn.getItems().size(); i++) {
                        System.out.println((i + 1) + ". " + currentTurn.getItems().get(i).getName());
                    }
                    System.out.println(Menu.yellow + "Nếu bạn không muốn chọn các vật phẩm sau đây, bạn có thể nhập \"0\" để thoát" + Menu.reset);
                    System.out.println(Menu.red+"Lưu ý: Nếu bạn bắn ra ngoài bản đồ, hệ thống sẽ coi như bạn từ chối sử dụng vật phẩm" + Menu.reset);
                    System.out.print("Chọn vật phẩm để sử dụng: ");
                    int itemIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    BoardController opponentBoardController;
                    if(playerFunction2 != null)
                        opponentBoardController = (currentTurn == game.getPlayer1()) ? playerFunction2.boardController : playerFunction1.boardController;
                    else opponentBoardController = botFunction.getBoardController();
                    currentPlayerFunction.useItem(currentTurn, itemIndex, opponentBoardController);
                }
                else
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại");
            }catch (Exception e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại");
                System.out.println(e.getMessage());
            }
        }
    }

    private void botTurn(Player currentTurn, Player opponent) {
        while (true) {
            //status(currentTurn, opponent);
            clearScreen(2);
            System.out.println("\n===== Lượt của " + currentTurn.getName() + " =====");
            FireResult result = botFunction.fireAt(currentTurn, opponent);
            handleFireResult(result);
            if (result == FireResult.MISS || opponent.getRemainingShips() == 0) {
                break;
            }
            System.out.println(currentTurn.getName() + " đã trúng tàu của bạn, nó sẽ bắn thêm lần nữa!");
        }
    }

    private void handleItemReward(Player currentPlayer) {
        int randomItem = random.nextInt(3);
        System.out.println("Bạn có thể mở túi đồ để sử dụng vật phẩm.");
        sleep(2000);

        switch (randomItem) {
            case 1:
                currentPlayer.addItem(new Bomb());
                break;
            case 2:
                currentPlayer.addItem(new Light());
                break;
            default:
                currentPlayer.addItem(new Shield());
                break;
        }
    }

    private void handleFireResult(FireResult result) {
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
    }

    public void end(Player currentTurn, Player opponent, int turn){
        clearScreen(1);
        game.setStatus(GameStatus.FINISHED);
        menu.end();
        System.out.println(currentTurn.getName() + " chiến thắng!");
        sleep(1000);
        System.out.println("===== Bảng của " + currentTurn.getName() + " và Bảng sương mù =====");

        currentTurn.getBoard().displayBoardsSideBySide(opponent.getBoard());
        System.out.println("Màn hình chính sẽ được mở sau 3s.");
        clearScreen(3);
        leaderboard.addRecord(new PlayerRecord(currentTurn.getName(), turn/2, currentTurn.getRemainingShips()));
    }

    public void saveGame(Player currentTurn, Player opponent, int turn) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempGame.txt"))) {
            GameState gameState = new GameState(currentTurn, opponent, turn, itemEnabled);
            oos.writeObject(gameState);
        } catch (Exception e) {
            System.out.println("Có lỗi trong quá trình lưu file: " +e.getMessage());
        }
    }

    public static GameState loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempGame.txt"))) {
            return (GameState) ois.readObject();
        } catch (Exception e) {
            System.out.println("Có lỗi trong quá trình đọc file: " + e.getMessage());
        }
        return null;
    }

    private void clearTempGame() {
        try (FileWriter fw = new FileWriter("tempGame.txt", false)) {
            fw.write("");
        } catch (IOException e) {
            System.out.println("Lỗi khi xóa tempGame.txt: " + e.getMessage());
        }
    }
}