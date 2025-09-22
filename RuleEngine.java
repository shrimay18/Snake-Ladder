import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RuleEngine {
    private final List<Rule> rules;

    public RuleEngine() {
        this.rules = new ArrayList<>();
    }

    
    public void addRule(Rule rule) {
        rules.add(rule);
    }

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
