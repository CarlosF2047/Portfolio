// Author: Carlos Fuentes
// Course: ITSE 2417
// Program Set 1
// References: None
package rpslscf;

import java.util.Scanner;
import java.util.Random;

public class RPSLSCF {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        int wins = 0;
        int losses = 0;
        int comWins = 0;
        int ties = 0;
        int player;

        int input;
        String com_weapon;
        String player_weapon;

        System.out.println("Welcome! The best 2 of 3 games wins!\n");

        while (wins <= 1 && losses <= 1) {
            System.out.print("Choose: \nRock (1) \nPaper (2) \nScissors (3) \nlizard (4) \nSpock! (5):\n");
            input = scan.nextInt();
            player = input;

            switch (player) {
                case 1:
                    player_weapon = "Rock";
                    break;
                case 2:
                    player_weapon = "Paper";
                    break;
                case 3:
                    player_weapon = "Scissors";
                    break;
                case 4:
                    player_weapon = "Lizard";
                    break;
                default:
                    player_weapon = "Spock!";
                    break;
            }

            int com = rand.nextInt(5) + 1;
            switch (com) {
                case 1:
                    com_weapon = "Rock";
                    break;
                case 2:
                    com_weapon = "Paper";
                    break;
                case 3:
                    com_weapon = "Scissors";
                    break;
                case 4:
                    com_weapon = "Lizard";
                    break;
                default:
                    com_weapon = "Spock!";
                    break;
            }
            
            int i = 0;
            String message;

            if ((player == 3 && com == 2)||(player == 2 && com == 3)) {
                i = 1;
            }
            if ((player == 2 && com == 1)||(player == 1 && com == 2)) {
                i = 2;
            }
            if ((player == 1 && com == 4)||(player == 4 && com == 1)) {
                i = 3;
            }
            if ((player == 4 && com == 5)||(player == 5 && com == 4)) {
                i = 4;
            }
            if ((player == 5 && com == 3)||(player == 3 && com == 5)) {
                i = 5;
            }
            if ((player == 3 && com == 4)||(player == 4 && com == 3)) {
                i = 6;
            }
            if ((player == 4 && com == 2)||(player == 2 && com == 4)) {
                i = 7;
            }
            if ((player == 2 && com == 5)||(player == 5 && com == 2)) {
                i = 8;
            }
            if ((player == 5 && com == 1)||(player == 1 && com == 5)) {
                i = 9;
            }
            if ((player == 1 && com == 3)||(player == 1 && com == 3)) {
                i = 10;
            }
            
            switch (i) {
                case 1:
                    message = "(Scissors CUTS Paper)";
                    break;
                case 2:
                    message = "(Paper COVERS Rock)";
                    break;
                case 3:
                    message = "(Rock CRUSHES Lizard)";
                    break;
                case 4:
                    message = "(Lizard POISONS Spock)";
                    break;
                case 5:
                    message = "(Spock SMASHES Scissors)";
                    break;
                case 6:
                    message = "(Scissors DECAPITATES Lizard)";
                    break;
                case 7:
                    message = "(Lizard EATS Paper)";
                    break;
                case 8:
                    message = "(Paper DISPROVES Spock)";
                    break;
                case 9:
                    message = "(Spock VAPORIZES Rock)";
                    break;
                case 10:
                    message = "(Rock CRUSHES Scissors)";
                    break;
                default:
                    message = "INVALID";
            }

            if ((player == 3 && com == 2)
                    || (player == 2 && com == 1)
                    || (player == 1 && com == 4)
                    || (player == 4 && com == 5)
                    || (player == 5 && com == 3)
                    || (player == 3 && com == 4)
                    || (player == 4 && com == 2)
                    || (player == 2 && com == 5)
                    || (player == 5 && com == 1)
                    || (player == 1 && com == 3)) {
                System.out.println("The Computer is " + com_weapon + ". " + "You are " + player_weapon + message + "- You Win!");
                wins++;
            } else if (player == com) {
                System.out.println("The Computer is " + com_weapon + ". " + "You are also " + player_weapon + "- It is a draw!");
                ties++;
            } else {
                System.out.println("The Computer is " + com_weapon + ". " + "You are " + player_weapon + message + "- You Lose!");
                losses++;
                comWins++;
            }

            System.out.println("-------------------------------------------");

        }

        System.out.println("Wins: " + wins + "\nLosses: " + losses + "\nTies: " + ties);
        if (wins > comWins) {
            System.out.println("Congratulations- You beat the computer: " + wins + " games to " + losses);
        } else {
            System.out.println("Too bad- You lost to the computer: " + losses + " games to " + wins);
        }

    }

}
