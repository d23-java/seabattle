package enums;

import gameObjects.Players;

import java.util.Comparator;

public class SortCmp implements Comparator<Players> {
    public int compare(Players firstPlayer, Players secondPlayer) {
        if (firstPlayer.getShotCell() == secondPlayer.getShotCell()) {
            return secondPlayer.getShips().size()- firstPlayer.getShips().size();
        }
        return secondPlayer.getShotCell()-firstPlayer.getShotCell();
    }
}
