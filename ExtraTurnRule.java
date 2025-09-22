import java.util.List;

public class ExtraTurnRule implements Rule {
    @Override
    public boolean apply(Player player, Board board, RollResult rollResult, List<Player> players) {
        if (rollResult.isAllSixes()) {
            System.out.println(player.getName() + " rolled all sixes 🎉 — gets another turn!");
            return true;
        }
        return false;
    }
}
