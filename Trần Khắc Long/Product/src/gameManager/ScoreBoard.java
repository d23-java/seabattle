package gameManager;

import enums.SortCmp;
import gameObjects.Players;

import java.util.ArrayList;
import java.util.Arrays;

public class ScoreBoard {
    private static ArrayList<Players> infoList = new ArrayList<>();
    public static void addInfo(Players winner) {
        infoList.add(winner);
    }
    private static int findLongestPlayerName(){
        if(infoList.size() == 0) return 1;
        int maxLength = -1;
        for(Players winner : infoList){
            if(winner.getPlayerName().length() > maxLength)
                maxLength = winner.getPlayerName().length();
        }
        return maxLength;
    }
    private static void drawTitle(){
        int distance = findLongestPlayerName() - 4;
        System.out.print("| Rank | Name ");
        if(distance >= 0)
            for (int times = 1 ; times <= findLongestPlayerName() -4; times++)
                System.out.print(" ");
        System.out.print("| Shot | Remaining Ship");
        System.out.println();
        for(int times = 1; times <= Math.max(findLongestPlayerName(),4 ) +22 +7; times++){
            System.out.print("-");
        }
        System.out.println();
    }
    public static void show(){
        drawTitle();
        infoList.sort(new SortCmp());
        int index = 1;
        for (Players winner : infoList) {
            System.out.print(index + " ".repeat(7 - (index + "").length()) + "| ");
            System.out.print(winner.getPlayerName() +" ".repeat( findLongestPlayerName() - winner.getPlayerName().length()) + " | ");
            System.out.print(winner.getShotCell() +" ".repeat(4- (""  + winner.getShotCell()).length()) + " | ");
            System.out.println(winner.getShips().size());
            System.out.println();
        }
    }
}
