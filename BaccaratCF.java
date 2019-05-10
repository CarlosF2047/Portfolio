//Carlos Fuentes
//03/25/2019
// Course: ITSE 2417
// Program 5
// References: help file

import java.util.Scanner;

public class BaccaratCF {

    public static void main(String[] args) {
        boolean playing = true;
        balance newBalance = new balance();
        newBalance.setBalance(500);
        bet newBet = new bet();

        while (playing) {

            playerObjects newObjects = new playerObjects();
            deck newDeck = new deck();


            while (newBalance.getBalance() != 0) {
                newObjects.setPlayersCards();
                newObjects.setComputersCards();

                //*********Players hand************
                System.out.print("Players cards : ");
                newDeck.showCards(newObjects.getPlayersHand());
                System.out.println();
                System.out.print("Players points : " + newObjects.mainPoints(newObjects.getPlayersHand()));
                System.out.println();
                System.out.println();

                //*********Dealers hand************
                System.out.print("Dealers cards : ");
                newDeck.showCards(newObjects.getComputerHand());
                System.out.println();
                System.out.print("Dealers points : " + newObjects.mainPoints(newObjects.getComputerHand()));
                System.out.println();
                System.out.println();

                if (newObjects.setThirdCard() == true) {
                    newBalance.showBalance();
                    System.out.print("\nPlayers cards : ");
                    newDeck.showCards(newObjects.getPlayersHand());
                    System.out.println();
                    System.out.print("Players points : " + newObjects.mainPoints(newObjects.getPlayersHand()));
                    System.out.print("\n\nDealers cards : ");
                    newDeck.showCards(newObjects.getComputerHand());
                    System.out.println();
                    System.out.print("Dealers points : " + newObjects.mainPoints(newObjects.getComputerHand()));
                    System.out.println("\nPlease make a bet (must be over $0)");
                    Scanner scan = new Scanner(System.in);
                    int betAmount = scan.nextInt();
                    if (betAmount <= 0) {
                        System.out.println("Invalid Bet, must be over $0");
                    }
                    newBet.setBet(betAmount);
                }

                //Print out the winner
                if (newObjects.showTheWinner() == 'T') {
                    newBalance.increaseBalance(newBet.getBet());
                    newBalance.showBalance();
                } else if (newObjects.showTheWinner() == 'F') {
                    newBalance.decreaseBalance(newBet.getBet());
                    newBalance.showBalance();
                }else if (newObjects.showTheWinner() == 'N')
                    newObjects.showTheWinner();
                    newBalance.showBalance();
            }

            System.out.println("Keep Playing? y/n");
            Scanner scan = new Scanner(System.in);
            String answer = scan.next();
            if (answer.equalsIgnoreCase("y") == true) {
                playing = true;
            } else {
                playing = false;
            }
        }


    }
}

//Balance class
class balance {

    int initBalance;

    public void setBalance(int updBalance) {
        initBalance = updBalance;
    }

    public int getBalance() {
        return initBalance;
    }

    public void increaseBalance(int bet) {
        int newBalance;
        int oldBalance = getBalance();
        newBalance = oldBalance + bet;
        setBalance(newBalance);
        System.out.println("You won " + bet);
    }

    public void decreaseBalance(int bet) {
        int newBalance;
        int oldBalance = getBalance();
        newBalance = oldBalance - bet;
        setBalance(newBalance);
        System.out.println("You lost " + bet);
    }

    public void showBalance() {
        System.out.println("\nCurrent Balance: " + getBalance());
    }
}

//Bet class
class bet {

    int bet;

    public void setBet(int betAmount) {
        this.bet = betAmount;
    }

    public int getBet() {
        return bet;
    }
}


//Players class
class playerObjects {

    //Initialize arrays and points variable
    private String[] playersHand = new String[3];
    private String[] computersHand = new String[3];
    int totalPoints = 0;


    //Create objects to access specific method
    deck newDeck = new deck();

    //******PLAYER*********
    public void setPlayersCards() {

        playersHand[0] = newDeck.getCards();
        if (playersHand[1] == null)
            playersHand[1] = newDeck.getCards();
    }

    public String[] getPlayersHand() {

        return playersHand;
    }

    //******Third card method*************
    public boolean setThirdCard() {
        boolean thirdCard = false;

        int pCard = mainPoints(getPlayersHand());
        int cCard = mainPoints(getComputerHand());
        //***Find the winner based on points****
        if ((pCard < 8) && (cCard < 8)) {
            if (pCard <= 5) {
                playersHand[2] = newDeck.getCards();
                thirdCard = true;
            }
            if (cCard < 3) {
                computersHand[2] = newDeck.getCards();
                thirdCard = true;
            }
            if ((cCard == 3) && (mainPoints(getPlayersHand()) != 8)) {
                computersHand[2] = newDeck.getCards();
                thirdCard = true;
            }
            if ((cCard == 6) && (mainPoints(getPlayersHand()) >= 6)) {
                computersHand[2] = newDeck.getCards();
                thirdCard = true;
            }
            if ((cCard == 5) && (mainPoints(getPlayersHand()) >= 4)) {
                computersHand[2] = newDeck.getCards();
                thirdCard = true;
            }
            if ((cCard == 4) && (mainPoints(getPlayersHand()) >= 2)) {
                computersHand[2] = newDeck.getCards();
                thirdCard = true;
            }
        }
        return thirdCard;
    }

    //*********COMPUTER***********
    public void setComputersCards() {

        computersHand[0] = newDeck.getCards();
        if (computersHand[1] == null)
            computersHand[1] = newDeck.getCards();
    }

    //*****Get computer hand method******
    public String[] getComputerHand() {

        return computersHand;
    }

    //******Main Points method************
    public int mainPoints(String[] theirPoints) {

        totalPoints = 0;
        for (int i = 0; i < theirPoints.length; i++) {
            if (theirPoints[i] != null) {
                totalPoints += newDeck.getCardValues(theirPoints[i]);
            } else
                continue;
        }
        totalPoints = (totalPoints % 10);
        return totalPoints;
    }

    //******Showing the final winner********
    public char showTheWinner() {

        int xp = mainPoints(getPlayersHand());
        int yc = mainPoints(getComputerHand());
        char win;

        if (xp == yc) {
            System.out.print("It's a tie!");
            win = 'N';
            System.out.println("----------------------\n");
        } else if (xp > yc) {
            System.out.print("Player wins!\n");
            win = 'T';
            System.out.println("----------------------\n");
        } else {
            System.out.print("Dealer wins!\n");
            win = 'F';
            System.out.println("----------------------\n");
        }
        return win;
    }
}

//Cards class
class deck {

    private String[] cards = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
    private int cardCount = -1;
    private boolean isShuffled = false;

    //**********Shuffle cards method****************************
    private void shuffleCards() {//shuffle cards method

        for (int i = 0; i < cards.length; i++) {
            int index = (int) (Math.random() * 13);
            String temp = cards[i];
            cards[i] = cards[index];
            cards[index] = temp;
        }
        isShuffled = true;
    }

    //********Ensure the cards get shuffled before dealing******
    public String getCards() {
        if(cardCount > 11){
            cardCount = -1;
            shuffleCards();
        }

        if (isShuffled == true) {
            cardCount++;
            return cards[cardCount];
        } else {
            shuffleCards();//shuffle if they have not
            cardCount++;
            return cards[cardCount];
        }
    }

    //********Show cards method*********************************
    public void showCards(String[] theirCards) {

        for (int i = 0; i < theirCards.length; i++) {
            if (theirCards[i] == null) {
                continue;
            }
            System.out.print(theirCards[i] + " ");
        }
    }

    //*******Card value method**********************************
    public int getCardValues(String tempChar) {

        int indValues = 1;

        switch (tempChar) {
            case "A":
                indValues = 1;
                break;
            case "K":
                indValues = 0;
                break;
            case "Q":
                indValues = 0;
                break;
            case "J":
                indValues = 0;
                break;
            case "10":
                indValues = 0;
                break;
            case "9":
                indValues = 9;
                break;
            case "8":
                indValues = 8;
                break;
            case "7":
                indValues = 7;
                break;
            case "6":
                indValues = 6;
                break;
            case "5":
                indValues = 5;
                break;
            case "4":
                indValues = 4;
                break;
            case "3":
                indValues = 3;
                break;
            case "2":
                indValues = 2;

        }
        return indValues;
    }
}