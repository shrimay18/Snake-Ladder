public class Snake extends Entities {

    public Snake(String name, int start, int end) {
        super(name, start, end);
        if (end >= start) {
            throw new IllegalArgumentException("Snake end must be lower than start.");
        }
    }
}
