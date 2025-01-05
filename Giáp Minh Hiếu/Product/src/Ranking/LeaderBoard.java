package Ranking;
import Body.Player;

import java.util.*;
public class LeaderBoard {
    private final ArrayList<Player> players;
    public LeaderBoard() {
        players = new ArrayList<>();
    }
    public void addPlayer(Player player) {
        players.add(player);
        players.sort(Comparator.comparingInt(Player::getHits));
    }
    public void displayLeaderBoard() {
        System.out.print("\n=== RANKING ===\n");
        if (players.isEmpty()) {
            System.out.println("No players found");
        }
        else {
            for (int i = 0; i < players.size(); i++) {
                System.out.println((i+1) + ". " + players.get(i).getName() + ": " + players.get(i).getHits() + " " + players.get(i).getAliveShip());
            }
        }
    }
}
