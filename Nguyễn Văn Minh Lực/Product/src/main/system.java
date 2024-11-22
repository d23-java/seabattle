package main;

import java.util.Scanner;

public class system {
    public static Scanner scanner  = new Scanner(System.in);
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
