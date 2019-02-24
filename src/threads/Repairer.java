package threads;

import interfaces.Floor;
import interfaces.Space;

public class Repairer extends Thread {

    private Floor floor;

    public Repairer(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        Space[] spaces = floor.getArraySpaces();
        for (int i = 0; i < spaces.length; i++) {
            if (!interrupted()) {
                System.out.println("Repairing space number " + i + " with total area " +
                        spaces[i].getSquare() + " square meters");
            }
            else {
                break;
            }
        }
    }

}
