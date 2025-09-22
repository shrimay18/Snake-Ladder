public class Bot extends Player {
    public Bot(String name, String color) {
        super(name, color);
    }

    @Override
    public boolean isHuman() {
        return false;
    }
}
