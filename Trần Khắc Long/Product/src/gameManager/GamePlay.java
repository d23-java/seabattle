package gameManager;

import Scanner.*;
import gameObjects.*;
import enums.*;

import java.util.Random;

public class GamePlay {
    private static Players firstPlayer;
    private static Players secondPlayer;
    //Player 1 đi trước
    private static Players currentPlayer;
    private static Players opponentPlayer;
    private static boolean gameOver = false;

    private static boolean successive = false;
    private static Random random = new Random();

    public static void playGame() {
        infoSetUp();
        MenuManger.showMenuSetUp( "~~~~ " + currentPlayer.getPlayerName() + " sets up the ship " + "~~~~");
        takeTurn();
        MenuManger.showMenuSetUp( "~~~~ " + currentPlayer.getPlayerName() + " sets up the ship " + "~~~~");
        takeTurn();
        inGame();
    }

    public static void inGame() {
        while (!gameOver) {
            MenuManger.showMenuIngame(currentPlayer);
            checkGameover();
            takeTurn();
        }
        gameOver = false;
        ScoreBoard.addInfo(opponentPlayer);
        ScreenManager.drawBothBoard(opponentPlayer, currentPlayer);
        System.out.println("Game Over!!");
        System.out.println(Color.ANSI_GREEN + opponentPlayer.getPlayerName() + " is the winner :>");
    }

    public static void takeTurn() {
        //Đổi lượt luân phiên
        currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
        opponentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
    }

    public static void setUp(int choice) {
        if (choice == 1) {
            shipSetUp();
        } else if (choice == 2)
            randomShipSetUp();

    }

    private static void infoSetUp() {
        int size;
        while (true) {
            //Nhập size
            System.out.println("Enter the board's size (10 -> 20): ");
            size = Integer.parseInt(Input.scanner.nextLine());
            if (size > 20 || size < 10) {
                System.out.println(Color.ANSI_RED + "Invalid size!" + Color.ANSI_RESET);
                ScreenManager.waitForEnter();
                ScreenManager.clearScreen();
            } else break;
        }
        //Nhập tên firstPlayer
        System.out.println("Enter Player 1's name: ");
        String firstPlayerName = Input.scanner.nextLine();
        //Nhập tên secondPlayer
        System.out.println("Enter Player 2's name: ");
        String secondPlayerName = Input.scanner.nextLine();
        //Khởi tạo
        firstPlayer = new Players(firstPlayerName, size);
        secondPlayer = new Players(secondPlayerName, size);
        currentPlayer = firstPlayer;
        opponentPlayer = secondPlayer;
        ScreenManager.clearScreen();
    }

    public static void randomShipSetUp() {
        Ships[] ships = {defaultShip.PATROLBOAT, defaultShip.SUBMARINE, defaultShip.DESTROYERBOAT, defaultShip.BATTLESHIP};
        int[] quantity = {2, 1, 1, 1};
        for (int currendtIndex = 0; currendtIndex < 4; currendtIndex++) {
            while (quantity[currendtIndex] != 0) {
                int row = random.nextInt(currentPlayer.getBoard().getSize());
                int colum = random.nextInt(currentPlayer.getBoard().getSize());
                String position = String.format("%c%d", 'a' + row, colum);
                int shipDirection = random.nextInt(2) + 1;
                if (isValidPositionPlaceShip(position, shipDirection, ships[currendtIndex].getShipSize())) {
                    Ships ship = new Ships(ships[currendtIndex]);
                    ship.setShipDirection(shipDirection);
                    ship.setShipStart(position);
                    currentPlayer.getShips().add(ship);
                    placeShip(ship);
                    quantity[currendtIndex]--;
                }
            }
        }
        String titleSetup = "~~~~ " + currentPlayer.getPlayerName() + " sets up the ship " + "~~~~";
        ScreenManager.drawMyBoard(currentPlayer, titleSetup);
        System.out.println(Color.ANSI_GREEN + "+--------------------------+" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_GREEN + "|All ship have been set up!|" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_GREEN + "+--------------------------+" + Color.ANSI_RESET);
        ScreenManager.waitForEnter();
        ScreenManager.clearScreen();
    }

    public static void shipSetUp() {
        String titleSetup = "~~~~ " + currentPlayer.getPlayerName() + " sets up the ship " + "~~~~";
        ScreenManager.drawMyBoard(currentPlayer, titleSetup);
        patrolboatSetUp();
        ScreenManager.drawMyBoard(currentPlayer, titleSetup);
        patrolboatSetUp();
        ScreenManager.drawMyBoard(currentPlayer, titleSetup);
        submarineSetUp();
        ScreenManager.drawMyBoard(currentPlayer, titleSetup);
        destroyerboatSetUp();
        ScreenManager.drawMyBoard(currentPlayer, titleSetup);
        battleshipSetUp();
        ScreenManager.drawMyBoard(currentPlayer, titleSetup);
        System.out.println(Color.ANSI_GREEN + "+--------------------------+" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_GREEN + "|All ship have been set up!|" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_GREEN + "+--------------------------+" + Color.ANSI_RESET);
        ScreenManager.waitForEnter();
        ScreenManager.clearScreen();
    }

    private static void patrolboatSetUp() {
        Ships patrolBoat = new Ships(defaultShip.PATROLBOAT);
        shipInput(patrolBoat);
        ScreenManager.waitForEnter();
        ScreenManager.clearScreen();
    }

    private static void destroyerboatSetUp() {
        Ships DESTROYERBOAT = new Ships(defaultShip.DESTROYERBOAT);
        shipInput(DESTROYERBOAT);
        ScreenManager.waitForEnter();
        ScreenManager.clearScreen();
    }

    private static void submarineSetUp() {
        Ships SUBMARINE = new Ships(defaultShip.SUBMARINE);
        shipInput(SUBMARINE);
        ScreenManager.waitForEnter();
        ScreenManager.clearScreen();
    }

    private static void battleshipSetUp() {
        Ships BATTLESHIP = new Ships(defaultShip.BATTLESHIP);
        shipInput(BATTLESHIP);
        ScreenManager.waitForEnter();
        ScreenManager.clearScreen();
    }

    private static void shipInput(Ships ship) {
        do {
            System.out.println("Choose the initial position's " + ship.getShipType() + "(size: " + ship.getShipSize() + ")" + ": ");
            String position = Input.scanner.nextLine();
            System.out.println("Choose the ship's direction: ");
            System.out.println("1. Horizontal");
            System.out.println("2. Vertical");
            System.out.println("Enter your choice: ");
            int shipDirection = Integer.parseInt(Input.scanner.nextLine());
            if (isValidPositionPlaceShip(position, shipDirection, ship.getShipSize())) {
                ship.setShipDirection(shipDirection);
                ship.setShipStart(position);
                currentPlayer.getShips().add(ship);
                placeShip(ship);
                System.out.println(Color.ANSI_GREEN + "Ship set successfully." + Color.ANSI_RESET);
                break;
            }
            System.out.println(Color.ANSI_RED + "Invalid position. Try again." + Color.ANSI_RESET);
        } while (true);
    }

    private static void placeShip(Ships ship) {
        Cell[][] board = currentPlayer.getBoard().getCells();
        int shipSize = ship.getShipSize();
        String position = ship.getShipStart();
        int shipDirection = ship.getShipDirection();
        //Lấy ra hàng và cột
        int row = position.charAt(0) - 'a';
        int colum = position.charAt(1) - '0';
        if (position.length() == 3) colum = colum * 10 + position.charAt(2) - '1' - 1;
        else colum--;
        if (shipDirection == 1)
            for (int index = colum; index < colum + shipSize; index++)
                board[row][index] = new Cell(ship.getShipType().charAt(0) + "", ship.getShipColor());
        else for (int index = row; index < row + shipSize; index++)
            board[index][colum] = new Cell(ship.getShipType().charAt(0) + "", ship.getShipColor());

    }

    public static void fire() {
        do {
            ScreenManager.drawOpponentBoard(opponentPlayer, "~~~~ " + "Enemy board" + " ~~~~");
            String position;
            while (true) {
                System.out.println("Choose the position you want to fire: ");
                position = Input.scanner.nextLine();
                if (!isValidPosition(position)) {
                    System.out.println(Color.ANSI_RED + "Invalid position! Try again." + Color.ANSI_RESET);
                } else if (isShot(position))
                    System.out.println(Color.ANSI_RED + "This position has been shot! Try again." + Color.ANSI_RESET);
                else break;
            }
            currentPlayer.increaseShotCell();
            if (isHit(position)) {
                successive = true;
                if (checkSunk()) {
                    currentPlayer.increaseShotShip();
                    successive = false;
                    ScreenManager.clearScreen();
                    System.out.println(Color.ANSI_GREEN + "-----> A ship has been sunk!! <-----" + Color.ANSI_RESET);
                    ScreenManager.waitForEnter();
                    ScreenManager.clearScreen();
                    break;
                }
                System.out.println("You have " + Color.ANSI_GREEN_BACKGROUND + "hit" + Color.ANSI_RESET + " a part of ship!!");
                ScreenManager.waitForEnter();
                ScreenManager.clearScreen();
            } else {
                successive = false;
                System.out.println("You have " + Color.ANSI_RED_BACKGROUND + "miss" + Color.ANSI_RESET + " the shot ;-;");
                ScreenManager.waitForEnter();
                ScreenManager.clearScreen();
            }
        } while (successive);
    }

    private static void checkGameover() {
        if (opponentPlayer.getShips().isEmpty()) {
            gameOver = true;
        }
    }

    private static boolean checkSunk() {
        for (Ships ship : opponentPlayer.getShips()) {
            if (ship.isSunk(opponentPlayer)) {
                opponentPlayer.getShips().remove(ship);
                return true;
            }
        }
        return false;
    }

    private static boolean isShot(String position) {
        int row = Cell.getRow(position);
        int colum = Cell.getColum(position);
        Cell[][] board = opponentPlayer.getBoard().getCells();
        if (board[row][colum].getHit() || board[row][colum].getMiss()) {
            return true;
        }
        return false;
    }

    private static boolean isHit(String position) {
        int row = Cell.getRow(position);
        int colum = Cell.getColum(position);
        Cell[][] board = opponentPlayer.getBoard().getCells();
        if (board[row][colum].isEmpty()) {
            board[row][colum].setMiss();
            return false;
        }
        board[row][colum].setHit();
        return true;
    }

    private static boolean isValidPosition(String position) {
        if (position.isEmpty() || position.length() != 3 && position.length() != 2)
            return false;

        //Lấy ra hàng và cột
        int row = Cell.getRow(position);
        int colum = Cell.getColum(position);
        if (row < 0 || colum < 0 || row >= currentPlayer.getBoard().getSize() || colum >= currentPlayer.getBoard().getSize())
            return false;

        return true;
    }

    private static boolean isValidPositionPlaceShip(String position, int shipDirection, int sizeShip) {
        int sizeBoard = currentPlayer.getBoard().getSize();
        Cell[][] board = currentPlayer.getBoard().getCells();
        if (!isValidPosition(position)) {
            return false;
        }
        int row = Cell.getRow(position);
        int colum = Cell.getColum(position);
        //Kiểm tra xem hướng nhập có đúng hay không
        if (shipDirection != 1 && shipDirection != 2)
            return false;
        //Xét theo hướng đặt, kiểm tra xem kích thước thuyền có vượt quá bảng không
        if (shipDirection == 1) {
            if (colum + sizeShip - 1 >= sizeBoard)
                return false;
            else
                //Kiểm tra xem các các ô đó có bị trùng hay không
                for (int index = colum; index <= colum + sizeShip - 1; index++)
                    if (!board[row][index].isEmpty()) {
                        return false;
                    }
        } else if (row + sizeShip - 1 >= sizeBoard)
            return false;
        else
            //Kiểm tra xem các các ô đó có bị trùng hay không
            for (int index = row; index <= row + sizeShip - 1; index++)
                if (!board[index][colum].isEmpty()) {
                    return false;
                }
        return true;
    }
}
