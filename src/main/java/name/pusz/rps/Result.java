package name.pusz.rps;

import java.util.Random;

public enum Result {

    LOSE,
    DRAW,
    WIN;

    public static Result getRandomResult(boolean rngBlessingOn) {
        double[] playerWinDrawLoseChances;
        if (rngBlessingOn) {
            playerWinDrawLoseChances = new double[]{25 / 100., 25 / 100., 50 / 100.};
        } else {
            playerWinDrawLoseChances = new double[]{1 / 3., 1 / 3., 1 / 3.};
        }

        double random = new Random().nextDouble();

        if (random < playerWinDrawLoseChances[0]) {
            return WIN;
        } else if (random < playerWinDrawLoseChances[0] + playerWinDrawLoseChances[1]) {
            return DRAW;
        } else {
            return LOSE;
        }
    }
}
