package name.pusz.rps;

public final class Round {

    private final Move player;
    private final Move computer;
    private final Result result;

    public Round(final Move player, final Move computer, final Result result) {
        this.player = player;
        this.computer = computer;
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "You: " + player + " \t computer: " + computer + " result: " + result;
    }
}
