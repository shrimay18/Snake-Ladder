import java.util.Map;

public class BoardRenderer {
    public static void display(Board board, Map<Player, Integer> positions) {
        System.out.println("\nCurrent Board:");
        for (int i = board.getSize(); i > 0; i--) {
            String cell = String.format("%3d", i);
            for (Map.Entry<Player, Integer> entry : positions.entrySet()) {
                if (entry.getValue() == i) {
                    cell += "(" + entry.getKey().getName() + ")";
                }
            }
            System.out.print(cell + " ");
            if (i % 10 == 1) System.out.println();
        }
        System.out.println();
    }
}
