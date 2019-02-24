package threads;

import interfaces.Floor;
import interfaces.Space;

public class SequentalRepairer implements Runnable {

    private Semaphore semaphore;
    private Floor floor;

    public SequentalRepairer(Semaphore semaphore, Floor floor) {
        this.semaphore = semaphore;
        this.floor = floor;
    }

    @Override
    public void run() {
        Space[] spaces = floor.getArraySpaces();
        for (int i = 0; i < spaces.length; i++) {
            semaphore.repair(i);
        }
    }
}
