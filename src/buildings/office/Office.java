package buildings.office;

import interfaces.Space;

import java.io.Serializable;

public class Office implements Space, Serializable, Cloneable {

    private static final int DEFAULT_COUNT_ROOMS = 1;
    private static final double DEFAULT_SQUARE = 250;

    private int countOfRooms;
    private double square;

    //конструктор по умолчанию
    public Office(){
        this(DEFAULT_COUNT_ROOMS, DEFAULT_SQUARE);
    }

    //конструктор принимает площадь офиса
    public Office(double squareOfOffice){
        this(DEFAULT_COUNT_ROOMS, squareOfOffice);
    }

    //конструктор принимает площадь офиса и количество комнат
    public Office(int countOfRooms, double square){
        this.countOfRooms = countOfRooms;
        this.square = square;
    }

    //метод получения количества комнат в офисе
    public int getRooms() {
        return countOfRooms;
    }

    //метод получения площади офиса
    public double getSquare() {
        return square;
    }

    //метод изменения количества комнат в офисе
    public void setRoom(int newCountOfRooms) {
        this.countOfRooms = newCountOfRooms;
    }

    //метод изменения площади офиса
    public void setSquare(double newSquare) {
        this.square = newSquare;
    }

    @Override
    public String toString(){
        //todo String.format() +++
        return String.format("Office (%d; %.1f)", countOfRooms, square);
    }

    @Override
    public boolean equals(Object obj) {
        //todo return (square == other.square && countOfRooms == other.countOfRooms) +++
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Office))
            return false;
        Office other = (Office) obj;
        if (square != other.square)
            return false;
        if (countOfRooms != other.countOfRooms)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return countOfRooms ^ (int) Double.doubleToLongBits(square) >> 32 ^ (int) Double.doubleToLongBits(square);
    }

    //todo clone()? +++
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Space o) {
        if (o instanceof Office) {
            Office office = (Office) o;
            return Double.compare(square, office.getSquare());
        }
        return Integer.MIN_VALUE;
    }
}