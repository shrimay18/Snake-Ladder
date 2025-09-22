public class Bot implements Player {
    private String name;
    private String color;
    private int row;
    private int col;

    @Override
    public void setName(String name) { this.name = name; }
    @Override
    public void setColor(String color) { this.color = color; }
    @Override
    public void setPosition(int row, int col) { this.row = row; this.col = col; }

    @Override
    public String getName() { return name; }
    @Override
    public String getColor() { return color; }
    @Override
    public int getRow() { return row; }
    @Override
    public int getCol() { return col; }
}
