public class RuleResult {
    private final int newPosition;
    private final boolean extraTurn;

    public RuleResult(int newPosition, boolean extraTurn) {
        this.newPosition = newPosition;
        this.extraTurn = extraTurn;
    }

    public int getNewPosition() { return newPosition; }
    public boolean hasExtraTurn() { return extraTurn; }
}
