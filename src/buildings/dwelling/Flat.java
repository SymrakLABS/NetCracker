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
        //todo String.format()
        return "Flat (" + countRoom + ", " + square + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Flat))
            return false;
        Flat other = (Flat) obj;
        //todo return (square == other.square && countOfRooms == other.countOfRooms)
        if (square != other.square)
            return false;
        if (countRoom != other.countRoom)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return countRoom ^ (int) Double.doubleToLongBits(square) >> 32 ^ (int) Double.doubleToLongBits(square);
    }


    @Override
<<<<<<< HEAD
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getClassName(){
        return "Flat";
=======
    public Object clone() {
        //todo не надо отлавливать CloneNotSupportedException здесь, прописывай это исключение в сигнатуре метода,
        Object result;
        try {
            result = super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        return result;
>>>>>>> ad9b837a97c31a232edcdd253468da34758c7593
    }
}
