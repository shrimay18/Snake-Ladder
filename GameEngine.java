import java.util.List;
import java.util.Map;

public class GameEngine {
    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private final RuleEngine ruleEngine;

    public GameEngine(Board board, List<Player> players, Dice dice, RuleEngine ruleEngine) {
        this.board = board;
        this.players = players;
        this.dice = dice;
        this.ruleEngine = ruleEngine;
    }

    /**
     * Runs the game until a player wins
     */
    public void start(Map<Player, Integer> positions) {
        boolean gameOver = false;
        int turn = 0;
        int totalCells = board.getSize() * board.getSize();

        while (!gameOver) {
            Player current = players.get(turn);

            boolean extraTurn;
            do {
                extraTurn = false;

                if (current.isHuman()) {
                    System.out.println("\n" + current.getName() + ", press ENTER to roll dice...");
                    new java.util.Scanner(System.in).nextLine();
                } else {
                    System.out.println("\nBot " + current.getName() + " is rolling...");
                }

                int roll = dice.roll();
                System.out.println(current.getName() + " rolled: " + roll);

                // Apply all rules via RuleEngine
                RuleResult result = ruleEngine.applyRules(current, roll, board, positions);

                current.setPosition(result.getNewPosition());
                positions.put(current, result.getNewPosition());

                // Check extra turn
                extraTurn = result.hasExtraTurn();

                // Display board
                board.display(players);

                // Check win
                if (result.getNewPosition() == totalCells) {
                    System.out.println("\n🏆 " + current.getName() + " WINS! 🏆");
                    gameOver = true;
                    break;
                }

            } while (extraTurn);

            if (!gameOver) {
                turn = (turn + 1) % players.size();
            }
        }
    }
}
