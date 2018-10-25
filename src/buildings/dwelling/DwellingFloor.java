package buildings.dwelling;

import interfaces.Floor;
import interfaces.Space;

public class DwellingFloor implements Floor {

    Flat[] flats;
    private int size;

    //конструктор, принимающий массив квартир этажа
    public DwellingFloor(Flat[] flats) {
        this.flats = flats;
        size = flats.length;
    }

    //конструктор, принимающий количество квартир на этаже
    public DwellingFloor(int countFlats) {
        this.flats = new Flat[countFlats];
        size = 0;
    }

    //расширение массива
    private Flat[] expandArray(){
        Flat expandedArray[] = new Flat[2 * flats.length];
        System.arraycopy(flats, 0, expandedArray, 0,flats.length);
        return expandedArray;
    }

    //метод получения количества квартир на этаже
    public int getSize() {
        return size;
    }

    //метод получения общей площади квартир этажа
    public int getSquare() {
        int allSquare = 0;
        for (int i = 0; i < size; i++) {
            allSquare += flats[i].getSquare();
        }
        return allSquare;
    }

    //метод получения общего количества комнат этажа
    public int getRooms() {
        int allRooms = 0;
        for (int i = 0; i < size; i++) {
            allRooms += flats[i].getRooms();
        }
        return allRooms;
    }

    //метод получения массива квартир этажа
    public Flat[] getArraySpaces() {
        return flats;
    }

    //метод получения объекта квартиры, по ее номеру на этаже
    public Flat getSpace(int number) {
        return flats[number];
    }

    //метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру
    public void setSpace(int number, Space newFlat) {
        flats[number] = (Flat) newFlat;
    }

    //метод добавления новой квартиры на этаже по будущему номеру квартиры
    public void addSpace(int index, Space newFlat) {
        if (index < size || index > 0) {
            System.arraycopy(flats, index, flats, index + 1, flats.length - (index + 1));
        }
        while (flats.length == size || index > flats.length) {
            flats = expandArray();
        }
        flats[index] = (Flat) newFlat;
        size++;
    }

    //метод удаления квартиры по ее номеру на этаже
    public void deleteSpace(int index){
        if (index < size || index > 0) {
            System.arraycopy(flats, index  + 1, flats, index, flats.length - (index + 1));
            size--;
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    //метод получения самой большой по площади квартиры этажа
    public Flat getBestSpace() {
        int maxSquare = 0;
        for(int i = 0; i < size; i++){
            if(flats[i].getSquare() > maxSquare){
                maxSquare = flats[i].getSquare();
            }
        }

        for(int i = 0; i < size; i++){
            if(maxSquare == flats[i].getSquare()){
                return flats[i];
            }
        }
        return null;
    }
}
