public class HumanPlayer extends Player {
    public HumanPlayer(String name, String color) {
        super(name, color);
    }

    @Override
    public boolean isHuman() {
        return true;
    }
}
