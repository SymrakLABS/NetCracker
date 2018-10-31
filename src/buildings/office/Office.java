package buildings.office;

import interfaces.Space;

import java.io.Serializable;

public class Office implements Space, Serializable {

    private static final int DEFAULT_COUNT_ROOMS = 1;
    private static final int DEFAULT_SQUARE = 250;

    private int countOfRooms;
    private int square;

    //конструктор по умолчанию
    public Office(){
       this(DEFAULT_COUNT_ROOMS, DEFAULT_SQUARE);
    }

    //конструктор принимает площадь офиса
    public Office(int squareOfOffice){
        this(DEFAULT_COUNT_ROOMS, squareOfOffice);
    }

    //конструктор принимает площадь офиса и количество комнат
    public Office(int square, int countOfRooms){
        this.countOfRooms = countOfRooms;
        this.square = square;
    }

    //метод получения количества комнат в офисе
    public int getRooms() {
        return countOfRooms;
    }

    //метод получения площади офиса
    public int getSquare() {
        return square;
    }

    //метод изменения количества комнат в офисе
    public void setRoom(int newCountOfRooms) {
        this.countOfRooms = newCountOfRooms;
    }

    //метод изменения площади офиса
    public void setSquare(int newSquare) {
        this.square = newSquare;
    }

    @Override
    public String toString(){
        return "Office (" + countOfRooms+ ", " + square + ")";
    }

    @Override
    public boolean equals(Object obj) {
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
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(square);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + countOfRooms;
        return result;
    }
}
