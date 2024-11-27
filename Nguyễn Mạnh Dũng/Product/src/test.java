import ui.TextScreen;
import utilz.LoadSave;

public class test {
    public static void main(String[] args) {
        LoadSave.loadLeaderboard();
        TextScreen.displayLeaderboard();
    }
}
