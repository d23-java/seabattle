package game;
import template.Template;
public class ShowBoard {

    public static void showBoard(Player player) {
        System.out.printf("    ");
        for(int i = 1; i <= GameManager.kichThuoc; i++)
        {
            System.out.printf("%s", Template.listNumberIcons[i]);
            //else system.out.printf(" %d", i);
        }
        System.out.println();
        for(int i = 1; i <= GameManager.kichThuoc; i++)
        {
            System.out.printf("%s ", Template.listNumberIcons[i]);
            System.out.printf(" ");
            for(int j = 1; j <= GameManager.kichThuoc; j+=1)
            {
                System.out.printf("%s", player.getBoard()[i][j]);
            }
            System.out.printf("%n");
        }
    }
    public static void showEnemyBoard(Player player) {
        System.out.printf("    ");
        for(int i = 1; i <= GameManager.kichThuoc; i++)
        {
            //if (i < 10)
                System.out.printf("%s", Template.listNumberIcons[i]);
            //else system.out.printf(" %d", i);
        }
        System.out.println();
        for(int i = 1; i <= GameManager.kichThuoc; i++)
        {
            System.out.printf("%s ", Template.listNumberIcons[i]);
            System.out.printf(" ");
            for(int j = 1; j <= GameManager.kichThuoc; j+=1)
            {
                System.out.printf("%s", player.getEnemyBoard()[i][j]);
            }
            System.out.printf("%n");
        }
    }
    public static void showBoards(Player player) {
        System.out.printf("    ");
        for(char i = 'A'; i < 'A' + GameManager.kichThuoc; i++)
        {
            System.out.printf("%-3c",i);
        }
        System.out.println();
        for(int i = 1; i <= GameManager.kichThuoc; i++)
        {
            System.out.printf("%-3s ",i);
            for(int j = 1; j <= GameManager.kichThuoc; j+=1)
            {
                System.out.printf("%-3s", player.getBoard()[i][j]);
            }
            System.out.printf("%n");
        }
    }
    public static void showEnemyBoards(Player player) {
        System.out.printf("    ");
        for(char i = 'A'; i < 'A' + GameManager.kichThuoc; i++)
        {
            System.out.printf("%-3c",i);
        }
        System.out.println();
        for(int i = 1; i <= GameManager.kichThuoc; i++)
        {
            System.out.printf("%-3s ",i);
            for(int j = 1; j <= GameManager.kichThuoc; j+=1)
            {
                System.out.printf("%-3s", player.getEnemyBoard()[i][j]);
            }
            System.out.printf("%n");
        }
    }
    public static void showBotEnemyBoards(Bot bot) {
        System.out.printf("    ");
        for(char i = 'A'; i < 'A' + GameManager.kichThuoc; i++)
        {
            System.out.printf("%-3c",i);
        }
        System.out.println();
        for(int i = 1; i <= GameManager.kichThuoc; i++)
        {
            System.out.printf("%-3s ",i);
            for(int j = 1; j <= GameManager.kichThuoc; j+=1) {
                if (bot.getEnemyBoard()[i][j] != "?")
                    System.out.printf("%-3s", bot.getEnemyBoard()[i][j]);
                else System.out.printf(".  ");
            }
            System.out.printf("%n");
        }
    }
}
