package GamePlay;

import java.util.Scanner;
import PlayerActivity.CheckInit;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        Game game = new Game();
        CheckInit checkInit = new CheckInit();
        ClearTerminal clearTerminal = new ClearTerminal();
        clearTerminal.clear(0);
        System.out.println("\u001B[35m" + "+==========================================================+");
        System.out.println("| ____  _____    _      ____    _  _____ _____ _     _____ |");
        System.out.println("|/ ___|| ____|  / \\    | __ )  / \\|_   _|_   _| |   | ____||");
        System.out.println("|\\___ \\|  _|   / _ \\   |  _ \\ / _ \\ | |   | | | |   |  _|  |");
        System.out.println("| ___) | |___ / ___ \\  | |_) / ___ \\| |   | | | |___| |___ |");
        System.out.println("||____/|_____/_/   \\_\\ |____/_/   \\_|_|   |_| |_____|_____||");
        System.out.println("+==========================================================+" + "\u001B[0m");
        menu.start();
        boolean check = false;
        String chooseString = "";
        int choose = 0;
        while(!check){
            System.out.print("Enter your choice: ");
            chooseString = sc.nextLine().trim();
            if(!checkInit.checkInt(chooseString, 1, 1)){
                System.out.println("Invalid input. Try again.");
                continue;
            }
            choose = Integer.parseInt(chooseString);
            if(choose <= 2 && choose >= 1)  check = true;
            if(!check) System.out.println("Invalid Choice. Please try again");
        }
        if (choose == 1) {
            clearTerminal.clear(0);
            //GAME ON
            System.out.println("\u001B[32m"+"+==========================================+");
            System.out.println("|  ____    _    __  __ _____    ___  _   _ |");
            System.out.println("| / ___|  / \\  |  \\/  | ____|  / _ \\| \\ | ||");
            System.out.println("|| |  _  / _ \\ | |\\/| |  _|   | | | |  \\| ||");
            System.out.println("|| |_| |/ ___ \\| |  | | |___  | |_| | |\\  ||");
            System.out.println("| \\____/_/   \\_|_|  |_|_____|  \\___/|_| \\_||");
            System.out.println("+==========================================+"+"\u001B[0m");
            game.initTwoPlayer();
            game.fire();
        }
        else{
            //GAME OVER
            System.out.println("\u001B[33m"+"+=======================================================+");
            System.out.println("|  ____    _    __  __ _____    _____     _______ ____  |");
            System.out.println("| / ___|  / \\  |  \\/  | ____|  / _ \\ \\   / | ____|  _ \\ |");
            System.out.println("|| |  _  / _ \\ | |\\/| |  _|   | | | \\ \\ / /|  _| | |_) ||");
            System.out.println("|| |_| |/ ___ \\| |  | | |___  | |_| |\\ V / | |___|  _ < |");
            System.out.println("| \\____/_/   \\_|_|  |_|_____|  \\___/  \\_/  |_____|_| \\_\\|");
            System.out.println("+=======================================================+"+"\u001B[0m");
        }
    }
}