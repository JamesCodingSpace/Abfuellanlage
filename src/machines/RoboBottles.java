package machines;

import product.Bottle;
import utilitys.StorageArea;

public class RoboBottles implements IMachine {
    private final StorageArea storageArea;
    private final int[] position;
    private final RoboStorage roboStorage;
    private boolean isTurnedOn;
    private Bottle currentBottle;
    private FillingMachine fillingMachine;

    public RoboBottles(RoboStorage roboStorage, StorageArea storageArea) {
        startMachine();
        this.position = new int[2];
        this.storageArea = storageArea;
        this.roboStorage = roboStorage;
        roboStorage.placeBoxToStorageArea();
    }

    @Override
    public void startMachine() {
        isTurnedOn = true;
    }

    @Override
    public void stopMachine() {
        isTurnedOn = false;
    }

    @Override
    public boolean checkMachineStatus() {
        return isTurnedOn;
    }

    public boolean takeBottle() {
        if (isTurnedOn) {
            currentBottle = fillingMachine.giveBottleAway();
            storeBottleToStorageArea();
            return true;
        } else {
            return false;
        }
    }

    private void storeBottleToStorageArea() {
        storageArea.retrieveBottleForBox(currentBottle, position[0], position[1]);
        if (position[1] < 2) {
            position[1]++;
        } else if (position[0] < 2) {
            position[0]++;
            if (position[1] == 2) {
                position[1] = 0;
            }
        } else {
            putNewBoxToStorageArea();
            position[1] = 0;
            position[0] = 0;
        }
    }

    private void putNewBoxToStorageArea() {
        roboStorage.placeBoxToStorageArea();
    }

    public void setFillingMachine(FillingMachine fillingMachine) {
        this.fillingMachine = fillingMachine;
    }
}
