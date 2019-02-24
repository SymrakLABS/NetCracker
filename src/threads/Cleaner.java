package threads;

import interfaces.Floor;
import interfaces.Space;

public class Cleaner extends Thread {

    private Floor floor;

    public Cleaner(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        Space[] spaces = floor.getArraySpaces();
        for(int i = 0; i < spaces.length; i++) {
            if (!interrupted()) {
                System.out.println("Cleaning room number " + i + " with total area " +
                        spaces[i].getSquare() + " square meters");
            } else {
                break;
            }
        }
    }
}
