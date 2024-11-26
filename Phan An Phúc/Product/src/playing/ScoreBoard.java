package playing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreBoard {
	private String namePlayer;
	private int turn;
	private int numOfRemainShip;

	public ScoreBoard(String namePlayer, int turn, int numOfRemainShip) {
		this.namePlayer = namePlayer;
		this.turn = turn;
		this.numOfRemainShip = numOfRemainShip;
	}

	public String getNamePlayer() {
		return namePlayer;
	}

	public int getTurn() {
		return turn;
	}

	public int getNumOfRemainShip() {
		return numOfRemainShip;
	}

	@Override
	public String toString() {
		return String.format("Người thắng cuộc: %s, Số lượt: %d, Tàu còn lại: %d", namePlayer, turn, numOfRemainShip);
	}

	public static void saveScoreBoard(ScoreBoard score) {
		try (FileWriter writer = new FileWriter("scoreboard.txt", true)) {
			writer.write(score.toString() + "\n");
		} catch (IOException e) {
			System.err.println("Lỗi khi lưu bảng xếp hạng: " + e.getMessage());
		}
	}

	public static List<ScoreBoard> loadScoresBoard() {
		List<ScoreBoard> scores = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader("scoreboard.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(", ");
				String name = parts[0].split(": ")[1];
				int turns = Integer.parseInt(parts[1].split(": ")[1]);
				int ships = Integer.parseInt(parts[2].split(": ")[1]);
				scores.add(new ScoreBoard(name, turns, ships));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file bảng xếp hạng. Tạo bảng mới.");
		} catch (IOException e) {
			System.err.println("Lỗi khi đọc bảng xếp hạng: " + e.getMessage());
		}
		return scores;
	}

	public static void displayScoresBoard() {
		List<ScoreBoard> scores = loadScoresBoard();
		scores.sort(Comparator.comparingInt(ScoreBoard::getTurn)
				.thenComparing(Comparator.comparingInt(ScoreBoard::getNumOfRemainShip).reversed()));

		System.out.println(Color.yellow + " ____      _    _   _ _  _____ _   _  ____ \r\n" + //
				"|  _ \\    / \\  | \\ | | |/ /_ _| \\ | |/ ___|\r\n" + //
				"| |_) |  / _ \\ |  \\| | ' / | ||  \\| | |  _ \r\n" + //
				"|  _ <  / ___ \\| |\\  | . \\ | || |\\  | |_| |\r\n" + //
				"|_| \\_\\/_/   \\_\\_| \\_|_|\\_\\___|_| \\_|\\____|" + Color.ANSI_Reset);
		System.out.println();
		System.out.printf("|%-5s| %-20s| %-15s| %-15s|\n", "STT", "Tên người chơi", "Số lượt chơi", "Số tàu còn lại");
		System.out.printf("|%-5s| %-20s| %-15s| %-15s|\n", "-----", "--------------------", "---------------",
				"---------------");
		for (int i = 0; i < scores.size(); i++) {
			ScoreBoard score = scores.get(i);
			System.out.printf("|%-5d| %-20s| %-15d| %-15d|\n", i + 1, score.getNamePlayer(), score.getTurn(),
					score.getNumOfRemainShip());
		}

		System.out.printf("|%-5s| %-20s| %-15s| %-15s|\n", "-----", "--------------------", "---------------",
				"---------------");
	}
}
