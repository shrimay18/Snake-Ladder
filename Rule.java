import java.util.List;

public interface Rule {
    boolean apply(Player player, Board board, RollResult rollResult, List<Player> players);
}
