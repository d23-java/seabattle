package main;

import template.Template;
import player.GameManager;

public class Main {
    public static void main(String[] args) {
        GameManager Game = new GameManager();

        while(true){
            Template.showGameMenu();
            int selection = Integer.parseInt(system.scanner.nextLine());
            switch(selection){
                case 1 : Game.showRank(); break;
                case 2 : Game.start(); break;
                case 3 : return;
            }
        }
    }
}