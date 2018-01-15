package name.pusz.rps;

public class RpsRunner {

    public static void main(String[] args) {
        Commander commander = Commander.getInstance();
        commander.askNameAndWonRoundsQuantity();
        do {
            commander.startNewGame();
            if (commander.close()) break;
            if (commander.newGame()) continue;
            commander.checkIfContinue();
        }
        while (!commander.close());
        System.out.println("Good bye " + commander.getPlayerName() + "!");
    }
}
