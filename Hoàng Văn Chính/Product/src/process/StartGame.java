package process;

import display.StartGameScreen;

import java.util.Scanner;

public class StartGame {
    public void startGame() {
        while (true) {
            StartGameScreen.startGameScreen();
            System.out.print("y or n: ");
            String feature = ScannerInput.scanner.nextLine();
            switch (feature) {
                case "y":

                case "n":
                    return;
            }
        }
    }
}
