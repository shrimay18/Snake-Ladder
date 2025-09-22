public class Ladder extends Entities {

    public Ladder(String name, int start, int end) {
        super(name, start, end);
        if (end <= start) {
            throw new IllegalArgumentException("Ladder end must be higher than start.");
        }
    }
}
