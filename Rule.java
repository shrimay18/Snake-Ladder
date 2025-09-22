import java.util.Map;

public interface Rule {
    RuleResult apply(Player player, int rollResult, Board board, Map<Player, Integer> positions);
}
