package utilz;

import entities.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadSave {
    private static final String FILE_NAME = "E:/ProPTIT/seabattle/Nguyen Manh Dung/Product/SaveGame/leaderboard.txt";

    public static void savePlayer(Player player) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(player.getName() + "," + player.getScore() + "," + player.getShipLeft());
            writer.newLine();
        }
        catch (IOException e) {
            System.err.println("Error saving leaderboard: " + e.getMessage());
        }
    }

    public static ArrayList<String> loadLeaderboard() {
        ArrayList<String> leaderboard = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                leaderboard.add(line);
            }
        }
        catch (IOException e) {
            System.err.println("Error loading leaderboard: " + e.getMessage());
        }
        return leaderboard;
    }
}
