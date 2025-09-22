import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RuleEngine {
    private final List<Rule> rules;

    public RuleEngine() {
        this.rules = new ArrayList<>();
    }

    /**
     * Add a rule to the engine
     */
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    /**
     * Apply all rules sequentially for a player
     * @param player Current player
     * @param rollResult Dice roll result
     * @param board Game board
     * @param positions Current positions of all players
     * @return The final RuleResult (position + extra turn) after applying all rules
     */
    public RuleResult applyRules(Player player, int rollResult, Board board, Map<Player, Integer> positions) {
        int currentPos = positions.get(player);
        boolean extraTurn = false;

        for (Rule rule : rules) {
            RuleResult result = rule.apply(player, rollResult, board, positions);
            currentPos = result.getNewPosition();
            if (result.hasExtraTurn()) extraTurn = true;
        }

        return new RuleResult(currentPos, extraTurn);
    }

    public List<Rule> getRules() {
        return rules;
    }
}
