package game;

public class PlayerRecord {
    public String playerName;
    public int shotsTaken;
    public int remainingShips;

    public PlayerRecord(String playerName, int shotsTaken, int remainingShips) {
        this.playerName = playerName;
        this.shotsTaken = shotsTaken;
        this.remainingShips = remainingShips;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getShotsTaken() {
        return shotsTaken;
    }

    public int getRemainingShips() {
        return remainingShips;
    }
}
