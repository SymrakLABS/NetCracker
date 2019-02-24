package interfaces;

//todo extends Cloneable +++
public interface Space extends Cloneable, Comparable<Space> {
    int getRooms();
    double getSquare();
    void setRoom(int newCountOfRooms);
    void setSquare(double newSquare);
    Object clone() throws CloneNotSupportedException;
}
