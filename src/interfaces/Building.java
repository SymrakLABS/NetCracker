package interfaces;

import java.util.Iterator;

public interface Building {
    public int getSize();
    public int getSpaces();
    public int getSquare();
    public int getRooms();
    public Floor[] getArrayOfFloors();
    public Floor getFloor(int index);
    public void setFloor(int index, Floor newFloor);
    public Space getSpace(int index);
    public void setSpace(int index, Space newOffice);
    public void addSpace(int index, Space newOffice);
    public void deleteSpace(int index);
    public Space getBestSpace();
    public Space[] sortSpaces();
    public Object clone() throws CloneNotSupportedException;
    Iterator<Floor> iterator();
    String getClassName();
}
