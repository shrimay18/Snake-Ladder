import java.util.Map;

public class ExtraTurnRule implements Rule {
    private final int numDice;

    public ExtraTurnRule(int numDice) {
        this.numDice = numDice;
    }

    @Override
    public RuleResult apply(Player player, int roll, Board board, Map<Player, Integer> positions) {
        int newPos = positions.get(player) + roll;
        if (roll == numDice * 6) {
            System.out.println(player.getName() + " rolled all sixes! Gets an extra turn.");
            return new RuleResult(newPos, true);
        }
        return new RuleResult(newPos, false);
    }
}
