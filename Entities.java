public abstract class Entities {
    protected String id;
    protected int start;
    protected int end;

    public Entities(String id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public String getId() { return id; }
    public int getStart() { return start; }
    public int getEnd() { return end; }
}
