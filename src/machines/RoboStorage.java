package machines;

import product.Box;
import utilitys.StorageArea;
import utilitys.Track;

public class RoboStorage implements IMachine {
    private final StorageArea storageArea;
    private final Track track;
    private boolean isTurnedOn;

    public RoboStorage(StorageArea storageArea, Track track) {
        startMachine();
        this.track = track;
        this.storageArea = storageArea;
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

    public void placeBoxToStorageArea() {
        if (isTurnedOn) {
            Box box = track.getBox();
            storageArea.storeBox(box);
        } else {
            System.out.println("Storage Robo Is not turned on");
        }
    }
}
