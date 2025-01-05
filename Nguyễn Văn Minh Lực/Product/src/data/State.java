package data;

import game.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class State {
    public static Player player1, player2;
    static int kichThuoc;

    public static void loadFromFile() throws IOException {
        player1 = new Player();
        player2 = new Player();
        File f = new File("state.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        while(true) {
            String size = br.readLine();
            if (size == null) { break;}
            kichThuoc = Integer.parseInt(size);
            String name = br.readLine();
            player1.setName(name);
            for(int i = 1; i <= kichThuoc; i++) {
                String line = br.readLine();
                for(int j = 1; j <= kichThuoc; j++) {
                    String s = new String("" + line.charAt(0));
                    player1.setBoard(s,i,j);
                }
            }
            break;
        }
        br.close(); fr.close();
    }
}
