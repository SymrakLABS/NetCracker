import interfaces.Floor;
import interfaces.Space;

import java.util.Iterator;

public class SynchronizedFloor implements Floor {

    private Floor floor;

    public SynchronizedFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public synchronized int getSize() {
        return floor.getSize();
    }

    @Override
    public synchronized double getSquare() {
        return floor.getSquare();
    }

    @Override
    public synchronized int getRooms() {
        return floor.getRooms();
    }

    @Override
    public synchronized Space[] getArraySpaces() {
        return floor.getArraySpaces();
    }

    @Override
    public synchronized Space getSpace(int number) {
        return getSpace(number);
    }

    @Override
    public synchronized void setSpace(int number, Space newSpace) {
        floor.setSpace(number, newSpace);
    }

    @Override
    public synchronized void addSpace(int number, Space newSpace) {
        floor.addSpace(number, newSpace);
    }

    @Override
    public synchronized void deleteSpace(int number) {
        deleteSpace(number);
    }

    @Override
    public synchronized Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public synchronized Object clone() throws CloneNotSupportedException {
        return floor.clone();
    }

    @Override
    public synchronized Iterator<Space> iterator() {
        return floor.iterator();
    }

    @Override
    public synchronized String toString() {
        return floor.toString();
    }

    @Override
    public synchronized int compareTo(Floor o) {
        return floor.compareTo(o);
    }

    @Override
    public synchronized int hashCode() {
        return floor.hashCode();
    }

    @Override
    public synchronized boolean equals(Object obj) {
        return floor.equals(obj);
    }
}
