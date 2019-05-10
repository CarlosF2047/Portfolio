// Author: Carlos Fuentes
// Course: ITSE 2417
// Program Set 1
// References: None
package countdollarscf;

import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class CountDollarsCF {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Enter file path.");
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        File file = new File("src/" + input);
        
        try (final Scanner data = new Scanner(file)) {
            final int lines = data.nextInt();
            final Collection<Integer> totalPerLine = new ArrayList<>(lines);

            for (int i = 0; i < lines; i++) {
                int sum = 0;

                for (int j = 0; j < 13; j++) {
                    final int value = data.nextInt();
                    sum += getDollars(j, value);
                }

                totalPerLine.add(sum);
                System.out.println("Line " + (i+1) + ": $" + sum);
            }
        }
    }

    private static double getDollars(
            final int type,
            final int value) {
        switch (type) {
            case 0: // Penny
                return value / 100D;
            case 1: // Nickle
                return value / 20D;
            case 2: // Dime
                return value / 10D;
            case 3: // Quarter
                return value / 4D;
            case 4: // Half
                return value / 2D;
            case 5: // Dollar coin
            case 6: // Dollar bill
                return value;
            case 7: // Two dollars bill
                return value * 2D;
            case 8: // Five dollars bill
                return value * 5D;
            case 9: // Ten dollars bill
                return value * 10D;
            case 10: // Twenty dollars bill
                return value * 20D;
            case 11: // Fifty dollars bill
                return value * 50D;
            case 12: // A hundred dollars bill
                return value * 100D;
            default:
                return 0;
        }
    }

}
