package entities;

import main.Game;
import system.ClearScreen;
import system.InputManager;
import system.RandomCase;
import ui.TextScreen;

import java.util.Scanner;

import static utilz.Constants.gameConstants.*;
import static utilz.Constants.textConstants.GREEN_TEXT;
import static utilz.Constants.textConstants.RESET;

public class Board {
    private Game game;
    private boolean switchTurn = true;
    private final int[] shipSizes = {PATROL_BOAT_SIZE, PATROL_BOAT_SIZE, DESTROYER_BOAT_SIZE, SUBMARINE_SIZE, BATTLE_SHIP_SIZE};
    private final String[] ships = {PATROL_BOAT, PATROL_BOAT, DESTROYER_BOAT, SUBMARINE, BATTLE_SHIP};
    private final String[] shipNames = {"Patrol Boat", "Patrol Boat", "Destroyer Boat", "Submarine", "Battle Ship"};

    public Board(Game game) {
        this.game = game;
    }

    public void setupShips(String[][] board, TextScreen screen, Player player) {
        for(int i = 0; i < shipSizes.length; i++) {
            char a = (char) ('A' + Game.boardSize - 1);
            System.out.println();
            System.out.print(GREEN_TEXT + "Enter the beginning cell of " + shipNames[i] +  " (A-" + a + ", 1-" + Game.boardSize + ") and direction (H/V): " + RESET);
            Scanner sc = InputManager.getScanner();
            String input = sc.next().toUpperCase();
            boolean check = placeShip(board, input, shipSizes[i], ships[i]);
            if(!check) {
                i--;
                System.out.println();
                System.out.println("Try again !!!");
                continue;
            }
            ClearScreen.clearConsole();
            screen.printBoard(board);
        }
    }

    private boolean placeShip(String[][] board, String input, int size, String name) {
        try {
            char startRow = input.charAt(0);
            int startCol = Integer.parseInt(input.substring(1, input.length() - 1));
            char direction = input.charAt(input.length() - 1);

            int row = startRow - 'A';
            int col = startCol - 1;

            if (direction == 'H') {
                if (col + size > Game.boardSize) return false;
                for (int i = 0; i < size; i++) {
                    if (!board[row][col + i].equals(EMPTY_BOAT)) return false;
                }
                for (int i = 0; i < size; i++) {
                    board[row][col + i] = name;
                }
            } else if (direction == 'V') {
                if (row + size > Game.boardSize) return false;
                for (int i = 0; i < size; i++) {
                    if (!board[row + i][col].equals(EMPTY_BOAT)) return false;
                }
                for (int i = 0; i < size; i++) {
                    board[row + i][col] = name;
                }
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean playTurn(String[][] fog, String[][] opponentBoard, Player currentPlayer) {
        TextScreen.printBoard(fog);
        char a = (char) ('A' + Game.boardSize - 1); //name of row
        System.out.print(GREEN_TEXT + "Enter fire cell (A-" + a + ", 1-" + Game.boardSize + "): " + RESET);
        Scanner sc = InputManager.getScanner();
        String input = sc.next().toUpperCase();
        try {
            char rowChar = input.charAt(0);
            int col = Integer.parseInt(input.substring(1)) - 1;

            int row = rowChar - 'A';
            if (fog[row][col].equals(MISS_CELL) || fog[row][col].equals(DESTROYER_CELL)) {
                System.out.println("You already shot a fire cell");
                switchTurn = false;
                return false;
            }

            if (opponentBoard[row][col].equals(PATROL_BOAT) || opponentBoard[row][col].equals(DESTROYER_BOAT) || opponentBoard[row][col].equals(SUBMARINE) || opponentBoard[row][col].equals(BATTLE_SHIP)) {
                System.out.println("Bull's eye !!!");
                currentPlayer.setScore(currentPlayer.getScore() + 1);
                fog[row][col] = DESTROYER_CELL;
                opponentBoard[row][col] = DESTROYER_CELL;
                switchTurn = false;
            }
            else {
                System.out.println("Miss !!!");
                fog[row][col] = MISS_CELL;
                switchTurn = true;
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
            switchTurn = false;
            return false;
        }

        return checkWin(opponentBoard);
    }

    public boolean checkWin(String[][] board) {
        for (int i = 0; i < Game.boardSize; i++) {
            for (int j = 0; j < Game.boardSize; j++) {
                if (board[i][j].equals(PATROL_BOAT) || board[i][j].equals(DESTROYER_BOAT) || board[i][j].equals(SUBMARINE) || board[i][j].equals(BATTLE_SHIP)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void randomSetupShips(String[][] board, Player currentPlayer) {
        for(int i = 0; i < shipSizes.length; i++) {
            String input = RandomCase.randomChar('A',(char)('A' + Game.boardSize - 1)) + String.valueOf(RandomCase.randomNumber(1,Game.boardSize)) + RandomCase.randomDir();
            boolean check = placeShip(board, input, shipSizes[i], ships[i]);
            if(!check) {
                i--;
                continue;
            }
        }
    }

    public int checkShipLeft(String [][] board) {
        int cntShip = 5;
        int cntP = 0, cntD = 0, cntS = 0, cntB = 0;
        for(int i = 0; i < Game.boardSize; i++) {
            for (int j = 0; j < Game.boardSize; j++) {
                switch (board[i][j]) {
                    case PATROL_BOAT -> ++cntP;
                    case DESTROYER_BOAT -> ++cntD;
                    case SUBMARINE -> ++cntS;
                    case BATTLE_SHIP -> ++cntB;
                }
            }
        }
        if(cntP < 2)        cntShip -= 2;
        else if(cntP < 4)   cntShip--;
        if(cntD < 4)        cntShip--;
        if(cntS < 3)        cntShip--;
        if(cntB < 5)        cntShip--;
        return cntShip;
    }

    public boolean getSwitchTurn() {
        return switchTurn;
    }

    public void setSwitchTurn(boolean switchTurn) {
        this.switchTurn = switchTurn;
    }

}
