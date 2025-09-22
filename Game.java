import java.util.*;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("🎲 Welcome to Snake & Ladder! 🎲");

        System.out.print("Enter number of players: ");
        int numPlayers = sc.nextInt();

        System.out.print("Enter number of bots out of " + numPlayers + ": ");
        int numBots = sc.nextInt();
        sc.nextLine(); 

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numBots; i++) {
            System.out.print("Enter bot name " + (i + 1) + ": ");
            String botName = sc.nextLine();
            players.add(new Bot(botName, "Red"));
        }
        for (int i = numBots; i < numPlayers; i++) {
            System.out.print("Enter player name " + (i + 1) + ": ");
            String playerName = sc.nextLine();
            players.add(new HumanPlayer(playerName, "Blue"));
        }

        System.out.print("Enter board size (n for n*n board): ");
        int n = sc.nextInt();

        System.out.print("Enter number of dice: ");
        int numDice = sc.nextInt();
        Dice dice = new Dice(numDice);

        sc.nextLine(); 
        System.out.print("Enter difficulty (easy / medium / hard): ");
        String difficulty = sc.nextLine();

        BoardBuilder builder = new BoardBuilder(n);
        Board board = builder.buildBoard(difficulty);

        RuleEngine ruleEngine = new RuleEngine();
        ruleEngine.addRule(new KillRule());
        ruleEngine.addRule(new SnakeLadderRule());
        ruleEngine.addRule(new ExactWinRule(n * n));
        ExtraTurnRule extraTurnRule = new ExtraTurnRule(numDice);

        Map<Player, Integer> positions = new HashMap<>();
        for (Player p : players) positions.put(p, 0);

        System.out.println("\nInitial Board:");
        board.display(players);

        boolean gameOver = false;
        int turn = 0;

        while (!gameOver) {
            Player current = players.get(turn);

            boolean extraTurn;
            do {
                extraTurn = false;

                if (current.isHuman()) {
                    System.out.println("\n" + current.getName() + ", press ENTER to roll dice...");
                    sc.nextLine(); 
                } else {
                    System.out.println("\nBot " + current.getName() + " is rolling...");
                }

                int roll = dice.roll();
                System.out.println(current.getName() + " rolled: " + roll);

                int newPos = positions.get(current);
                for (Rule rule : ruleEngine.getRules()) {
                    RuleResult result = rule.apply(current, roll, board, positions);
                    newPos = result.getNewPosition();
                    if (result.hasExtraTurn()) extraTurn = true;
                }

                current.setPosition(newPos);
                positions.put(current, newPos);

                board.display(players);

                if (newPos == n * n) {
                    System.out.println("\n🏆 " + current.getName() + " WINS! 🏆");
                    gameOver = true;
                    break;
                }

            } while (extraTurn);

            if (!gameOver) turn = (turn + 1) % players.size();
        }

        sc.close();
    }
}
