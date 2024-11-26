package main;

import template.Template;
import player.GameManager;
import data.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Template.printSeaBattle();
        GameManager Game = new GameManager();
        Bxh.loadFromFile();
        while(true){
            Template.showGameMenu();
            int selection = Integer.parseInt(system.scanner.nextLine());
            switch(selection){
                case 1 : Bxh.showRank(); break;
                case 2 : Game.start(); break;
                case 3 : Bxh.saveToFile(); return;
                default : Template.enterAgain();
            }
        }
    }
}
