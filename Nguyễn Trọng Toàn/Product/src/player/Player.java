package player;

import bang.Bang;

import java.util.Scanner;

public class Player {
    private Bang bang;
    public Player(Bang bang){
        this.bang=bang;
    }
    private String name;
    Scanner scanner = new Scanner(System.in);
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int xuli(char s1,char s2){
        int x=0;
        x=(s1-'0')*10+(s2-'0');
        return x;
    }
//    public void kiemtradattau(int x1,int y1,int x2,int y2){
//        if(x1==x2){
//            for(int i=y1-1;i<y2;++i){
//                if
//            }
//        }
//    }
    public void datthuyen(){
        System.out.println("bạn hãy đặt 2 thuyền tuần tra(1*2)");
        String x,y;
        for(int k=0;k<2;++k) {
            x = scanner.nextLine();
            y = scanner.nextLine();
            int x1 = x.charAt(0) - 'a';
            int y1 = (x.length() <= 2) ? x.charAt(1) - '0' : xuli(x.charAt(1), x.charAt(2));
            int x2 = y.charAt(0) - 'a';
            int y2 = (y.length() <= 2) ? y.charAt(1) - '0' : xuli(y.charAt(1), y.charAt(2));
            if (x1 == x2) {
                for (int i = y1-1; i < y2; ++i) {
                    bang.capNhatBang(x1, i, 'B');
                }
            } else if (y1 == y2) {
                for (int i = x1; i <= x2; ++i) {
                    bang.capNhatBang(i, y1-1, 'B');
                }
            }
            System.out.println("bạn đã đặt thuyền thành công");

        }
    }
    public void dattaukhutruc(){
        System.out.println("bạn hãy đặt 1 tàu khu trục (1*4)");
        String x,y;
        x = scanner.nextLine();
        y = scanner.nextLine();
        int x1 = x.charAt(0) - 'a';
        int y1 = (x.length() <= 2) ? x.charAt(1) - '0' : xuli(x.charAt(1), x.charAt(2));
        int x2 = y.charAt(0) - 'a';
        int y2 = (y.length() <= 2) ? y.charAt(1) - '0' : xuli(y.charAt(1), y.charAt(2));
        if (x1 == x2) {
            for (int i = y1-1; i < y2; ++i) {
                bang.capNhatBang(x1, i, 'B');
            }
        } else if (y1 == y2) {
            for (int i = x1; i <= x2; ++i) {
                bang.capNhatBang(i, y1-1, 'B');
            }
        }
    }
    public void dattaungam(){
        System.out.println("bạn hãy đặt 1 tàu ngầm (1*3)");
        String x,y;
        x = scanner.nextLine();
        y = scanner.nextLine();
        int x1 = x.charAt(0) - 'a';
        int y1 = (x.length() <= 2) ? x.charAt(1) - '0' : xuli(x.charAt(1), x.charAt(2));
        int x2 = y.charAt(0) - 'a';
        int y2 = (y.length() <= 2) ? y.charAt(1) - '0' : xuli(y.charAt(1), y.charAt(2));
        if (x1 == x2) {
            for (int i = y1-1; i < y2; ++i) {
                bang.capNhatBang(x1, i, 'B');
            }
        } else if (y1 == y2) {
            for (int i = x1; i <= x2; ++i) {
                bang.capNhatBang(i, y1-1, 'B');
            }
        }
    }
    public void datthietgiap(){
        System.out.println("bạn hãy đặt 1 thiết giáp hạm 1*4)");
        String x,y;
        x = scanner.nextLine();
        y = scanner.nextLine();
        int x1 = x.charAt(0) - 'a';
        int y1 = (x.length() <= 2) ? x.charAt(1) - '0' : xuli(x.charAt(1), x.charAt(2));
        int x2 = y.charAt(0) - 'a';
        int y2 = (y.length() <= 2) ? y.charAt(1) - '0' : xuli(y.charAt(1), y.charAt(2));
        if (x1 == x2) {
            for (int i = y1-1; i < y2; ++i) {
                bang.capNhatBang(x1, i, 'B');
            }
        } else if (y1 == y2) {
            for (int i = x1; i <= x2; ++i) {
                bang.capNhatBang(i, y1-1, 'B');
            }
        }
    }
    public void shoot(Bang bang){
        System.out.println("Hãy nhập ô bạn muốn bắn ");
        String x = scanner.nextLine();
        int x1 = x.charAt(0) - 'a';
        int y1 = (x.length() <= 2) ? x.charAt(1) - '0' : xuli(x.charAt(1), x.charAt(2));
        bang.xoaViTri(x1,y1-1);
    }

}
