package system;

import main.Game;

import java.util.Random;

public class RandomCase {
    static Random random = new Random();

    public static int randomNumber(int minNum, int maxNum) {
        int randomInRange = random.nextInt(maxNum - minNum + 1) + minNum;
        return randomInRange;
    }

    public static char randomChar(char minChar, char maxChar) {
        int randomInRange = random.nextInt(maxChar - minChar + 1) + minChar;
        return ((char)randomInRange);
    }

    public static char randomDir() {
        int randomInRange = random.nextInt(2);
        if(randomInRange == 1)
            return 'H';
        return 'V';
    }
}
