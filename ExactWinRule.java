import java.util.Map;

public class ExactWinRule implements Rule {
    private final int finalCell;

    public ExactWinRule(int finalCell) {
        this.finalCell = finalCell;
    }

    @Override
    public RuleResult apply(Player player, int rollResult, Board board, Map<Player, Integer> positions) {
        int currentPos = positions.get(player);
        int newPos = currentPos + rollResult;

        if (newPos > finalCell) {
            System.out.println(player.getName() + " cannot move, needs exact roll to win.");
            newPos = currentPos; // stay in same place
        }
        return new RuleResult(newPos, false);
    }
}
