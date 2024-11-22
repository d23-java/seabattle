package player;

import main.system;
import template.Template;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    Player player1, player2;
    public static List<String> rank = new ArrayList<String>();
    public static int sl = 0;

    public void showRank() {
        System.out.println("Bảng xếp hạng");
        for(int i = 0; i < sl; i++)
        {
            System.out.println((i+1) + ". " + rank.get(i));
        }
    }

    public void enterInformation() {
        System.out.println("Nhập tên người chơi thứ 1: ");
        String name1 = system.scanner.nextLine();
        player1 = new Player(name1);

        System.out.println("Nhập tên người chơi thứ 2: ");
        String name2 = system.scanner.nextLine();
        player2 = new Player(name2);

    }

    public void showBoard(char[][] board)
    {
        System.out.printf("   ");
        for(int i = 1; i <= 10; i++)
        {
            if (i<10) System.out.printf(" %d ", i);
            else System.out.printf(" %d", i);
        }
        System.out.println();
        for(int i = 1; i <= 10; i++)
        {
            System.out.printf("%d ", i);
            if (i < 10) System.out.printf(" ");
            for(int j = 1; j <= 10; j+=1)
            {
                System.out.printf("[%c]", board[i][j]);
            }
            System.out.printf("%n");
        }
    }
    int x1 = 0,y1 = 0,x2 = 0,y2 = 0;
    public void nhapToaDo() {
        System.out.println("Nhập hoành độ điểm đầu: ");
        x1 = Integer.parseInt(system.scanner.nextLine());
        System.out.println("Nhập tung độ điểm đầu ");
        y1 = Integer.parseInt(system.scanner.nextLine());
        System.out.println("Nhập hoành độ điểm cuối:");
        x2 = Integer.parseInt(system.scanner.nextLine());
        System.out.println("Nhập tung độ điểm cuối: ");
        y2 = Integer.parseInt(system.scanner.nextLine());
    }

    void setBoard(Player player, char shipSymbol) {
        if (x1 == x2) {
            if (y1 < y2)
                for(int i = y1; i <= y2; i++) {
                    player.setMyBoard(shipSymbol, x1, i);

                }
            else for(int i = y2; i <= y1; i++)
                    player.setMyBoard(shipSymbol, x1, i );
        }
        else
        {
            if (x1 < x2)
                for(int i = x1; i <= x2; i++)
                    player.setMyBoard(shipSymbol, i, y1 );
            else for(int i = x2; i <= x1; i++)
                player.setMyBoard(shipSymbol, i, y1 );
        }
    }
    public void placeShip(Player player) {
        showBoard(player.getMyBoard());
        Template.showAllShips();


        System.out.printf("Đặt vị trí 2 tàu tuần tra:%n");
        System.out.println("Chọn tọa độ điểm đầu và tọa độ điểm cuối của Thuyền Tuần Tra thứ nhất (1x2): ");
        nhapToaDo();
        setBoard(player, 'p');
        showBoard(player.getMyBoard());
        System.out.println("Chọn tọa độ điểm đầu và tọa độ điểm cuối của Thuyền tuần tra thứ hai (1x2): ");
        nhapToaDo();
        setBoard(player, 'P');
        showBoard(player.getMyBoard());
        System.out.println("Chọn tọa độ điểm đầu và tọa độ điểm cuối của Tàu Khu Trục (1x4): ");
        nhapToaDo();
        setBoard(player, 'D');
        showBoard(player.getMyBoard());
        System.out.println("Chọn tọa độ điểm đầu và tọa độ điểm cuối của Tàu Ngầm (1x3): ");
        nhapToaDo();
        setBoard(player, 'S');
        showBoard(player.getMyBoard());
        System.out.println("Chọn tọa độ điểm đầu và tọa độ điểm cuối của Thiết Giáp Hạm (1x5): ");
        nhapToaDo();
        setBoard(player, 'B');
        showBoard(player.getMyBoard());
    }

    public void onePlayer() {

    }

    int checkWin = 0;
    public void turnPlayer(Player player, Player enemy)
    {
        System.out.println(player.getName());
        System.out.println("Tình trạng.");
        System.out.println("Số ô đã bắn: " + player.getSoODaBan());
        System.out.println("Số ô trúng đích: " + player.getSoOTrungDich());
        System.out.println("Số tàu đã phá: " + player.getSoTauDaPha());
        System.out.println("Số tàu còn lại: " + (5 - player.getSoTauDaPha()));

        int checkKhaiHoa = 0;

        while(true)
        {
            Template.showPlayerMenu();
            int selection =  Integer.parseInt(system.scanner.nextLine());
            switch(selection)
            {
                case 1: showBoard(player.getMyBoard()); break;
                case 2:
                    showBoard(player.getEnemyBoard());
                    break;
                case 3:
                    if (checkKhaiHoa == 1)
                    {
                        System.out.println("Bạn đã khai hoả.");
                        break;
                    }
                    while(true)
                    {
                        showBoard(player.getEnemyBoard());
                        player.updateSoODaBan();
                        System.out.println("Nhập tọa độ muốn khai hỏa: ");
                        System.out.println("Nhập hoành độ: ");
                        int x = Integer.parseInt(system.scanner.nextLine());
                        System.out.println("Nhập tung độ: ");
                        int y = Integer.parseInt(system.scanner.nextLine());
                        Character point = enemy.getMyBoard()[x][y];
                        if ( point.equals('p') || point.equals('P') || point.equals('D') || point.equals('S')|| point.equals('B'))
                        {
                            if (point.equals('p'))
                            {
                                enemy.decreasePatrolBoat1Point();
                                if (enemy.getPatrolBoat1Point() == 0)
                                {
                                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ!");
                                    player.updateSoTauDaPha();
                                }
                            }
                            else if (point.equals('P'))
                            {
                                enemy.decreasePatrolBoat2Point();
                                if (enemy.getPatrolBoat2Point() == 0)
                                {
                                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ!");
                                    player.updateSoTauDaPha();
                                }
                            }
                            else if (point.equals('D'))
                            {
                                enemy.decreaseDestroyerBoatPoint();
                                if (enemy.getDestroyerBoatPoint() == 0)
                                {
                                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ!");
                                    player.updateSoTauDaPha();
                                }
                            }else if (point.equals('S'))
                            {
                                enemy.decreaseSubmarinePoint();
                                if (enemy.getSubmarinePoint() == 0)
                                {
                                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ!");
                                    player.updateSoTauDaPha();
                                }
                            }else if (point.equals('B'))
                            {
                                enemy.decreaseBattleShipPoint();
                                if (enemy.getBattleShipPoint() == 0)
                                {
                                    System.out.println("Bạn đã hạ gục 1 tàu của đối thủ!");
                                    player.updateSoTauDaPha();
                                }
                            }
                            if (player.getSoTauDaPha() == 5)
                            {
                                System.out.println(player.getName() + " chiến thắng");
                                checkWin = 1;
                                return;
                            }
                            System.out.println("Bạn đã bắn trúng! Bạn có thể bắn tiếp.");
                            enemy.setMyBoard('X', x, y);
                            player.setEnemyBoard('X', x, y);
                            player.updateSoOTrungDich();
                        }
                        else
                        {
                            System.out.println("Bạn đã bắn trượt!");
                            if (point.equals('X') == false)
                                player.setEnemyBoard('O', x, y);
                            break;
                        }
                    }
                    checkKhaiHoa = 1;
                    break;
                case 4: return;
            }
        }
    }

    public void twoPlayer()
    {
        enterInformation();
        System.out.printf("Lượt người chơi %s xếp tàu.%n", player1.getName());
        placeShip(player1);
        System.out.println("Bấm enter để chuyển lượt cho người thứ 2.");
        system.scanner.nextLine();
        System.out.printf("Lượt người chơi %s xếp tàu.%n", player2.getName());
        placeShip(player2);
        System.out.println("Bấm enter để bắt đầu trò chơi.");
        system.scanner.nextLine();
        while(true)
        {
            turnPlayer(player1, player2);
            if (checkWin == 1) return;
            turnPlayer(player2, player1);
            if (checkWin == 1) return;
        }
    }
    public void start()
    {
        Template.showBattleMenu();
        int selection = Integer.parseInt(system.scanner.nextLine());
        if (selection == 1)         {onePlayer();}
        else if (selection == 2)    {twoPlayer();}
    }
}
