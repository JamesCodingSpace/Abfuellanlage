package test;

import org.junit.jupiter.api.Test;
import product.Bottle;
import product.Box;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoxTest {

    @Test
    void testBottleInBoxPlacement() {
        Bottle bottle = new Bottle();
        Box box = new Box();
        Bottle[][] storage = new Bottle[3][3];
        storage[2][1] = bottle;

        box.addBottleToBox(bottle, 2, 1);
        Bottle[][] retrievedStorage = box.getStorage();

        assertEquals(retrievedStorage[2][1], storage[2][1]);
    }

}