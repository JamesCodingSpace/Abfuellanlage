package utilitys;

import product.Bottle;
import product.Box;

public class StorageArea {
    private final MyStackArray<Box> boxStorage;

    public StorageArea() {
        boxStorage = new MyStackArray<>();
    }

    public void storeBox(Box box) {
        boxStorage.push(box);
    }

    public void retrieveBottleForBox(Bottle bottle, int row, int col) {
        boxStorage.peek().addBottleToBox(bottle, row, col);
    }

}
