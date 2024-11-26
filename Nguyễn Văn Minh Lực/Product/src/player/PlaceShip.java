package player;

import main.system;
import ship.ListOfShips;
import ship.Ship;
import template.Template;

import java.util.Random;

public class PlaceShip {
    public static void placeShip(Player player) {
        for(Ship ship : ListOfShips.list) {
            ShowBoard.showBoard(player);
            switch(ship.getSymbol()) {
                case "\uD83D\uDEF3\uFE0F": java.lang.System.out.println("Đặt Thuyền Tuần Tra thứ nhất (kích thước 1 x " + ship.getSize() + "):");break;
                case "\uD83D\uDC25": java.lang.System.out.println("Đặt Thuyền Tuần Tra thứ hai (kích thước 1 x " + ship.getSize() + "):");break;
                case "\uD83D\uDC26": java.lang.System.out.println("Đặt Tàu Khu Trục (kích thước 1 x " + ship.getSize() + "):");break;
                case "\uD83D\uDC27": java.lang.System.out.println("Đặt Tàu Ngầm (kích thước 1 x " + ship.getSize() + "):");break;
                case "\uD83D\uDC14": java.lang.System.out.println("Đặt Thiết Giáp Hạm (kích thước 1 x " + ship.getSize() + "):");break;
            }

            while(true) {
                int selection1;
                int hoanhdo;
                int tungdo;
                boolean check = true;
                while (true) {
                    System.out.println("Chọn hướng đặt tàu:");
                    System.out.println("1. Ngang.");
                    System.out.println("2. Dọc");
                    selection1 = Integer.parseInt(system.scanner.nextLine());
                    if (selection1 == 1 || selection1 == 2) break;
                    else java.lang.System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập lại.");
                }

                System.out.println("Nhập tọa độ điểm đầu ");
                System.out.printf("Nhập hoành độ: ");
                hoanhdo = Integer.parseInt(system.scanner.nextLine());
                System.out.printf("Nhập tung độ: ");
                tungdo = Integer.parseInt(system.scanner.nextLine());

                if (hoanhdo < 1 || hoanhdo > 10 || tungdo < 1 || tungdo > 10) {
                    Template.enterAgain();
                    System.out.println("1----");
                    check  = false;
                }
                else if (selection1 == 1) {
                    for(int i = 0; i < ship.getSize(); i++){
                        if (tungdo + i > 10 || (player.getBoard()[hoanhdo][tungdo + i]).equals("\uD83C\uDF0A") == false) {
                            Template.enterAgain();
                            check = false;
                            break;
                        }
                    }
                }
                else if (selection1 == 2) {
                    for(int i = 0; i < ship.getSize(); i++){
                        if (hoanhdo + i > 10 || (player.getBoard()[hoanhdo + i][tungdo]).equals("\uD83C\uDF0A") == false) {
                            Template.enterAgain();
                            check = false;
                            break;
                        }
                    }
                }
                if (check == false) continue;

                if (selection1 == 1) {
                    for(int i = 0; i < ship.getSize(); i++)
                        player.setBoard(ship.getSymbol(), hoanhdo, tungdo + i);
                }
                else {
                    for(int i = 0; i < ship.getSize(); i++)
                        player.setBoard(ship.getSymbol(), hoanhdo + i, tungdo);
                }
                break;
            }
        }
    }

    private static boolean checkToaDo(int sx, Ship ship, Player player,int x, int y) {
        if (x > 10 || y > 10 || x < 1 || y < 1) return false;
        if (sx == 0){
            for(int i = 0; i < ship.getSize(); i++) {
                if (y + i > 10 || player.getBoard()[x][y+i].equals("\uD83C\uDF0A") == false) {
                    return false;
                }
            }
        }
        else{
            for(int i = 0; i < ship.getSize(); i++) {
                if (x + i > 10 || player.getBoard()[x + i][y].equals("\uD83C\uDF0A") == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void randomPlaceShip(Player player) {
        for(int i = 0; i < ListOfShips.list.size(); i++)
        {
            while(true){
                Random generator = new Random();
                int sx = generator.nextInt(2) ;

                int x = generator.nextInt(10) + 1;
                int y = generator.nextInt(10) + 1;
                if (checkToaDo(sx, ListOfShips.list.get(i), player, x, y) == false) continue;
                else {
                    if (sx == 0) {
                        for(int j = 0; j < ListOfShips.list.get(i).getSize(); j++) {
                            player.setBoard(ListOfShips.list.get(i).getSymbol(), x, y + j);
                        }
                    }
                    else {
                        for(int j = 0; j < ListOfShips.list.get(i).getSize(); j++) {
                            player.setBoard(ListOfShips.list.get(i).getSymbol(), x + j, y);
                        }
                    }
                    break;
                }
            }
        }

    }
}
