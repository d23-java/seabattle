package main;

import java.util.Scanner;

public class system {
    public static Scanner scanner  = new Scanner(java.lang.System.in);

    public static void clearScreen() {
        java.lang.System.out.print("\033[H\033[2J");
        java.lang.System.out.flush();
    }

}
