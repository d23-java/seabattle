package playing;

import Main.Input;
import element.Boat;
import element.Bot;
import element.Player;
import element.PlayerOpponent;
import java.util.Scanner;

public class GameController {
	static ClearConsole clear = new ClearConsole();
	static Scanner sc = Input.getScanner();
	private Player player1;
	private PlayerOpponent player2;
	private Bot bot;
	public int numTurn1 = 1;
	public int numTurn2 = 1;
	boolean isGameOver;

	public GameController(Player player1, PlayerOpponent player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public GameController(Player player1, Bot player2) {
		this.player1 = player1;
		this.bot = player2;
	}

	public void startGame() {
		int turn = 0;

		while (true) {
			if (turn == 0) {
				clear.clearConsole();
				player2.mapPlaying();
				System.out.println("Show your functions");
				System.out.println("1. Watch my map");
				System.out.println("2. Shot");
				int choice = Integer.parseInt(sc.nextLine());
				boolean hit = false;
				switch (choice) {
				case 1:
					player1.mapPlayingAfterShot();
				case 2:
					hit = takeTurn(player1, player2);
					break;
				}
				if (isGameOver == true) {
					ScoreBoard scoreBoard = new ScoreBoard(player1.getNamePlayer(), numTurn1, player1.getNumOfShip());
					ScoreBoard.saveScoreBoard(scoreBoard);
					System.out.println("Game Over! Your scores have been saved");
					break;
				}
				if (hit) {
					System.out.println("Player1 keep shotting!");
					numTurn1++;
				} else {
					clear.clearConsole();
					turn = 1;
				}
			} else {
				clear.clearConsole();
				player1.mapPlaying();
				System.out.println("Show your functions");
				System.out.println("1. Watch my map");
				System.out.println("2. Shot");
				int choice = Integer.parseInt(sc.nextLine());
				boolean hit = false;
				switch (choice) {
				case 1:
					player2.mapPlayingAfterShot();
				case 2:
					hit = takeTurn(player2, player1);
					break;
				}
				if (isGameOver == true) {
					ScoreBoard scoreBoard = new ScoreBoard(player2.getNamePlayer(), numTurn2, player2.getNumOfShip());
					ScoreBoard.saveScoreBoard(scoreBoard);
					System.out.println("Game Over! Your scores have been saved");
					break;
				}
				if (hit) {
					System.out.println("Player2 keep shotting!");
					numTurn2++;
				} else {
					clear.clearConsole();
					turn = 0;
				}
			}
		}
	}

	public void startGameWithBot() {
		int turn = 0;

		while (true) {
			if (turn == 0) {
				clear.clearConsole();
				System.out.println("Your turn!");
				bot.mapPlaying();
				System.out.println("Show your functions");
				System.out.println("1. Watch my map");
				System.out.println("2. Shot");
				int choice = Integer.parseInt(sc.nextLine());
				boolean hit = false;
				switch (choice) {
				case 1:
					player1.mapPlayingAfterShot();
				case 2:
					hit = takeTurn(player1, bot);
					break;
				}
				if (isGameOver == true) {
					ScoreBoard scoreBoard = new ScoreBoard(player1.getNamePlayer(), numTurn1, player1.getNumOfShip());
					ScoreBoard.saveScoreBoard(scoreBoard);
					System.out.println("Game Over! Your scores have been saved");
					break;
				}
				if (hit) {
					System.out.println("Player1 keep shotting");
					numTurn1++;
				} else {
					clear.clearConsole();
					turn = 1;
				}
			} else {
				clear.clearConsole();
				System.out.println("Bot turn!");
				boolean hit = takeTurnWithBot(bot, player1);
				player1.mapPlaying();
				Effect.delay(4);
				if (isGameOver == true) {
					ScoreBoard scoreBoard = new ScoreBoard(player2.getNamePlayer(), numTurn2, player2.getNumOfShip());
					ScoreBoard.saveScoreBoard(scoreBoard);
					System.out.println("Game Over! Your scores have been saved");
					break;
				}
				if (hit) {
					System.out.println("Bot keep shotting");
					numTurn2++;
				} else {
					clear.clearConsole();
					turn = 0;
				}
			}
		}
	}

	private boolean checkGameOver(Player player) {
		for (Boat boat : player.getBoats()) {
			if (!boat.isDestroyed(player.getMapPlayer())) {
				return false;
			}
		}
		return true;
	}

	private boolean takeTurn(Player current, Player opponent) {
		int[] coordinates = current.getCoordinatesFromPlayer(current.getNamePlayer());
		int x = coordinates[0];
		int y = coordinates[1];
		if (opponent.getMapPlayer()[x][y].isHit()) {
			System.out.println("Cell has been shotted. Please choose the other cell");
			return takeTurn(current, opponent);
		}

		if (opponent.getMapPlayer()[x][y].hasBoat()) {
			opponent.getMapPlayer()[x][y].setHit(true);
			Effect.isHit();
			Effect.delay(3);
//			opponent.mapPlaying();

			for (Boat boat : opponent.getBoats()) {
				if (boat.isSunk(x, y)) {
					Effect.isSunk();
					System.out.println(Color.purple + "Ship " + boat.getName() + " has been sunk!" + Color.ANSI_Reset);
					opponent.setNumOfShip(boat.getNumOfShip());
					Effect.delay(3);
					clear.clearConsole();
				}
			}

			if (checkGameOver(opponent)) {
				System.out.println("All of opponent ships " + opponent.getNamePlayer() + " have been demolished!");
				Effect.isVictory();
				Effect.delay(3);
				isGameOver = true;
				return true;
			}

			return true;
		} else {
			opponent.getMapPlayer()[x][y].setHit(true);
			Effect.isMiss();
			Effect.delay(3);
//			opponent.mapPlaying();
			return false;
		}
	}

	private boolean takeTurn(Player current, Bot opponent) {
		int[] coordinates = current.getCoordinatesFromPlayer(current.getNamePlayer());
		int x = coordinates[0];
		int y = coordinates[1];
		if (opponent.getMapPlayer()[x][y].isHit()) {
			System.out.println("Cell has been shotted. Please choose the other cell");
			return takeTurn(current, opponent);
		}

		if (opponent.getMapPlayer()[x][y].hasBoat()) {
			opponent.getMapPlayer()[x][y].setHit(true);
			Effect.isHit();
			Effect.delay(2);
//			opponent.mapPlaying();

			for (Boat boat : opponent.getBoats()) {
				if (boat.isSunk(x, y)) {
					Effect.isSunk();
					System.out.println(Color.purple + "Ship " + boat.getName() + " has been sunk!" + Color.ANSI_Reset);
					opponent.setNumOfShip(boat.getNumOfShip());
					Effect.delay(2);
					clear.clearConsole();
				}
			}

			if (checkGameOver(opponent)) {
				System.out.println("All of opponent ships " + opponent.getNamePlayer() + " have been demolished!");
				Effect.isVictory();
				Effect.delay(3);
				isGameOver = true;
				return true;
			}

			return true;
		} else {
			opponent.getMapPlayer()[x][y].setHit(true);
			Effect.isMiss();
			Effect.delay(4);
//			opponent.mapPlaying();
			return false;
		}
	}

	private boolean takeTurnWithBot(Bot current, Player opponent) {
		int[] coordinates;

		if (current.inTargetShip()) {
			coordinates = current.getNextCoordinates(opponent.getMapPlayer());
		} else {
			coordinates = current.getRandomCoordinates(opponent.getMapPlayer());
		}

		int x = coordinates[0];
		int y = coordinates[1];

		if (opponent.getMapPlayer()[x][y].isHit()) {
			return takeTurnWithBot(current, opponent);
		}

		opponent.getMapPlayer()[x][y].setHit(true);

		if (opponent.getMapPlayer()[x][y].hasBoat()) {
			Effect.isHit();
			Effect.delay(4);
			current.detechTargetShip(x, y, opponent.getMapPlayer());

			for (Boat boat : opponent.getBoats()) {
				if (boat.isSunk(x, y)) {
					Effect.isSunk();
					System.out.println(Color.purple + "Ship " + boat.getName() + " has been sunk!" + Color.ANSI_Reset);
					Effect.delay(4);
					current.getTargetHit().clear();
					opponent.setNumOfShip(boat.getNumOfShip());
					break;
				}
			}
			Effect.delay(2);
			if (checkGameOver(opponent)) {
				System.out.println("All of opponent ships " + opponent.getNamePlayer() + " have been demolished!");
				Effect.isVictory();
				Effect.delay(4);
				isGameOver = true;
				return true;
			}
			return true;
		} else {
			Effect.isMiss();
			Effect.delay(4);
			return false;
		}
	}

}
