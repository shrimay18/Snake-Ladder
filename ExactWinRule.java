import java.util.List;

public class ExactWinRule implements Rule {
    @Override
    public boolean apply(Player player, Board board, RollResult rollResult, List<Player> players) {
        int finalCell = board.getSize() * board.getSize();
        int prevPos = rollResult.getPrevPos();
        if (prevPos + rollResult.getSum() > finalCell) {
            System.out.println(player.getName() + " needs an exact roll to win. Staying at " + prevPos);
            
            int[] rc = board.getRowCol(prevPos);
            player.setPosition(rc[0], rc[1]);
        }
        return false;
    }
}
