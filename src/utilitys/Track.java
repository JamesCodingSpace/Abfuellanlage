package utilitys;

import product.Bottle;
import product.Box;

import java.util.LinkedList;
import java.util.Queue;

public class Track {
    private final Queue<Bottle> bottleQueue = new LinkedList<>();
    private final Queue<Box> boxQueue = new LinkedList<>();

    public Track() {
        for (int i = 0; i < 27; i++) {
            bottleQueue.offer(new Bottle());
        }
        for (int i = 0; i < 3; i++) {
            boxQueue.offer(new Box());
        }
    }

    public Bottle getBottle() {
        return bottleQueue.poll();
    }

    public Box getBox() {
        return boxQueue.poll();
    }

    public int getAmoountOfLeftBottles() {
        return bottleQueue.size();
    }

}
