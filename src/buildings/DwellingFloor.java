package buildings;

/**
 * Добавлено поле size, хранящее размерность массива
 * В соответствии изменены методы добавления/удаления квартир и конструкторы
 */

public class DwellingFloor {

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

    private Flat[] expandArray(){
        Flat expandedArray[] = new Flat[2 * flats.length];
        System.arraycopy(flats, 0, expandedArray, 0,flats.length);
        return expandedArray;
    }

    //метод получения количества квартир на этаже
    public int getCountOfFlats() {
        return size;
    }

    //метод получения общей площади квартир этажа
    public int getAllSquareOfDwelling() {
        int allSquare = 0;
        for (int i = 0; i < size; i++) {
            allSquare += flats[i].getSquare();
        }
        return allSquare;
    }

    //метод получения общего количества комнат этажа
    public int getAllRoomsOfDwelling() {
        int allRooms = 0;
        for (int i = 0; i < size; i++) {
            allRooms += flats[i].getCountRoom();
        }
        return allRooms;
    }

    //метод получения массива квартир этажа
    public Flat[] getFlatsOfDwelling() {
        return flats;
    } //копия

    //метод получения объекта квартиры, по ее номеру на этаже
    public Flat getFlat(int number) {
        return flats[number];
    }

    //метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру
    public void changeFlat(int number, Flat newFlat) {
        flats[number] = newFlat;
    }

    //метод добавления новой квартиры на этаже по будущему номеру квартиры
    public void addNewFlat(int index, Flat newFlat) {
        if (index < size || index > 0) {
            System.arraycopy(flats, index, flats, index + 1, flats.length - (index + 1));
        }
        while (flats.length == size || index > flats.length) {
            flats = expandArray();
        }
        flats[index] = newFlat;
        size++;
    }

    //метод удаления квартиры по ее номеру на этаже
    public void deleteFlat(int index){
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
