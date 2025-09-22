import java.util.List;

public class RollResult {
    private final List<Integer> rolls;
    private final int sum;
    private final boolean allSixes;
    private int prevPos; 

    public RollResult(List<Integer> rolls) {
        this.rolls = rolls;
        int s = 0;
        boolean all6 = true;
        for (int r : rolls) {
            s += r;
            if (r != 6) all6 = false;
        }
        this.sum = s;
        this.allSixes = all6;
    }

    public List<Integer> getRolls() { return rolls; }
    public int getSum() { return sum; }
    public boolean isAllSixes() { return allSixes; }

    public int getPrevPos() { return prevPos; }
    public void setPrevPos(int prevPos) { this.prevPos = prevPos; }

    @Override
    public String toString() {
        return rolls.toString() + " => " + sum + (allSixes ? " (all6s)" : "");
    }
}
