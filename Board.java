import java.util.*;

public class Board {
    private final int size;
    private List<Entities> entities;
    private Map<Integer, Entities> entityMap;

    public Board(int size) {
        this.size = size;
        this.entities = new ArrayList<>();
        this.entityMap = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public void setEntities(List<Entities> entities) {
        this.entities = entities;
        entityMap.clear();
        for (Entities e : entities) entityMap.put(e.getStart(), e);
    }

    public Entities getEntityAt(int cell) {
        return entityMap.get(cell);
    }

    public void display(List<Player> players) {
        Map<Integer, List<String>> playerAtCell = new HashMap<>();
        for (Player p : players) {
            playerAtCell.computeIfAbsent(p.getPosition(), k -> new ArrayList<>())
                        .add(p.getName().substring(0, 1));
        }

        int n = size;
        for (int row = n - 1; row >= 0; row--) {
            for (int col = 0; col < n; col++) {
                int cellNum;
                if ((n - row) % 2 == 0) { // reverse every alternate row
                    cellNum = row * n + (n - 1 - col) + 1;
                } else {
                    cellNum = row * n + col + 1;
                }

                StringBuilder display = new StringBuilder();
                Entities e = getEntityAt(cellNum);
                if (e != null) display.append(e.getName());
                else display.append(String.format("%2d", cellNum));

                if (playerAtCell.containsKey(cellNum)) {
                    for (String ch : playerAtCell.get(cellNum)) display.append("(" + ch + ")");
                }

                System.out.print(display + "\t");
            }
            System.out.println("\n");
        }
    }
}
