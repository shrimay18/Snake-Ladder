import java.util.*;

public class BoardBuilder {
    private final int size;
    private final Random rand = new Random();

    public BoardBuilder(int size) {
        this.size = size;
    }

    public Board buildBoard(String difficulty) {
        Board board = new Board(size);
        List<Entities> entities = new ArrayList<>();
        int totalCells = size * size;

        int numSnakes, numLadders;
        switch (difficulty.toLowerCase()) {
            case "easy":   numSnakes = totalCells / 20; numLadders = totalCells / 15; break;
            case "medium": numSnakes = totalCells / 15; numLadders = totalCells / 12; break;
            default:       numSnakes = totalCells / 10; numLadders = totalCells / 10; break;
        }

        Set<Integer> used = new HashSet<>();

        // Snakes
        for (int i = 0; i < numSnakes; i++) {
            int start, end;
            do {
                start = rand.nextInt(totalCells - size) + size; // avoid first row
                end = rand.nextInt(start - 1) + 1;
            } while (used.contains(start) || used.contains(end) || sameRow(start, end));
            used.add(start); used.add(end);
            entities.add(new Snake("S" + (i + 1), start, end));
        }

        // Ladders
        for (int i = 0; i < numLadders; i++) {
            int start, end;
            do {
                start = rand.nextInt(totalCells - size) + 1;
                end = rand.nextInt(totalCells - start) + start + 1;
            } while (used.contains(start) || used.contains(end) || sameRow(start, end));
            used.add(start); used.add(end);
            entities.add(new Ladder("L" + (i + 1), start, end));
        }

        board.setEntities(entities);
        return board;
    }

    private boolean sameRow(int cell1, int cell2) {
        int row1 = (cell1 - 1) / size;
        int row2 = (cell2 - 1) / size;
        return row1 == row2;
    }
}
