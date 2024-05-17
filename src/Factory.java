import machines.FillingMachine;
import machines.RoboBottles;
import machines.RoboStorage;
import utilitys.StorageArea;
import utilitys.Track;

public class Factory {
    private FillingMachine fillingMachine;
    private RoboBottles roboBottles;
    private RoboStorage roboStorage;
    private StorageArea storageArea;
    private Track track;


    public void startProduction() {
        track = new Track();
        storageArea = new StorageArea();
        roboStorage = new RoboStorage(storageArea, track);
        roboBottles = new RoboBottles(roboStorage, storageArea);
        fillingMachine = new FillingMachine(roboBottles, track);

        System.out.println(fillingMachine.startFilling());
    }
}
