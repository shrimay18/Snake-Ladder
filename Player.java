public interface Player {
    void setName(String name);
    void setColor(String color);
    void setPosition(int row, int col);

    String getName();
    String getColor();
    int getRow();
    int getCol();
}
