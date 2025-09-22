import java.util.Random;

public class Dice {
    private final int numDice;
    private final Random random = new Random();

    public Dice(int numDice) {
        this.numDice = numDice;
    }

    public int roll() {
        int sum = 0;
        for (int i = 0; i < numDice; i++) {
            sum += random.nextInt(6) + 1;  // always 6-faced dice
        }
        return sum;
    }
}
