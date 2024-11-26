package data;

public class RankComparator implements java.util.Comparator<PlayerRank>{
    @Override
    public int compare(PlayerRank p1, PlayerRank p2) {
        return p1.getScore() - p2.getScore();
    }
}
