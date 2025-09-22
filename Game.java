import java.util.*;

public class Game {
    private static Random rand = new Random();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🎲 Welcome to Snake & Ladder! 🎲");
        System.out.print("Enter number of players: ");
        int numPlayers = sc.nextInt();

        System.out.print("Enter number of bots out of " + numPlayers + ": ");
        int numBots = sc.nextInt();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numBots; i++) {
            System.out.print("Enter bot name " + (i + 1) + ": ");
            String botName = sc.next();
            Player bot = new Bot();
            bot.setName(botName);
            bot.setColor("Red"); 
            bot.setPosition(0, 0);
            players.add(bot);
        }

        for (int i = numBots; i < numPlayers; i++) {
            System.out.print("Enter player name " + (i + 1) + ": ");
            String playerName = sc.next();
            Player human = new HumanPlayer();
            human.setName(playerName);
            human.setColor("Blue"); 
            human.setPosition(0, 0);
            players.add(human);
        }

        System.out.print("Enter board size (n for n*n board): ");
        int n = sc.nextInt();

        System.out.print("Enter number of dice: ");
        int numDice = sc.nextInt();

        System.out.print("Enter difficulty (easy / medium / hard): ");
        String difficulty = sc.next();

        sc.nextLine();

        Board board = new Board(n, difficulty, new ArrayList<>());

        addEntities(board, difficulty);

        List<Rule> rules = new ArrayList<>();
        rules.add(new KillRule());           
        rules.add(new SnakeLadderRule());    
        rules.add(new ExtraTurnRule());      
        rules.add(new ExactWinRule());       

        System.out.println("\nInitial Board:");
        board.display(players);

        boolean gameOver = false;
        int turn = 0;

        while (!gameOver) {
            Player current = players.get(turn);
            System.out.println("\n👉 " + current.getName() + "'s turn!");

            RollResult rr;
            if (current instanceof HumanPlayer) {
                System.out.println("Press Enter to roll the dice...");
                sc.nextLine(); 
                rr = rollDice(numDice);
            } else {
                System.out.println(current.getName() + " (Bot) is rolling...");
                rr = rollDice(numDice);
            }

            int prevPos = board.getLinearPos(current.getRow(), current.getCol());
            rr.setPrevPos(prevPos);

            System.out.println(current.getName() + " rolled: " + rr.toString());

            board.movePlayer(current, rr.getSum());

            boolean keepTurn = false;
            for (Rule rule : rules) {
                boolean result = rule.apply(current, board, rr, players);
                if (result) keepTurn = true;
            }

            board.display(players);

            if (board.hasPlayerWon(current)) {
                System.out.println("\n🏆 " + current.getName() + " WINS! 🏆");
                gameOver = true;
                break;
            }

            if (!keepTurn) {
                turn = (turn + 1) % players.size();
            } else {
                System.out.println(current.getName() + " will play again (extra turn).");
            }
        }

        sc.close();
    }

    private static RollResult rollDice(int numDice) {
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < numDice; i++) {
            rolls.add(rand.nextInt(6) + 1);
        }
        return new RollResult(rolls);
    }

    private static void addEntities(Board board, String difficulty) {
        int n = board.getSize();
        int totalCells = n * n;

        int numSnakes, numLadders;
        if (difficulty.equalsIgnoreCase("easy")) {
            numSnakes = Math.max(1, totalCells / 20);
            numLadders = Math.max(1, totalCells / 15);
        } else if (difficulty.equalsIgnoreCase("medium")) {
            numSnakes = Math.max(1, totalCells / 15);
            numLadders = Math.max(1, totalCells / 12);
        } else { 
            numSnakes = Math.max(1, totalCells / 10);
            numLadders = Math.max(1, totalCells / 10);
        }

        Set<Integer> used = new HashSet<>();
        List<Entities> entities = new ArrayList<>();

        // add snakes
        for (int i = 0; i < numSnakes; i++) {
            int start, end;
            do {
                start = rand.nextInt(totalCells - n) + n; 
                end = rand.nextInt(start) + 1;                
            } while (used.contains(start) || used.contains(end) || start == end || start == totalCells);
            used.add(start);
            used.add(end);
            entities.add(new Snake("S" + (i + 1), start, end));
        }

        // add ladders
        for (int i = 0; i < numLadders; i++) {
            int start, end;
            do {
                start = rand.nextInt(totalCells - n) + 1; 
                end = rand.nextInt(totalCells - start) + start + 1; 
            } while (used.contains(start) || used.contains(end) || start == end || end == totalCells);
            used.add(start);
            used.add(end);
            entities.add(new Ladder("L" + (i + 1), start, end));
        }

        board.setEntities(entities);
    }
}
