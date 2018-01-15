package name.pusz.rps;

import java.util.Arrays;
import java.util.Scanner;

import static name.pusz.rps.Move.*;

public class Commander {

    private static Commander ourInstance = new Commander();
    private boolean close = false;
    private boolean newGame = true;
    private Scanner sc = new Scanner(System.in);
    private RpsGame rpsGame;
    private String playerName;
    private int wonRoundsToPlay;

    public static Commander getInstance() {
        return ourInstance;
    }

    private Commander() {
    }

    public boolean close() {
        return close;
    }

    public boolean newGame() {
        return newGame;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void displayMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1 - Rock\n");
        stringBuilder.append("2 - Paper\n");
        stringBuilder.append("3 - Scissors\n");
        stringBuilder.append("4 - Lizard\n");
        stringBuilder.append("5 - Spock\n");
        stringBuilder.append("n - New game \n");
        stringBuilder.append("x - Exit");
        System.out.println(stringBuilder.toString());
    }

    public void doCommand(String command) {
        if (command.equals("1")) {
            rpsGame.playRound(ROCK);
        } else if (command.equals("2")) {
            rpsGame.playRound(PAPER);
        } else if (command.equals("3")) {
            rpsGame.playRound(SCISSORS);
        } else if (command.equals("4")) {
            rpsGame.playRound(LIZARD);
        } else if (command.equals("5")) {
            rpsGame.playRound(SPOCK);
        } else if (command.equals("n")) {
            System.out.print("Are you sure you want begin new game? (Y/n) ");
            String choice = sc.nextLine().trim().toLowerCase();
            String[] confirmations = {"yes", "y", "yeah", "sure", "why not"};
            if (Arrays.asList(confirmations).contains(choice)) {
                newGame = true;
            } else {
                displayMenu();
            }
        } else if (command.equals("x")) {
            System.out.print("Please confirm quiting the game. (Y/n) ");
            String choice = sc.nextLine().trim().toLowerCase();
            String[] confirmations = {"yes", "y", "yeah", "sure", "why not"};
            if (Arrays.asList(confirmations).contains(choice)) {
                close = true;
            } else {
                displayMenu();
            }
        } else {
            System.out.println("\"" + command + "\" is not recognized as a valid command.");
        }

        if (Arrays.asList("1", "2", "3", "4", "5").contains(command) && wonRoundsToPlay > rpsGame.getRoundsWon()) {
            rpsGame.displayRoundResult();
            displayMenu();
        }
    }

    public void startNewGame() {
        newGame = false;
        rpsGame = new RpsGame();
        String scannerLine;
        displayMenu();
        while (wonRoundsToPlay > rpsGame.getRoundsWon() && !newGame && !close) {
            scannerLine = sc.nextLine().trim();
            doCommand(scannerLine);
        }
        rpsGame.displayGameResult();
    }

    public void askNameAndWonRoundsQuantity() {
        String scanerLine;
        System.out.print("Enter your name: ");
        while ((scanerLine = sc.nextLine().trim()).isEmpty()) {
        }
        playerName = scanerLine;
        System.out.print("Hello " + playerName + ". Please enter limit of won rounds: ");
        do {
            try {
                wonRoundsToPlay = Integer.valueOf(sc.nextLine().trim());
                if (!(wonRoundsToPlay > 0)) {
                    System.out.print("Wrong input! Enter again: ");
                }
            } catch (Exception e) {
                System.out.print("Wrong input! Enter again: ");
            }
        }
        while (!(wonRoundsToPlay > 0));
    }

    public void checkIfContinue() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("n - New game \n");
        stringBuilder.append("x - Exit");
        String menu = stringBuilder.toString();
        String scannerLine;
        do {
            System.out.println(menu);
            scannerLine = sc.nextLine().trim();
            if (scannerLine.equals("n")) {
                newGame = true;
            } else if (scannerLine.equals("x")) {
                close = true;
            } else {
                System.out.println("\"" + scannerLine + "\" is not recognized as a valid command.");
            }
        }
        while (!newGame && !close);
    }
}
