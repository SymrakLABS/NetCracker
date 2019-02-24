package threads;

import interfaces.Floor;
import interfaces.Space;

public class SequentalCleaner implements Runnable {

    private Semaphore semaphore;
    private Floor floor;

    public SequentalCleaner (Semaphore semaphore, Floor floor) {
        this.semaphore = semaphore;
        this.floor = floor;
    }

    @Override
    public void run() {
        Space[] spaces = floor.getArraySpaces();
        for (int i = 0; i < spaces.length; i++) {
            semaphore.clean(i);
        }
    }
}
