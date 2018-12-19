package interfaces;

//todo extends Cloneable
public interface Space {
    public int getRooms();
    public int getSquare();
    public void setRoom(int newCountOfRooms);
    public void setSquare(int newSquare);
    public Object clone() throws CloneNotSupportedException;
    public String getClassName();
}
