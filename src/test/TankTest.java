package test;

import org.junit.jupiter.api.Test;
import utilitys.Tank;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TankTest {

    @Test
    void checkIfLiquidGetsRemovedCorrectly() {
        int amount = 500;

        Tank tank = new Tank();


        char[][][] tankLiquid = tank.getContent();
        int liquidLeft = countOccurrences(tankLiquid, 'g');
        System.out.println(liquidLeft);
        tank.removeGin(amount);
        liquidLeft = countOccurrences(tankLiquid, 'g');
        System.out.println(liquidLeft);
        assertEquals(13000, liquidLeft);

    }

    private int countOccurrences(char[][][] array, char target) {
        int count = 0;
        for (char[][] subArray : array) {
            for (char[] innerArray : subArray) {
                for (char value : innerArray) {
                    if (value == target) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}