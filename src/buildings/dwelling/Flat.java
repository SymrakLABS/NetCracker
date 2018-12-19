package buildings.dwelling;

import interfaces.Space;

import java.io.Serializable;

public class Flat implements Space, Serializable, Cloneable {
    private static final int DEFAULT_SQUARE = 50;
    private static final int DEFAULT_COUNT_ROOMS = 2;

    private int square;
    private int countRoom;

    //конструктор по умолчанию
    public Flat(){
        this(DEFAULT_COUNT_ROOMS, DEFAULT_SQUARE);
    }

    //конструктор, принимающий площадь квартиры
    public Flat(int square){
        this(DEFAULT_COUNT_ROOMS, square);
    }

    //конструктор, принимающий площадь и количество комнат квартиры
    public Flat(int square, int countOfRoom){
        this.square = square;
        this.countRoom = countOfRoom;
    }

    //метод получения площади квартиры
    public int getSquare() {
        return square;
    }

    //метод получения количества комнат в квартире
    public int getRooms() {
        return countRoom;
    }

    //метод изменения площади квартиры
    public void setSquare(int sqare) {
        this.square = sqare;
    }

    //метод изменения количества комнат в квартире
    public void setRoom(int room) {
        this.countRoom = room;
    }

    @Override
    public String toString(){
        //todo String.format() +++
        return String.format("Flat (%d; %.1f)", countRoom, square);
    }

    @Override
    public boolean equals(Object obj) {
        //todo return (square == other.square && countOfRooms == other.countOfRooms) +++
        if (this == obj)
            return true;
        if (!(obj instanceof Flat)) {
            return false;
        }
        Flat flat = (Flat) obj;
        return (flat.getSquare() == square && flat.getRooms() == countRoom);
    }

    @Override
    public int hashCode() {
        return countRoom ^ (int) Double.doubleToLongBits(square) >> 32 ^ (int) Double.doubleToLongBits(square);
    }


    //todo не надо отлавливать CloneNotSupportedException здесь, прописывай это исключение в сигнатуре метода, +++
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getClassName(){
        return "Flat";
    }
}
