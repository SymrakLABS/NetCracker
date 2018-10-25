package buildings.office;

import interfaces.Space;

public class Office implements Space {

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
}
