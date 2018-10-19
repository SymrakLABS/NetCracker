package buildings;

/**
 * Добавлено поле size, изменены конструкторы и методы
 */

public class Dwelling {

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
    public int getAllDwellingOfBuild() {
        return size;
    }

    //метод получения общего количества квартир дома
    public int getAllFlatsOfBuild() {
        int countFlatsOfBuild = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < dwellingFloors[i].getCountOfFlats(); j++) {
                countFlatsOfBuild++;
            }
        }
        return countFlatsOfBuild;
    }

    //метод получения общей площади квартир дома
    public int getAllSquareFlatsBuild() {
        int squareFlatsBuild = 0;
        for (int j = 0; j < dwellingFloors.length; j++) {
            squareFlatsBuild += dwellingFloors[j].getAllSquareOfDwelling();
        }
        return squareFlatsBuild;
    }

    //метод получения общего количества комнат дома
    public int getAllRoomsBuild() {
        int countRooms = 0;
        for (int i = 0; i < size; i++) {
            countRooms += dwellingFloors[i].getAllRoomsOfDwelling();
        }
        return countRooms;
    }

    //метод получения массива этажей жилого дома
    public DwellingFloor[] getDwellindFloorOfBuild() {
        return dwellingFloors;
    }



    //метод получения объекта этажа, по его номеру в доме
    public DwellingFloor getDwellingFlatByNumber(int number) {
       return dwellingFloors[number];
    }

    //метод изменения этажа по его номеру в доме и ссылке на обновленный этаж
    public void changeDwellingByNumber(int number, DwellingFloor newDwellingFloor) {
        dwellingFloors[number] = newDwellingFloor;
    }

    //вспомогательный метод получения номера квартиры и номера этажа
    private FinderFlat findFlatByNumber(int number) {
        int count = 0;
        for(int i = 0; i < size; i++) {
            int length = dwellingFloors[i].getCountOfFlats();
            if (number - count < length) {
                return new FinderFlat(i, number - count);
            } else {
                count += length;
            }
        }
        return null;
    }

    //метод получения объекта квартиры по ее номеру в доме
    public Flat getFlatByNumber(int number){
        FinderFlat finderFlat = findFlatByNumber(number);
        return dwellingFloors[finderFlat.getFloorNumber()].getFlat(finderFlat.getFlatNumber());
    }

    //метод изменения объекта квартиры по ее номеру в доме и ссылке типа квартиры
    public void changeFlatByNumber(int flatNumber, Flat newFlat) {
        FinderFlat finderFlat = findFlatByNumber(flatNumber);
        dwellingFloors[finderFlat.getFloorNumber()].changeFlat(finderFlat.getFlatNumber(), newFlat);
    }

    //метод добавления квартиры в дом по будущему номеру квартиры в доме
    public void addNewFlatByNumber(int number, Flat newFlat) {
        FinderFlat finderFlat = findFlatByNumber(number);
        dwellingFloors[finderFlat.getFloorNumber()].addNewFlat(finderFlat.getFlatNumber(), newFlat);
    }

    //метод удаления квартиры по ее номеру в доме
    public void deleteFlatByNumber(int number) {
        FinderFlat finderFlat = findFlatByNumber(number);
        dwellingFloors[finderFlat.getFloorNumber()].deleteFlat(finderFlat.getFlatNumber());
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
    public Flat[] sortingFlats() {
        Flat[] sortFlts = new Flat[getAllFlatsOfBuild()];
        int counter = 0;

        for (int i = 0; i < size; i++ ) {
            for (int j = 0; j < dwellingFloors[i].getFlatsOfDwelling().length; j++) {
                sortFlts[counter] = dwellingFloors[i].getFlat(j);
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
}
