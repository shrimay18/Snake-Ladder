import java.util.List;

public class SnakeLadderRule implements Rule {
    @Override
    public boolean apply(Player player, Board board, RollResult rollResult, List<Player> players) {
        int currentPos = board.getLinearPos(player.getRow(), player.getCol());
        for (Entities e : board.getEntities()) {
            if (e.getStart() == currentPos) {
                System.out.println(player.getName() + " hit " + e.getId() + " → moving to " + e.getEnd());
                int[] rc = board.getRowCol(e.getEnd());
                player.setPosition(rc[0], rc[1]);
                break;
            }
        }
        return false;
    }
}
