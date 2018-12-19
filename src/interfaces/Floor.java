package interfaces;

import java.util.Iterator;

public interface Floor {
    public int getSize();
    public int getSquare();
    public int getRooms();
    public Space[] getArraySpaces();
    public Space getSpace(int number);
    public void setSpace(int index, Space newSpace);
    public void addSpace(int index, Space newSpace);
    public void deleteSpace(int index);
    public Space getBestSpace();
    public Object clone() throws CloneNotSupportedException;
    public String getClassName();
    Iterator<Space> iterator();
    int compareTo(Floor o);
}
