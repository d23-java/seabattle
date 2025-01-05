package main;

<<<<<<< HEAD
import game.ShowBoard;
import template.Template;
import game.GameManager;
=======
import template.Template;
import player.GameManager;
>>>>>>> c3c8aaf3e3b14f5686a0ca6104018351027dc829
import data.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Template.printSeaBattle();
        GameManager Game = new GameManager();
<<<<<<< HEAD
        FileRank.loadFromFile();
        //State.loadFromFile();
        //ShowBoard.showBoards(State.player1);
=======
        Bxh.loadFromFile();
>>>>>>> c3c8aaf3e3b14f5686a0ca6104018351027dc829
        while(true){
            Template.showGameMenu();
            int selection = Integer.parseInt(system.scanner.nextLine());
            switch(selection){
<<<<<<< HEAD
                case 1 : FileRank.showRank(); break;
                case 2 : Game.start(); break;
                case 3 : FileRank.saveToFile(); return;
=======
                case 1 : Bxh.showRank(); break;
                case 2 : Game.start(); break;
                case 3 : Bxh.saveToFile(); return;
>>>>>>> c3c8aaf3e3b14f5686a0ca6104018351027dc829
                default : Template.enterAgain();
            }
        }
    }
}
