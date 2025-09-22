import java.util.*;

class Board {
    private int size;
    private String difficulty;
    private List<Entities> entities;

    public Board(int size, String difficulty, List<Entities> entities) {
        this.size = size;
        this.difficulty = difficulty;
        this.entities = entities;
    }

    public int getSize() { return size; }
    public void setEntities(List<Entities> entities) { this.entities = entities; }
    public List<Entities> getEntities() { return entities; }

    public int getLinearPos(int row, int col) {
        return row * size + col + 1; 
    }

    public int[] getRowCol(int pos) {
        pos--; 
        return new int[]{pos / size, pos % size};
    }

    public void movePlayer(Player player, int diceRoll) {
        int currentPos = getLinearPos(player.getRow(), player.getCol());
        int newPos = currentPos + diceRoll;
        int maxCell = size * size;

        if (newPos > maxCell) {
            newPos = currentPos; 
        }

        int[] rc = getRowCol(newPos);
        player.setPosition(rc[0], rc[1]);
    }

    public boolean hasPlayerWon(Player player) {
        int finalCell = size * size;
        int currentPos = getLinearPos(player.getRow(), player.getCol());
        return currentPos == finalCell;
    }

    public void display(List<Player> players) {
        Map<Integer, String> cellMap = new HashMap<>();

        
        for (Entities e : entities) {
            cellMap.put(e.getStart(), e.getId());
            cellMap.put(e.getEnd(), e.getId());
        }

        for (Player p : players) {
            int pos = getLinearPos(p.getRow(), p.getCol());
            String abbrev = p.getName().length() >= 2 ?
                    p.getName().substring(0, 2).toUpperCase() :
                    p.getName().toUpperCase();
            
            if (cellMap.containsKey(pos)) {
                cellMap.put(pos, cellMap.get(pos) + "|" + abbrev);
            } else {
                cellMap.put(pos, abbrev);
            }
        }

        int n = size;
        System.out.println("\n📌 Current Board:");
        for (int r = n - 1; r >= 0; r--) {
            for (int c = 0; c < n; c++) {
                int pos = getLinearPos(r, c);
                String val = cellMap.getOrDefault(pos, String.valueOf(pos));
                System.out.printf("%6s", val);
            }
            System.out.println();
        }
    }
}
