package machines;

import product.Bottle;
import utilitys.Tank;
import utilitys.Track;

public class FillingMachine implements IMachine {
    private final RoboBottles roboBottle;
    private final Tank tank;
    private final Track track;
    private boolean isTurnedOn;
    private Bottle currentBottle;

    public FillingMachine(RoboBottles roboBottle, Track track) {
        startMachine();
        this.roboBottle = roboBottle;
        this.roboBottle.setFillingMachine(this);
        tank = new Tank();
        this.track = track;
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

    public String startFilling() {
        while (isTurnedOn) {
            if (!(track.getAmoountOfLeftBottles() == 0)) {
                takeBottle();
                fillBottleWithLiquid();
                if (!tellRoboToTakeBottle()) {
                    break;
                }
            } else {
                stopMachine();
            }
        }
        if (track.getAmoountOfLeftBottles() == 0) {
            return "Finished Successful";
        } else {
            return "Failed";
        }
    }

    private boolean tellRoboToTakeBottle() {
        return roboBottle.takeBottle();
    }

    private void takeBottle() {
        currentBottle = track.getBottle();
    }

    private void fillBottleWithLiquid() {
        currentBottle.setContent(tank.takeContent(500));
    }

    public Bottle giveBottleAway() {
        Bottle bottleToReturn = currentBottle;
        currentBottle = null;
        return bottleToReturn;
    }

}
