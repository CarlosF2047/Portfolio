// Author: Carlos Fuentes
// Course: ITSE 2417
// Program Set 1
// References: None
package hotorcoldcf;

import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class HotOrColdCF {

    public static void main(String[] args) {

        boolean playing = true;

        while (playing) {
            Random random = new Random();
            Scanner scan = new Scanner(System.in);
            int guess = 0;
            int lastGuess = 0;

            int number = random.nextInt(100);
            int guesses = 1;

            while (guess != number && guess != -1) {

                System.out.print("Enter your guess (1-100 or -1 to quit): ");

                guess = scan.nextInt();

                int lastGuessD = number - lastGuess;
                int currentD = number - guess;

                if (guess != -1) {
                    if (guess != number && Math.abs(lastGuessD) < Math.abs(currentD)) {
                        System.out.println("Colder.");
                    } else if (guess != number && Math.abs(lastGuessD) > Math.abs(currentD)) {
                        System.out.println("Warmer");
                    } else if (guess == lastGuess) {
                        System.out.println("Same");
                    } else if (guess == number) {
                        System.out.println("Found, the number was " + number);
                        System.out.println("Number of guesses: " + guesses);
                    }
                }
                lastGuess = guess;
                guesses++;
            }

            System.out.println("Play again? Y/N");
            String answer = scan.next();
            if (answer.equalsIgnoreCase("y") == true) {
                playing = true;
            } else {
                playing = false;
            }

        }
    }
}
