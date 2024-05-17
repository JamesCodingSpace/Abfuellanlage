package product;

public class Box {

    private final Bottle[][] storage;

    public Box() {
        storage = new Bottle[3][3];
    }

    public void addBottleToBox(Bottle bottle, int row, int column) {
        storage[row][column] = bottle;
    }

    public Bottle[][] getStorage() {
        return storage;
    }
}
