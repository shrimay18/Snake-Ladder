import java.util.Map;

public class KillRule implements Rule {

    @Override
    public RuleResult apply(Player player, int rollResult, Board board, Map<Player, Integer> positions) {
        int newPos = positions.get(player) + rollResult;

        // Check if any other player is on the same cell
        for (Map.Entry<Player, Integer> entry : positions.entrySet()) {
            Player other = entry.getKey();
            if (!other.equals(player) && entry.getValue() == newPos) {
                System.out.println(player.getName() + " landed on " + other.getName() + "! " +
                        other.getName() + " goes back to start.");
                positions.put(other, 0);
                other.setPosition(0);
            }
        }
        return new RuleResult(newPos, false);
    }
}
