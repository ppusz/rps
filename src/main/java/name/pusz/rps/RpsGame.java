package name.pusz.rps;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static name.pusz.rps.Result.DRAW;
import static name.pusz.rps.Result.LOSE;
import static name.pusz.rps.Result.WIN;

class RpsGame {

    private final List<Round> rounds = new LinkedList<>();

    public void playRound(Move playerMove) {
        Result playerResult = Result.getRandomResult(true);
        Move computerMove = Move.getComputerMove(playerMove, playerResult);
        Round round = new Round(playerMove, computerMove, playerResult);
        rounds.add(round);

        System.out.print("Player: " + playerMove + "\t computer: " + computerMove + "\t ");
        if (playerResult == WIN) {
            System.out.println("You win!");
        } else if (playerResult == LOSE) {
            System.out.println("You lose!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    public void displayRoundResult() {
        Map results = getResults();
        System.out.println("Results after round:\twins: " + results.get(WIN) + "\t\tdraws: " + results.get(DRAW) + "\tlosses: " + results.get(LOSE) + "\n");
    }

    public void displayGameResult() {
        Map<Result, Integer> results = getResults();
        StringBuilder sb = new StringBuilder();
        sb.append("Game over.\nFinal results:\nwins: " + results.get(WIN) + "\t\tdraws: " + results.get(DRAW) + "\tlosses: " + results.get(LOSE) + "\n");
        if (results.get(WIN) > results.get(LOSE)) {
            sb.append("You won the game!");
        } else if (results.get(WIN) < results.get(LOSE)) {
            sb.append("You lost the game!");
        } else {
            sb.append("It's a draw!");
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    public int getRoundsWon() {
        return Math.max(getResults().get(WIN), getResults().get(LOSE));
    }

    private Map<Result, Integer> getResults() {
        int wins = 0;
        int draws = 0;
        int losses = 0;

        for (Round round : rounds) {
            if (round.getResult() == WIN) {
                wins++;
            } else if (round.getResult() == LOSE) {
                losses++;
            } else {
                draws++;
            }
        }

        Map<Result, Integer> results = new HashMap<>();
        results.put(WIN, wins);
        results.put(DRAW, draws);
        results.put(LOSE, losses);

        return results;
    }
}
