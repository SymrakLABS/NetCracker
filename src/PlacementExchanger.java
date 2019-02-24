import exceptions.FloorIndexOutOfBoundsException;
import exceptions.InexchangeableFloorsException;
import exceptions.InexchangeableSpacesException;
import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

public class PlacementExchanger {

    //метод проверки возможности обмена помещениями
    public static boolean isExchangePossible(Space firstSpace, Space secondSpace){
        return firstSpace.getSquare() == secondSpace.getSquare() &&
                firstSpace.getRooms() == secondSpace.getRooms();
    }

    //метод проверки возможности обмена этажами
    public static boolean isExchangePossible(Floor firstFloor, Floor secondFloor){
        return firstFloor.getSize() == secondFloor.getSize() &&
                firstFloor.getSquare() == secondFloor.getSquare();
    }

    //метод обмена помещениями
    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2)
            throws InexchangeableSpacesException {
        if (!isExchangePossible(floor1.getSpace(index1), floor2.getSpace(index2))) {
            throw new InexchangeableSpacesException();
        } else if ((index1 >= floor1.getSize() || index1 < 0) || (index2 >= floor2.getSize() || index2 < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        } else {
            Space temp = floor1.getSpace(index1);
            floor1.setSpace(index1, floor2.getSpace(index2));
            floor2.setSpace(index2, temp);
        }
    }

    //метод обмена этажами
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2)
            throws InexchangeableFloorsException{
        if(!isExchangePossible(building1.getFloor(index1), building2.getFloor(index2))){
            throw new InexchangeableFloorsException();
        } else if((index1 >= building1.getSize() || index1 < 0) || (index2 >= building2.getSize() || index2 < 0)){
            throw new FloorIndexOutOfBoundsException();
        } else {
            Floor temp = building1.getFloor(index1);
            building1.setFloor(index1, building2.getFloor(index2));
            building2.setFloor(index2, temp);
        }
    }
}
