import java.util.Map;

public class SnakeLadderRule implements Rule {
    @Override
    public RuleResult apply(Player player, int roll, Board board, Map<Player, Integer> positions) {
        int currentPos = positions.get(player);
        int newPos = currentPos + roll;

        Entities e = board.getEntityAt(newPos);
        if (e != null) {
            if (e instanceof Snake)
                System.out.println(player.getName() + " bitten by " + e.getName() + "! Moves down to " + e.getEnd());
            else
                System.out.println(player.getName() + " climbed " + e.getName() + "! Moves up to " + e.getEnd());
            newPos = e.getEnd();
        }

        return new RuleResult(newPos, false);
    }
}
