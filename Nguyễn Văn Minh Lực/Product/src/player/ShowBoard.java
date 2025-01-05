package player;
import template.Template;
public class ShowBoard {

    public static void showBoard(Player player) {
        System.out.printf("    ");
        for(int i = 1; i <= 10; i++)
        {
            System.out.printf("%s", Template.listNumberIcons[i]);
            //else system.out.printf(" %d", i);
        }
        System.out.println();
        for(int i = 1; i <= 10; i++)
        {
            System.out.printf("%s ", Template.listNumberIcons[i]);
            System.out.printf(" ");
            for(int j = 1; j <= 10; j+=1)
            {
                System.out.printf("%s", player.getBoard()[i][j]);
            }
            System.out.printf("%n");
        }
    }
    public static void showEnemyBoard(Player player) {
        System.out.printf("    ");
        for(int i = 1; i <= 10; i++)
        {
            //if (i < 10)
                System.out.printf("%s", Template.listNumberIcons[i]);
            //else system.out.printf(" %d", i);
        }
        System.out.println();
        for(int i = 1; i <= 10; i++)
        {
            System.out.printf("%s ", Template.listNumberIcons[i]);
            System.out.printf(" ");
            for(int j = 1; j <= 10; j+=1)
            {
                System.out.printf("%s", player.getEnemyBoard()[i][j]);
            }
            System.out.printf("%n");
        }
    }
}
