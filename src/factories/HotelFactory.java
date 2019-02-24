package factories;

import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import interfaces.Building;
import interfaces.BuildingFactory;
import interfaces.Floor;
import interfaces.Space;

public class HotelFactory implements BuildingFactory {

    public Space createSpace(double square) {
        return new Flat(square);
    }

    public Space createSpace(int roomsCount, double square) {
        return new Flat(roomsCount, square);
    }

    public Floor createFloor(int spacesCount) {
        return new HotelFloor(spacesCount);
    }

    public Floor createFloor(Space[] spaces) {
        return new HotelFloor(spaces);
    }

    public Building createBuilding(int floorsCount, int[] spacesCount) {
        return new Hotel(floorsCount, spacesCount);
    }

    public Building createBuilding(Floor[] floors) {
        return new Hotel(floors);
    }
}
