package factory;

import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import interfaces.Building;
import interfaces.BuildingFactory;
import interfaces.Floor;
import interfaces.Space;

public class OfficeFactory implements BuildingFactory {

    public Space createSpace(int square) {
        return new Office(square);
    }

    public Space createSpace(int roomsCount, int square) {
        return new Office(roomsCount, square);
    }

    public Floor createFloor(int spacesCount) {
        return new OfficeFloor(spacesCount);
    }

    public Floor createFloor(Space[] spaces) {
        return new OfficeFloor(spaces);
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return new OfficeBuilding(floorsCount, spacesCounts);
    }

    public Building createBuilding(Floor[] floors) {
        return new OfficeBuilding(floors);
    }
}
