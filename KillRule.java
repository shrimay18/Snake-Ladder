import java.util.List;

public class KillRule implements Rule {
    @Override
    public boolean apply(Player player, Board board, RollResult rollResult, List<Player> players) {
        int playerPos = board.getLinearPos(player.getRow(), player.getCol());
        for (Player other : players) {
            if (other == player) continue;
            int otherPos = board.getLinearPos(other.getRow(), other.getCol());
            if (otherPos == playerPos) {
                System.out.println(player.getName() + " landed on " + other.getName()
                        + " — sending " + other.getName() + " back to start.");
                other.setPosition(0, 0);
            }
        }
        return false;
    }
}
