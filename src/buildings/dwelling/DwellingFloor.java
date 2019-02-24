package buildings.dwelling;

import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

public class DwellingFloor implements Floor, Cloneable, Serializable {

    private Space[] flats;
    private int size;

    //конструктор, принимающий массив квартир этажа
    public DwellingFloor(Space[] flats) {
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
    public double getSquare() {
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
    public Space[] getArraySpaces() {
        return flats;
    }

    //метод получения объекта квартиры, по ее номеру на этаже
    public Space getSpace(int number) {
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
    public Space getBestSpace() {
        double maxSquare = 0;
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

    @Override
    public String toString(){
        //todo StringBuilder +++
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DwellinFloor (").append(size);
        for (int i = 0; i < size; i++) {
            stringBuilder.append(", ").append(flats[i]);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        //todo Arrays.deepEquals(), а не просто equals() +++
        if (this == obj)
            return true;
        if (!(obj instanceof DwellingFloor)) {
            return false;
        }
        DwellingFloor dwellingFloor = (DwellingFloor) obj;
        if (dwellingFloor.size != size) {
            return false;
        }
        return Arrays.deepEquals(dwellingFloor.flats, flats);
    }

    @Override
    public int hashCode() {
        int hashCode = size;
        for (int i = 0; i < size; i++) {
            hashCode ^= flats[i].hashCode();
        }
        return hashCode;
    }

    //todo clone() +++
    @Override
    public Object clone() throws CloneNotSupportedException{
        DwellingFloor dwellingFloor = (DwellingFloor) super.clone();
        dwellingFloor.flats = flats.clone();
        for (int i = 0; i < size; i++) {
            dwellingFloor.flats[i] = (Flat) flats[i].clone();
        }
        return dwellingFloor;
    }

    public String getClassName(){
        return "DwellingFloor";
    }

    public Space[] toArray() {
        Space[] flatsCopy = new Space[size];
        for (int i = 0; i < size; i++) {
            flatsCopy[i] = flats[i];
        }
        return flatsCopy;
    }

    @Override
    public int compareTo(Floor o) {
        if (o instanceof DwellingFloor) {
            if (this.equals(o)) {
                return 0;
            } else {
                return this.getSize() - o.getSize();
            }
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public Iterator<Space> iterator() {
        return new Iterator<Space>() {
            private int currentInd = 0;
            @Override
            public boolean hasNext() {
                return currentInd < size && flats[currentInd] != null;
            }

            @Override
            public Space next() {
                return flats[currentInd++];
            }
        };
    }

}