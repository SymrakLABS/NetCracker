package interfaces;

import java.util.Iterator;

public interface Floor extends Comparable<Floor> {
    int getSize();
    double getSquare();
    int getRooms();
    Space[] getArraySpaces();
    Space getSpace(int number);
    void setSpace(int index, Space newSpace);
    void addSpace(int index, Space newSpace);
    void deleteSpace(int index);
    Space getBestSpace();
    Object clone() throws CloneNotSupportedException;
    Iterator<Space> iterator();
}
