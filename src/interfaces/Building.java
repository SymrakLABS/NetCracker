package interfaces;

import java.util.Iterator;

public interface Building {
    int getSize();
    int getSpaces();
    double getSquare();
    int getRooms();
    Floor[] getArrayOfFloors();
    Floor getFloor(int index);
    void setFloor(int index, Floor newFloor);
    Space getSpace(int index);
    void setSpace(int index, Space newOffice);
    void addSpace(int index, Space newOffice);
    void deleteSpace(int index);
    Space getBestSpace();
    Space[] sortSpaces();
    Object clone() throws CloneNotSupportedException;
    Iterator<Floor> iterator();
}
