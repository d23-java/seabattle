package GamePlay;

import PlayerActivity.Fire;
import PlayerActivity.ManagerPlayer;

public class Game {
    private ManagerPlayer managerPlayer;
    private Integer turn;
    private Fire firee;
    public Game(){
        managerPlayer = new ManagerPlayer();
        turn = 1;
        firee = new Fire();
    }

    public void initTwoPlayer(){
        System.out.println("Enter data of Player 1:");
        managerPlayer.initPlayer1();
        managerPlayer.displayPlayer1Board();
        System.out.println("Enter data of Player 2:");
        managerPlayer.initPlayer2();
        managerPlayer.displayPlayer2Board();
    }

    public void fire(){
        firee.attack(turn);
    }
}