package buildings.dwelling;

import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.util.Arrays;

public class Dwelling implements Building {

    DwellingFloor[] dwellingFloors;
    private int size;

    //конструктор, принимающий массив этажей дома
    public Dwelling(DwellingFloor[] dwellingFloors) {
        this.dwellingFloors = dwellingFloors;
        size = dwellingFloors.length;
    }

    //конструктор, принимающий количество этажей и массив количества квартир по этажам
    public Dwelling(int countOfDwelling, int [] countOfFlats) {
        this.dwellingFloors = new DwellingFloor[countOfDwelling];
        if(countOfDwelling == countOfFlats.length){
            for (int i = 0; i < countOfFlats.length; i++) {
                this.dwellingFloors[i] = new DwellingFloor(countOfFlats[i]);
            }
        }
    }

    //метод получения общего количества этажей дома
    public int getSize() {
        return size;
    }

    //метод получения общего количества квартир дома
    public int getSpaces() {
        int countFlatsOfBuild = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < dwellingFloors[i].getSize(); j++) {
                countFlatsOfBuild++;
            }
        }
        return countFlatsOfBuild;
    }

    //метод получения общей площади квартир дома
    public int getSquare() {
        int squareFlatsBuild = 0;
        for (int j = 0; j < dwellingFloors.length; j++) {
            squareFlatsBuild += dwellingFloors[j].getSquare();
        }
        return squareFlatsBuild;
    }

    //метод получения общего количества комнат дома
    public int getRooms() {
        int countRooms = 0;
        for (int i = 0; i < size; i++) {
            countRooms += dwellingFloors[i].getRooms();
        }
        return countRooms;
    }

    //метод получения массива этажей жилого дома
    public DwellingFloor[] getArrayOfFloors() {
        return dwellingFloors;
    }

    //метод получения объекта этажа, по его номеру в доме
    public DwellingFloor getFloor(int number) {
       return dwellingFloors[number];
    }

    //метод изменения этажа по его номеру в доме и ссылке на обновленный этаж
    public void setFloor(int number, Floor newDwellingFloor) {
        dwellingFloors[number] = (DwellingFloor) newDwellingFloor;
    }

    //вспомогательный метод получения номера квартиры и номера этажа
    private FinderFlat findFlatByNumber(int number) {
        int count = 0;
        for(int i = 0; i < size; i++) {
            int length = dwellingFloors[i].getSize();
            if (number - count < length) {
                return new FinderFlat(i, number - count);
            } else {
                count += length;
            }
        }
        return null;
    }

    //метод получения объекта квартиры по ее номеру в доме
    public Flat getSpace(int number){
        FinderFlat finderFlat = findFlatByNumber(number);
        return dwellingFloors[finderFlat.getFloorNumber()].getSpace(finderFlat.getFlatNumber());
    }

    //метод изменения объекта квартиры по ее номеру в доме и ссылке типа квартиры
    public void setSpace(int flatNumber, Space newFlat) {
        FinderFlat finderFlat = findFlatByNumber(flatNumber);
        dwellingFloors[finderFlat.getFloorNumber()].setSpace(finderFlat.getFlatNumber(), newFlat);
    }

    //метод добавления квартиры в дом по будущему номеру квартиры в доме
    public void addSpace(int number, Space newFlat) {
        FinderFlat finderFlat = findFlatByNumber(number);
        dwellingFloors[finderFlat.getFloorNumber()].addSpace(finderFlat.getFlatNumber(), newFlat);
    }

    //метод удаления квартиры по ее номеру в доме
    public void deleteSpace(int number) {
        FinderFlat finderFlat = findFlatByNumber(number);
        dwellingFloors[finderFlat.getFloorNumber()].deleteSpace(finderFlat.getFlatNumber());
    }

    //метод получения самой большой по площади квартиры дома
    public Flat getBestSpace() {
        Flat bestSpaceFlat = new Flat();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < dwellingFloors[i].flats.length; j++) {
                bestSpaceFlat = dwellingFloors[i].getBestSpace();
            }
        }
        return bestSpaceFlat;
    }

    //метод получения отсортированного по убыванию площадей массива квартир
    public Flat[] sortSpaces() {
        Flat[] sortFlts = new Flat[getSpaces()];
        int counter = 0;

        for (int i = 0; i < size; i++ ) {
            for (int j = 0; j < dwellingFloors[i].getArraySpaces().length; j++) {
                sortFlts[counter] = dwellingFloors[i].getSpace(j);
                counter++;
            }
        }

        for(int i = sortFlts.length - 1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++) {
                if(sortFlts[j].getSquare() > sortFlts[j + 1].getSquare()){
                    Flat tmp = sortFlts[j];
                    sortFlts[j] = sortFlts[j + 1];
                    sortFlts[j + 1] = tmp;
                }
            }
        }
        return sortFlts;
    }

    @Override
    public String toString() {
        //todo StringBuilder
        StringBuffer s = new StringBuffer();
        s.append("Dwelling (").append(size).append(", ");
        for (int i = 0; i < size; i++) {
            if (i > 0) s.append(", ");
            s.append(dwellingFloors[i].toString());
        }
        s.append(")");
        return s.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Dwelling))
            return false;
        Dwelling other = (Dwelling) obj;
        if (!Arrays.equals(dwellingFloors, other.dwellingFloors))//todo Arrays.deepEquals(), а не просто equals()
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(dwellingFloors); //todo Arrays.deepHashCode(), а не просто hashCode()
        return result;
    }
    //todo clone()

}
