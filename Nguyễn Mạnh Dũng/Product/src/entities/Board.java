package entities;

import main.Game;
import system.ClearScreen;
import system.InputManager;
import ui.TextScreen;

import java.util.Scanner;

import static utilz.Constants.gameConstants.*;
import static utilz.Constants.textConstants.GREEN_TEXT;
import static utilz.Constants.textConstants.RESET;

public class Board {
    private Game game;
    private boolean continueTurn = true;

    public Board(Game game) {
        this.game = game;
    }

    public void initializeBoards(String[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_BOAT;
            }
        }
    }

    public void setupShips(String[][] board, TextScreen screen, Player player) {
        int[] shipSizes = {PATROL_BOAT_SIZE, PATROL_BOAT_SIZE, DESTROYER_BOAT_SIZE, SUBMARINE_SIZE, BATTLE_SHIP_SIZE};
        String [] ships = {PATROL_BOAT, PATROL_BOAT, DESTROYER_BOAT, SUBMARINE, BATTLE_SHIP};
        String [] shipNames = {"Patrol Boat", "Patrol Boat", "Destroyer Boat", "Submarine", "Battle Ship"};
        for(int i = 0; i < shipSizes.length; i++) {
            char a = 'A' + BOARD_SIZE - 1;
            System.out.println();
            System.out.print(GREEN_TEXT + "Enter the beginning cell of " + shipNames[i] +  " (A-" + a + ", 1-" + BOARD_SIZE + ") and direction (H/V): " + RESET);
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
            screen.printBoard(board,player.getName());
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
                if (col + size > BOARD_SIZE) return false;
                for (int i = 0; i < size; i++) {
                    if (!board[row][col + i].equals(EMPTY_BOAT)) return false;
                }
                for (int i = 0; i < size; i++) {
                    board[row][col + i] = name;
                }
            } else if (direction == 'V') {
                if (row + size > BOARD_SIZE) return false;
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
        TextScreen.printBoard(fog, currentPlayer.getName());
        char a = 'A' + BOARD_SIZE - 1;
        System.out.print(GREEN_TEXT + "Enter fire cell (A-" + a + ", 1-" + BOARD_SIZE + "): " + RESET);
        Scanner sc = InputManager.getScanner();
        String input = sc.next().toUpperCase();
        try {
            char rowChar = input.charAt(0);
            int col = Integer.parseInt(input.substring(1)) - 1;

            int row = rowChar - 'A';
            if (fog[row][col].equals(SHOTTED_CELL) || fog[row][col].equals(DESTROYER_CELL)) {
                System.out.println("You already shot a fire cell");
                continueTurn = true;
                return false;
            }

            if (opponentBoard[row][col].equals(PATROL_BOAT) || opponentBoard[row][col].equals(DESTROYER_BOAT) || opponentBoard[row][col].equals(SUBMARINE) || opponentBoard[row][col].equals(BATTLE_SHIP)) {
                System.out.println("Bull's eye !!!");
                fog[row][col] = DESTROYER_CELL;
                opponentBoard[row][col] = DESTROYER_CELL;
                continueTurn = false;
            }
            else {
                System.out.println("Miss !!!");
                fog[row][col] = SHOTTED_CELL;
                continueTurn = true;
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
            continueTurn = false;
            return false;
        }

        return checkWin(opponentBoard);
    }

    private boolean checkWin(String[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j].equals(PATROL_BOAT) || board[i][j].equals(DESTROYER_BOAT) || board[i][j].equals(SUBMARINE) || board[i][j].equals(BATTLE_SHIP)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean getContinueTurn() {
        return continueTurn;
    }

    public void setContinueTurn(boolean continueTurn) {
        this.continueTurn = continueTurn;
    }
}
