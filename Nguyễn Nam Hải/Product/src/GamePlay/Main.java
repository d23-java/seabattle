package GamePlay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        Game game = new Game();
        menu.start();
        boolean check = false;
        int choose = 0;
        while(!check){
            System.out.print("Enter your choice: ");
            choose = Integer.parseInt(sc.nextLine());
            if(choose <= 2 && choose >= 1)  check = true;
            if(!check) System.out.println("Invalid Choice. Please try again");
        }
        if(choose == 1){
            System.out.println("=====GAME ON=====");
            game.initTwoPlayer();
            game.fire();
        }
        else{
            System.out.println("====GAME OVER====");
        }
    }
}