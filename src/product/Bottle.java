package product;

import utilitys.MTRandom;

public class Bottle {

    private final String name;
    private final float height;
    private final int diameter;
    private final int marginalCapacity;
    private final String serialNumber;
    private final MoutPice mouthPiece;
    private final int weight;
    private final char[] content;
    private boolean isFilled;

    public Bottle() {
        name = "Lab Gin 2008";
        content = new char[500];
        mouthPiece = MoutPice.CARNETTE;
        height = 164.5f;
        diameter = 86;
        marginalCapacity = 545;
        weight = 400;
        isFilled = false;
        serialNumber = generateSerialNumber();
    }

    private String generateSerialNumber() {
        MTRandom rand = new MTRandom();
        long randomNumber = rand.nextLong();
        return String.format("%010d", randomNumber % 10_000_000_000L);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public char[] getContent() {
        return content;
    }

    public void setContent(char[] liquid) {
        System.arraycopy(liquid, 0, content, 0, liquid.length);
        isFilled = true;
    }

}
