package gamemanager;

import game.*;
import enums.FireResult;
import enums.GameStatus;
import items.*;

import java.io.*;
import java.util.Random;


public class GameFunction {
    private int turn;
    public Game game;
    private Player currentTurn;
    private Player opponent;
    public PlayerFunction playerFunction1;
    public PlayerFunction playerFunction2;
    public BotFunction botFunction;
    public Leaderboard leaderboard;
    public boolean isPvE;
    public boolean itemEnabled;
    public static Random random = new Random();
    public boolean isPaused;

    public GameFunction(Game game, Leaderboard leaderboard, PlayerFunction playerFunction1, PlayerFunction playerFunction2, BotFunction botFunction, boolean isPvE, int turn, boolean itemEnabled) {
        this.game = game;
        this.leaderboard = leaderboard;
        this.playerFunction1 = playerFunction1;
        this.playerFunction2 = playerFunction2;
        this.botFunction = botFunction;
        this.isPvE = isPvE;
        this.turn = turn;
        this.itemEnabled = itemEnabled;
        this.isPaused = false;
        game.setStatus(GameStatus.IN_PROGRESS);
        clearTempGame();
    }

    private void runGameLoop() {
        if(turn==1) readyState();
        while (game.getStatus() == GameStatus.IN_PROGRESS) {
            ++turn;
            currentTurn = game.getCurrentTurn();
            opponent = (currentTurn == game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1();
            System.out.println("Lượt bắn của " + Menu.red + currentTurn.getName() + Menu.reset + " sẽ bắt đầu sau 3 giây, hãy sẵn sàng!");
            if (isPvE && currentTurn == game.getPlayer2()) {
                botTurn();
            } else {
                playerTurn();
            }
            if (opponent.getRemainingShips() == 0) {
                end();
                break;
            }
            if(isPaused){
                clearScreen(1);
                break;
            }
            game.setCurrentTurn(opponent);
        }
    }

    private void readyState() {
        clearScreen(1);
        Menu.start();
        playerFunction1.placeShips();
        if (isPvE)
            botFunction.placeShips();
        else {
            playerFunction2.placeShips();
        }
        System.out.println("===== Khai màn trận chiến =====");
    }

    private void status() {
        System.out.println("\n===== Lượt của " + Menu.blue + currentTurn.getName() + Menu.reset + " =====");
        System.out.println("Số tàu còn lại của địch: " + opponent.getRemainingShips());
        System.out.println("Số lần bắn trúng tàu địch: " + opponent.getHits());
        System.out.println("Số tàu của bạn đã bị phá hủy: " + currentTurn.getSunkShips());
        System.out.println("===== Bảng của " + Menu.blue + currentTurn.getName() + Menu.reset + " và Bảng sương mù =====");
        currentTurn.getBoard().displayBoardsSideBySide(opponent.getBoard());
    }

    private void playerTurn() {
        PlayerFunction currentPlayerFunction = (currentTurn == game.getPlayer1()) ? playerFunction1 : playerFunction2;
        int consecutiveHits = 0;
        final int REWARD_THRESHOLD = 3;
        while (true) {
            clearScreen(2);
            if(itemEnabled && consecutiveHits % REWARD_THRESHOLD == 0 && consecutiveHits >= REWARD_THRESHOLD) {
                System.out.println(Menu.yellow + "Bạn đã bắn trúng liên tiếp " + REWARD_THRESHOLD + " lần! Nhận được một vật phẩm." + Menu.reset);
                handleItemReward();
                consecutiveHits=0;
            }
            status();
            int choice=1;
            try {
                if(itemEnabled) {
                    System.out.println("0. Tạm dừng");
                    System.out.println("1. Tấn công");
                    System.out.println("2. Sử dụng vật phẩm");
                    System.out.print("Lựa chọn của bạn: ");
                    choice = Integer.parseInt(Main.scanner.nextLine());
                }
                if(choice == 0){
                    saveAndPauseGame();
                    break;
                }
                else if (choice == 1) {
                    FireResult result = currentPlayerFunction.fireAt(opponent.getBoard());
                    if (result == null) {
                        saveAndPauseGame();
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
                        alert("Bạn không có vật phẩm nào.");
                        sleep(2000);
                        continue;
                    }
                    System.out.println("Danh sách vật phẩm:");
                    for (int i = 0; i < currentTurn.getItems().size(); i++) {
                        System.out.println((i + 1) + ". " + currentTurn.getItems().get(i).getName());
                    }
                    System.out.println(Menu.yellow + "Nếu bạn không muốn chọn các vật phẩm sau đây, bạn có thể nhập \"0\" để thoát" + Menu.reset);
                    alert("Lưu ý: Nếu bạn chọn ô ngoài bản đồ, hệ thống sẽ coi như bạn từ chối sử dụng vật phẩm");
                    System.out.print("Chọn vật phẩm để sử dụng: ");
                    int itemIndex = Integer.parseInt(Main.scanner.nextLine()) - 1;
                    BoardController opponentBoardController;
                    if(playerFunction2 != null)
                        opponentBoardController = (currentTurn == game.getPlayer1()) ? playerFunction2.boardController : playerFunction1.boardController;
                    else opponentBoardController = botFunction.getBoardController();
                    currentPlayerFunction.useItem(itemIndex, opponentBoardController);
                }
                else
                    alert("Lựa chọn không hợp lệ. Vui lòng nhập lại");
            }catch (Exception e) {
                alert("Lựa chọn không hợp lệ. Vui lòng nhập lại");
                System.out.println(e.getMessage());
            }
        }
    }

    private void botTurn() {
        while (true) {
            //status(currentTurn, opponent);
            clearScreen(2);
            System.out.println("\n===== Lượt của " + currentTurn.getName() + " =====");
            FireResult result = botFunction.fireAt(opponent);
            handleFireResult(result);
            if (result == FireResult.MISS || opponent.getRemainingShips() == 0) {
                break;
            }
            System.out.println(currentTurn.getName() + " đã trúng tàu của bạn, nó sẽ bắn thêm lần nữa!");
        }
    }

    private void handleItemReward() {
        int randomItem = random.nextInt(3);
        System.out.println("Bạn có thể mở túi đồ để sử dụng vật phẩm.");
        sleep(2000);

        switch (randomItem) {
            case 1:
                currentTurn.addItem(new Bomb());
                break;
            case 2:
                currentTurn.addItem(new Light());
                break;
            default:
                currentTurn.addItem(new Shield());
                break;
        }
    }

    private void handleFireResult(FireResult result) {
        switch (result) {
            case HIT:
                Menu.hit();
                break;
            case MISS:
                Menu.miss();
                break;
            case SUNK:
                Menu.sunk();
                break;
        }
    }

    public void end(){
        clearScreen(1);
        game.setStatus(GameStatus.FINISHED);
        Menu.end();
        System.out.println(currentTurn.getName() + " chiến thắng!");
        sleep(1000);
        System.out.println("===== Bảng của " + currentTurn.getName() + " và Bảng sương mù =====");

        currentTurn.getBoard().displayBoardsSideBySide(opponent.getBoard());
        System.out.println("Màn hình chính sẽ được mở sau 3s.");
        clearScreen(3);
        leaderboard.addRecord(new PlayerRecord(currentTurn.getName(), turn/2, currentTurn.getRemainingShips()));
    }

    public void saveGame() {
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

    public void startGameLoop(){
        runGameLoop();
    }

    public static void alert(String notification){
        System.out.println(Menu.red + notification + Menu.reset);
    }

    private void saveAndPauseGame() {
        saveGame();
        System.out.println("Game đã được lưu. Kết thúc lượt.");
        isPaused = true;
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void clearScreen(int seconds) {
        sleep(seconds*1000);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}