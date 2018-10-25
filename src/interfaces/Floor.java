package interfaces;

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
}
