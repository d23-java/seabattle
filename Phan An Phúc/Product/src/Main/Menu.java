package Main;

import java.util.ArrayList;
import java.util.Scanner;

import element.Boat;
import element.Bot;
import element.Cell;
import element.Player;
import element.PlayerOpponent;
import playing.BeforePlaying;
import playing.ClearConsole;
import playing.Color;
import playing.GameController;
import playing.ScoreBoard;

public class Menu {
	static Scanner sc = Input.getScanner();
	static ClearConsole clear = new ClearConsole();

	public void Menu() {
		System.out.println(Color.cyan + " ________  _______   ________                                     \r\n"
				+ "|\\   ____\\|\\  ___ \\ |\\   __  \\                                    \r\n"
				+ "\\ \\  \\___|\\ \\   __/|\\ \\  \\|\\  \\                                   \r\n"
				+ " \\ \\_____  \\ \\  \\_|/_\\ \\   __  \\                                  \r\n"
				+ "  \\|____|\\  \\ \\  \\_|\\ \\ \\  \\ \\  \\                                 \r\n"
				+ "    ____\\_\\  \\ \\_______\\ \\__\\ \\__\\                                \r\n"
				+ "   |\\_________\\|_______|\\|__|\\|__|                                \r\n"
				+ "   \\|_________|                                                   \r\n"
				+ "                                                                  \r\n"
				+ "                                                                  \r\n"
				+ " ________  ________  _________  _________  ___       _______      \r\n"
				+ "|\\   __  \\|\\   __  \\|\\___   ___\\\\___   ___\\\\  \\     |\\  ___ \\     \r\n"
				+ "\\ \\  \\|\\ /\\ \\  \\|\\  \\|___ \\  \\_\\|___ \\  \\_\\ \\  \\    \\ \\   __/|    \r\n"
				+ " \\ \\   __  \\ \\   __  \\   \\ \\  \\     \\ \\  \\ \\ \\  \\    \\ \\  \\_|/__  \r\n"
				+ "  \\ \\  \\|\\  \\ \\  \\ \\  \\   \\ \\  \\     \\ \\  \\ \\ \\  \\____\\ \\  \\_|\\ \\ \r\n"
				+ "   \\ \\_______\\ \\__\\ \\__\\   \\ \\__\\     \\ \\__\\ \\ \\_______\\ \\_______\\\r\n"
				+ "    \\|_______|\\|__|\\|__|    \\|__|      \\|__|  \\|_______|\\|_______|" + Color.ANSI_Reset);

		System.out.println();
		boolean breakMenu = false;
		while (breakMenu == false) {
			System.out.println("==================================");
			System.out.println("|              Menu              |");
			System.out.println("==================================");
			System.out.println("| 1. Play PVP                    |");
			System.out.println("| 2. Play with Bot               |");
			System.out.println("| 3. View Scoreboard             |");
			System.out.println("| 4. Quit                        |");
			System.out.println("==================================");
			System.out.print("--> Enter your choice: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				StartGame();
				break;
			case 2:
				StartGameBot();
				break;
			case 3:
				ScoreBoard.displayScoresBoard();
				System.out.println("Bạn có muốn quay lại menu?");
				System.out.println("1. Quay lại");
				System.out.println("2. Thoát");
				System.out.print("--> Enter your choice: ");
				int typeChoice = Integer.parseInt(sc.nextLine().trim());

				if (typeChoice == 1) {
					break;
				} else if (typeChoice == 2) {
					System.out.println("Cảm ơn bạn đã sử dụng chương trình. Hẹn gặp lại!");
					return;
				}
			case 4:
				return;

			}

		}
	}

	public void StartGame() {

		System.out.println("Nhập kích cỡ của bảng");
		int size = Integer.parseInt(sc.nextLine());

		Cell[][] mapPlayer1 = new Cell[size][size];
		Cell[][] mapPlayer2 = new Cell[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mapPlayer1[i][j] = new Cell();
				mapPlayer2[i][j] = new Cell();
			}
		}
		ArrayList<Boat> boats1 = new ArrayList<>();
		ArrayList<Boat> boats2 = new ArrayList<>();
		System.out.println("Nhập tên người chơi 1:");
		String nguoi_choi_1 = sc.nextLine();
		Player player1 = new Player(nguoi_choi_1, boats1, mapPlayer1);

		System.out.println("Nhập tên người chơi 2:");
		String nguoi_choi_2 = sc.nextLine();
		PlayerOpponent player2 = new PlayerOpponent(nguoi_choi_2, boats2, mapPlayer2);

		BeforePlaying beforePlaying = new BeforePlaying();
		System.out.println("Đặt tàu cho người chơi 1");
		System.out.println("Bạn muốn đặt tàu thủ công hay tự động ");
		System.out.println("1. Thủ công");
		System.out.println("2. Tự động");

		int choice_1 = Integer.parseInt(sc.nextLine());
		boolean choice_player1;
		if (choice_1 == 1)
			choice_player1 = true;
		else
			choice_player1 = false;
		beforePlaying.setUpBoatsForPlayer(player1, choice_player1);
		clear.clearConsole();

		System.out.println("Đặt tàu cho người chơi 2");
		System.out.println("Bạn muốn đặt tàu thủ công hay tự động ");
		System.out.println("1. Thủ công");
		System.out.println("2. Tự động");
		int choice_2 = Integer.parseInt(sc.nextLine());
		boolean choice_player2;
		if (choice_2 == 1)
			choice_player2 = true;
		else
			choice_player2 = false;
		beforePlaying.setUpBoatsForOpponent(player2, choice_player2);
		clear.clearConsole();

		System.out.println("\nTất cả tàu đã được đặt. Trò chơi bắt đầu!");
		clear.clearConsole();
		GameController gameController = new GameController(player1, player2);
		gameController.startGame();

		System.out.println("Trò chơi kết thúc!");

	}

	public void StartGameBot() {

		System.out.println("Nhập kích cỡ của bảng");
		int size = Integer.parseInt(sc.nextLine());

		Cell[][] mapPlayer1 = new Cell[size][size];
		Cell[][] mapPlayer2 = new Cell[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mapPlayer1[i][j] = new Cell();
				mapPlayer2[i][j] = new Cell();
			}
		}
		ArrayList<Boat> boats1 = new ArrayList<>();
		ArrayList<Boat> boats2 = new ArrayList<>();
		System.out.println("Nhập tên người chơi 1:");
		String nguoi_choi_1 = sc.nextLine();
		Player player1 = new Player(nguoi_choi_1, boats1, mapPlayer1);

		Bot player2 = new Bot("Bot", boats2, mapPlayer2);

		BeforePlaying beforePlaying = new BeforePlaying();
		System.out.println("Đặt tàu cho người chơi 1");
		System.out.println("Bạn muốn đặt tàu thủ công hay tự động ");
		System.out.println("1. Thủ công");
		System.out.println("2. Tự động");

		int choice_1 = Integer.parseInt(sc.nextLine());
		boolean choice_player1;
		if (choice_1 == 1)
			choice_player1 = true;
		else
			choice_player1 = false;
		beforePlaying.setUpBoatsForPlayer(player1, choice_player1);
		clear.clearConsole();

		beforePlaying.setUpBoatsForBot(player2);
		clear.clearConsole();

		System.out.println("\nTất cả tàu đã được đặt. Trò chơi bắt đầu!");
		clear.clearConsole();
		GameController gameController = new GameController(player1, player2);
		gameController.startGameWithBot();

		System.out.println("Trò chơi kết thúc!");

	}
}
