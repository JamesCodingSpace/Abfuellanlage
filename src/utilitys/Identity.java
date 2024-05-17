package utilitys;

import product.Bottle;

public class Identity {
    private final Bottle bottleOne;
    private final Bottle bottleTwo;
    private String id;

    public Identity(Bottle bottleOne, Bottle bottleTwo) {
        this.bottleOne = bottleOne;
        this.bottleTwo = bottleTwo;
    }

    public boolean equals() {
        if (bottleOne == null || bottleTwo == null) {
            return false;
        }

        if (bottleOne == bottleTwo) {
            return true;
        }
        if (!(bottleOne instanceof Bottle && bottleTwo instanceof Bottle)) {
            return false;
        }
        if (bottleOne.getClass() != bottleOne.getClass()) {
            return false;
        }

        return bottleOne.getSerialNumber().equals(bottleTwo.getSerialNumber());
    }
}
